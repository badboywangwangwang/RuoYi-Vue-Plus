package org.dromara.pay.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper; // 💡 导入更新构造器
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.query.QueryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 💡 引入事务控制
import org.dromara.pay.domain.bo.GanPayAppConfigRelBo;
import org.dromara.pay.domain.vo.GanPayAppConfigRelVo;
import org.dromara.pay.domain.GanPayAppConfigRel;
import org.dromara.pay.mapper.GanPayAppConfigRelMapper;
import org.dromara.pay.service.IGanPayAppConfigRelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 程序与商户绑定表Service业务层处理
 *
 * @author Lion Li
 * @date 2026-08-12 00:32:13
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GanPayAppConfigRelServiceImpl implements IGanPayAppConfigRelService {

    private final GanPayAppConfigRelMapper ganPayAppConfigRelMapper;

    /**
     * 查询程序与商户绑定表
     *
     * @param id 主键
     * @return 程序与商户绑定表
     */
    @Override
    public GanPayAppConfigRelVo queryById(Long id) {
        return ganPayAppConfigRelMapper.selectVoById(id);
    }

    /**
     * 分页查询程序与商户绑定表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 程序与商户绑定表分页列表
     */
    @Override
    public PageResult<GanPayAppConfigRelVo> queryPageList(GanPayAppConfigRelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GanPayAppConfigRel> lqw = buildQueryWrapper(bo);
        Page<GanPayAppConfigRelVo> result = ganPayAppConfigRelMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的程序与商户绑定表列表
     *
     * @param bo 查询条件
     * @return 程序与商户绑定表列表
     */
    @Override
    public List<GanPayAppConfigRelVo> queryList(GanPayAppConfigRelBo bo) {
        LambdaQueryWrapper<GanPayAppConfigRel> lqw = buildQueryWrapper(bo);
        return ganPayAppConfigRelMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GanPayAppConfigRel> buildQueryWrapper(GanPayAppConfigRelBo bo) {
        Map<String, Object> params = bo.getParams();
        return QueryBuilder.lambda(GanPayAppConfigRel.class)
                .eqIfPresent(GanPayAppConfigRel::getAppId, bo.getAppId())
                .eqIfPresent(GanPayAppConfigRel::getConfigId, bo.getConfigId())
                .eqIfPresent(GanPayAppConfigRel::getIsDefault, bo.getIsDefault())
                .eqIfPresent(GanPayAppConfigRel::getCreateDept, bo.getCreateDept())
                .eqIfText(GanPayAppConfigRel::getCreateBy, bo.getCreateBy())
                .betweenParams(GanPayAppConfigRel::getCreateTime, params, "beginCreateTime", "endCreateTime")
                .eqIfText(GanPayAppConfigRel::getUpdateBy, bo.getUpdateBy())
                .betweenParams(GanPayAppConfigRel::getUpdateTime, params, "beginUpdateTime", "endUpdateTime")
                .orderByAsc(GanPayAppConfigRel::getId)
                .build();
    }

    /**
     * 新增程序与商户绑定表
     *
     * @param bo 程序与商户绑定表
     * @return 是否新增成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 💡 建议加上事务
    public Boolean insertByBo(GanPayAppConfigRelBo bo) {
        GanPayAppConfigRel add = MapstructUtils.convert(bo, GanPayAppConfigRel.class);
        validEntityBeforeSave(add);

        // 💡 1. 如果新绑定设置为默认，则将当前 appId 下的其他绑定统一置为非默认 (false)
        if (Boolean.TRUE.equals(add.getIsDefault())) {
            clearOtherDefault(add.getAppId(), null);
        }

        boolean flag = ganPayAppConfigRelMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改程序与商户绑定表
     *
     * @param bo 程序与商户绑定表
     * @return 是否修改成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 💡 建议加上事务
    public Boolean updateByBo(GanPayAppConfigRelBo bo) {
        GanPayAppConfigRel update = MapstructUtils.convert(bo, GanPayAppConfigRel.class);
        validEntityBeforeSave(update);

        // 💡 2. 如果修改为默认，则把当前 appId 下除自己以外的其他绑定全设为非默认 (false)
        if (Boolean.TRUE.equals(update.getIsDefault())) {
            clearOtherDefault(update.getAppId(), update.getId());
        }

        return ganPayAppConfigRelMapper.updateById(update) > 0;
    }

    /**
     * 💡 辅助方法：将同应用下其他记录的 isDefault 置为 false
     *
     * @param appId     当前应用ID
     * @param excludeId 排除的主键ID（新增传 null）
     */
    private void clearOtherDefault(Long appId, Long excludeId) {
        if (appId == null) return;

        LambdaUpdateWrapper<GanPayAppConfigRel> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(GanPayAppConfigRel::getAppId, appId)
                .set(GanPayAppConfigRel::getIsDefault, false);

        // 更新时排除当前记录自身
        if (excludeId != null) {
            updateWrapper.ne(GanPayAppConfigRel::getId, excludeId);
        }

        ganPayAppConfigRelMapper.update(null, updateWrapper);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GanPayAppConfigRel entity) {
        // 可在此扩展通用业务校验
    }

    /**
     * 校验并批量删除程序与商户绑定表信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 可在此扩展删除前业务校验
        }
        return ganPayAppConfigRelMapper.deleteByIds(ids) > 0;
    }

}

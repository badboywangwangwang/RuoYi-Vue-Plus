package org.dromara.pay.service.impl;

import cn.hutool.core.util.ObjectUtil;
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
import org.dromara.pay.domain.bo.GanPaySysConfigBo;
import org.dromara.pay.domain.vo.GanPaySysConfigVo;
import org.dromara.pay.domain.GanPaySysConfig;
import org.dromara.pay.mapper.GanPaySysConfigMapper;
import org.dromara.pay.service.IGanPaySysConfigService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 系统配置Service业务层处理
 *
 * @author Lion Li
 * @date 2026-08-12 03:09:59
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GanPaySysConfigServiceImpl implements IGanPaySysConfigService {

    private final GanPaySysConfigMapper ganPaySysConfigMapper;

    /**
     * 查询系统配置
     *
     * @param id 主键
     * @return 系统配置
     */
    @Override
    public GanPaySysConfigVo queryById(Long id) {
        return ganPaySysConfigMapper.selectVoById(id);
    }

    /**
     * 分页查询系统配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统配置分页列表
     */
    @Override
    public PageResult<GanPaySysConfigVo> queryPageList(GanPaySysConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GanPaySysConfig> lqw = buildQueryWrapper(bo);
        Page<GanPaySysConfigVo> result = ganPaySysConfigMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的系统配置列表
     *
     * @param bo 查询条件
     * @return 系统配置列表
     */
    @Override
    public List<GanPaySysConfigVo> queryList(GanPaySysConfigBo bo) {
        LambdaQueryWrapper<GanPaySysConfig> lqw = buildQueryWrapper(bo);
        return ganPaySysConfigMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GanPaySysConfig> buildQueryWrapper(GanPaySysConfigBo bo) {
        return QueryBuilder.lambda(GanPaySysConfig.class)
            .orderByAsc(GanPaySysConfig::getId)
            .build();
    }

    /**
     * 新增系统配置
     *
     * @param bo 系统配置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GanPaySysConfigBo bo) {
        GanPaySysConfig add = MapstructUtils.convert(bo, GanPaySysConfig.class);
        validEntityBeforeSave(add);
        boolean flag = ganPaySysConfigMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改系统配置
     *
     * @param bo 系统配置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GanPaySysConfigBo bo) {
        GanPaySysConfig update = MapstructUtils.convert(bo, GanPaySysConfig.class);
        validEntityBeforeSave(update);
        return ganPaySysConfigMapper.updateById(update) > 0;
    }



    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GanPaySysConfig entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除系统配置信息
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
        return ganPaySysConfigMapper.deleteByIds(ids) > 0;
    }

}

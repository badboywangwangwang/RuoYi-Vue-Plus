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
import org.dromara.pay.domain.bo.GanPayMerchantBo;
import org.dromara.pay.domain.vo.GanPayMerchantVo;
import org.dromara.pay.domain.GanPayMerchant;
import org.dromara.pay.mapper.GanPayMerchantMapper;
import org.dromara.pay.service.IGanPayMerchantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 法人信息Service业务层处理
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GanPayMerchantServiceImpl implements IGanPayMerchantService {

    private final GanPayMerchantMapper ganPayMerchantMapper;

    /**
     * 查询法人信息
     *
     * @param merchantId 主键
     * @return 法人信息
     */
    @Override
    public GanPayMerchantVo queryById(Long merchantId) {
        return ganPayMerchantMapper.selectVoById(merchantId);
    }

    /**
     * 分页查询法人信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法人信息分页列表
     */
    @Override
    public PageResult<GanPayMerchantVo> queryPageList(GanPayMerchantBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GanPayMerchant> lqw = buildQueryWrapper(bo);
        Page<GanPayMerchantVo> result = ganPayMerchantMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的法人信息列表
     *
     * @param bo 查询条件
     * @return 法人信息列表
     */
    @Override
    public List<GanPayMerchantVo> queryList(GanPayMerchantBo bo) {
        LambdaQueryWrapper<GanPayMerchant> lqw = buildQueryWrapper(bo);
        return ganPayMerchantMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GanPayMerchant> buildQueryWrapper(GanPayMerchantBo bo) {
        return QueryBuilder.lambda(GanPayMerchant.class)
            .likeIfText(GanPayMerchant::getLegalPersonName, bo.getLegalPersonName())
            .orderByAsc(GanPayMerchant::getMerchantId)
            .build();
    }

    /**
     * 新增法人信息
     *
     * @param bo 法人信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GanPayMerchantBo bo) {
        GanPayMerchant add = MapstructUtils.convert(bo, GanPayMerchant.class);
        validEntityBeforeSave(add);
        boolean flag = ganPayMerchantMapper.insert(add) > 0;
        if (flag) {
            bo.setMerchantId(add.getMerchantId());
        }
        return flag;
    }

    /**
     * 修改法人信息
     *
     * @param bo 法人信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GanPayMerchantBo bo) {
        GanPayMerchant update = MapstructUtils.convert(bo, GanPayMerchant.class);
        validEntityBeforeSave(update);
        return ganPayMerchantMapper.updateById(update) > 0;
    }

    /**
     * 修改法人信息状态
     *
     * @param merchantId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateStatus(Long merchantId, String status) {
        return ganPayMerchantMapper.lambda()
            .set(GanPayMerchant::getStatus, status)
            .eq(GanPayMerchant::getMerchantId, merchantId)
            .update();
    }


    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GanPayMerchant entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除法人信息信息
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
        return ganPayMerchantMapper.deleteByIds(ids) > 0;
    }

}

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
import org.dromara.pay.domain.bo.GanPayConfigBo;
import org.dromara.pay.domain.vo.GanPayConfigVo;
import org.dromara.pay.domain.GanPayConfig;
import org.dromara.pay.mapper.GanPayConfigMapper;
import org.dromara.pay.service.IGanPayConfigService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 支付通道与商户配置Service业务层处理
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GanPayConfigServiceImpl implements IGanPayConfigService {

    private final GanPayConfigMapper ganPayConfigMapper;

    /**
     * 查询支付通道与商户配置
     *
     * @param configId 主键
     * @return 支付通道与商户配置
     */
    @Override
    public GanPayConfigVo queryById(Long configId) {
        return ganPayConfigMapper.selectVoById(configId);
    }

    /**
     * 分页查询支付通道与商户配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 支付通道与商户配置分页列表
     */
    @Override
    public PageResult<GanPayConfigVo> queryPageList(GanPayConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GanPayConfig> lqw = buildQueryWrapper(bo);
        Page<GanPayConfigVo> result = ganPayConfigMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的支付通道与商户配置列表
     *
     * @param bo 查询条件
     * @return 支付通道与商户配置列表
     */
    @Override
    public List<GanPayConfigVo> queryList(GanPayConfigBo bo) {
        LambdaQueryWrapper<GanPayConfig> lqw = buildQueryWrapper(bo);
        return ganPayConfigMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GanPayConfig> buildQueryWrapper(GanPayConfigBo bo) {
        return QueryBuilder.lambda(GanPayConfig.class)
            .eqIfPresent(GanPayConfig::getMerchantId, bo.getMerchantId())
            .likeIfText(GanPayConfig::getConfigName, bo.getConfigName())
            .eqIfText(GanPayConfig::getChannelType, bo.getChannelType())
            .eqIfText(GanPayConfig::getMchMode, bo.getMchMode())
            .eqIfText(GanPayConfig::getMchId, bo.getMchId())
            .eqIfText(GanPayConfig::getStatus, bo.getStatus())
            .orderByAsc(GanPayConfig::getConfigId)
            .build();
    }

    /**
     * 新增支付通道与商户配置
     *
     * @param bo 支付通道与商户配置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GanPayConfigBo bo) {
        GanPayConfig add = MapstructUtils.convert(bo, GanPayConfig.class);
        validEntityBeforeSave(add);
        boolean flag = ganPayConfigMapper.insert(add) > 0;
        if (flag) {
            bo.setConfigId(add.getConfigId());
        }
        return flag;
    }

    /**
     * 修改支付通道与商户配置
     *
     * @param bo 支付通道与商户配置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GanPayConfigBo bo) {
        GanPayConfig update = MapstructUtils.convert(bo, GanPayConfig.class);
        validEntityBeforeSave(update);
        return ganPayConfigMapper.updateById(update) > 0;
    }

    /**
     * 修改支付通道与商户配置状态
     *
     * @param configId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateStatus(Long configId, String status) {
        return ganPayConfigMapper.lambda()
            .set(GanPayConfig::getStatus, status)
            .eq(GanPayConfig::getConfigId, configId)
            .update();
    }


    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GanPayConfig entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除支付通道与商户配置信息
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
        return ganPayConfigMapper.deleteByIds(ids) > 0;
    }

}

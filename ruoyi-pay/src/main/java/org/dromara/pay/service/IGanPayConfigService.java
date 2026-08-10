package org.dromara.pay.service;

import org.dromara.pay.domain.vo.GanPayConfigVo;
import org.dromara.pay.domain.bo.GanPayConfigBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 支付通道与商户配置Service接口
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
public interface IGanPayConfigService {

    /**
     * 查询支付通道与商户配置
     *
     * @param configId 主键
     * @return 支付通道与商户配置
     */
    GanPayConfigVo queryById(Long configId);

    /**
     * 分页查询支付通道与商户配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 支付通道与商户配置分页列表
     */
    PageResult<GanPayConfigVo> queryPageList(GanPayConfigBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的支付通道与商户配置列表
     *
     * @param bo 查询条件
     * @return 支付通道与商户配置列表
     */
    List<GanPayConfigVo> queryList(GanPayConfigBo bo);


    /**
     * 新增支付通道与商户配置
     *
     * @param bo 支付通道与商户配置
     * @return 是否新增成功
     */
    Boolean insertByBo(GanPayConfigBo bo);

    /**
     * 修改支付通道与商户配置
     *
     * @param bo 支付通道与商户配置
     * @return 是否修改成功
     */
    Boolean updateByBo(GanPayConfigBo bo);

    /**
     * 修改支付通道与商户配置状态
     *
     * @param configId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    Boolean updateStatus(Long configId, String status);


    /**
     * 校验并批量删除支付通道与商户配置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}

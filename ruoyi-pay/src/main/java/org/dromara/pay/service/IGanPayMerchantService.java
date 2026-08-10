package org.dromara.pay.service;

import org.dromara.pay.domain.vo.GanPayMerchantVo;
import org.dromara.pay.domain.bo.GanPayMerchantBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 法人信息Service接口
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
public interface IGanPayMerchantService {

    /**
     * 查询法人信息
     *
     * @param merchantId 主键
     * @return 法人信息
     */
    GanPayMerchantVo queryById(Long merchantId);

    /**
     * 分页查询法人信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 法人信息分页列表
     */
    PageResult<GanPayMerchantVo> queryPageList(GanPayMerchantBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的法人信息列表
     *
     * @param bo 查询条件
     * @return 法人信息列表
     */
    List<GanPayMerchantVo> queryList(GanPayMerchantBo bo);


    /**
     * 新增法人信息
     *
     * @param bo 法人信息
     * @return 是否新增成功
     */
    Boolean insertByBo(GanPayMerchantBo bo);

    /**
     * 修改法人信息
     *
     * @param bo 法人信息
     * @return 是否修改成功
     */
    Boolean updateByBo(GanPayMerchantBo bo);

    /**
     * 修改法人信息状态
     *
     * @param merchantId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    Boolean updateStatus(Long merchantId, String status);


    /**
     * 校验并批量删除法人信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}

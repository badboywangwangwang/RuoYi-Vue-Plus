package org.dromara.pay.service;

import org.dromara.pay.domain.vo.GanPayAppVo;
import org.dromara.pay.domain.bo.GanPayAppBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 应用信息Service接口
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
public interface IGanPayAppService {

    /**
     * 查询应用信息
     *
     * @param appId 主键
     * @return 应用信息
     */
    GanPayAppVo queryById(Long appId);

    /**
     * 分页查询应用信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 应用信息分页列表
     */
    PageResult<GanPayAppVo> queryPageList(GanPayAppBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的应用信息列表
     *
     * @param bo 查询条件
     * @return 应用信息列表
     */
    List<GanPayAppVo> queryList(GanPayAppBo bo);


    /**
     * 新增应用信息
     *
     * @param bo 应用信息
     * @return 是否新增成功
     */
    Boolean insertByBo(GanPayAppBo bo);

    /**
     * 修改应用信息
     *
     * @param bo 应用信息
     * @return 是否修改成功
     */
    Boolean updateByBo(GanPayAppBo bo);

    /**
     * 修改应用信息状态
     *
     * @param appId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    Boolean updateStatus(Long appId, String status);


    /**
     * 校验并批量删除应用信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}

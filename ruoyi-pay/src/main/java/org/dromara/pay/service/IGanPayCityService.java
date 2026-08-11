package org.dromara.pay.service;

import org.dromara.pay.domain.vo.GanPayCityVo;
import org.dromara.pay.domain.bo.GanPayCityBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 城市信息Service接口
 *
 * @author Lion Li
 * @date 2026-08-11 20:17:25
 */
public interface IGanPayCityService {

    /**
     * 查询城市信息
     *
     * @param cityId 主键
     * @return 城市信息
     */
    GanPayCityVo queryById(Long cityId);

    /**
     * 分页查询城市信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 城市信息分页列表
     */
    PageResult<GanPayCityVo> queryPageList(GanPayCityBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的城市信息列表
     *
     * @param bo 查询条件
     * @return 城市信息列表
     */
    List<GanPayCityVo> queryList(GanPayCityBo bo);


    /**
     * 新增城市信息
     *
     * @param bo 城市信息
     * @return 是否新增成功
     */
    Boolean insertByBo(GanPayCityBo bo);

    /**
     * 修改城市信息
     *
     * @param bo 城市信息
     * @return 是否修改成功
     */
    Boolean updateByBo(GanPayCityBo bo);

    /**
     * 修改城市信息状态
     *
     * @param cityId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    Boolean updateStatus(Long cityId, String status);


    /**
     * 校验并批量删除城市信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}

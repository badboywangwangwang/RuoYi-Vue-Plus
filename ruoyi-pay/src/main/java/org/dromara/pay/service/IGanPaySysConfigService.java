package org.dromara.pay.service;

import org.dromara.pay.domain.vo.GanPaySysConfigVo;
import org.dromara.pay.domain.bo.GanPaySysConfigBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 系统配置Service接口
 *
 * @author Lion Li
 * @date 2026-08-12 03:09:59
 */
public interface IGanPaySysConfigService {

    /**
     * 查询系统配置
     *
     * @param id 主键
     * @return 系统配置
     */
    GanPaySysConfigVo queryById(Long id);

    /**
     * 分页查询系统配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统配置分页列表
     */
    PageResult<GanPaySysConfigVo> queryPageList(GanPaySysConfigBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的系统配置列表
     *
     * @param bo 查询条件
     * @return 系统配置列表
     */
    List<GanPaySysConfigVo> queryList(GanPaySysConfigBo bo);


    /**
     * 新增系统配置
     *
     * @param bo 系统配置
     * @return 是否新增成功
     */
    Boolean insertByBo(GanPaySysConfigBo bo);

    /**
     * 修改系统配置
     *
     * @param bo 系统配置
     * @return 是否修改成功
     */
    Boolean updateByBo(GanPaySysConfigBo bo);



    /**
     * 校验并批量删除系统配置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}

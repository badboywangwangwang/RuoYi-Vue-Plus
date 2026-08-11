package org.dromara.pay.service;

import org.dromara.pay.domain.vo.GanPayAppConfigRelVo;
import org.dromara.pay.domain.bo.GanPayAppConfigRelBo;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 程序与商户绑定表Service接口
 *
 * @author Lion Li
 * @date 2026-08-12 00:32:13
 */
public interface IGanPayAppConfigRelService {

    /**
     * 查询程序与商户绑定表
     *
     * @param id 主键
     * @return 程序与商户绑定表
     */
    GanPayAppConfigRelVo queryById(Long id);

    /**
     * 分页查询程序与商户绑定表列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 程序与商户绑定表分页列表
     */
    PageResult<GanPayAppConfigRelVo> queryPageList(GanPayAppConfigRelBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的程序与商户绑定表列表
     *
     * @param bo 查询条件
     * @return 程序与商户绑定表列表
     */
    List<GanPayAppConfigRelVo> queryList(GanPayAppConfigRelBo bo);


    /**
     * 新增程序与商户绑定表
     *
     * @param bo 程序与商户绑定表
     * @return 是否新增成功
     */
    Boolean insertByBo(GanPayAppConfigRelBo bo);

    /**
     * 修改程序与商户绑定表
     *
     * @param bo 程序与商户绑定表
     * @return 是否修改成功
     */
    Boolean updateByBo(GanPayAppConfigRelBo bo);



    /**
     * 校验并批量删除程序与商户绑定表信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}

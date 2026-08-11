package org.dromara.pay.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.redis.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelBuilder;
import org.dromara.pay.domain.vo.GanPayAppConfigRelVo;
import org.dromara.pay.domain.bo.GanPayAppConfigRelBo;
import org.dromara.pay.service.IGanPayAppConfigRelService;
import org.dromara.common.core.domain.PageResult;

/**
 * 程序与商户绑定表
 *
 * @author Lion Li
 * @date 2026-08-12 00:32:13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay/payAppConfigRel")
public class GanPayAppConfigRelController extends BaseController {

    private final IGanPayAppConfigRelService ganPayAppConfigRelService;

    /**
     * 查询程序与商户绑定表列表
     */
    @SaCheckPermission("pay:payAppConfigRel:list")
    @GetMapping("/list")
    public R<PageResult<GanPayAppConfigRelVo>> list(GanPayAppConfigRelBo bo, PageQuery pageQuery) {
        return R.ok(ganPayAppConfigRelService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出程序与商户绑定表列表
     */
    @SaCheckPermission("pay:payAppConfigRel:export")
    @Log(title = "程序与商户绑定表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GanPayAppConfigRelBo bo, HttpServletResponse response) {
        List<GanPayAppConfigRelVo> list = ganPayAppConfigRelService.queryList(bo);
        ExcelBuilder.of(list, GanPayAppConfigRelVo.class).sheetName("程序与商户绑定表").toResponse(response);
    }

    /**
     * 获取程序与商户绑定表详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pay:payAppConfigRel:query")
    @GetMapping("/{id}")
    public R<GanPayAppConfigRelVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(ganPayAppConfigRelService.queryById(id));
    }

    /**
     * 新增程序与商户绑定表
     */
    @SaCheckPermission("pay:payAppConfigRel:add")
    @Log(title = "程序与商户绑定表", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GanPayAppConfigRelBo bo) {
        return toAjax(ganPayAppConfigRelService.insertByBo(bo));
    }

    /**
     * 修改程序与商户绑定表
     */
    @SaCheckPermission("pay:payAppConfigRel:edit")
    @Log(title = "程序与商户绑定表", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GanPayAppConfigRelBo bo) {
        return toAjax(ganPayAppConfigRelService.updateByBo(bo));
    }



    /**
     * 删除程序与商户绑定表
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pay:payAppConfigRel:remove")
    @Log(title = "程序与商户绑定表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(ganPayAppConfigRelService.deleteWithValidByIds(List.of(ids), true));
    }
}

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
import org.dromara.pay.domain.vo.GanPayAppVo;
import org.dromara.pay.domain.bo.GanPayAppBo;
import org.dromara.pay.service.IGanPayAppService;
import org.dromara.common.core.domain.PageResult;

/**
 * 应用信息
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay/payApp")
public class GanPayAppController extends BaseController {

    private final IGanPayAppService ganPayAppService;

    /**
     * 查询应用信息列表
     */
    @SaCheckPermission("pay:payApp:list")
    @GetMapping("/list")
    public R<PageResult<GanPayAppVo>> list(GanPayAppBo bo, PageQuery pageQuery) {
        return R.ok(ganPayAppService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出应用信息列表
     */
    @SaCheckPermission("pay:payApp:export")
    @Log(title = "应用信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GanPayAppBo bo, HttpServletResponse response) {
        List<GanPayAppVo> list = ganPayAppService.queryList(bo);
        ExcelBuilder.of(list, GanPayAppVo.class).sheetName("应用信息").toResponse(response);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param appId 主键
     */
    @SaCheckPermission("pay:payApp:query")
    @GetMapping("/{appId}")
    public R<GanPayAppVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long appId) {
        return R.ok(ganPayAppService.queryById(appId));
    }

    /**
     * 新增应用信息
     */
    @SaCheckPermission("pay:payApp:add")
    @Log(title = "应用信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GanPayAppBo bo) {
        return toAjax(ganPayAppService.insertByBo(bo));
    }

    /**
     * 修改应用信息
     */
    @SaCheckPermission("pay:payApp:edit")
    @Log(title = "应用信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GanPayAppBo bo) {
        return toAjax(ganPayAppService.updateByBo(bo));
    }

    /**
     * 修改应用信息状态
     */
    @SaCheckPermission("pay:payApp:edit")
    @Log(title = "应用信息", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody GanPayAppBo bo) {
        return toAjax(ganPayAppService.updateStatus(bo.getAppId(), bo.getStatus()));
    }


    /**
     * 删除应用信息
     *
     * @param appIds 主键串
     */
    @SaCheckPermission("pay:payApp:remove")
    @Log(title = "应用信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{appIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] appIds) {
        return toAjax(ganPayAppService.deleteWithValidByIds(List.of(appIds), true));
    }
}

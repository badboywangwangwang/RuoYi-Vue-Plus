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
import org.dromara.pay.domain.vo.GanPaySysConfigVo;
import org.dromara.pay.domain.bo.GanPaySysConfigBo;
import org.dromara.pay.service.IGanPaySysConfigService;
import org.dromara.common.core.domain.PageResult;

/**
 * 系统配置
 *
 * @author Lion Li
 * @date 2026-08-12 03:09:59
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay/paySysConfig")
public class GanPaySysConfigController extends BaseController {

    private final IGanPaySysConfigService ganPaySysConfigService;

    /**
     * 查询系统配置列表
     */
    @SaCheckPermission("pay:paySysConfig:list")
    @GetMapping("/list")
    public R<PageResult<GanPaySysConfigVo>> list(GanPaySysConfigBo bo, PageQuery pageQuery) {
        return R.ok(ganPaySysConfigService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出系统配置列表
     */
    @SaCheckPermission("pay:paySysConfig:export")
    @Log(title = "系统配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GanPaySysConfigBo bo, HttpServletResponse response) {
        List<GanPaySysConfigVo> list = ganPaySysConfigService.queryList(bo);
        ExcelBuilder.of(list, GanPaySysConfigVo.class).sheetName("系统配置").toResponse(response);
    }

    /**
     * 获取系统配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("pay:paySysConfig:query")
    @GetMapping("/{id}")
    public R<GanPaySysConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(ganPaySysConfigService.queryById(id));
    }

    /**
     * 新增系统配置
     */
    @SaCheckPermission("pay:paySysConfig:add")
    @Log(title = "系统配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GanPaySysConfigBo bo) {
        return toAjax(ganPaySysConfigService.insertByBo(bo));
    }

    /**
     * 修改系统配置
     */
    @SaCheckPermission("pay:paySysConfig:edit")
    @Log(title = "系统配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GanPaySysConfigBo bo) {
        return toAjax(ganPaySysConfigService.updateByBo(bo));
    }



    /**
     * 删除系统配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("pay:paySysConfig:remove")
    @Log(title = "系统配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(ganPaySysConfigService.deleteWithValidByIds(List.of(ids), true));
    }
}

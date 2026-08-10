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
import org.dromara.pay.domain.vo.GanPayConfigVo;
import org.dromara.pay.domain.bo.GanPayConfigBo;
import org.dromara.pay.service.IGanPayConfigService;
import org.dromara.common.core.domain.PageResult;

/**
 * 支付通道与商户配置
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay/payConfig")
public class GanPayConfigController extends BaseController {

    private final IGanPayConfigService ganPayConfigService;

    /**
     * 查询支付通道与商户配置列表
     */
    @SaCheckPermission("pay:payConfig:list")
    @GetMapping("/list")
    public R<PageResult<GanPayConfigVo>> list(GanPayConfigBo bo, PageQuery pageQuery) {
        return R.ok(ganPayConfigService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出支付通道与商户配置列表
     */
    @SaCheckPermission("pay:payConfig:export")
    @Log(title = "支付通道与商户配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GanPayConfigBo bo, HttpServletResponse response) {
        List<GanPayConfigVo> list = ganPayConfigService.queryList(bo);
        ExcelBuilder.of(list, GanPayConfigVo.class).sheetName("支付通道与商户配置").toResponse(response);
    }

    /**
     * 获取支付通道与商户配置详细信息
     *
     * @param configId 主键
     */
    @SaCheckPermission("pay:payConfig:query")
    @GetMapping("/{configId}")
    public R<GanPayConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long configId) {
        return R.ok(ganPayConfigService.queryById(configId));
    }

    /**
     * 新增支付通道与商户配置
     */
    @SaCheckPermission("pay:payConfig:add")
    @Log(title = "支付通道与商户配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GanPayConfigBo bo) {
        return toAjax(ganPayConfigService.insertByBo(bo));
    }

    /**
     * 修改支付通道与商户配置
     */
    @SaCheckPermission("pay:payConfig:edit")
    @Log(title = "支付通道与商户配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GanPayConfigBo bo) {
        return toAjax(ganPayConfigService.updateByBo(bo));
    }

    /**
     * 修改支付通道与商户配置状态
     */
    @SaCheckPermission("pay:payConfig:edit")
    @Log(title = "支付通道与商户配置", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody GanPayConfigBo bo) {
        return toAjax(ganPayConfigService.updateStatus(bo.getConfigId(), bo.getStatus()));
    }


    /**
     * 删除支付通道与商户配置
     *
     * @param configIds 主键串
     */
    @SaCheckPermission("pay:payConfig:remove")
    @Log(title = "支付通道与商户配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{configIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] configIds) {
        return toAjax(ganPayConfigService.deleteWithValidByIds(List.of(configIds), true));
    }
}

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
import org.dromara.pay.domain.vo.GanPayMerchantVo;
import org.dromara.pay.domain.bo.GanPayMerchantBo;
import org.dromara.pay.service.IGanPayMerchantService;
import org.dromara.common.core.domain.PageResult;

/**
 * 法人信息
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay/payMerchant")
public class GanPayMerchantController extends BaseController {

    private final IGanPayMerchantService ganPayMerchantService;

    /**
     * 查询法人信息列表
     */
    @SaCheckPermission("pay:payMerchant:list")
    @GetMapping("/list")
    public R<PageResult<GanPayMerchantVo>> list(GanPayMerchantBo bo, PageQuery pageQuery) {
        return R.ok(ganPayMerchantService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出法人信息列表
     */
    @SaCheckPermission("pay:payMerchant:export")
    @Log(title = "法人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GanPayMerchantBo bo, HttpServletResponse response) {
        List<GanPayMerchantVo> list = ganPayMerchantService.queryList(bo);
        ExcelBuilder.of(list, GanPayMerchantVo.class).sheetName("法人信息").toResponse(response);
    }

    /**
     * 获取法人信息详细信息
     *
     * @param merchantId 主键
     */
    @SaCheckPermission("pay:payMerchant:query")
    @GetMapping("/{merchantId}")
    public R<GanPayMerchantVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long merchantId) {
        return R.ok(ganPayMerchantService.queryById(merchantId));
    }

    /**
     * 新增法人信息
     */
    @SaCheckPermission("pay:payMerchant:add")
    @Log(title = "法人信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GanPayMerchantBo bo) {
        return toAjax(ganPayMerchantService.insertByBo(bo));
    }

    /**
     * 修改法人信息
     */
    @SaCheckPermission("pay:payMerchant:edit")
    @Log(title = "法人信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GanPayMerchantBo bo) {
        return toAjax(ganPayMerchantService.updateByBo(bo));
    }

    /**
     * 修改法人信息状态
     */
    @SaCheckPermission("pay:payMerchant:edit")
    @Log(title = "法人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody GanPayMerchantBo bo) {
        return toAjax(ganPayMerchantService.updateStatus(bo.getMerchantId(), bo.getStatus()));
    }


    /**
     * 删除法人信息
     *
     * @param merchantIds 主键串
     */
    @SaCheckPermission("pay:payMerchant:remove")
    @Log(title = "法人信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{merchantIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] merchantIds) {
        return toAjax(ganPayMerchantService.deleteWithValidByIds(List.of(merchantIds), true));
    }
}

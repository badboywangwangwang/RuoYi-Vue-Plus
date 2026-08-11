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
import org.dromara.pay.domain.vo.GanPayCityVo;
import org.dromara.pay.domain.bo.GanPayCityBo;
import org.dromara.pay.service.IGanPayCityService;
import org.dromara.common.core.domain.PageResult;

/**
 * 城市信息
 *
 * @author Lion Li
 * @date 2026-08-11 20:17:25
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pay/payCity")
public class GanPayCityController extends BaseController {

    private final IGanPayCityService ganPayCityService;

    /**
     * 查询城市信息列表
     */
    @SaCheckPermission("pay:payCity:list")
    @GetMapping("/list")
    public R<PageResult<GanPayCityVo>> list(GanPayCityBo bo, PageQuery pageQuery) {
        return R.ok(ganPayCityService.queryPageList(bo, pageQuery));
    }

    /**
     * 导出城市信息列表
     */
    @SaCheckPermission("pay:payCity:export")
    @Log(title = "城市信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GanPayCityBo bo, HttpServletResponse response) {
        List<GanPayCityVo> list = ganPayCityService.queryList(bo);
        ExcelBuilder.of(list, GanPayCityVo.class).sheetName("城市信息").toResponse(response);
    }

    /**
     * 获取城市信息详细信息
     *
     * @param cityId 主键
     */
    @SaCheckPermission("pay:payCity:query")
    @GetMapping("/{cityId}")
    public R<GanPayCityVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long cityId) {
        return R.ok(ganPayCityService.queryById(cityId));
    }

    /**
     * 新增城市信息
     */
    @SaCheckPermission("pay:payCity:add")
    @Log(title = "城市信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GanPayCityBo bo) {
        return toAjax(ganPayCityService.insertByBo(bo));
    }

    /**
     * 修改城市信息
     */
    @SaCheckPermission("pay:payCity:edit")
    @Log(title = "城市信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GanPayCityBo bo) {
        return toAjax(ganPayCityService.updateByBo(bo));
    }

    /**
     * 修改城市信息状态
     */
    @SaCheckPermission("pay:payCity:edit")
    @Log(title = "城市信息", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody GanPayCityBo bo) {
        return toAjax(ganPayCityService.updateStatus(bo.getCityId(), bo.getStatus()));
    }


    /**
     * 删除城市信息
     *
     * @param cityIds 主键串
     */
    @SaCheckPermission("pay:payCity:remove")
    @Log(title = "城市信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cityIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] cityIds) {
        return toAjax(ganPayCityService.deleteWithValidByIds(List.of(cityIds), true));
    }
}

package org.dromara.pay.domain.vo;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.pay.domain.GanPayAppConfigRel;
import org.apache.fesod.sheet.annotation.ExcelIgnoreUnannotated;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 程序与商户绑定表视图对象 gan_pay_app_config_rel
 *
 * @author Lion Li
 * @date 2026-08-12 00:32:13
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GanPayAppConfigRel.class)
public class GanPayAppConfigRelVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 应用/小程序ID（关联 gan_pay_app.app_id）
     */
    @ExcelProperty(value = "应用/小程序ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "关=联,g=an_pay_app.app_id")
    private Long appId;

    /**
     * 支付商户配置ID（关联 gan_pay_config.config_id）
     */
    @ExcelProperty(value = "支付商户配置ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "关=联,g=an_pay_config.config_id")
    private Long configId;

    /**
     * 在此应用下是否为默认通道（0否 1是）
     */
    @ExcelProperty(value = "在此应用下是否为默认通道", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Boolean isDefault;


}

package org.dromara.pay.domain.vo;

import java.math.BigDecimal;
import org.dromara.pay.domain.GanPayConfig;
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
 * 支付通道与商户配置视图对象 gan_pay_config
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GanPayConfig.class)
public class GanPayConfigVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置主键ID
     */
    @ExcelProperty(value = "配置主键ID")
    private Long configId;

    /**
     * 归属法人
     */
    private Long merchantId;

    /**
     * 归属法人名称
     *
     */
    @ExcelProperty(value = "归属法人")
    private String merchantName;

    /**
     * 商户名称
     */
    @ExcelProperty(value = "商户名称")
    private String configName;

    /**
     * 支付渠道
     */
    @ExcelProperty(value = "支付渠道", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "gan_pay_channel")
    private String channelType;

    /**
     * 商户模式
     */
    @ExcelProperty(value = "商户模式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "gan_mch_mode")
    private String mchMode;

    /**
     * 商户号
     */
    @ExcelProperty(value = "商户号")
    private String mchId;

    /**
     * 状态（0启用 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}

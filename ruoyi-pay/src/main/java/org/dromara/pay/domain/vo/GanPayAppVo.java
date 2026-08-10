package org.dromara.pay.domain.vo;

import org.dromara.pay.domain.GanPayApp;
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

/**
 * 应用信息视图对象 gan_pay_app
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GanPayApp.class)
public class GanPayAppVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用主键
     */
    @ExcelProperty(value = "应用主键")
    private Long appId;

    /**
     * 归属法人ID
     */
    private Long merchantId;

    /**
     * 归属法人名称
     * 💡 如果有对应 Translation 处理器，直接放开下面的 @Translation 注解
     */
    @ExcelProperty(value = "归属法人")
    private String merchantName;


    /**
     * 应用名称
     */
    @ExcelProperty(value = "应用名称")
    private String appName;

    /**
     * 应用类型
     */
    @ExcelProperty(value = "应用类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "gan_app_type")
    private String appType;

    /**
     * 应用标识
     */
    @ExcelProperty(value = "应用标识")
    private String appIdentity;

    /**
     * 应用密钥
     */
    @ExcelProperty(value = "应用密钥")
    private String appSecret;

    /**
     * 客服类型
     */
    @ExcelProperty(value = "客服类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "gan_custom_service_type")
    private String customServiceType;

    /**
     * 客服会话
     */
    @ExcelProperty(value = "客服会话")
    private String sessionId;

    /**
     * 注册邮箱
     */
    @ExcelProperty(value = "注册邮箱")
    private String accountEmail;

    /**
     * 状态（0正常 1停用）
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

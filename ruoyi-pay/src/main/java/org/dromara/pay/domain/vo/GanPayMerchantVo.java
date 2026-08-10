package org.dromara.pay.domain.vo;

import org.dromara.pay.domain.GanPayMerchant;
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
 * 法人信息视图对象 gan_pay_merchant
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GanPayMerchant.class)
public class GanPayMerchantVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 法人ID
     */
    @ExcelProperty(value = "法人ID")
    private Long merchantId;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String legalPersonName;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String mobile;

    /**
     * 备用手机号
     */
    @ExcelProperty(value = "备用手机号")
    private String backupMobile;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String idCard;

    /**
     * 银行名称
     */
    @ExcelProperty(value = "银行名称")
    private String bankName;

    /**
     * 对公账号
     */
    @ExcelProperty(value = "对公账号")
    private String bankCardNo;

    /**
     * 公司名称
     */
    @ExcelProperty(value = "公司名称")
    private String companyName;

    /**
     * 公司税号
     */
    @ExcelProperty(value = "公司税号")
    private String taxNo;

    /**
     * 公司地址
     */
    @ExcelProperty(value = "公司地址")
    private String companyAddress;

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

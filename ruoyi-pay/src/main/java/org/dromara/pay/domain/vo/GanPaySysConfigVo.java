package org.dromara.pay.domain.vo;

import org.dromara.pay.domain.GanPaySysConfig;
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
 * 系统配置视图对象 gan_pay_sys_config
 *
 * @author Lion Li
 * @date 2026-08-12 03:09:59
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GanPaySysConfig.class)
public class GanPaySysConfigVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID (固定为1，全局唯一配置)
     */
    @ExcelProperty(value = "主键ID (固定为1，全局唯一配置)")
    private Long id;

    /**
     * 客服电话
     */
    @ExcelProperty(value = "客服电话")
    private String customerServicePhone;

    /**
     * 是否显示小程序退款 (1:显示 0:隐藏)
     */
    @ExcelProperty(value = "是否显示小程序退款 (1:显示 0:隐藏)", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer showMiniappRefund;

    /**
     * 是否显示手机号 (1:显示 0:隐藏)
     */
    @ExcelProperty(value = "是否显示手机号 (1:显示 0:隐藏)", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer showPhone;

    /**
     * 是否显示身份证 (1:显示 0:隐藏)
     */
    @ExcelProperty(value = "是否显示身份证 (1:显示 0:隐藏)", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer showIdCard;

    /**
     * 退款密码
     */
    @ExcelProperty(value = "退款密码")
    private String refundPassword;

    /**
     * 小程序会话
     */
    @ExcelProperty(value = "小程序会话")
    private String miniappAesKey;

    /**
     * 小程序密钥
     */
    @ExcelProperty(value = "小程序密钥")
    private String miniappSecret;

    /**
     * 查询企业的密钥
     */
    @ExcelProperty(value = "查询企业的密钥")
    private String enterpriseQueryKey;

    /**
     * 查询的最大次数
     */
    @ExcelProperty(value = "查询的最大次数")
    private Integer maxQueryLimit;

    /**
     * 服务协议
     */
    @ExcelProperty(value = "服务协议")
    private String serviceAgreement;

    /**
     * 注册协议
     */
    @ExcelProperty(value = "注册协议")
    private String registrationAgreement;

    /**
     * 首页轮播图 (JSON格式存储图片与跳转配置)
     */
    @ExcelProperty(value = "首页轮播图 (JSON格式存储图片与跳转配置)")
    private String homeBanners;

    /**
     * 首页公告
     */
    @ExcelProperty(value = "首页公告")
    private String homeNotice;


}

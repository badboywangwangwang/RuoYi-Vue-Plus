package org.dromara.pay.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 法人信息对象 gan_pay_merchant
 *
 * @author Lion Li
 * @date 2026-08-11 20:37:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gan_pay_merchant")
public class GanPayMerchant extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 法人ID
     */
    @TableId(value = "merchant_id")
    private Long merchantId;

    /**
     * 姓名
     */
    private String legalPersonName;

    /**
     * 关联城市
     */
    private Long cityId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 备用手机号
     */
    private String backupMobile;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 对公账号
     */
    private String bankCardNo;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司税号
     */
    private String taxNo;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}

package org.dromara.pay.domain.bo;

import org.dromara.pay.domain.GanPayMerchant;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 法人信息业务对象 gan_pay_merchant
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@AutoMapper(target = GanPayMerchant.class, reverseConvertGenerate = false)
public class GanPayMerchantBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 法人ID
     */
    @NotNull(message = "法人ID不能为空", groups = { EditGroup.class })
    private Long merchantId;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String legalPersonName;

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
     * 备注
     */
    private String remark;


}

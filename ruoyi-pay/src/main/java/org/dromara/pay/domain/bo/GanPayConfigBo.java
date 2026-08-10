package org.dromara.pay.domain.bo;

import org.dromara.pay.domain.GanPayConfig;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 支付通道与商户配置业务对象 gan_pay_config
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@AutoMapper(target = GanPayConfig.class, reverseConvertGenerate = false)
public class GanPayConfigBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置主键ID
     */
    @NotNull(message = "配置主键ID不能为空", groups = { EditGroup.class })
    private Long configId;

    /**
     * 归属法人
     */
    @NotNull(message = "归属法人不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;

    /**
     * 商户名称
     */
    private String configName;

    /**
     * 支付渠道
     */
    @NotBlank(message = "支付渠道不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channelType;

    /**
     * 商户模式
     */
    private String mchMode;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 子商户号
     */
    private String subMchId;

    /**
     * 证书在 COS 的下载 URL
     */
    private String certCosUrl;

    /**
     * 私钥在 COS 的下载 URL
     */
    private String keyCosUrl;

    /**
     * 动态参数 JSON
     */
    private String extraConfig;

    /**
     * 是否开启分账（0否 1是）
     */
    private String isProfitSharing;

    /**
     * 分账比例上限(%)
     */
    private BigDecimal maxSharingRatio;

    /**
     * 状态（0启用 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}

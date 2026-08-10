package org.dromara.pay.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * 支付通道与商户配置对象 gan_pay_config
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gan_pay_config")
public class GanPayConfig extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置主键ID
     */
    @TableId(value = "config_id")
    private Long configId;

    /**
     * 归属法人
     */
    private Long merchantId;

    /**
     * 商户名称
     */
    private String configName;

    /**
     * 支付渠道
     */
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
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注
     */
    private String remark;


}

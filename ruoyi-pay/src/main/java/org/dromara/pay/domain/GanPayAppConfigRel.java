package org.dromara.pay.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 程序与商户绑定表对象 gan_pay_app_config_rel
 *
 * @author Lion Li
 * @date 2026-08-12 00:32:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gan_pay_app_config_rel")
public class GanPayAppConfigRel extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 应用/小程序ID（关联 gan_pay_app.app_id）
     */
    private Long appId;

    /**
     * 支付商户配置ID（关联 gan_pay_config.config_id）
     */
    private Long configId;

    /**
     * 在此应用下是否为默认通道（0否 1是）
     */
    private Boolean isDefault;


}

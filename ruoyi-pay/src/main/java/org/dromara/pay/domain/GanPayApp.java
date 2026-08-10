package org.dromara.pay.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 应用信息对象 gan_pay_app
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gan_pay_app")
public class GanPayApp extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用主键
     */
    @TableId(value = "app_id")
    private Long appId;

    /**
     * 归属法人
     */
    private Long merchantId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 应用标识
     */
    private String appIdentity;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 客服类型
     */
    private String customServiceType;

    /**
     * 客服会话
     */
    private String sessionId;

    /**
     * 注册邮箱
     */
    private String accountEmail;

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

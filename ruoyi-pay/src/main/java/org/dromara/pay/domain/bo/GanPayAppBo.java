package org.dromara.pay.domain.bo;

import org.dromara.pay.domain.GanPayApp;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 应用信息业务对象 gan_pay_app
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Data
@AutoMapper(target = GanPayApp.class, reverseConvertGenerate = false)
public class GanPayAppBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用主键
     */
    @NotNull(message = "应用主键不能为空", groups = { EditGroup.class })
    private Long appId;

    /**
     * 归属法人
     */
    @NotNull(message = "归属法人不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}

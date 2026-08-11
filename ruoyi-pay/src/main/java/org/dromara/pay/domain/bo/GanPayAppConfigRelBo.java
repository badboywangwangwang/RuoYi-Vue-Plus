package org.dromara.pay.domain.bo;

import org.dromara.pay.domain.GanPayAppConfigRel;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 程序与商户绑定表业务对象 gan_pay_app_config_rel
 *
 * @author Lion Li
 * @date 2026-08-12 00:32:13
 */
@Data
@AutoMapper(target = GanPayAppConfigRel.class, reverseConvertGenerate = false)
public class GanPayAppConfigRelBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 应用/小程序ID（关联 gan_pay_app.app_id）
     */
    @NotNull(message = "应用/小程序ID（关联 gan_pay_app.app_id）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;

    /**
     * 支付商户配置ID（关联 gan_pay_config.config_id）
     */
    @NotNull(message = "支付商户配置ID（关联 gan_pay_config.config_id）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long configId;

    /**
     * 在此应用下是否为默认通道（0否 1是）
     */
    private Boolean isDefault;

    /**
     * 创建部门
     */
    private Long createDept;

    /**
     * 绑定操作人
     */
    private String createBy;

    /**
     * 绑定时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 查询参数
     */
    private Map<String, Object> params = new HashMap<>();

}

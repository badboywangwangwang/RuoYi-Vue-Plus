package org.dromara.pay.domain.bo;

import org.dromara.pay.domain.GanPaySysConfig;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 系统配置业务对象 gan_pay_sys_config
 *
 * @author Lion Li
 * @date 2026-08-12 03:09:59
 */
@Data
@AutoMapper(target = GanPaySysConfig.class, reverseConvertGenerate = false)
public class GanPaySysConfigBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID (固定为1，全局唯一配置)
     */
    @NotNull(message = "主键ID (固定为1，全局唯一配置)不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客服电话
     */
    private String customerServicePhone;

    /**
     * 是否显示小程序退款 (1:显示 0:隐藏)
     */
    private Integer showMiniappRefund;

    /**
     * 是否显示手机号 (1:显示 0:隐藏)
     */
    private Integer showPhone;

    /**
     * 是否显示身份证 (1:显示 0:隐藏)
     */
    private Integer showIdCard;

    /**
     * 退款密码
     */
    private String refundPassword;

    /**
     * 小程序会话
     */
    private String miniappAesKey;

    /**
     * 小程序密钥
     */
    private String miniappSecret;

    /**
     * 查询企业的密钥
     */
    private String enterpriseQueryKey;

    /**
     * 查询的最大次数
     */
    private Integer maxQueryLimit;

    /**
     * 服务协议
     */
    private String serviceAgreement;

    /**
     * 注册协议
     */
    private String registrationAgreement;

    /**
     * 首页轮播图 (JSON格式存储图片与跳转配置)
     */
    private String homeBanners;

    /**
     * 首页公告
     */
    private String homeNotice;


}

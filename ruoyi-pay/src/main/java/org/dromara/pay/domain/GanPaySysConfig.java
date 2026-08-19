package org.dromara.pay.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 系统配置对象 gan_pay_sys_config
 *
 * @author Lion Li
 * @date 2026-08-12 03:09:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gan_pay_sys_config")
public class GanPaySysConfig extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID (固定为1，全局唯一配置)
     */
    @TableId(value = "id")
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

    /**
     * 备注
     */
    private String remark;


}

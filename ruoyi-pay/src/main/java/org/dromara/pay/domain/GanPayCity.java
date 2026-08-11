package org.dromara.pay.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 城市信息对象 gan_pay_city
 *
 * @author Lion Li
 * @date 2026-08-11 20:17:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gan_pay_city")
public class GanPayCity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 城市主键ID
     */
    @TableId(value = "city_id")
    private Long cityId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市编码
     */
    private String cityCode;

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

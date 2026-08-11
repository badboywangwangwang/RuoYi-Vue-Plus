package org.dromara.pay.domain.bo;

import org.dromara.pay.domain.GanPayCity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

/**
 * 城市信息业务对象 gan_pay_city
 *
 * @author Lion Li
 * @date 2026-08-11 20:17:25
 */
@Data
@AutoMapper(target = GanPayCity.class, reverseConvertGenerate = false)
public class GanPayCityBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 应用主键
     */
    @NotNull(message = "应用主键不能为空", groups = { EditGroup.class })
    private Long cityId;

    /**
     * 城市名称
     */
    @NotBlank(message = "城市名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cityName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 状态（0正常 1停用）
     */
    private String status;


}

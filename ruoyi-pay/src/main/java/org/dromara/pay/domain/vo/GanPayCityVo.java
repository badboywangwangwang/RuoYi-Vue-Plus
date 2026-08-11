package org.dromara.pay.domain.vo;

import org.dromara.pay.domain.GanPayCity;
import org.apache.fesod.sheet.annotation.ExcelIgnoreUnannotated;
import org.apache.fesod.sheet.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 城市信息视图对象 gan_pay_city
 *
 * @author Lion Li
 * @date 2026-08-11 20:17:25
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GanPayCity.class)
public class GanPayCityVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    @ExcelProperty(value = "城市名称id")
    private String cityId;
    /**
     * 城市名称
     */
    @ExcelProperty(value = "城市名称")
    private String cityName;



    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}

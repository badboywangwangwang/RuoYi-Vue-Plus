package org.dromara.pay.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.query.QueryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.dromara.pay.domain.bo.GanPayCityBo;
import org.dromara.pay.domain.vo.GanPayCityVo;
import org.dromara.pay.domain.GanPayCity;
import org.dromara.pay.mapper.GanPayCityMapper;
import org.dromara.pay.service.IGanPayCityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 城市信息Service业务层处理
 *
 * @author Lion Li
 * @date 2026-08-11 20:17:25
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GanPayCityServiceImpl implements IGanPayCityService {

    private final GanPayCityMapper ganPayCityMapper;

    /**
     * 查询城市信息
     *
     * @param cityId 主键
     * @return 城市信息
     */
    @Override
    public GanPayCityVo queryById(Long cityId) {
        return ganPayCityMapper.selectVoById(cityId);
    }

    /**
     * 分页查询城市信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 城市信息分页列表
     */
    @Override
    public PageResult<GanPayCityVo> queryPageList(GanPayCityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GanPayCity> lqw = buildQueryWrapper(bo);
        Page<GanPayCityVo> result = ganPayCityMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的城市信息列表
     *
     * @param bo 查询条件
     * @return 城市信息列表
     */
    @Override
    public List<GanPayCityVo> queryList(GanPayCityBo bo) {
        LambdaQueryWrapper<GanPayCity> lqw = buildQueryWrapper(bo);
        return ganPayCityMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GanPayCity> buildQueryWrapper(GanPayCityBo bo) {
        return QueryBuilder.lambda(GanPayCity.class)
            .likeIfText(GanPayCity::getCityName, bo.getCityName())
            .eqIfText(GanPayCity::getStatus, bo.getStatus())
            .orderByAsc(GanPayCity::getCityId)
            .build();
    }

    /**
     * 新增城市信息
     *
     * @param bo 城市信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GanPayCityBo bo) {
        GanPayCity add = MapstructUtils.convert(bo, GanPayCity.class);
        validEntityBeforeSave(add);
        boolean flag = ganPayCityMapper.insert(add) > 0;
        if (flag) {
            bo.setCityId(add.getCityId());
        }
        return flag;
    }

    /**
     * 修改城市信息
     *
     * @param bo 城市信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GanPayCityBo bo) {
        GanPayCity update = MapstructUtils.convert(bo, GanPayCity.class);
        validEntityBeforeSave(update);
        return ganPayCityMapper.updateById(update) > 0;
    }

    /**
     * 修改城市信息状态
     *
     * @param cityId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateStatus(Long cityId, String status) {
        return ganPayCityMapper.lambda()
            .set(GanPayCity::getStatus, status)
            .eq(GanPayCity::getCityId, cityId)
            .update();
    }


    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GanPayCity entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除城市信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 可在此扩展删除前业务校验
        }
        return ganPayCityMapper.deleteByIds(ids) > 0;
    }

}

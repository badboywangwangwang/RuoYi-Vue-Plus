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
import org.dromara.pay.domain.bo.GanPayAppBo;
import org.dromara.pay.domain.vo.GanPayAppVo;
import org.dromara.pay.domain.GanPayApp;
import org.dromara.pay.mapper.GanPayAppMapper;
import org.dromara.pay.service.IGanPayAppService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 应用信息Service业务层处理
 *
 * @author Lion Li
 * @date 2026-08-11 03:02:09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GanPayAppServiceImpl implements IGanPayAppService {

    private final GanPayAppMapper ganPayAppMapper;

    /**
     * 查询应用信息
     *
     * @param appId 主键
     * @return 应用信息
     */
    @Override
    public GanPayAppVo queryById(Long appId) {
        return ganPayAppMapper.selectVoById(appId);
    }

    /**
     * 分页查询应用信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 应用信息分页列表
     */
    @Override
    public PageResult<GanPayAppVo> queryPageList(GanPayAppBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GanPayApp> lqw = buildQueryWrapper(bo);
        Page<GanPayAppVo> result = ganPayAppMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(result.getRecords(), result.getTotal());
    }

    /**
     * 查询符合条件的应用信息列表
     *
     * @param bo 查询条件
     * @return 应用信息列表
     */
    @Override
    public List<GanPayAppVo> queryList(GanPayAppBo bo) {
        LambdaQueryWrapper<GanPayApp> lqw = buildQueryWrapper(bo);
        return ganPayAppMapper.selectVoList(lqw);
    }


    private LambdaQueryWrapper<GanPayApp> buildQueryWrapper(GanPayAppBo bo) {
        return QueryBuilder.lambda(GanPayApp.class)
            .eqIfPresent(GanPayApp::getMerchantId, bo.getMerchantId())
            .likeIfText(GanPayApp::getAppName, bo.getAppName())
            .eqIfText(GanPayApp::getAppType, bo.getAppType())
            .eqIfText(GanPayApp::getCustomServiceType, bo.getCustomServiceType())
            .eqIfText(GanPayApp::getStatus, bo.getStatus())
            .orderByAsc(GanPayApp::getAppId)
            .build();
    }

    /**
     * 新增应用信息
     *
     * @param bo 应用信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GanPayAppBo bo) {
        GanPayApp add = MapstructUtils.convert(bo, GanPayApp.class);
        validEntityBeforeSave(add);
        boolean flag = ganPayAppMapper.insert(add) > 0;
        if (flag) {
            bo.setAppId(add.getAppId());
        }
        return flag;
    }

    /**
     * 修改应用信息
     *
     * @param bo 应用信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GanPayAppBo bo) {
        GanPayApp update = MapstructUtils.convert(bo, GanPayApp.class);
        validEntityBeforeSave(update);
        return ganPayAppMapper.updateById(update) > 0;
    }

    /**
     * 修改应用信息状态
     *
     * @param appId 主键
     * @param status 状态值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateStatus(Long appId, String status) {
        return ganPayAppMapper.lambda()
            .set(GanPayApp::getStatus, status)
            .eq(GanPayApp::getAppId, appId)
            .update();
    }


    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GanPayApp entity) {
        // 可在此扩展通用业务校验
    }


    /**
     * 校验并批量删除应用信息信息
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
        return ganPayAppMapper.deleteByIds(ids) > 0;
    }

}

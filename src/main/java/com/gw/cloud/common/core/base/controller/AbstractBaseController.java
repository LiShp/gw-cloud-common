package com.gw.cloud.common.core.base.controller;

import com.gw.cloud.common.core.base.entity.AbstractBaseQueryEntity;
import com.gw.cloud.common.core.base.entity.AbstractBaseUpdateEntity;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.base.result.PageResult;
import com.gw.cloud.common.core.base.service.BaseService;
import com.gw.cloud.common.core.constant.BaseMsgConstant;
import com.gw.cloud.common.core.util.JsonResultUtil;
import com.gw.gwlog.GWMLSimpleLogger;
import com.gw.gwlog.GWMLoggerFactory;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.MessageFormat;
import java.util.List;

/**
 * 基本控制层抽象类
 *
 * @param <T> 结果实体
 * @param <U> 更新实体
 * @param <Q> 查询实体
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public abstract class AbstractBaseController<T extends AbstractBaseUpdateEntity<Long>, U extends AbstractBaseUpdateEntity<Long>, Q extends AbstractBaseQueryEntity<Long>> {

    //打印普通日志
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //打印日志到日志服务
    protected GWMLSimpleLogger gwLogger = GWMLoggerFactory.getSimpleLogger(this.getClass());

    protected abstract BaseService<Long, T, U, Q> getService();

    @RequestMapping(value = "/searchList", method = RequestMethod.POST)
    @ApiOperation(value = "列表查询", notes = "列表精准查询")
    public JsonResult<List<T>> searchList(@RequestBody Q query) {
        JsonResult<List<T>> jsonResult;
        try {
            List<T> result = this.getService().queryList(query);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/searchPage", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询", notes = "分页精准查询")
    public JsonResult<PageResult<T>> searchPage(@RequestBody Q query) {
        JsonResult<PageResult<T>> jsonResult;
        try {
            PageResult<T> result = this.getService().queryPage(query);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/searchById", method = RequestMethod.POST)
    @ApiOperation(value = "单行查询", notes = "根据ID单行查询数据")
    public JsonResult<T> searchById(@RequestParam Long id) {
        JsonResult<T> jsonResult;
        try {
            T result = this.getService().queryById(id);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ApiOperation(value = "物理删除", notes = "根据ID单行物理删除数据")
    public JsonResult<Integer> remove(@RequestParam Long id) {
        JsonResult<Integer> jsonResult;
        try {
            int result = this.getService().deleteById(id);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/removeBatch", method = RequestMethod.POST)
    @ApiOperation(value = "批量物理删除", notes = "根据多个ID批量物理删除数据")
    public JsonResult<Integer> removeBatch(@RequestParam String ids) {
        JsonResult<Integer> jsonResult;
        try {
            int result = this.getService().deleteBatch(ids);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "根据ID单行修改数据")
    public JsonResult<Integer> edit(@RequestBody U update) {
        JsonResult<Integer> jsonResult;
        try {
            int result = this.getService().updateById(update);
            if (result > 0) {
                jsonResult = JsonResultUtil.createSuccessJsonResult(result);
            } else {
                jsonResult = JsonResultUtil.createFailureJsonResult(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UPDATE, BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DATA_DOES_NOT_EXIST));
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UPDATE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UPDATE, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/editBatch", method = RequestMethod.POST)
    @ApiOperation(value = "批量修改", notes = "批量根据ID修改数据")
    public JsonResult<Integer> editBatch(@RequestBody List<U> updateList) {
        JsonResult<Integer> jsonResult;
        try {
            int result = this.getService().updateBatch(updateList);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UPDATE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UPDATE, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "创建", notes = "单行创建数据")
    public JsonResult<Integer> create(@RequestBody U update) {
        JsonResult<Integer> jsonResult;
        try {
            int result = this.getService().save(update);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_CREATE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_CREATE, e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/createBatch", method = RequestMethod.POST)
    @ApiOperation(value = "批量创建", notes = "批量创建数据")
    public JsonResult<Integer> createBatch(@RequestBody List<U> updateList) {
        JsonResult<Integer> jsonResult;
        try {
            int result = this.getService().saveBatch(updateList);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_CREATE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_CREATE, e);
        }
        return jsonResult;
    }
}
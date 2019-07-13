package com.gw.cloud.common.base.controller;

import com.github.nickvl.xspring.core.log.aop.annotation.LogInfo;
import com.gw.cloud.common.base.entity.BaseEntity;
import com.gw.cloud.common.base.service.BaseService;
import com.gw.cloud.common.base.util.QueryResult;
import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.constant.BaseMsgConstant;
import com.gw.cloud.common.core.util.JsonResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

/***
* base controller for common api
*/
@LogInfo
public abstract class BaseController<ID extends Serializable, T extends BaseEntity<ID>> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Class<T> domainClass;

    @Autowired
    protected BaseService<ID, T> baseService;

    public BaseController() {
        Type[] types = ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments();
        this.domainClass = (Class<T>) types[1];
    }

    @ApiOperation( value = "添加", notes = "添加", httpMethod = "POST" )
    @PostMapping
    public JsonResult<Object> create(@RequestBody T t) {
        JsonResult<Object> jsonResult;
        try {
            // OperationEntityWebUtils.setProperty(model);
            baseService.create(t);
            jsonResult = JsonResultUtil.createSuccessJsonResult(null);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_CREATE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_CREATE, e);
        }
        return jsonResult;
    }

    @ApiOperation( value = "修改", notes = "修改", httpMethod = "PUT" )
    @PutMapping
    public JsonResult<Integer> update(@RequestBody T t) {
        JsonResult<Integer> jsonResult;
        try {
            // OperationEntityWebUtils.setProperty(model);
            baseService.updateSelective(t);
            jsonResult = JsonResultUtil.createSuccessJsonResult(null);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UPDATE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UPDATE, e);
        }
        return jsonResult;
    }

    @ApiOperation( value = "按ID查询", notes = "按ID查询", httpMethod = "GET" )
    @GetMapping("{id}")
    public JsonResult<T> findById(@ApiParam(name = "id", value = "需要查询的ID", required = true) @PathVariable("id") ID id) {
        JsonResult<T> jsonResult;
        try {
            T result = baseService.selectByPk(id);
            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e);
        }
        return jsonResult;
    }

    @ApiOperation( value = "批量ID查询", notes = "批量ID查询", httpMethod = "GET" )
    @GetMapping("batch")
    public JsonResult<List<T>> findByIds(@ApiParam(name = "ids", value = "需要查询的ID集合", required = true) @RequestParam("ids") List<ID> ids) {
        JsonResult<List<T>> jsonResult;
        try {
            List<T> listResult = baseService.selectByPks(ids);
            jsonResult = JsonResultUtil.createSuccessJsonResult(listResult);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e);
        }
        return jsonResult;
    }
 
//    @ApiOperation( value = "按查询条件返回列表", notes = "按查询条件返回列表", httpMethod = "GET" )
//    @GetMapping
//    public JsonResult<List<T>> listByCondition(@ModelAttribute T t,
//                                   @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
//                                   @ApiParam(name = "rows", value = "每页显示条数（默认为10）" ) @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
//
//        JsonResult<List<T>> jsonResult;
//        try {
//            List<T> listResult = Optional.ofNullable(baseService.paginateList(t, page, rows)).orElse(Collections.emptyList());
//            jsonResult = JsonResultUtil.createSuccessJsonResult(listResult);
//        } catch (Exception e) {
//            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e.getMessage()));
//            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_SEARCH, e);
//        }
//        return jsonResult;
//    }

    @ApiOperation( value = "按条件查询返回列表", notes = "按条件查询返回列表", httpMethod = "GET" )
    @GetMapping("result")
    public JsonResult<QueryResult<T>> paginateByCondition(@ModelAttribute T t,
                                              @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @ApiParam(name = "rows", value = "每页显示条数（默认为10）" ) @RequestParam(value = "rows", defaultValue = "10") Integer rows) {
        JsonResult jsonResult;
        try {
            QueryResult<T> pageResult = baseService.paginateQueryResult(t, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception var4) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", var4.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", var4);
        }

        return jsonResult;
    }

    @ApiOperation( value = "按条件模糊查询返回列表", notes = "按条件模糊查询返回列表", httpMethod = "GET" )
    @GetMapping("likeresult")
    public JsonResult<QueryResult<T>> paginateByLikeCondition(@ModelAttribute T t,
                                                          @ApiParam(name = "page", value = "页码（默认为1）") @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                          @ApiParam(name = "rows", value = "每页显示条数（默认为10）" ) @RequestParam(value = "rows", defaultValue = "10") Integer rows) {

        Class tClass = t.getClass();
        Method[] methods = tClass.getMethods();
        Example example = new Example(tClass);
        Example.Criteria criteria = example.createCriteria();
        try {
        for (Method method : methods) {
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
                String parameterName = returnType.getName();
                if("java.lang.String".equals(parameterName) && methodName.startsWith("get")){
                    String filedName = methodName.replace("get","");
                    filedName = filedName.replace(filedName.substring(0, 1),filedName.substring(0, 1).toLowerCase()) ;
                    Object result = method.invoke(t,null);
                    if(null == result){
                        continue;
                    }
                    criteria.andLike(filedName,"%"+result.toString()+"%" );
                }
        }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        JsonResult jsonResult;
        try {
            QueryResult<T> pageResult = baseService.paginateQueryResultByExample(example, page, rows);
            jsonResult = JsonResultUtil.createSuccessJsonResult(pageResult);
        } catch (Exception var4) {
            this.logger.error(MessageFormat.format("查询失败！ {0}", var4.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult("查询失败！ {0}", var4);
        }

        return jsonResult;
    }

    @ApiOperation( value = "删除", notes = "删除", httpMethod = "DELETE" )
    @DeleteMapping
    public JsonResult<Object> delete(@RequestBody T t) {
        JsonResult<Object> jsonResult;
        try {
             baseService.delete(t);
            jsonResult = JsonResultUtil.createSuccessJsonResult(null);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e);
        }
        return jsonResult;

    }

    @ApiOperation(value = "按ID删除", notes = "按ID删除", httpMethod = "DELETE")
    @DeleteMapping("{id}")
    public JsonResult<Object> deleteById(@ApiParam(name = "id", value = "需要删除的ID", required = true) @PathVariable("id") ID id) {

        JsonResult<Object> jsonResult;
        try {
            baseService.deleteByPk(id);
            jsonResult = JsonResultUtil.createSuccessJsonResult(null);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e);
        }
        return jsonResult;
    }



    @ApiOperation(value = "批量ID删除", notes = "批量ID删除", httpMethod = "DELETE")
    @DeleteMapping("batch")
    public JsonResult<Object> deleteByIds(@ApiParam(name = "ids", value = "需要删除的ID集合", required = true) @RequestParam("ids") Collection<ID> ids) {

        JsonResult<Object> jsonResult;
        try {
            baseService.deleteByPks(ids);
            jsonResult = JsonResultUtil.createSuccessJsonResult(null);
        } catch (Exception e) {
            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e.getMessage()));
            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e);
        }
        return jsonResult;
    }

//    @ApiOperation(value = "逻辑ID删除", notes = "根据ID单行逻辑删除数据",httpMethod = "DELETE")
//    @DeleteMapping(value = "logic/{id}")
//    public JsonResult<Integer> removeLogic(@ApiParam(name = "id", value = "需要查询的ID", required = true) @PathVariable("id") String id) {
//        JsonResult<Integer> jsonResult;
//        try {
//            int result = baseService.deleteLogicById(id);
//            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
//        } catch (Exception e) {
//            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e.getMessage()));
//            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e);
//        }
//        return jsonResult;
//    }
//
//    @ApiOperation(value = "批量逻辑删除", notes = "根据多个ID批量逻辑删除数据",httpMethod = "DELETE")
//    @DeleteMapping(value = "/logicBatch")
//    public JsonResult<Integer> removeLogicBatch(@RequestParam String ids) {
//        JsonResult<Integer> jsonResult;
//        try {
//            int result = baseService.deleteLogicBatch(ids);
//            jsonResult = JsonResultUtil.createSuccessJsonResult(result);
//        } catch (Exception e) {
//            logger.error(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e.getMessage()));
//            jsonResult = JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DELETE, e);
//        }
//        return jsonResult;
//    }
}

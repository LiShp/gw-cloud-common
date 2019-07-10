package com.gw.cloud.common.base.constant;

/**
 * 基本信息常量类
 *
 * @author WRQ
 * @date 2019/4/11
 * @since 1.0.0
 */
public class BaseMsgConstant {

    public static final String BASE_MSG_INFO_DEFAULT = "操作成功！";

    public static final String BASE_MSG_ERROR_DEFAULT = "操作失败！";

    public static final String BASE_MSG_ERROR_FORMAT_SEARCH = "查询失败！ {0}";

    public static final String BASE_MSG_ERROR_FORMAT_DELETE = "删除失败！ {0}";

    public static final String BASE_MSG_ERROR_FORMAT_UPDATE = "更新失败！ {0}";

    public static final String BASE_MSG_ERROR_FORMAT_CREATE = "创建失败！ {0}";

    public static final String BASE_MSG_ERROR_FORMAT_IS_NOT_NULL = "参数{0}不可为空！";

    public static final String BASE_MSG_ERROR_FORMAT_UNRECOGNIZED = "未识别的{0}: {1}！";

    public static final String BASE_MSG_ERROR_FORMAT_DATA_EXISTS = "数据已存在:{0}！";

    public static final String BASE_MSG_ERROR_FORMAT_DATA_DOES_NOT_EXIST = "数据不存在:{0}！";

    public static final String BASE_MSG_INFO_ID = "ID";

    public static final String BASE_MSG_ERROR_PARAM_IS_NOT_NULL = "参数不可为空！";

    public static final String BASE_MSG_ERROR_FACTORY_ID_IS_NOT_NULL = "工厂ID不可为空！";

    public static final String BASE_MSG_ERROR_FACTORY_CODE_IS_NOT_NULL = "工厂编码不可为空！";

    public static final String BASE_MSG_ERROR_BARCODECONTENT_IS_NOT_NULL = "参数关键件条码不可为空！";
    
    public static final String BASE_MSG_ERROR_BARCODECONTENT_NUM = "参数关键件条码必须大于11位！";
    
    public static final String BASE_MSG_ERROR_BARCODECONTENT_MATERIAL = "该条码没有对应物料信息！";

    public static final String BASE_MSG_ERROR_FORMAT_DATA_NOT_EXISTS = "请先维护{0}：{1}的{2}！";

    public static final String BASE_MSG_ERROR_FORMAT_DATA_CONVERSION = "数据转换错误！{0}";
    
    public static final String BASE_MSG_ERROR_PARAM_SORTNO_IS_NOT_NULL = "根据车辆VIN，获取在内饰一的上线点顺序及车辆信息为空！";
    
    public static final String BASE_MSG_ERROR_PARAM_VEHICLEINFO_IS_NOT_NULL = "根据车辆VIN，获取车辆信息为空！";
    
    public static final String BASE_MSG_ERROR_PARAM_PER_VIN_IS_NOT_NULL = "根据车辆VIN，获取上一辆车的车辆识别号码为空！";

    public static final String BASE_MSG_ERROR_DICTIONARY_DATA_EXISTS = "请维护{0}对照表信息！";

    public static final String BASE_MSG_ERROR_SOME_DATA_NOT_EXISTS = "以下数据{0}的{1}信息不存在！";
    
    public static final String BASE_MSG_ERROR_NOT_REPEAT = "该条码关键件已经录入，不能重复录入！";

    public static final String BASE_MSG_ERROR_INFO = "{0} 信息验证错误！";

    public static final String BASE_MSG_ERROR_PLAN_INFO="{0}计划暂无，请先维护{0}计划";

    public static final String BASE_MSG_ERROR_OPERATION= "操作失败！{0}";

    public static final String BASE_MSG_ERROR_FORMAT_NOT_EXISTS = "没有待获取的{0}信息";

    public static final String BASE_MSG_ERROR_INTEGRATION_FAIL = "接口调用失败！";
}
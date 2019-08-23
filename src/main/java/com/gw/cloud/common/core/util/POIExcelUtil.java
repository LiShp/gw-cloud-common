package com.gw.cloud.common.core.util;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

/**
 * POI  Excel工具类
 *
 * @author 李贵兴
 * @date 2019/8/23
 * @since 1.0.0
 */
public class POIExcelUtil {

    /**
     * 私有构造函数，不允许实例化
     */
    private POIExcelUtil() {
    }


    /**
     * 生成下拉框列表 适用于下拉列表元素不多的情况(255以内的下拉)
     * @param sheet
     * @param textList  下拉框数组
     * @param firstRow   起始行
     * @param endRow  终止行
     * @param firstCol  起始列
     * @param endCol  终止列
     * @return
     */
    public static DataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        //加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        //DVConstraint constraint = new DVConstraint();
        constraint.setExplicitListValues(textList);
        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
        //数据有效性对象
        DataValidation data_validation = helper.createValidation(constraint, regions);
        //DataValidation data_validation = new DataValidation(regions, constraint);
        return data_validation;
    }
}

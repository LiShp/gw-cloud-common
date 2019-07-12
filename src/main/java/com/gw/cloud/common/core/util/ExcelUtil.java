package com.gw.cloud.common.core.util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.gw.cloud.common.core.base.exception.ApplicationException;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel工具类
 *
 * @author WRQ
 * @date 2019/6/27
 * @since 1.0.0
 */
public class ExcelUtil {

    /**
     * 通过模板导出Excel
     *
     * @param response  HttpServletResponse
     * @param clazz     模板实体类
     * @param modelList 数据列表
     */
    public static void exportByTemplate(HttpServletResponse response, Class<? extends BaseRowModel> clazz, List<? extends BaseRowModel> modelList) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
            createExcelByTemplate(out, clazz, modelList);
        } catch (IOException e) {
            throw new ApplicationException("Failed to export excel by template.");
        } finally {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过模板实体创建Excel
     *
     * @param out       输出流
     * @param clazz     模板实体类
     * @param modelList 数据列表
     */
    private static void createExcelByTemplate(OutputStream out, Class<? extends BaseRowModel> clazz, List<? extends BaseRowModel> modelList) {

        // 创建Sheet
        Sheet sheet1 = new Sheet(1, 0, clazz);
        // Sheet名
        sheet1.setSheetName("sheet1");
        // 是否自适应宽度
        sheet1.setAutoWidth(Boolean.TRUE);
        // 设置表格样式
        sheet1.setTableStyle(createTableStyleDefault());

        // 创建写入对象
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        // 将数据写入目标Sheet
        writer.write(modelList, sheet1);
        // 写入文件
        writer.finish();
    }

    /**
     * 创建默认表格样式
     *
     * @return 默认表格样式
     */
    private static TableStyle createTableStyleDefault() {

        // 表头样式
        Font headFont = new Font();
        // 字体是否加粗
        headFont.setBold(true);
        // 字体大小
        headFont.setFontHeightInPoints((short) 12);
        // 字体
        headFont.setFontName("微软雅黑");

        // 内容样式
        Font contextFont = new Font();
        contextFont.setBold(false);
        contextFont.setFontHeightInPoints((short) 12);
        contextFont.setFontName("微软雅黑");

        // 初始化表格样式
        TableStyle tableStyle = new TableStyle();
        // 设置表头样式
        tableStyle.setTableHeadFont(headFont);
        tableStyle.setTableHeadBackGroundColor(IndexedColors.GREY_40_PERCENT);
        // 设置内容样式
        tableStyle.setTableContentFont(contextFont);
        tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE);
        return tableStyle;
    }
}

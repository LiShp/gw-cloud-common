package com.gw.cloud.common.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel解析监听器
 *
 * @author WRQ
 * @date 2019/7/15
 * @since 1.0.0
 */
public class ExcelAnalysisEventListenner extends AnalysisEventListener {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelAnalysisEventListenner.class);
    /**
     * 数据暂存区
     */
    private List<Object> dataList = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        // 打印日志
        LOGGER.info("CurrentSheet: {}; CurrentRowNum: {}.", context.getCurrentSheet(), context.getCurrentRowNum());
        // 添加到数据暂存区供后续批量业务逻辑处理
        dataList.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 解析结束销毁不用的资源
        // dataList.clear();
    }

    public List<Object> getDataList() {
        return dataList;
    }
}

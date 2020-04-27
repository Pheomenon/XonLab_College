package com.xonlab.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author:Gao
 * @Date:2020-04-18 15:37
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //按行读取excel内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("****"+data);
    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }
    //读取完成后要做的事
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

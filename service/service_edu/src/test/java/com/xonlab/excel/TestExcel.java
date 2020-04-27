package com.xonlab.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Gao
 * @Date:2020-04-18 15:19
 */
public class TestExcel {
    public static void main(String[] args) {
//        //实现写的操作
//        //设置写入文件夹地址和Excel文件名称
//        String fileName = "D:\\write.xlsx";
//        //调用easyexcel里面的方法实现写操作
//        //write方法两个参数，第一个参数文件路径名称，第二个参数实体类class
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());

        //实现读操作
        String filename = "D:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    //创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}

package com.xonlab.staservice.schedule;

import com.xonlab.staservice.entity.StatisticsDaily;
import com.xonlab.staservice.service.StatisticsDailyService;
import com.xonlab.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author:Gao
 * @Date:2020-05-06 17:38
 */
@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService service;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        service.registerCount(day);
    }
}

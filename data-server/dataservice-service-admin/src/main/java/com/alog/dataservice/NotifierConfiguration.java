package com.alog.dataservice;


import de.codecentric.boot.admin.notify.Notifier;
import de.codecentric.boot.admin.notify.RemindingNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.TimeUnit;

/**
 * 为监控的服务添加邮件通知
 * @author zhuoweihuang
 * @date 20190102
 */
@Configuration
@EnableScheduling
public class NotifierConfiguration {


    @Autowired
    private Notifier notifier;

    //服务上线或者下线都通知
    private String[] reminderStatues = {"DOWN"};

    @Bean
    @Primary
    public RemindingNotifier remindingNotifier(){
        RemindingNotifier remindingNotifier = new RemindingNotifier(notifier);
        //设定时间，五分钟提醒一次
        remindingNotifier.setReminderPeriod(TimeUnit.MINUTES.toMinutes(5));
        //设置监控服务状态，状态改变为给定值得时候提醒
        remindingNotifier.setReminderStatuses(reminderStatues);
        return remindingNotifier;
    }
}

package com.advert.smallapp.task;

import com.advert.smallapp.utils.DahuZhongxueFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.time.LocalDateTime;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class FileCheckTask {

//    @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 0 10,16 * * ?")
    private void configureTasks() throws ParseException {
        log.info("开始检查达浒中学新文件");
        DahuZhongxueFileUtils.checkDhzxFile();
        log.info("检查达浒中学文件完成");
    }

}

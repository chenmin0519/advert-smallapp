package com.advert.smallapp.task;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.ParseException;
import java.util.Date;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class FileCheckTask {


}

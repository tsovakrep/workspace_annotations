package ru.javastudy.mvcHtml5Angular.mvc.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created for JavaStudy.ru on 02.03.2016.
 */
@Component
public class ScheduleTask {

    @Scheduled(fixedDelay = 10000)
    public void fixedDelaySchedule() {
        System.out.println("fixedDelaySchedule every 10 seconds" + new Date());
    }

    //every 30 seconds (seconds, minutes, hours, day of month, month, day of week, year(optional))
    @Scheduled(cron = "0/30 * * * * ?")
    public void cronSchedule() {
        System.out.println("cronSchedule every 30 seconds" + new Date());
    }

}

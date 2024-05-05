package com.vamceep.jobscheduler.factory;

import com.vamceep.jobscheduler.enums.JobSchedulerType;
import com.vamceep.jobscheduler.service.IJobScheduler;
import com.vamceep.jobscheduler.service.schedulers.FCFSJobScheduler;
import com.vamceep.jobscheduler.service.schedulers.SJFScheduler;
import org.springframework.stereotype.Service;

@Service
public class SchedulerFactory {
    FCFSJobScheduler fcfsJobScheduler;
    SJFScheduler sjfJobScheduler;
    static SchedulerFactory INSTANCE= null;

    private SchedulerFactory(FCFSJobScheduler fcfsJobScheduler1, SJFScheduler sjfsScheduler1) {
        fcfsJobScheduler = fcfsJobScheduler1;
        sjfJobScheduler = sjfsScheduler1;
    }
    public static IJobScheduler getScheduler(JobSchedulerType type) {
        if(INSTANCE == null) {
            INSTANCE = new SchedulerFactory(new FCFSJobScheduler(), new SJFScheduler());
        }
        switch (type) {
            case FCFS -> {
                return INSTANCE.fcfsJobScheduler;
            }
            case SJF ->  {
                return INSTANCE.sjfJobScheduler;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

}

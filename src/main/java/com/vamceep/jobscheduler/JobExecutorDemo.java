package com.vamceep.jobscheduler;

import com.vamceep.jobscheduler.dto.Job;
import com.vamceep.jobscheduler.enums.JobSchedulerType;
import com.vamceep.jobscheduler.factory.SchedulerFactory;
import com.vamceep.jobscheduler.service.JobExecutor;

import java.util.List;

public class JobExecutorDemo {
    public static void main(String[] args) {
        JobExecutor executor = new JobExecutor(2, SchedulerFactory.getScheduler(JobSchedulerType.FCFS));
        List<Job> jobs = List.of(
                Job.builder()
                        .name("job1")
                        .durationInMilly(3000)
                        .build(),
                Job.builder()
                        .name("job2")
                        .durationInMilly(5000)
                        .build(),
                Job.builder()
                        .name("job3")
                        .durationInMilly(1000)
                        .build()
        );

        executor.execute(jobs);
    }
}

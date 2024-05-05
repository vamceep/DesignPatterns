package com.vamceep.jobscheduler.dto;

import com.vamceep.jobscheduler.enums.JobStatus;
import com.vamceep.jobscheduler.enums.ThreadStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@Setter
@Slf4j
public class CustomThread {
    Job job;
    String name;
    ThreadStatus status;
    Long startTimeMilli;

    public void run() {
        log.trace("running job:{} on thread: {}", job.name, name);
        job.run();
        long now = System.currentTimeMillis();
        if(job.getDurationInMilly() < now - startTimeMilli) {
            job.setStatus(JobStatus.FINISHED);
        }
    }
}

package com.vamceep.jobscheduler.dto;

import com.vamceep.jobscheduler.enums.JobStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@Setter
@Slf4j
public class Job {
    JobStatus status;
    int priority;
    long durationInMilly;
    String name;

    public void run() {

    }

}

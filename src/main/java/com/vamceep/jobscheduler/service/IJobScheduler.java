package com.vamceep.jobscheduler.service;

import com.vamceep.jobscheduler.dto.Job;

import java.util.List;


public interface IJobScheduler {
    List<Job> schedule(List<Job> jobs);
}

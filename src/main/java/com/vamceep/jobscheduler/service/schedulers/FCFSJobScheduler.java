package com.vamceep.jobscheduler.service.schedulers;

import com.vamceep.jobscheduler.dto.Job;
import com.vamceep.jobscheduler.service.IJobScheduler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FCFSJobScheduler implements IJobScheduler {
    @Override
    public List<Job> schedule(List<Job> jobs) {
        return jobs;
    }
}

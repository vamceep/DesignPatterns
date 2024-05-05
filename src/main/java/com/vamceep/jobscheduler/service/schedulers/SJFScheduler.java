package com.vamceep.jobscheduler.service.schedulers;

import com.vamceep.jobscheduler.dto.Job;
import com.vamceep.jobscheduler.service.IJobScheduler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SJFScheduler implements IJobScheduler {
    @Override
    public List<Job> schedule(List<Job> jobs) {
        return jobs.stream().sorted((x,y) -> Math.toIntExact(x.getDurationInMilly() - y.getDurationInMilly()))
                .collect(Collectors.toList());
    }
}

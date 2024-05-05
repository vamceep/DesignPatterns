package com.vamceep.jobscheduler.service;

import com.vamceep.jobscheduler.dto.CustomThread;
import com.vamceep.jobscheduler.dto.Job;
import com.vamceep.jobscheduler.enums.JobStatus;
import com.vamceep.jobscheduler.enums.ThreadStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JobExecutor {
    private final List<CustomThread> freeThreads;
    private List<CustomThread> runningThreads;
    private final IJobScheduler scheduler;
    
    public JobExecutor(int threadCount, IJobScheduler scheduler) {
        this.scheduler = scheduler;
        this.freeThreads= new LinkedList<>();
        for(int i = 0; i < threadCount; i++){
            freeThreads.add(CustomThread.builder()
                    .name("thread_" + i)
                    .status(ThreadStatus.IDLE)
                    .build());
        }
        this.runningThreads = new LinkedList<>();
    }
    
    public void execute(List<Job> jobs) {
        List<Job> jobQueue = scheduler.schedule(jobs);
        int index = 0;
        while (index < jobQueue.size()) {
            Job job = jobs.get(index);
            if (assignThread(job)) {
                index++;
            } else {
                runCurrentRunningJob();
            }
        }
        while(!runningThreads.isEmpty()) {
            runCurrentRunningJob();
        }
    }

    private void runCurrentRunningJob() {
        for (CustomThread runningThread : runningThreads) {
            runningThread.run();
            Job runningJob = runningThread.getJob();
            if (runningJob.getStatus().equals(JobStatus.FINISHED)) {
                log.info("Job : {} on thread: {} finished at : {} sec", runningJob.getName(),
                        runningThread.getName(), new Date());
                runningThread.setStatus(ThreadStatus.IDLE);
                runningThread.setJob(null);
                freeThreads.addLast(runningThread);
            }
        }
        runningThreads = runningThreads.stream().filter(x -> x.getStatus().equals(ThreadStatus.RUNNING))
                .collect(Collectors.toList());
    }

    private boolean assignThread(Job job) {
        if(!freeThreads.isEmpty()) {
            CustomThread newThread = freeThreads.removeLast();
            newThread.setJob(job);
            job.setStatus(JobStatus.RUNNING);
            newThread.setStartTimeMilli(System.currentTimeMillis());
            newThread.setStatus(ThreadStatus.RUNNING);
            log.info("Starting job with name: {} on thread: {} at {} sec", job.getName(), newThread.getName(),
                    new Date());
            runningThreads.add(newThread);
            return true;
        }
        return false;
    }
}

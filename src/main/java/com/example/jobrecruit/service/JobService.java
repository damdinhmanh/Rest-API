package com.example.jobrecruit.service;

import com.example.jobrecruit.model.Job;
import com.example.jobrecruit.request.UpsertJobRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.*;

@Service
public class JobService {
    private List<Job> jobs;
    List<Job> sortMaxList;

    public JobService() {
        jobs = new ArrayList<Job>();
        sortMaxList = new ArrayList<Job>();

        jobs.add(new Job("AAA", "Recruit Java Dev",
                "Backend", "Ha Noi", 500,
                2000, "tuyendungAAA@gmail.com"));
        jobs.add(new Job("BBB", "Recruit React Dev",
                "ReactJS Frontend", "Ho Chi Minh", 400,
                1000, "tuyendungBBB@gmail.com"));
        jobs.add(new Job("CCC", "Recruit PHP Dev",
                "Backend", "Da Nang", 300,
                1500, "tuyendungCCC@gmail.com"));
    }

    private String getRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public Job getJobById(String id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    public Job createJob(UpsertJobRequest request) {
        String id = getRandomString();

        Job job = new Job(id, request.getTitle(), request.getDescription(),
                request.getLocation(), request.getMinSalary(), request.getMaxSalary(),
                request.getEmailTo());
        jobs.add(job);
        return job;
    }

    public Job updateJobById(String id, UpsertJobRequest request) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                job.setTitle(request.getTitle());
                job.setDescription(request.getDescription());
                job.setLocation(request.getLocation());
                job.setMinSalary(request.getMinSalary());
                job.setMaxSalary(request.getMaxSalary());
                job.setEmailTo(request.getEmailTo());
                return job;
            }
        }
        return null;
    }

    public void deleteJobById(String id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                jobs.remove(job);
                return;
            }
        }
    }

    public List<Job> getJobList() {
        return jobs;
    }

    public Job getJobRandom() {
        Random random = new Random();
        int randomId = random.ints(0, jobs.size()).findFirst().getAsInt();
        return jobs.get(randomId);
    }

    public List<Job> getSortBySalaryList(int maxSalary) {
        sortMaxList.clear();

        for (Job job : jobs) {
            if (job.getMaxSalary() <= maxSalary) {
                sortMaxList.add(job);
            }
        }

        Collections.sort(sortMaxList, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o2.getMaxSalary() - o1.getMaxSalary();
            }
        });

        return sortMaxList;
    }
}

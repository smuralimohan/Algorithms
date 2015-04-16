package org.coursera.stanford;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SchedulingByWeightsAndLengths {

    public static void main(String[] args) {

        String filePath = "X:\\work\\AlgoPractice\\src\\main\\resources\\jobs.txt";
        System.out.println("Completion Time by Diff:" + findCompletionTimeByDiff(getJobsList(filePath)));
        System.out.println("Completion Time by Ratio:" + findCompletionTimeByRatio(getJobsList(filePath)));

    }
    static Long findCompletionTimeByDiff(List<Job> jobs) {
        Long completionTime = 0L;
        Long jobCompletionTime = 0L;

        Collections.sort(jobs, new CompareByDifference());

        for (Job job: jobs) {
            jobCompletionTime += job.length;
            completionTime += jobCompletionTime * job.weight;
        }
        return completionTime;
    };

    static Long findCompletionTimeByRatio(List<Job> jobs) {
        Long completionTime = 0L;
        Long jobCompletionTime = 0L;

        Collections.sort(jobs, new CompareByRatio());

        for (Job job: jobs) {
            jobCompletionTime += job.length;
            completionTime += jobCompletionTime * job.weight;
        }
        return completionTime;
    }

    static List<Job> getJobsList(String filePath) {
        List<Job> jobs = new ArrayList<>();

        try {
            Iterator<String> linesIterator = Files.readAllLines(Paths.get(filePath)).iterator();
            linesIterator.next();

            while (linesIterator.hasNext()) {
                String line = linesIterator.next();
                jobs.add(new Job(Long.parseLong(line.split(" ")[0]), Long.parseLong(line.split(" ")[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }
};


class CompareByDifference implements Comparator<Job>
{
    public int compare(Job j1, Job j2)
    {
        if ((j1.weight - j1.length) == (j2.weight - j2.length)) {
            return j2.weight.compareTo(j1.weight);
        } else {
            return ((Long) (j2.weight - j2.length)).compareTo(j1.weight - j1.length);
        }
    }
}


class CompareByRatio implements Comparator<Job>
{
    public int compare(Job j1, Job j2)
    {
        return ((Long) (j2.weight * j1.length)).compareTo(j1.weight * j2.length);
    }
}

class Job {
    Long weight;
    Long length;

    Job(Long weight, Long length) {
        this.weight = weight;
        this.length = length;
    }

}

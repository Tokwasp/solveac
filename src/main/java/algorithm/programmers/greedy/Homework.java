package algorithm.programmers.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Homework {

    public String[] solution(String[][] plans) {
        // 작업 변환
        List<Job> jobs = new ArrayList<>();

        for(int row = 0; row < plans.length; row++){
            String[] plan = plans[row];
            String name = plan[0];
            int minute = convertMinuteByHour(plan[1]);
            int duration = Integer.parseInt(plan[2]);

            jobs.add(new Job(name, minute, duration));
        }

        // 정렬
        jobs.sort(Comparator.naturalOrder());

        // 작업
        Stack<RemainJob> remainJobStack = new Stack<>();
        List<String> exitJobs = new ArrayList<>();

        for(int i = 0; i < jobs.size() - 1; i++){
            Job job = jobs.get(i);
            Job nextJob = jobs.get(i + 1);

            int jobEndMinute = job.startMinute + job.duration;
            int freeMinute = nextJob.startMinute - jobEndMinute;

            if(freeMinute >= 0){
                exitJobs.add(job.name);

                while(!remainJobStack.isEmpty() && freeMinute != 0){
                    RemainJob remainJob = remainJobStack.pop();

                    if(remainJob.remainMinute > freeMinute){
                        int remain = remainJob.remainMinute - freeMinute;
                        remainJobStack.push(new RemainJob(remainJob.name, remain));
                        freeMinute = 0;
                    } else {
                        exitJobs.add(remainJob.name);
                        freeMinute -= remainJob.remainMinute;
                    }
                }
            } else {
                remainJobStack.push(new RemainJob(job.name, Math.abs(freeMinute)));
            }
        }

        Job lastJob = jobs.get(jobs.size() - 1);
        exitJobs.add(lastJob.name);

        while(!remainJobStack.isEmpty()){
            exitJobs.add(remainJobStack.pop().name);
        }

        return exitJobs.stream().toArray(String[]::new);
    }

    private int convertMinuteByHour(String hour){
        String[] times = hour.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    static class Job implements Comparable<Job>{
        String name;
        int startMinute;
        int duration;

        Job(String name, int startMinute, int duration) {
            this.name = name;
            this.startMinute = startMinute;
            this.duration = duration;
        }

        public int compareTo(Job anotherJob){
            return this.startMinute - anotherJob.startMinute;
        }
    }

    static class RemainJob {
        String name;
        int remainMinute;

        RemainJob(String name, int remainMinute) {
            this.name = name;
            this.remainMinute = remainMinute;
        }
    }

}

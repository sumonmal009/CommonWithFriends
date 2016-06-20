package concurrency.ScheduledThreadPoolExecutor.Task.DelayedExecution;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import concurrency.ScheduledThreadPoolExecutor.Task.Task;

public class ScheduledThreadPoolExecutorExample {
	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		Task task1 = new Task("Demo Task 1");
		Task task2 = new Task("Demo Task 2");

		System.out.println("The time is : " + new Date()); //print current time

		executor.schedule(task1, 5, TimeUnit.SECONDS);  // execute after 5 sec
		executor.schedule(task2, 10, TimeUnit.SECONDS); // execute after 10 sec

		try {
			executor.awaitTermination(1, TimeUnit.DAYS); // wait for 1 day
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();
	}
}
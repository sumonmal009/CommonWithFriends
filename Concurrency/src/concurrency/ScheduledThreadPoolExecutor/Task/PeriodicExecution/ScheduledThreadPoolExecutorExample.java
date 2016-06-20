package concurrency.ScheduledThreadPoolExecutor.Task.PeriodicExecution;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import concurrency.ScheduledThreadPoolExecutor.Task.Task;

public class ScheduledThreadPoolExecutorExample {
	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		Task task1 = new Task("Demo Task 1");

		System.out.println("The time is : " + new Date());

		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 2, 5, TimeUnit.SECONDS); //first execution after 2 sec then repeat in 5 sec interval 

		try {
			TimeUnit.MILLISECONDS.sleep(20000); // execute for 20000 milisecond  / 20 sec time frame 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();
	}
}
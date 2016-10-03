package concurrency.callableFuture;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCallableRealExample {
	private static final Executor ExecutorService = null;
	static final int THRESHOLD = 10;

	public static void main(String[] arg) {
		int[] arry = new int[1000];
int tempresult=0;
		for (int i = 0; i < arry.length; i++) {
			arry[i] = i + 10;
			tempresult=tempresult+arry[i];
		}
		
		System.out.println("Avg:"+tempresult/1000);

		ExecutorService es = Executors.newFixedThreadPool(THRESHOLD);

		List<Future> futureList = new LinkedList<Future>();
		for (int i = 0; i < arry.length;) {
			int end = Math.min((i + THRESHOLD), arry.length - 1);
			Future f = es.submit(new AvgGenerater(i, end, arry));
			System.out.println("------------ thread count:" + Thread.activeCount());
			futureList.add(f);
			i = end + 1;
		}
		int result = 0;
		int count = 0;
		for (Future f : futureList) {
			try {
				System.out.println(f.get());
				result = result + (int) f.get();
				count++;
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("-------------------");
		System.out.println("Result: " + result / count);
		es.shutdown();
	}

}

class AvgGenerater implements Callable<Integer> {

	int start;
	int end;
	int[] array;

	public AvgGenerater(int start, int end, int[] array) {
		this.start = start;
		this.end = end;
		this.array = array;
	}

	@Override
	public Integer call() throws Exception {
		int result = 0;
		for (int i = start; i <= end; i++) {
			result = result + array[i];
		}

		return (result / ((end - start)+1));
	}

}
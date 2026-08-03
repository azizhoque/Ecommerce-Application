package az.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Executor_1 {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i=1;i<10;i++) {
			
			int taskid=i;
			
			executor.execute(()->System.out.println("task "+taskid+" thread name=> "+Thread.currentThread().getName()));
		}
		executor.shutdown();
	}

}

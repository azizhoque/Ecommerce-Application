package az.multithread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Executor_3 {

	public static void main(String[] args) {

		ThreadPoolExecutor executor = 
				new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
			
		for(int i=1;i<=5;i++) {
			
			int taskid=i;
			
			executor.execute(()->{
			System.out.println("task "+taskid+" thread name=> "
			                   +Thread.currentThread().getName());
            try {
            	Thread.sleep(2000);
                }catch(Exception e) {}
			});
		}	
	
		executor.shutdown();
		executor.close();
	}

}

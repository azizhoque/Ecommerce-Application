package az.multithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Executor_2 {

	public static void main(String[] args) {

		ExecutorService executor=Executors.newFixedThreadPool(2);
		
		Future<Integer>f1= executor.submit(()->{
			try {
				Thread.sleep(3000);
			}catch(Exception e) {
				
			}
			return 10;
		});

		try {
			System.out.println(f1.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}

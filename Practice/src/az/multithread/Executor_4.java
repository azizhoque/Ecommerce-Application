package az.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Executor_4 {

	public static void main(String[] args) {

		CompletableFuture<Integer> f1= CompletableFuture.supplyAsync(()->10)
				.thenApply(result->result*2);
		CompletableFuture<Integer> f= CompletableFuture.supplyAsync(()->10);
		
		CompletableFuture<Void> f2= CompletableFuture.supplyAsync(()->10)
				.thenAccept(result->System.out.println(result));
		
		CompletableFuture<Void> f3= CompletableFuture.supplyAsync(()->10)
				.thenRun(()->System.out.println("Hello"));
		
		CompletableFuture<Void> result= f.thenCombine(f1,(a,b)->a+b )
				.thenAccept((value)->System.out.println("(f1+f2) value is=>"+value));
		try {
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(f3.get());
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}

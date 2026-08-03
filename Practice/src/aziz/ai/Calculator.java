package aziz.ai;

import java.util.Scanner;

class Cal{
	
	int a, b, c;
	
	public void add() {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Please Enter first number");
		a=sc.nextInt();sc.nextLine();
		System.out.println("Please Enter seconf number");

		b=sc.nextInt();
		c=a+b;
		
		System.out.println("result:: " +c);
	}
	
}

public class Calculator {

	public static void main(String[] args) {

		Cal c=new Cal();
		c.add();
		
	}

}

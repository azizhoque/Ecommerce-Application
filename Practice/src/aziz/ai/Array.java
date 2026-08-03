package aziz.ai;

import java.util.Scanner;

public class Array {

	public static void main(String[] args) {

		int arr[] = {10,20,40,50,30};
		
		int ar[]= new int[5];
		
		ar[0]=40;
		ar[1]=20;
		ar[2]=70;
		ar[3]=50;
		ar[4]=90;
		
		for(int i=0;i<ar.length;i++) {
		System.out.println(ar[i]);
		}
		
		Scanner sc = new Scanner(System.in);
		int[] c =new int [5];
		
		System.out.println("Enter the elements of array");
		for(int i =0;i<c.length;i++) {
			c[i]=sc.nextInt();sc.nextLine();
		}
		System.out.println("Elements of arrays are:: ");
		for(int i=0;i<c.length;i++) {
			System.out.print(c[i]);
			System.out.print(" ");
			}
		
	}

}

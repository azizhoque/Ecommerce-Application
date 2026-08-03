package aziz.ai;


class Calculator1{
	
	int add(int a, int b) {
		
		return a+b;
	}
	
	float add(int a, float b) {
		
		return a*b;
	}
	
	float add(float a, float b) {
		
		return a+b;
	}
	

}

public class MethodOverloading {

	public static void main(String[] args) {

		Calculator1 cal =new Calculator1();
		
		float f=cal.add(25.0f, 35.0f);
		System.out.println(f);
		
		float f1 =cal.add(51, 34.0f);
		System.out.println(f1);
		
	}

}

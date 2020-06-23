package week2.day2;

public class OverLoadingClass1 {
	
	int a, b;
	public OverLoadingClass1()
	{
		System.out.println("this is overloading class1");
		
	}
	public OverLoadingClass1(int ol1, int ol2)
	{
		System.out.println("this is overloading class1 with parameters");
		int sum = ol1+ol2;
		System.out.println("sum is " +sum);
		
	}
	public void sum()
	{
		a=10;
		b=20;
		System.out.println("sum of a and b " +(a+b));
	}
	public void sum(int num1, int num2)
	{
		System.out.println("sum of 2 numbers " +(num1+num2));
	}

}

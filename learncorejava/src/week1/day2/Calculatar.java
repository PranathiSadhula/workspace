package week1.day2;

public class Calculatar {
	public static void main(String[] args) {
		Calculatar cal = new Calculatar();
		int addres = cal.add(10, 2);
		int subres =cal.sub(5, 3);
		int mulres =cal.mul(25, 5);
		int divres =cal.divide(30, 6);
		System.out.println(addres+","+subres+","+mulres+","+divres);
		System.out.println();
		cal.clear();
	}

	public int add(int num1, int num2) {
		return num1 + num2;
	}

	public int sub(int num1, int num2) {
		return num1 - num2;
	}

	public int mul(int num1, int num2) {
		return num1 * num2;
	}

	public int divide(int num1, int num2) {
		return num1 / num2;
	}

	public void clear() {
		System.out.println("i cleared ");
	}
}

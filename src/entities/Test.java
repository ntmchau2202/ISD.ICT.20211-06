package entities;

public class Test {

	public static void main(String[] args) {
		try {
			Dock dock = new Dock("ABC", "123", "123 Tay Ho", 163.9, 10, 20);
			System.out.println(dock.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

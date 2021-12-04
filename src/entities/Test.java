package entities;

import java.util.ArrayList;

import controllers.EcoBikeInformationController;

public class Test {

	public static void main(String[] args) {
		try {
			EcoBikeInformationController controller;
			ArrayList<Bike> listBike;
			controller = new EcoBikeInformationController();
			Bike bike1 = new Bike("ABC", "normal", "ABC/123", "ABC-123", 10, 300, "VND", "12/03/2021");
			Bike bike2 = new Bike("ABC", "normal", "ABC/124", "ABC-124", 10, 300, "VND", "12/03/2021");
			listBike = new ArrayList<Bike>();
			listBike.add(bike2);
			listBike.add(bike1);
			controller.setBikeList(listBike);
			System.out.println(controller.getBikeInformation("ABC-123"));
			System.out.println(controller.getBikeInformation("ABC-124"));
			System.out.println(controller.getBikeInformation("ABC-125"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

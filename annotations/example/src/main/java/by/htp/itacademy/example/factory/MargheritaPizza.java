package by.htp.itacademy.example.factory;

import by.htp.itacademy.annotation.Factory;

@Factory(id = "Margherita", type = Meal.class)
public class MargheritaPizza implements Meal {

	@Override
	public float getPrice() {
		return 6f;
	}
}

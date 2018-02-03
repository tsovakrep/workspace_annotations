package by.htp.itacademy.example.factory;

import by.htp.itacademy.annotation.Factory;

@Factory(id = "Calzone", type = Meal.class)
public class CalzonePizza implements Meal {

	@Override
	public float getPrice() {
		return 8.5f;
	}
}

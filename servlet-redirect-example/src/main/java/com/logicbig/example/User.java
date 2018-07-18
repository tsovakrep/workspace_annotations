package com.logicbig.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class User {
	private String name;
	private int age;
}

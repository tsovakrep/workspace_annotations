package by.htp.itacademy.example;

import java.time.LocalDate;

import by.htp.itacademy.annotation.DateFormat;
import by.htp.itacademy.annotation.Factory;
import by.htp.itacademy.annotation.Hashed;

public class ClientTest {
	
    @Hashed 
    public static final String demo1 = "habr";
    @Hashed(method="SHA-1")
    public static final String demo2 = "habrahabr";
    @Hashed(method="SHA-256")
    public static final String demo3 = "habracadabra";
    @DateFormat("yyyy/MM/dd")
    private LocalDate date;
    
}

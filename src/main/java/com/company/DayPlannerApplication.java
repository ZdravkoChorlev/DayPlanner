package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DayPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DayPlannerApplication.class, args);
		Menu menu = new Menu();
		menu.setParameterchoice(args[0]);
		TaskController manager = new TaskController();
		menu.menuOptions(manager.getHashTask());
	}
}

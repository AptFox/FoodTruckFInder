package main.java.worksample.FoodTruckFinder.controller;

import java.util.List;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.model.ConsoleModel;
import main.java.worksample.FoodTruckFinder.view.ConsoleView;

public class ConsoleController {
	public static final Integer INITIAL_OFFSET = 0;
	public static final Integer PAGE_SIZE = 10;
	
	private ConsoleModel consoleModel;
	private ConsoleView consoleView;
	public void startApplication() {
		init();
		consoleView.start();
	}
	
	private void init() {
		this.consoleModel = new ConsoleModel(INITIAL_OFFSET, PAGE_SIZE);
		this.consoleView = new ConsoleView(this);
	}
	
	public List<FoodTruckDTO> getPage(boolean getLastPage) {
		return consoleModel.getPage(getLastPage);
	}

	public void exit() {
		System.exit(0);
	}
	
}

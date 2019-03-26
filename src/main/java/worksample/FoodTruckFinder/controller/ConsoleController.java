package main.java.worksample.FoodTruckFinder.controller;

import java.util.List;

import com.socrata.exceptions.LongRunningQueryException;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.model.ConsoleModel;
import main.java.worksample.FoodTruckFinder.view.ConsoleView;

public class ConsoleController {

	private ConsoleModel consoleModel;
	private ConsoleView consoleView;
	public void startApplication() {
		init();
		consoleView.start();
	}
	
	private void init() {
		this.consoleModel = new ConsoleModel();
		this.consoleView = new ConsoleView(this);
	}
	
	public List<FoodTruckDTO> getPage(boolean getLastPage) {
		List<FoodTruckDTO> foodTruckList = null;
		try {
			foodTruckList = consoleModel.getPage(getLastPage);
		} catch (LongRunningQueryException e) {
			e.printStackTrace();
		}
		return foodTruckList;
	}

	public void exit() {
		System.exit(0);
	}
	
}

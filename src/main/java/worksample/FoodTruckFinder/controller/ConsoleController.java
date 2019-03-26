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
		this.consoleModel = new ConsoleModel();
		this.consoleView = new ConsoleView(this);
		consoleView.start();
	}
	
	/**
m	 * Gets the next page from the consoleModel 
	 * 
	 * @param getLastPage
	 * @return
	 */
	public List<FoodTruckDTO> getPage(boolean getLastPage) {
		List<FoodTruckDTO> foodTruckList = null;
		try {
			foodTruckList = consoleModel.getPage(getLastPage);
		} catch (LongRunningQueryException e) {
			e.printStackTrace();
		}
		return foodTruckList;
	}

	/**
	 * Exits the program
	 */
	public void exit() {
		System.exit(0);
	}
	
}

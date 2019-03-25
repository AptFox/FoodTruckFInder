package main.java.worksample.FoodTruckFinder.model;

import java.util.List;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.service.FoodTruckFinderService;

public class ConsoleModel{
	
	private List<FoodTruckDTO> currentPage;

	private FoodTruckFinderService service = new FoodTruckFinderService();
	
	private Integer offset;
	private Integer pageSize;
	
	public ConsoleModel(Integer offset, Integer pageSize) {
		this.offset = offset;
		this.pageSize = pageSize;
	}

	/*
	 * Gets next or last page of records
	 */
	public List<FoodTruckDTO> getPage(boolean getLastPage) {
		if(getLastPage) {
			offset -= pageSize;
		}else {
			offset += pageSize;
		}
		currentPage = service.getPage(offset, pageSize);
		return currentPage;
	}
	
}

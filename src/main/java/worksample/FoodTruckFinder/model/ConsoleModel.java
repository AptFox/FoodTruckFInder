package main.java.worksample.FoodTruckFinder.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.socrata.exceptions.LongRunningQueryException;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.service.FoodTruckFinderService;

public class ConsoleModel{
	
	private List<FoodTruckDTO> currentPage;
	private Calendar cal;

	private FoodTruckFinderService service = new FoodTruckFinderService();
	private Integer offset;
	private Integer pageSize;
	
	public ConsoleModel(Integer offset, Integer pageSize) {
		this.offset = offset;
		this.pageSize = pageSize;
		init();
	}

	private void init() {
		cal = new GregorianCalendar(TimeZone.getTimeZone("America/Los_Angeles"));
	}

	/*
	 * Gets next or last page of records
	 */
	public List<FoodTruckDTO> getPage(boolean getLastPage) {

		if(getLastPage && offset > 0) {
			offset -= pageSize;
		}else if(currentPage != null){
			offset += currentPage.size();
		}
		try {
			currentPage = service.getPage(offset, pageSize, cal);
		} catch (LongRunningQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentPage;
	}
	
}

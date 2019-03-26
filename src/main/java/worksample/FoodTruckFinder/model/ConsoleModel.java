package main.java.worksample.FoodTruckFinder.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import com.socrata.exceptions.LongRunningQueryException;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.service.FoodTruckFinderService;

public class ConsoleModel{
	
	public static final Integer INITIAL_OFFSET = 0;
	public static final Integer PAGE_SIZE = 10;
	public static final String TIME_ZONE = "America/Los_Angeles";
	
	private List<FoodTruckDTO> currentPage;
	private Calendar cal;

	private FoodTruckFinderService service = new FoodTruckFinderService();
	private Integer offset;
	private Integer pageSize;
	
	public ConsoleModel() {
		this.offset = INITIAL_OFFSET;
		this.pageSize = PAGE_SIZE;
		init();
	}

	private void init() {
		cal = new GregorianCalendar(TimeZone.getTimeZone(TIME_ZONE));
	}

	/*
	 * Gets next or last page of records
	 */
	public List<FoodTruckDTO> getPage(boolean getLastPage) throws LongRunningQueryException{

		if(getLastPage && offset > 0) {
			offset -= pageSize;
		}else if(currentPage != null && !getLastPage){
			offset += pageSize;
		}
		currentPage = service.getPage(offset, pageSize, cal);
		
		return currentPage;
	}
	
}

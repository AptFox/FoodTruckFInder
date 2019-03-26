package main.java.worksample.FoodTruckFinder.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.socrata.api.Soda2Consumer;
import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.exceptions.LongRunningQueryException;
import com.socrata.exceptions.SodaError;
import com.socrata.model.soql.CompositeExpression;
import com.socrata.model.soql.ConditionalExpression;
import com.socrata.model.soql.Expression;
import com.socrata.model.soql.OrderByClause;
import com.socrata.model.soql.SoqlQuery;
import com.socrata.model.soql.SortOrder;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;

public class FoodTruckFinderService {
	//Make constants file
	private static final String API_BASE_URL ="https://data.sfgov.org";
	private static final String API_RESOURCE_ID="bbb8-hzi6";
	private static final String API_SOQL_FOOD_TRUCK_NAME="applicant";
	private static final String API_SOQL_FOOD_TRUCK_ADDR="location";
	private static final String API_SOQL_WEEKDAY="dayorder";
	private static final String API_SOQL_OPENING_HOUR="start24";
	private static final String API_SOQL_CLOSING_HOUR="end24";
	private static final String CURRENT_DAY_EXPRESSION="dayorder=%s";
	private static final String STARTING_HOUR_EXPRESSION="start24<='%s:00'";
	private static final String ENDING_HOUR_EXPRESSION="end24>'%s:00'";
	private static final Integer API_SUNDAY_INT = 0;
	private static final Integer JAVA_SUNDAY_INT = 7;
	private Soda2Consumer consumer;
	
	public FoodTruckFinderService() {
		consumer = Soda2Consumer.newConsumer(API_BASE_URL);
	}

	private ConditionalExpression prepareExpression(String expression, String value) {
		String formattedQuery = String.format(expression, value);
		return new ConditionalExpression(formattedQuery);
	}

	public List<FoodTruckDTO> getPage(Integer offset, Integer pageSize, Calendar cal) throws LongRunningQueryException{
		List<FoodTruckDTO> foodTrucks = new ArrayList<FoodTruckDTO>(); 
		Integer currentHour = cal.get(Calendar.HOUR_OF_DAY); //24H format
		Integer currentDay = cal.get(Calendar.DAY_OF_WEEK) == JAVA_SUNDAY_INT ? API_SUNDAY_INT: cal.get(Calendar.DAY_OF_WEEK);
		
		try {
			Expression dayOrderEqualsCurrentDay = prepareExpression(CURRENT_DAY_EXPRESSION, currentDay.toString());
			Expression start24IsLessThanCurrentHour = prepareExpression(STARTING_HOUR_EXPRESSION, currentHour.toString());
			Expression end24IsGreaterThanCurrentHour = prepareExpression(ENDING_HOUR_EXPRESSION, currentHour.toString());
			List<Expression> expList = new ArrayList<Expression>();
			expList.add(dayOrderEqualsCurrentDay);
			expList.add(start24IsLessThanCurrentHour);
			expList.add(end24IsGreaterThanCurrentHour);
			Expression combinedWhereClause = new CompositeExpression(expList);
			SoqlQuery foodTrucksOpenNowQuery = new SoqlQueryBuilder()
			        .addSelectPhrase(API_SOQL_FOOD_TRUCK_NAME)
			        .addSelectPhrase(API_SOQL_FOOD_TRUCK_ADDR)
			        .addSelectPhrase(API_SOQL_WEEKDAY)
			        .addSelectPhrase(API_SOQL_OPENING_HOUR)
			        .addSelectPhrase(API_SOQL_CLOSING_HOUR)
			        .setWhereClause(combinedWhereClause)
			        .addOrderByPhrase(new OrderByClause(SortOrder.Ascending, API_SOQL_FOOD_TRUCK_NAME)) //sort remaining results alphabetically by name
			        .setLimit(pageSize)
			        .setOffset(offset)
			        .build();
		
			foodTrucks = consumer.query(API_RESOURCE_ID,
										foodTrucksOpenNowQuery,
								        FoodTruckDTO.LIST_TYPE);
		} 
		catch (SodaError e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 

		return foodTrucks;	
	}

}

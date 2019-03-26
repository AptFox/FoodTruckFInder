package main.java.worksample.FoodTruckFinder.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	private static final String API_BASE_URL ="https://data.sfgov.org";
	private static final String API_RESOURCE_ID="bbb8-hzi6";
	private static final String API_FILTER="?$query=SELECT %s,%s WHERE dayorder=%s AND start24<=%s AND end24>=%s ORDER BY %s LIMIT=%s OFFSET=%s";
	//private static final String API_FILTER="?$select=%s, %s$where=dayorder=%s&start24<=%s&end24>=%s&$order=%s&$limit=%s&$offset=%s";
	private static final int API_SUNDAY_INT = 0;
	private static final int JAVA_SUNDAY_INT = 7;
	private Soda2Consumer consumer;
	
	public FoodTruckFinderService() {
		consumer = Soda2Consumer.newConsumer(API_BASE_URL);
		
	}

	public List<FoodTruckDTO> getPage(Integer offset, Integer pageSize, Calendar cal) throws LongRunningQueryException{
		List<FoodTruckDTO> foodTrucks = new ArrayList<FoodTruckDTO>(); 
		int currentHour = cal.get(Calendar.HOUR_OF_DAY); //24H format
		int currentDay = cal.get(Calendar.DAY_OF_WEEK) == JAVA_SUNDAY_INT ? API_SUNDAY_INT: cal.get(Calendar.DAY_OF_WEEK);
		
		try {
			//rawString method
//			ObjectMapper mapper = new ObjectMapper();
//			String simpleFilter =String.format(API_FILTER,"applicant","location", currentDay, currentHour, currentHour, "applicant", pageSize, offset);
//			URL url = new URL(API_BASE_URL);
//			foodTrucks = mapper.readValue(url, new TypeReference<List<FoodTruckDTO>>(){});

			
//			Response response = consumer.query(API_RESOURCE_ID,
//          HttpLowLevel.JSON_TYPE,
//          foodTrucksOpenNowQuery);
//	
		//	FoodTruckDTO payload = response.readEntity(FoodTruckDTO.class);
		//	System.out.println(payload);
		//			
			
			//SODA-JAVA method
			Expression dayOrderEqualsCurrentDay = new ConditionalExpression("dayorder="+currentDay); 
			Expression start24IsLessThanCurrentHour = new ConditionalExpression("start24<='"+currentHour+":00'"); 
			Expression end24IsGreaterThanCurrentHour = new ConditionalExpression("end24>'"+currentHour+":00'");
			List<Expression> expList = new ArrayList<Expression>();
			expList.add(dayOrderEqualsCurrentDay);
			expList.add(start24IsLessThanCurrentHour);
			expList.add(end24IsGreaterThanCurrentHour);
			Expression combinedWhereClause = new CompositeExpression(expList);
//			"DayOrder="+currentDay+" AND "+"start24<="+currentHour+" AND "+"end24>="+currentHour;
			SoqlQuery foodTrucksOpenNowQuery = new SoqlQueryBuilder()
			        .addSelectPhrase("applicant") //TODO Make this a constant
			        .addSelectPhrase("location") //TODO Make this a constant
			        .addSelectPhrase("dayorder")
			        .addSelectPhrase("start24")
			        .addSelectPhrase("end24")
			        .setWhereClause(combinedWhereClause)
			        .addOrderByPhrase(new OrderByClause(SortOrder.Ascending, "applicant")) //sort remaining results alphabetically by name
			        .setLimit(pageSize)
			        .setOffset(offset)
			        .build();
			String rawQuery = "SELECT applicant,location, dayorder, start24, end24 WHERE dayorder=3 AND start24<='11:00' AND end24>'11:00'  ORDER BY applicant ASC OFFSET 0 LIMIT 10";
			//https://data.sfgov.org/resource/bbb8-hzi6?%24query=select+applicant%2Clocation+where+dayorder%3D3+AND+start24%3C%3D12+AND+end24%3E12+order+applicant+ASC+offset+0+limit+10&%24%24version=2.0
			
			//URL: https://data.sfgov.org/resource/bbb8-hzi6?%24query=select+applicant%2Clocation+where+dayorder%3D3+AND+start24%3C%3D12+AND+end24%3E12+order+applicant+ASC++offset+0+limit+10+
			//Consider modifying the raw query until it matches what's expected based on the API docs
//			
//			
			foodTrucks = consumer.query(API_RESOURCE_ID,
					foodTrucksOpenNowQuery,
			        FoodTruckDTO.LIST_TYPE);
//			
			
		} 
		catch (SodaError e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
//		catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return foodTrucks;	
	}

}

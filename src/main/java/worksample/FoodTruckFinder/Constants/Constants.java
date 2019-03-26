package main.java.worksample.FoodTruckFinder.Constants;

public final class Constants {
	
	public static class ViewConstants{
		//View constants
		public static final String NEXT_PAGE = "N";
		public static final String LAST_PAGE = "L";
		public static final String EXIT = "E";
		public static final String EXIT_MSG = "Now Exiting...";
		public static final String ADDRESS = "ADDRESS";
		public static final String NAME = "NAME";
		public static final String COMMAND_SIGNATURE = "\n Commands: %s = Next Page, %s = Last Page,  %s = EXIT";
		public static final String INVALID_INPUT = "\n INVALID INPUT. PLEASE TRY AGAIN.\n";
		public static final String HEADER = "\n San Francisco Food Trucks Open Now! \n";
		public static final String LEFT_PADDING_SIZE = "75";
		public static final String RIGHT_PADDING_SIZE = "20";
		public static final String CLEAR_CONSOLE = "\033\143";
		public static final String NO_FOOD_TRUCKS_FOUND = "Sorry, I've either shown you all the trucks already or none are open.";
	}
	
	public static class ModelConstants{
		public static final Integer INITIAL_OFFSET = 0;
		public static final Integer PAGE_SIZE = 10;
		public static final String TIME_ZONE = "America/Los_Angeles";
		
		//API Constants
		public static final String API_BASE_URL ="https://data.sfgov.org";
		public static final String API_RESOURCE_ID="bbb8-hzi6";
		public static final String API_SOQL_FOOD_TRUCK_NAME="applicant";
		public static final String API_SOQL_FOOD_TRUCK_ADDR="location";
		public static final String API_SOQL_WEEKDAY="dayorder";
		public static final String API_SOQL_OPENING_HOUR="start24";
		public static final String API_SOQL_CLOSING_HOUR="end24";
		public static final String CURRENT_DAY_EXPRESSION="dayorder=%s";
		public static final String STARTING_HOUR_EXPRESSION="start24<='%s:00'";
		public static final String ENDING_HOUR_EXPRESSION="end24>'%s:00'";
		public static final Integer API_SUNDAY_INT = 0;
		public static final Integer JAVA_SUNDAY_INT = 7;
	}
}

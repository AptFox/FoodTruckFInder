package main.java.worksample.FoodTruckFinder.view;

import java.util.List;
import java.util.Scanner;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.controller.ConsoleController;

public class ConsoleView {
	//TODO make constants file
	//TODO Add comments everywhere
	//TODO Add method headers everywhere
	private static final String NEXT_PAGE = "N";
	private static final String LAST_PAGE = "L";
	private static final String EXIT = "E";
	private static final String EXIT_MSG = "Now Exiting...";
	private static final String ADDRESS = "ADDRESS";
	private static final String NAME = "NAME";
	private static final String COMMAND_SIGNATURE = "\n Commands: %s = Next Page, %s = Last Page,  %s = EXIT";
	private static final String INVALID_INPUT = "\n INVALID INPUT. PLEASE TRY AGAIN.\n";
	private static final String HEADER = "\n San Francisco Food Trucks Open Now! \n";
	private static final String LEFT_PADDING_SIZE = "75";
	private static final String RIGHT_PADDING_SIZE = "20";
	private static final String CLEAR_CONSOLE = "\033\143";
	private static final String NO_FOOD_TRUCKS_FOUND = "Sorry, I've either shown you all the trucks already or none are open.";
	
	private ConsoleController controller;
	private Scanner scanner;
	private String command;
	private boolean inputIsValid = false;
	private boolean getLastPage = false;
	private List<FoodTruckDTO> records;
	
	public ConsoleView(ConsoleController controller) {
		scanner = new Scanner(System.in);
		this.controller = controller;
		records = controller.getPage(getLastPage);
	}
	
	public void start() {
		printPage(records);
		while(true) {
			promptUserUntilValidInputFound();
			executeCommand();
		}
	}

	private void promptUserUntilValidInputFound() {
		String commandsOutput = String.format(COMMAND_SIGNATURE, NEXT_PAGE, LAST_PAGE, EXIT);
		printToConsole(commandsOutput, true);
		while(!inputIsValid) {
			this.command = validateInput();
			if(!inputIsValid) {
				printToConsole(INVALID_INPUT, true);
			}
		}	
	}
	
	private void executeCommand() {
		switch (command) {
		case NEXT_PAGE:
			getLastPage = false;
			printPage(controller.getPage(getLastPage));
			break;
		case LAST_PAGE:
			getLastPage = true;
			printPage(controller.getPage(getLastPage));
			break;
		case EXIT :
			printToConsole(EXIT_MSG, true);
			controller.exit();
			break;
		}
		command = null;
		inputIsValid = false;
	}
	
	private String validateInput() {
		String input = scanner.nextLine();
		String validatedInput = null;
		this.inputIsValid  = input != null && 
								(input.equalsIgnoreCase(NEXT_PAGE) ||
								input.equalsIgnoreCase(LAST_PAGE) ||
								input.equalsIgnoreCase(EXIT));
		if(inputIsValid) {
			validatedInput = input.toUpperCase();
		}
		return validatedInput;
	}
	
	private void printToConsole(String msg, boolean printWholeLine) {
		if(printWholeLine) {
			System.out.println(msg);
		}else {
			System.out.print(msg);
		}
	}
	
	private String formatColumnRow(String leftHeader, String rightHeader) {
		String leftPadding = "%-"+LEFT_PADDING_SIZE+"s";
		String rightPadding = "%-"+RIGHT_PADDING_SIZE+"s";
		String formattedRow = String.format(leftPadding, leftHeader) + String.format(rightPadding, rightHeader); 
		return formattedRow;
	}
	
	private void printPage(List<FoodTruckDTO> records) {
		printToConsole(CLEAR_CONSOLE, false);
		printToConsole(HEADER, true);
		if(records == null || records.size() == 0) {
			printToConsole(NO_FOOD_TRUCKS_FOUND, true);
		}else {
			String formattedHeader = formatColumnRow(NAME, ADDRESS);
			printToConsole(formattedHeader, true);
			for(FoodTruckDTO foodTruck : records) {
				String row = formatColumnRow(foodTruck.getApplicant(),foodTruck.getLocation());
				printToConsole(row, true);
			}
		}
	}
	
}

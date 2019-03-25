package main.java.worksample.FoodTruckFinder.view;

import java.util.List;
import java.util.Scanner;

import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.controller.ConsoleController;

public class ConsoleView {
	private static final String NEXT_PAGE = "N";
	private static final String LAST_PAGE = "L";
	private static final String EXIT = "E";
	private static final String ADDRESS = "ADDRESS";
	private static final String NAME = "NAME";
	private static final String COMMAND_SIGNATURE = "Commands: %s = Next Page, %s = Last Page,  %s = EXIT";
	private static final String INVALID_INPUT = "\n INVALID INPUT. PLEASE TRY AGAIN.\n";
	private static final String HEADER = "\n San Francisco Food Trucks Open Now! \n";
	private static final String PADDING_SIZE = "27";
	
	private ConsoleController controller;
	private Scanner scanner;
	private String command;
	private boolean inputIsValid = false;
	private boolean getLastPage = false;
	
	public ConsoleView(ConsoleController controller) {
		scanner = new Scanner(System.in);
		this.controller = controller;
	}
	
	public void start() {
		printToConsole(HEADER);
		printPage(controller.getPage(getLastPage));
		while(true) {
			promptUserUntilValidInputFound();
			executeCommand();
		}
	}

	private void promptUserUntilValidInputFound() {
		String commandsOutput = String.format(COMMAND_SIGNATURE, NEXT_PAGE, LAST_PAGE, EXIT);
		printToConsole(commandsOutput);
		while(!inputIsValid) {
			listenForInput();
			if(!inputIsValid) {
				printToConsole(INVALID_INPUT);
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
			controller.exit();
			break;
		}
		
	}

	private void listenForInput() {
		String enteredText = scanner.nextLine();
		this.command = validateInput(enteredText);
	}
	
	private String validateInput(String input) {
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
	
	private void printToConsole(String msg) {
		System.out.println(msg);
	}
	
	private String formatColumnRow(String leftHeader, String rightHeader) {
		String leftPadding = "%-"+PADDING_SIZE+"s";
		String rightPadding = "%"+PADDING_SIZE+"s";
		String formattedRow = String.format(leftPadding, leftHeader) + String.format(rightPadding, rightHeader); 
		return formattedRow;
	}
	
	private void printPage(List<FoodTruckDTO> records) {
		String formattedHeader = formatColumnRow(NAME, ADDRESS);
		printToConsole(formattedHeader);
		for(FoodTruckDTO foodTruck : records) {
			String row = formatColumnRow(foodTruck.getName(),foodTruck.getAddress());
			printToConsole(row);
		}
	}
}

package main.java.worksample.FoodTruckFinder.view;

import java.util.List;
import java.util.Scanner;
import main.java.worksample.FoodTruckFinder.DTO.FoodTruckDTO;
import main.java.worksample.FoodTruckFinder.controller.ConsoleController;
import static main.java.worksample.FoodTruckFinder.Constants.ConsoleConstants.ViewConstants.*;

public class ConsoleView {
	
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

	/**
	 * Repeatedly prompts the user for input.
	 * Outputs an error message if invalid input is entered.
	 */
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
	
	/**
	 * Executes the command entered by the user
	 */
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
	
	/**
	 * Checks if user input is equal to NEXT_PAGE, LAST_PAGE or EXIT commands
	 * 
	 * @return validated input string
	 */
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
	
	/**
	 * Prints the supplied string to the console. 
	 * 
	 * @param msg              The message to be printed
	 * @param printWholeLine   A boolean signifying if the message should consume an entire line
	 */
	private void printToConsole(String msg, boolean printWholeLine) {
		if(printWholeLine) {
			System.out.println(msg);
		}else {
			System.out.print(msg);
		}
	}
	
	/**
	 * Prints the supplied values to the console and pads them to based on the constants file 
	 * 
	 * @param leftValue    
	 * @param rightValue   
	 * @return
	 */
	private String formatColumnRow(String leftValue, String rightValue) {
		String leftPadding = "%-"+LEFT_PADDING_SIZE+"s";
		String rightPadding = "%-"+RIGHT_PADDING_SIZE+"s";
		String formattedRow = String.format(leftPadding, leftValue) + String.format(rightPadding, rightValue); 
		return formattedRow;
	}
	
	
	/**
	 * Clears the console and prints the supplied set of records
	 * 
	 * @param records  A list of foodTruck records.
	 */
	private void printPage(List<FoodTruckDTO> records) {
		printToConsole(CLEAR_CONSOLE, false);
		printToConsole(HEADER, true);
		if(records == null || records.size() == 0) {
			printToConsole(NO_FOOD_TRUCKS_FOUND, true);
		}else {
			String formattedHeader = formatColumnRow(NAME, ADDRESS);
			printToConsole(formattedHeader, true);
			for(FoodTruckDTO foodTruck : records) {
				String row = formatColumnRow(foodTruck.getName(),foodTruck.getAddress());
				printToConsole(row, true);
			}
		}
	}
	
}

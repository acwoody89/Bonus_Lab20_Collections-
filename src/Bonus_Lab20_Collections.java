import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bonus_Lab20_Collections {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// My hashmap items and cost
		HashMap<String, Double> items = new HashMap<>();
		items.put("black coffee", 9.99);
		items.put("almond milk", 2.99);
		items.put("pikachu mug", 5.00);
		items.put("flippity flops", 12.99);
		items.put("zombie brains", .99);
		items.put("pokemon ball", 200.00);
		items.put("evil toaster", 6.66);
		items.put("kirby fan", 15.99);

		int quant = 0;
		String inputChoice = "y";
		boolean itemExists = false;
		// my parallel arrays for items chosen by the user
		ArrayList<String> itemName = new ArrayList<>();
		ArrayList<Double> itemCost = new ArrayList<>();
		ArrayList<Integer> itemQuantity = new ArrayList<>();

		System.out.println("Welcome to the Hectic Eclectic Marketplace");
		System.out.println("**********************************************");
		
		do {
			printMenu(items); // prints out the item list
			itemExists = false; // resets itemExists to false
			while (!itemExists) {
				System.out.println("Please enter in an item name...");
				inputChoice = scan.nextLine().toLowerCase();
				inputChoice = menuOption(inputChoice);
				
				if (items.containsKey(inputChoice)) {
					itemExists = true;
					
					if (!itemName.contains(inputChoice)) {
						
						itemName.add(inputChoice); 				// adds their selection to the name arraylist
						itemCost.add(items.get(inputChoice));	// adds the price of the item to cost arraylist
						quant = Validator.getInt(scan, "How many...? 1-10 ", 1, 10); // min max quantity validation
						itemQuantity.add(quant); 				// adds their quantity selection to quantity arraylist
						
					} else if (itemName.contains(inputChoice)) {
						
						quant = Validator.getInt(scan, "How many...? 1-10 ", 1, 10);
						// this adds the new quantity onto the old quantity
						// allows them to stack essential
						itemQuantity.set(itemName.indexOf(inputChoice), 
								quant + itemQuantity.get(itemName.indexOf(inputChoice))); 
						
					}
					System.out.println("You have chosen: [" + inputChoice + "], with a cost of [$"
							+ items.get(inputChoice) + "], Price X Quantity: $" + (items.get(inputChoice) * quant));
					System.out.println("**********************************************");
				} else {

					printMenu(items);
					System.out.println("That item does not exist...");
				}

			}
			scan.nextLine(); // garbage
			System.out.println("Would you like to order anything else? y/n...");
			inputChoice = scan.nextLine();

		} while (inputChoice.equalsIgnoreCase("y"));

		System.out.println("Your shopping cart includes...");
		System.out.printf("%-15s %-15s %s%n", "Items", "Cost", "Quantity");
		System.out.println("**********************************************");
		
		// prints out everything they have selected, name, price, quantity
		for (int i = 0; i < itemCost.size(); i++) {
			System.out.printf("%-15s %-15.2f %d%n", itemName.get(i), itemCost.get(i), itemQuantity.get(i));
		}
		System.out.println("**********************************************");

		System.out.print("Your average item cost is: $"); // connected
		avgItemsCost(itemCost);							  // connected
		indexHigh(itemCost);
		indexLow(itemCost);
		highLowItems(itemName, itemCost);
		
		double total = 0.0;
		// gets the total cart amount
		for (int i = 0; i < itemCost.size(); i++) {
			total += itemCost.get(i) * itemQuantity.get(i);
		}
		System.out.println("The total purchase amount is $" + total);
		System.out.println("****************************************");
		System.out.println("The Hectic Eclectic bids you farewell.");
		System.out.println("****************************************");

	}

	/*
	 * finds the avg price of the items ordered
	 */
	public static void avgItemsCost(ArrayList<Double> itemsCost) {
		double average = 0.0;
		double sum = 0.0;

		for (Double num : itemsCost) {
			sum += num;
		}
		average = sum / itemsCost.size();

		System.out.printf("%.2f%n", average);
	}

	/*
	 * quick way to print the menu passes in the hashMap i created
	 */
	public static void printMenu(HashMap<String, Double> items) {
		System.out.printf("%-30s %-25s%n", "Items", "Cost");
		System.out.println("****************************************");
		int count = 1;
		for (String variableName : items.keySet()) {

			String variableKey = variableName;
			Double variableValue = items.get(variableName);

			System.out.printf("%-15s %13.2f%n", variableKey + " \t Code:" + count, variableValue);
			count++;
		}
		System.out.println("****************************************");
	}

	public static void indexHigh(ArrayList<Double> itemsCost) {
		double high = 0;
		int index = 0;
		for (double num : itemsCost) {
			if (num > high) {
				high = num;
				index = itemsCost.indexOf(num);
			}
		}

		System.out.println("The highest cost item was the $" + high + " at index: " + index);

	}

	public static void indexLow(ArrayList<Double> itemCost) {
		double low = itemCost.get(0);
		int index = 0;
		for (double num : itemCost) {
			if (low >= num) {
				low = num;
				index = itemCost.indexOf(num);
			}
		}

		System.out.println("The lowest cost item was $" + low + " at index: " + index);
	}

	public static void highLowItems(ArrayList<String> itemName, ArrayList<Double> itemCost) {
		double low = itemCost.get(0);
		int index = 0;
		for (double num : itemCost) {
			if (low >= num) {
				low = num;
				index = itemCost.indexOf(num);
			}
		}

		double high = 0;
		int index2 = 0;
		for (double num : itemCost) {
			if (num > high) {
				high = num;
				index2 = itemCost.indexOf(num);
			}
			
		}

		System.out.println("The most expensive item you selected was " 
				+ itemName.get(index2) + ", at $" + itemCost.get(index2));
		
		System.out.println("The least expensive item you selected was " 
				+ itemName.get(index) + ", at $" + itemCost.get(index));
	}

	/*
	 * I have no idea if this will work by the time it gets to you
	 * this is how it was printing out to the screen at time of completion
	 * thats how i mapped it to a switch statement
	 */
	public static String menuOption(String inputChoice) {
		switch (inputChoice) {
		case "pokemon ball":
		case "1":
			inputChoice = "pokemon ball";
			break;
		case "zombie brains":
		case "2":
			inputChoice = "zombie brains";
			break;

		case "black coffee":
		case "3":
			inputChoice = "black coffee";
			break;

		case "evil toaster":
		case "4":
			inputChoice = "evil toaster";
			break;

		case "almond milk":
		case "5":
			inputChoice = "almond milk";
			break;

		case "pikachu mug ":
		case "6":
			inputChoice = "pikachu mug ";
			break;

		case "kirby fan":
		case "7":
			inputChoice = "kirby fan";
			break;

		case "flippity flops":
		case "8":
			inputChoice = "flippity flops";
			break;
		default:
		}

		return inputChoice;
	}

}

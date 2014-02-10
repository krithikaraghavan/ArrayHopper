package practice.problems;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class ArrayHopper {
	
	Integer[] inputArray;
	
	/**
	 * prompt user to enter the path to the input file name
	 */
	public void readFileName(){

		System.out.println("\nEnter the complete path to the file containing the input data");
		Scanner scan = new Scanner(System.in);
		String filePath = scan.nextLine();
		try{
			this.readInputFromFile(filePath);
		}
		catch(FileNotFoundException fnfe){
			
			System.out.println("Sorry, couldnt find the input file. Please enter a valid path");
			this.readFileName();
			
		}
		catch(NumberFormatException nfe){
			System.out.println("There was an error reading the input file as the input numbers were not in right format\n" +
					"Please enter valid integer numbers - one per line in the input file");	
		}
	}
	
	
	/**
	 * reads input from file and convert it to the Integer array object
	 * @param filePath - path to the file given via the console prompt
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 */
	public void readInputFromFile(String filePath) throws FileNotFoundException, NumberFormatException{
		String line = "";
		int value;
		ArrayList<Integer> inArray;
		
		Scanner scan = new Scanner(new FileReader(filePath));
		inArray = new ArrayList<Integer>();
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			value = Integer.parseInt(line);
			inArray.add(value);
		}	
		scan.close();
		this.inputArray = new Integer[inArray.size()];
		inArray.toArray(this.inputArray);
		
	}
	
	/**
	 * Find the minimum number of hops required to traverse the array
	 * This is a simple algorithm to find the min no of hops required to traverse an array 
	 * The algorithm stores the intermediate traversals by storing the (sum of value at the array index + array Index)
	 * This Algorithm then finds the best of such a next hop by looping through the array from (current index to current index + value @ current index)
	 * Continues until the end of array or until the next hop has gone beyond the last array index 
	 * @param inArray - input array
	 * @return String of hops of the indices or "failure" if no such path is found
	 */
	
	public String findMinNoOfHops(Integer[] inArray) {

		if (inArray == null) {
			throw new IllegalArgumentException("No input array");
		} else if (inArray.length == 0) {
			throw new IllegalArgumentException("Input array is empty");
		}

		int[] interNextHop = new int[inArray.length];
		// calculate intermediate next hops
		for (int i = 0; i < inArray.length; i++) {
			interNextHop[i] = i + inArray[i];
		}

		StringBuilder hopsResult = new StringBuilder();
		String comma = new String(", ");
		int current = 0;
		hopsResult.append(current).append(comma);
		// Start searching for best next hop from position 0
		for (int i = 0; i < inArray.length; i++) {
			int arrayValue = inArray[current];
			int balanceToTraverse = inArray.length - 1 - current;

			if (arrayValue == balanceToTraverse) {
				hopsResult.append(inArray.length - 1).append(comma);
				current = inArray.length - 1;

			} else if (arrayValue < balanceToTraverse) {
				int bestNextHop = current;
				// check for the best next hop from current to (current + value)
				for (int j = current; j <= current + arrayValue; j++) {
					if (interNextHop[j] > interNextHop[bestNextHop]) {
						bestNextHop = j;
					}
				}
				// if search is unsuccessful then return failure
				if (bestNextHop == current) {
					hopsResult = new StringBuilder("failure\n");
					break;
				}
				hopsResult.append(bestNextHop).append(comma);
				current = bestNextHop;
			} else {
				hopsResult.append("out");
				break;
			}
		}
		System.out.println(hopsResult.toString());
		return hopsResult.toString();

	}
	
	public static void main(String args[]){
		ArrayHopper h = new ArrayHopper();
		h.readFileName();
		h.findMinNoOfHops(h.inputArray);
	}
	}



package stockDataAnalysis;

/**
 * This is to read the CSV file for the Hong Kong market
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderHK {
	
	// Read short outstanding file
	public ArrayList<StockItem> readFile1(String fileName) throws FileNotFoundException{
		
		ArrayList<StockItem> result = new ArrayList<>();
		File file = new File(fileName);
		Scanner in = new Scanner(file);
		
		in.nextLine();
		
		String line;
		while (in.hasNextLine()) {
			line = in.nextLine();
			
			String[] parts = line.split(",");
			//System.out.println(parts);
			String date = parts[0];
			//System.out.println(date);
			String stockCode = parts[1];
			//System.out.println(stockCode);
			String stockName = parts[2];
			//System.out.println(stockName);
			int shortQty = Integer.parseInt(parts[3]);
			//System.out.println(shortQty);
			double outShares = 0;
			
			StockItem item = new StockItem(date, stockCode, stockName,
					shortQty, outShares);
			
			result.add(item);
		}
		return result; 
	}
	
	// Read shares outstanding file
	public ArrayList<StockItem> readFile2(String fileName) throws FileNotFoundException{
		
		ArrayList<StockItem> result = new ArrayList<>();
		File file = new File(fileName);
		Scanner in = new Scanner(file);
		
		in.nextLine();
		
		String line;
		while (in.hasNextLine()) {
			line = in.nextLine();
			
			String[] parts = line.split(",");
			//System.out.println(parts);
			String stockCode = parts[0];
			//System.out.println(stockCode);
			String stockName = parts[1];
			//System.out.println(stockName);
			double outShares = Double.parseDouble(parts[2]);
			//System.out.println(shortQty);
			
			StockItem item = new StockItem(stockCode, stockName, outShares);
			
			result.add(item);
		}
		return result; 
	}
}
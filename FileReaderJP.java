package stockDataAnalysis;

/**
 * This is to read the CSV file for the Japan market
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderJP {

	public ArrayList<StockItem> readFile(String fileName) throws FileNotFoundException{
		
		ArrayList<StockItem> result = new ArrayList<>();
		File file = new File(fileName);
		Scanner in = new Scanner(file);
		
		int val = 0;
		
		for (int i = 0; i < 11; i++) {
		in.nextLine();
		}
		
		String line;
		while (in.hasNextLine()) {
			line = in.nextLine();
			//val = Integer.parseInt(line.trim());
			//line = line.replaceAll("\\uFEFF", "");
			
			String[] parts = line.split(",");
			//System.out.println(parts);
			
			String date = parts[1];
			System.out.println(date);
			
			String stockCode = parts[2];
			String stockName = parts[4];
			double shortRatio = Double.parseDouble(parts[10]);
			double shortQty = 0;
			
			/*
			if (!parts[11].isEmpty()) {
				shortQty = Double.parseDouble(parts[11]);
				System.out.println(shortQty);
			}
			else { 
				shortQty = 0;
			}
			*/
			
			//int shortAmt = 0;
			
			//int aggShortQty = 0; // Aggregated Short Positions (Shares)
			//int aggShortAmt = 0; // Aggregated Short Positions (HK$ or JPY)
			
			//StockItem item = new StockItem(date, stockCode, stockName, 
			//		 shortQty, shortAmt, shortRatio, aggShortQty, aggShortAmt);
			
			StockItem item = new StockItem(date, stockCode, stockName, 
					 shortQty, shortRatio);
			
			result.add(item);
		}
		return result; 
	}
}
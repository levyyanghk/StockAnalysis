package stockDataAnalysis;

import java.util.ArrayList;
import java.util.HashMap;

public class DataCalculation {

	public HashMap<String, Double> getShortRatio(ArrayList<StockItem> stockItemsHK, ArrayList<StockItem> outShares) {
		
		// Create a new ArrayList that combines the 1. short position and 2. shares outstanding files 	
		ArrayList<StockItem> updatedItems = new ArrayList<>();
		// updatedItems consists of a series of updatedItem, which is a single line in a file
		StockItem updatedItem = null;  
		
		// ArrayList of item1 indicates a single row in the HK Short Position file
		// Add outstanding shares to item1 by creating a new ArrayList
		// Last parameter, (item1.getShortQty() / item2.getOutShares()), is the short ratio
		for (StockItem item1: stockItemsHK) {	
			for (StockItem item2: outShares) {
					if (item1.getStockCode().equals(item2.getStockCode())) {
						updatedItem = new StockItem(item1.getDate(),item1.getStockCode(),
						item1.getStockName(), item1.getShortQty(), item2.getOutShares(), (item1.getShortQty() / item2.getOutShares()));
					}
			}
			updatedItems.add(updatedItem);
		}
		
		//System.out.println(updatedItems);
		
		// Create a Hashmap pair of <Ticker, Short Ratio> for sorting
		// This a because the updatedItems Arraylist can't be easily sorted with the built in sort() method.
		// The elements that consist of various element are not comparable.
		HashMap<String, Double> res = new HashMap<>(); 
		
		for(StockItem item: updatedItems) {
			res.put(item.getStockCode(), item.getShortRatio());
		}
		
		// Get the pair of <Ticker, Short Ratio> that has the max short ratio
		HashMap<String, Double> topRes = new HashMap<>();
		topRes = getMaxPair(res);
		
		return topRes;
	}
	
	// Create a HashMap pair for the max short ratio
	public HashMap<String, Double> getMaxPair(HashMap<String, Double> items) {
		
		HashMap<String, Double> topRes = new HashMap<>();
		String maxItem = null;
		double maxValue = 0;
		
		for(String item: items.keySet()) {	
			if (items.get(item) > maxValue) {
				maxValue = items.get(item);
				maxItem = item;	 
			}
		}
		topRes.put(maxItem, maxValue);
		return topRes;
	}
}
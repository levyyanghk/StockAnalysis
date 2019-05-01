package stockDataAnalysis;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class stockDataAnalysis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub

		//Example for getting data from Japan stock market
		IGetStockData Japan = new JapanStock();
		
		//Save date in current path + /JP
		Path currentRelativePath = Paths.get("");
		String FilePath = currentRelativePath.toAbsolutePath().toString();
		FilePath = FilePath + "/JP";
		String date = "20190324";
		String FileName = date +  "_Short_Positions.xls";
		Japan.getShortPositions(date, FilePath, FileName);
		
		//Example for getting data from HongKong stock market
		IGetStockData HongKong = new HongKongStock();
		FilePath = currentRelativePath.toAbsolutePath().toString();
		FilePath = FilePath + "/HK";
		date = "20190415";
		FileName = date + "Short_Position_Reporting_Aggregated_Data_" + date + ".csv";
		HongKong.getShortPositions(date, FilePath, FileName);
		
		// * Calculate a short ratio for HK *
		// 1. read SS file for HK
		// 2. read outstanding file for HK
		// 3. #1 and #2 will be passed on as a parameter to get the max short ratio 
		FileReaderJP frJP = new FileReaderJP(); // Read file for Japan and store data in an ArrayMap(incomplete)
		FileReaderHK frHK = new FileReaderHK(); // Read file for Hong Kong and store data in an ArrayMap
		DataCalculation dc = new DataCalculation();  // Max short ratio calculation 
		HashMap<String, Double> topShortRatio = new HashMap<>();
		
		try {	
			//ArrayList<StockItem> resJP = frJP.readFile("20190426_Short_Positions.csv");
			ArrayList<StockItem> resHK = frHK.readFile1("Short_Position_Reporting_Aggregated_Data_20190418.csv");
			ArrayList<StockItem> resHKOut = frHK.readFile2("HK_outstanding_shares.csv");
			
			topShortRatio = dc.getShortRatio(resHK,resHKOut);
			
			// Iterating over keys 
			for (String key : topShortRatio.keySet()) {
			    System.out.println("Ticker with Higest Short Ratio" + " as of " + date + " = " + key + " HK");
			}

			// Iterating over values
			for (Double value : topShortRatio.values()) {
			    System.out.println("Highest Short Ratio = " + value);
			}	
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}

}

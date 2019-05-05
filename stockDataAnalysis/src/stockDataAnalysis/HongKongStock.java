package stockDataAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class HongKongStock implements IGetStockData {

	public final static String baseUrl =  "https://www.sfc.hk/web/EN/pdf/spr/";


	/* (non-Javadoc)
	 * @see stockDataAnalysis.IGetStockData#getShortPositions(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	//HongKong Market only provide the data on every Friday
	//If the date is not Friday, this function will return the data of last friday
	
	public ArrayList <StockItem> getShortPositions(String date) {
	
		
		//Get Year, Month and Day
		int year  = Integer.parseInt(date.subSequence(0, 4).toString());
		int month = Integer.parseInt(date.subSequence(4, 6).toString());
		int day   = Integer.parseInt(date.subSequence(6, 8).toString());
		
		Calendar calendar = new GregorianCalendar();
		
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1); //January is 0 not 1
		calendar.set(Calendar.DAY_OF_MONTH, day);
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); //Sunday is 1 and Saturday is 7
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		
		
		if (dayOfWeek != 6 )
		{
			System.out.println("The date is not friday, get the data of Last Friday's");
			calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear - 1);
			calendar.set(Calendar.DAY_OF_WEEK, 6);
			
			year  = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH) + 1;
			day   = calendar.get(Calendar.DAY_OF_MONTH);
			
			System.out.println("Year " + year + " Month " + month + " Day " + day);						
		}
		String strMonth;
		String strDay;
		
		if (month < 10) {
			strMonth = "0" + month;
		} else {
			strMonth = Integer.toString(month);
		}
		
		if (day <10 ) {
			strDay = "0" + day;
		} else {
			strDay = Integer.toString(day);
		}
		
		String url = baseUrl + year + "/" + strMonth + "/" + strDay + "/"
				     + "Short_Position_Reporting_Aggregated_Data_" + year + strMonth + strDay + ".csv";
		
		//Save webpage in current path
		Path currentRelativePath = Paths.get("");
		String filePath = currentRelativePath.toAbsolutePath().toString();
		filePath = filePath + "/temp";
		String fileName = "HK.csv";
			
		if (StockDataDownload.HttpDownloadFile(url, filePath, fileName) == false) {
			System.out.println(url + " is not avaliable");
			return null;
		}
		
		ArrayList<StockItem> stockList;
		stockList = readFile1(filePath + "/" + fileName);
		
		ArrayList<StockItem> shareList;
		

		filePath = currentRelativePath.toAbsolutePath().toString();
		fileName = filePath + "/HK/" + "HK_outstanding_shares.csv";
		shareList = readFile2(fileName);
		
		return (getShortRatio(stockList, shareList));

	
	}
	
	// Read short outstanding file
		public ArrayList<StockItem> readFile1(String fileName){
			
			ArrayList<StockItem> result = new ArrayList<>();
			File file = new File(fileName);
			Scanner in;
			try {
				in = new Scanner(file);
			
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
				in.close();
				return result; 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		// Read shares outstanding file
		public ArrayList<StockItem> readFile2(String fileName){
			
			ArrayList<StockItem> result = new ArrayList<>();
			File file = new File(fileName);
			Scanner in;
			try {
				in = new Scanner(file);
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
					//System.out.println(outShares);
			
					StockItem item = new StockItem(stockCode, stockName, outShares);
				
					result.add(item);
				}
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result; 
		}
		
		public ArrayList <StockItem> getShortRatio(ArrayList<StockItem> stockItemsHK, ArrayList<StockItem> outShares) {
			
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

			return updatedItems;
		}

}

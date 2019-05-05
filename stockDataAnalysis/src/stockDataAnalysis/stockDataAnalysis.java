package stockDataAnalysis;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class stockDataAnalysis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub

		//Example for getting data from Japan stock market
		IGetStockData Japan = new JapanStock();
		
		ArrayList <StockItem> japanStockList;

		String date = "20190319";

		japanStockList = Japan.getShortPositions(date);
		
		if (japanStockList != null) {
			for (StockItem stockItem : japanStockList) {
				System.out.println("date " + stockItem.date + " code " + stockItem.stockCode + " shortRatio " + stockItem.shortRatio);
			}
		}
	/*	
		//Example for getting data from HongKong stock market
		IGetStockData HongKong = new HongKongStock();
		FilePath = currentRelativePath.toAbsolutePath().toString();
		FilePath = FilePath + "/HK";
		date = "20190415";
		FileName = date + "Short_Position_Reporting_Aggregated_Data_" + date + ".csv";
		HongKong.getShortPositions(date);
	*/

	}

}

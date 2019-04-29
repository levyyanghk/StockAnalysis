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
		

	}

}

package stockDataAnalysis;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class stockDataAnalysis {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub

		//Example for getting data from Japan stock market
		IGetStockData Japan = new JapanStock();
		
		ArrayList <StockItem> japanStockList;

		String date = "20190319";

		japanStockList = Japan.getShortPositions(date);

		//Sort the stock list based on short ratio
        ComparaShortRatio com = new ComparaShortRatio();
        Collections.sort(japanStockList,com);

	/*	
		if (japanStockList != null) {
			for (StockItem stockItem : japanStockList) {
				System.out.println("date " + stockItem.date + " code " + stockItem.stockCode + " shortRatio " + stockItem.shortRatio);
			}
		}
  */
	
		//Example for getting data from HongKong stock market
		IGetStockData HongKong = new HongKongStock();
		ArrayList <StockItem> hkStockList;

		date = "20190415";
		
		hkStockList = HongKong.getShortPositions(date);
		
        com = new ComparaShortRatio();
        Collections.sort(hkStockList,com);
        
        for (StockItem item:hkStockList) {
        	String ratio = String.format("%.2f", item.shortRatio);
        	item.shortRatio = Double.parseDouble(ratio);
        }

   /*     
		if (hkStockList != null) {
			for (StockItem stockItem : hkStockList) {
				System.out.println("date " + stockItem.date + " code " + stockItem.stockCode + " shortRatio " + stockItem.shortRatio);
			}
		}
	*/
        int top_n = 5;
        System.out.println("******************* TOP " + top_n + " short share lists *******************");
        System.out.println("*****Japan Market*****  " + "  ***** HongKong Market *****");
        System.out.println("    Code " + "   Short Ratio" + "    Code" + "     Short Ratio");
        for (int i=0; i <top_n; i++) {
        	System.out.println("    " +japanStockList.get(i).stockCode + "       " + japanStockList.get(i).shortRatio
        			+ "       " + hkStockList.get(i).stockCode + "      " + hkStockList.get(i).shortRatio);
        }
	

	}

}

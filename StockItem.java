package stockDataAnalysis;

/**
 * This is the Parent class for each stock item
 */
public class StockItem {
	
	String date;
	String stockCode;
	String stockName;
	double shortQty;
	//int shortAmt;
	
	String shortRatio; // Ratio: short position (Quantity) / stock outstanding
	double outShares; // Outstanding shares
	
	//int aggShortQty; // Aggregated Short Positions (Shares)
	//int aggShortAmt; // Aggregated Short Positions (HK$ or JPY)
	
	/*
	public StockItem(String date, String stockCode, String stockName, int shortQty, int shortAmt, String shortRatio, int aggShortQty, int aggShortAmt) {
		this.stockCode = stockCode;
		this.shortQty = shortQty;
		this.shortAmt = shortAmt;
		this.shortRatio = shortRatio;
		this.aggShortQty = aggShortQty;
		this.aggShortAmt = aggShortAmt;	
	}
	*/
	
	public StockItem(String date, String stockCode, String stockName, double shortQty, String shortRatio) {
		this.stockCode = stockCode;
		this.shortQty = shortQty;	
		this.shortRatio = shortRatio;
	}
	
	public StockItem(String date, String stockCode, String stockName, double shortQty) {
		this.date = date;
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.shortQty = shortQty;	
	}
	
	public StockItem(String stockCode, String stockName, double outShares) {
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.outShares = outShares;	
	}
}
package stockDataAnalysis;

/**
 * This is the Parent class for each stock item
 */
public class StockItem {
	
	public String date;
	public String stockCode;
	public String stockName;
	public double shortQty;
	
	public double shortRatio; // Ratio: short position (Quantity) / stock outstanding 
	public double outShares; // Outstanding shares
	
	/*
	// For Japan
	public StockItem(String date, String stockCode, String stockName, double shortQty, double shortRatio) {
		this.stockCode = stockCode;
		this.shortQty = shortQty;	
		this.shortRatio = shortRatio;
	}
	*/
	
	// For HK
	public StockItem(String date, String stockCode, String stockName, double shortQty, double outShares) {
		this.date = date;
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.shortQty = shortQty;	
		this.outShares = outShares;
	}
	
	// For the outstanding listed shares for HK
	public StockItem(String stockCode, String stockName, double outShares) {
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.outShares = outShares;	
	}
	
	// For HK - updated item with short ratio added
	public StockItem(String date, String stockCode, String stockName, double shortQty, double outShares, double shortRatio) {
		this.date = date;
		this.stockCode = stockCode;
		this.stockName = stockName;
		this.shortQty = shortQty;	
		this.outShares = outShares;
		this.shortRatio = shortRatio;
	}
	
	public StockItem() {
		// TODO Auto-generated constructor stub
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getShortQty() {
		return shortQty;
	}

	public void setShortQty(double shortQty) {
		this.shortQty = shortQty;
	}

	public Double getShortRatio() {
		return shortRatio;
	}

	public void setShortRatio(double shortRatio) {
		this.shortRatio = shortRatio;
	}

	public double getOutShares() {
		return outShares;
	}

	public void setOutShares(double outShares) {
		this.outShares = outShares;
	}
	
	
}
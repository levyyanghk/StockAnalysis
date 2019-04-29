package stockDataAnalysis;


public interface IGetStockData {
	//@Date: YYYYMMDD, it should be local time of specified stock market
	//@filePath: Absolute path name for stock output data
	//@fileName: File name for stock output data
	public boolean getShortPositions(String date, String filePath, String fileName);

}

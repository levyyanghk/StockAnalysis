package stockDataAnalysis;


public interface IGetStockData {

	/**
	 * @param date : YYYYMMDD, it should be local time of specified stock market
	 * @param filePath : Absolute path name for stock output data
	 * @param fileName : File name for stock output data
	 * @return
	 */
	public boolean getShortPositions(String date, String filePath, String fileName);

}

package stockDataAnalysis;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HongKongStock implements IGetStockData {

	public final static String baseUrl =  "https://www.sfc.hk/web/EN/pdf/spr/";


	/* (non-Javadoc)
	 * @see stockDataAnalysis.IGetStockData#getShortPositions(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	//HongKong Market only provide the data on every Friday
	//If the date is not Friday, this function will return the data of last friday
	
	public boolean getShortPositions(String date, String filePath, String fileName) {
	
		
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
			
		if (StockDataDownload.HttpDownloadFile(url, filePath, fileName) == false) {
			System.out.println(url + " is not avaliable");
			return false;
		}
		
		return true;
	
	}

}

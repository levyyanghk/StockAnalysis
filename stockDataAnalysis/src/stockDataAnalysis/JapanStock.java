package stockDataAnalysis;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.*;
import java.time.*;
import java.nio.file.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JapanStock implements IGetStockData {
	
	public static final String jpx_url = "https://www.jpx.co.jp";
	public static final String jpx_short_current = "/english/markets/public/short-selling/index.html";
	public static final String jpx_short_archived_base = "/english/markets/public/short-selling/";
	public String jpx_short_archived_url;

	@Override
	public boolean getShortPositions(String date, String filePath, String fileName) {
		String urlBase;
		urlBase = getStockBaseUrl(date);
		if (urlBase == null) {
			System.out.println("Could not get short positions");
			return false;
		}
		
		//Save webpage in current path
		Path currentRelativePath = Paths.get("");
		String httpFilePath = currentRelativePath.toAbsolutePath().toString();
		httpFilePath = httpFilePath + "/temp";
		String httpFileName = "temp.xml";
		
		if (StockDataDownload.HttpDownloadPage(urlBase, httpFilePath, httpFileName) == false) {
			System.out.println("Could not get the webpage from " + urlBase);
			return false;
		}
		
		String url;
		url = GetShortPositionUrl(httpFilePath, httpFileName, date);
		if (url != null)
			StockDataDownload.HttpDownloadFile(url, filePath, fileName);
		else
			return false;
		return true;
	}
	
	//Get the time difference in month between input date to current Japan local time
	//Input: YYYYMMDD, it should be Japan local time
	//Return: date difference in month, the value will be -1 if it is bigger than 12 or input date is later than
	//current time.
	public int getDateDifference(String date) {
		//Get current Japan local time
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
		int currentYear = zdt.getYear();
		int currentMonth = zdt.getMonthValue();
		
		//Compare with the specified date from input argument
		int inputYear = Integer.parseInt(date.subSequence(0, 4).toString());
		int inputMonth = Integer.parseInt(date.subSequence(4, 6).toString());
		
		System.out.println("local Year " + currentYear + " local month " + currentMonth
				+ " inputYear " + inputYear + " inputMonth " + inputMonth);
		
		int currentTime = currentYear*12 + currentMonth;
		int inputTime = inputYear*12 + inputMonth;
		
		if (inputTime > currentTime) {
			System.out.println("Error, the date is later than current time");
			return -1;
		} else {
			if ((currentTime - inputTime) > 12) {
				System.out.println("Error, could not get the data of 12 month's eariler");
				return -1;
			} else {
				return currentTime - inputTime;
			}
		}
		
	}
	
	public String getStockBaseUrl(String date) {
		int diff = getDateDifference(date);
		String day;
		String url = null;
		if (diff == -1)
		{
			System.out.println("Could not get the URL");
			return url;
		}
		
		if (diff == 0) {
			url = jpx_url + jpx_short_current;
		} else {
			if (diff < 10) {
				day = "0" + Integer.toString(diff);
			} else {
				day = Integer.toString(diff);
			}
			url = jpx_url + jpx_short_archived_base + "00-archives-" + day + ".html";
		}
		System.out.println("URL is: " + url);
		return url;
	}
	//Parse the webpage download from https://www.jpx.co.jp
	// Input: webpage's path and name, which day's date will be returned
	// Date's format is "YYYYMMDD", it should be Japan local time
	// Output: URL of specified day's stock short position list
	
	public String GetShortPositionUrl(String filePath, String fileName, String date){
		ArrayList<String> list=null;
	
		
		//Get all links from webpage's table
		try {
			File file = new File(filePath + File.separator + fileName);
			Document doc = Jsoup.parse(file, "GBK");
			Elements elements = doc.getElementsByClass("component-normal-table");

			list = new ArrayList<String>();

			for(Element element:elements){
				if(element.text()!=null){
					Elements es = element.select("tr");
					for(Element tdelement:es){
						Elements tdes = tdelement.select("td");
						for(int i = 0; i < tdes.size(); i++){
						list.add(tdes.get(i).html());
						}
					}
				}
			}
			//For debug purpose only
	//		for (String text:list) {
	//			System.out.println(text);
	//		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Get the URL based on date
		String link = null;
		for (String item:list) {
			if(item.contains(date)) {
				link = item.substring(item.indexOf('/'), item.indexOf("xls"));
			}
		}
		if (link == null) {
			System.out.println(date + " Stock market is closed");
			return link;
		}
		System.out.println("link is " + link);
		link = jpx_url + link + "xls";
		System.out.println("link is " + link);
		return link;
		
	}

}
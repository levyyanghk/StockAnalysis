package stockDataAnalysis;

import java.io.*;
import java.net.*;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;


public class StockDataDownload {
	/**
	 * @param httpUrl
	 * @param savePath
	 * @param fileName
	 * @return
	 */
	public static boolean HttpDownloadFile(String httpUrl, String savePath, String fileName){
		int bytesum = 0;
        int byteread = 0;
 
        URL url = null;
		try {
			url = new URL(httpUrl);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
 
        try {
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(3 * 1000);
            InputStream inStream = conn.getInputStream();
            
            File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			File file = new File(saveDir + File.separator + fileName);
			System.out.println("file path is " + file);
            FileOutputStream fs = new FileOutputStream(file);
 
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
            fs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

	/**
	 * @param url: HTTP address for the website
	 * @param savePath: The path of the outfile 
	 * @param fileName:
	 * @return
	 */
	public static boolean HttpDownloadPage(String url, String savePath, String fileName){
		try {

			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			File file = new File(saveDir + File.separator + fileName);
			Document doc = Jsoup.connect(url).data("jquery","java").userAgent("Mozilla").cookie("auth", "tiken").timeout(5000).get();
			String pageHtml = doc.toString();
			OutputStream out = new FileOutputStream(file);
			out.write(pageHtml.toString().getBytes());
			out.close();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


}

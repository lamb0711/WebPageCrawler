package edu.handong.csee.java.bonus;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class URLParser {
	private static URL urlAdderess; //URL address
	static ArrayList<String> urlLine = new ArrayList<String>();
	HttpURLConnection code;
	BufferedReader br;
	String line;
	
	public static ArrayList<String> getUrlLine() {
		return urlLine;
	}

	void saveUrlLine(String url) throws IOException 
	{

		//url = "http://hisnet.handong.edu";

		try {
			urlAdderess = new URL(url);
			code = (HttpURLConnection)urlAdderess.openConnection(); 

			br = new BufferedReader(new InputStreamReader(code.getInputStream()));

			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				urlLine.add(line);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


	}


}
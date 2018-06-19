package edu.handong.csee.java.bonus;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter {

	void saveInHtmlFile(String output) {

		String fileName = output+"/index.html";

		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(fileName);
		}catch(FileNotFoundException e) {
			System.out.println("Error opening the file "+fileName);
			System.exit(0);
		}

		for(String line : URLParser.getUrlLine()) {
			System.out.println(line);
			outputStream.println(line);
		}

		outputStream.close();
	}
}

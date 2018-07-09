package util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class ReadLineByLine implements Serializable{
	public BufferedReader reader = null;
	public String currentFile = null;

	public void clean() {
		currentFile = null;
		if (reader != null) {
			try {
				reader.close();
				reader = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String readLBL(String filename) {
		String a = null;
		if (currentFile == null || !currentFile.equals(filename)) {
			clean();
			currentFile = filename;
			File file = new File(currentFile);
			try {
				reader = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				System.out.println("Cannot find the file " + currentFile + ".\nRedirect the reader to STDIN.");
				reader = new BufferedReader(new InputStreamReader(System.in));
			}
		}
		try {
			a = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}
}

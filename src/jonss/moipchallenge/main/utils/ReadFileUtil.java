package jonss.moipchallenge.main.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadFileUtil {

	public static BufferedReader fromFile(String fileName) {
		try {
			return new BufferedReader(
					new InputStreamReader(
						new FileInputStream(fileName)));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado.");
		}
		return null;
	}
	
	public static Scanner fromKeyboard() {
		InputStream in = System.in;
		return new Scanner(in);
	}

}

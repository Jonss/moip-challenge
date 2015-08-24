package jonss.moipchallenge.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MoipLogReader {

	public static Scanner fromFile(String fileName) {
		try {
			return new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado.");
		}
		return null;
	}

}

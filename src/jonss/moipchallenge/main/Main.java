package jonss	.moipchallenge.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jonss.moipchallenge.main.utils.ReadFileUtil;
import jonss.moipchallenge.main.utils.StringUtils;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("Digite o nome do arquivo: ");
		
		Scanner fileName = ReadFileUtil.fromKeyboard();
		BufferedReader readFromFile = ReadFileUtil.fromFile(fileName.nextLine());
		String readLine = readFromFile.readLine();

		List<String> urlList = new ArrayList<>();
		List<Integer> statusList = new ArrayList<>();
		
		while(readLine != null) {
			System.out.println(readLine);
			if(!readLine.equals("")) {
				urlList.add(StringUtils.matchUrlPattern(readLine));
				statusList.add(StringUtils.matchStatusPattern(readLine));
			}
			
			readLine = readFromFile.readLine();
		}
		
		for (String string : urlList) {
			System.out.println(string);
		}
		
		for (Integer integer : statusList) {
			System.out.println(integer);
		}
		
		System.out.println(urlList.size());
		
	}

	
}

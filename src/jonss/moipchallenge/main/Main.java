package jonss.moipchallenge.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jonss.moipchallenge.main.utils.ReadFileUtil;
import jonss.moipchallenge.main.utils.StringUtils;
import jonss.moipchallenge.model.LogValue;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("Digite o nome do arquivo: ");

		Scanner fileName = ReadFileUtil.fromKeyboard();
		BufferedReader readFromFile = ReadFileUtil.fromFile(fileName.nextLine());
		String readLine = readFromFile.readLine();

		List<String> urlList = new ArrayList<>();
		List<String> statusResponseList = new ArrayList<>();

		while (readLine != null) {
			if (!readLine.equals("")) {
				urlList.add(StringUtils.matchUrlPattern(readLine));
				statusResponseList.add(StringUtils.matchStatusPattern(readLine));
			}
			readLine = readFromFile.readLine();
		}
		
		/* Lista pra receber as urls e status response com suas quantidades */
		List<LogValue> fileValuesList= new ArrayList<>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		LogValueCollections.addValuesOnMap(urlList, map);
		LogValueCollections.addValuesOnList(fileValuesList, map);
		List<LogValue> stream = LogValueCollections.fileValueStream(fileValuesList);
		
		
		/* Imprime os maiores valores*/
		for (int i = 0; i < LogValueCollections.MAX_VALUES ; i++) {
			System.out.println(stream.get(i));
		}
		
		// Limpa a lista e o map para ser reutilizada
		clear(fileValuesList, map);
		
		//Imprime todos os responseStatus
		LogValueCollections.addValuesOnMap(statusResponseList, map);
		LogValueCollections.addValuesOnList(fileValuesList, map);
		LogValueCollections.fileValueStream(fileValuesList)
							.forEach(s -> System.out.println(s));
		
	}

	private static void clear(List<LogValue> fileValuesList, Map<String, Integer> map) {
		map.clear();
		fileValuesList.clear();
		System.out.println("");
	}
	
}

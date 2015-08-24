package jonss.moipchallenge.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import jonss.moipchallenge.log.LogMatcher;
import jonss.moipchallenge.log.MoipLogReader;
import jonss.moipchallenge.model.LogValue;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("Digite o nome do arquivo: ");

		Scanner fileName = new Scanner(System.in);
		Scanner file = MoipLogReader.fromFile(fileName.next());

		fileName.close();

		if (file != null) {
			List<String> urlList = new ArrayList<>();
			List<String> statusResponseList = new ArrayList<>();

			while (file.hasNextLine()) {
				String nextLine = file.nextLine();
				if (!nextLine.equals("")) {
					urlList.add(LogMatcher.matchUrlPattern(nextLine));
					statusResponseList.add(LogMatcher.matchStatusPattern(nextLine));
				}
			}

			List<LogValue> fileValuesList = new ArrayList<>();
			Map<String, Integer> map = new TreeMap<String, Integer>();

			LogExtractValues.addValuesOnMap(urlList, map);
			LogExtractValues.addValuesOnList(fileValuesList, map);
			List<LogValue> stream = LogExtractValues.fileValueStream(fileValuesList);

			for (int i = 0; i < LogExtractValues.MAX_VALUES; i++) {
				System.out.println(stream.get(i));
			}

			clear(fileValuesList, map);

			LogExtractValues.addValuesOnMap(statusResponseList, map);
			LogExtractValues.addValuesOnList(fileValuesList, map);
			LogExtractValues.fileValueStream(fileValuesList)
							   .forEach(s -> System.out.println(s));
		}

	}

	private static void clear(List<LogValue> fileValuesList, Map<String, Integer> map) {
		map.clear();
		fileValuesList.clear();
		System.out.println("");
	}

}

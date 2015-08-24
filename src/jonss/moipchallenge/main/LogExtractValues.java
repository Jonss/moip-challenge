package jonss.moipchallenge.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jonss.moipchallenge.model.LogValue;

public class LogExtractValues {

	public static Integer MAX_VALUES = 3;

	private static Comparator<LogValue> reverseOrder() {
		return Collections.reverseOrder(Comparator.comparing(LogValue::getQuantity));
	}

	public static void addValuesOnMap(List<String> status, Map<String, Integer> map) {
		for (String string : status) {
			if (!map.containsKey(string)) {
				map.put(string, 0);
			}
			map.put(string, map.get(string) + 1);
		}
	}

	public static void addValuesOnList(List<LogValue> fileValues, Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			fileValues.add(new LogValue(entry.getKey(), entry.getValue()));
		}
	}

	public static List<LogValue> fileValueStream(List<LogValue> fileValuesList) {
		return fileValuesList.stream().sorted(reverseOrder()).distinct().collect(Collectors.toList());
	}

	public static List<LogValue> getHighestValues(List<LogValue> stream) {
		stream.clear();
		for (int i = 0; i < 3; i++) {
			stream.add(stream.get(i));
		}
		return stream;
	}

}

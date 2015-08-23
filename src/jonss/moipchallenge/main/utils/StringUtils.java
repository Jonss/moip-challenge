package jonss.moipchallenge.main.utils;

public class StringUtils {

	public static String matchUrlPattern(String string) {
		return string.isEmpty() ? "" : string.substring(
					string.indexOf("http"), 
					string.lastIndexOf(" response_headers"))
							.trim().replace("\"", "");
	}

	public static Integer matchStatusPattern(String string) {
		return Integer.parseInt(string.isEmpty() ? "" : string.substring(
					string.indexOf("status="), string.length())
						.replace("status=", "").replace("\"", ""));
	}

}

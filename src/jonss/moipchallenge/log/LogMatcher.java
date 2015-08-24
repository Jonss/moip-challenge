package jonss.moipchallenge.log;

public class LogMatcher {

	public static String matchUrlPattern(String string) {
		return string.isEmpty() ? "" : string.substring(
					string.indexOf("http"), 
					string.lastIndexOf(" response_headers"))
							.trim().replace("\"", "");
	}

	public static String matchStatusPattern(String string) {
		return string.isEmpty() ? "" : string.substring(
					string.indexOf("status="), string.length())
						.replace("status=", "").replace("\"", "");
	}

}

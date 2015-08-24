package jonss.moipchallenge.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jonss.moipchallenge.main.LogValueCollections;
import jonss.moipchallenge.main.utils.ReadFileUtil;
import jonss.moipchallenge.main.utils.StringUtils;
import jonss.moipchallenge.model.LogValue;

public class LogFileTest {

	private String urlRegex;
	private List<String> listUrls;
	private List<String> listStatus;
	private ArrayList<LogValue> logValuesList;
	private Map<String, Integer> map;

	@Before
	public void setup() {
		urlRegex = "^(https?:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
		listUrls = new ArrayList<>();
		listStatus = new ArrayList<>();
		logValuesList = new ArrayList<>();
		map = new HashMap<String, Integer>();
	}

	@Test
	public void should_assert_url_string_pattern() throws Exception {
		String google = "www.google.com";
		String notAUrl = "www.NotAnUrl";

		Assert.assertFalse(notAUrl.matches(urlRegex));
		Assert.assertTrue(google.matches(urlRegex));
	}

	@Test
	public void should_read_from_file_and_count_all_rows() throws Exception {
		BufferedReader br = ReadFileUtil.fromFile("arquivo.txt");

		String fileLine = br.readLine();

		while (fileLine != null) {
			if (!fileLine.equals("")) {
				listUrls.add(fileLine);
			}
			fileLine = br.readLine();
		}

		Assert.assertEquals(42, listUrls.size());
	}

	@Test
	public void should_read_from_file_and_validate_the_tree_firstUrls() throws Exception {
		getAllUrlsFromFile();

		Assert.assertEquals("https://grotesquemoon.de", listUrls.get(0));
		Assert.assertEquals("https://woodenoyster.com.br", listUrls.get(1));
		Assert.assertEquals("https://severeleather.com", listUrls.get(2));
	}

	@Test
	public void should_read_from_file_and_validate_the_tree_firstStatus() throws Exception {
		getAllResponseStatusfromFile();

		Assert.assertEquals("201", listStatus.get(0));
		Assert.assertEquals("503", listStatus.get(1));
		Assert.assertEquals("500", listStatus.get(2));
	}

	@Test
	public void should_get_the_three_max_urls_quantities() throws Exception {
		getAllUrlsFromFile();

		LogValueCollections.addValuesOnMap(listUrls, map);
		LogValueCollections.addValuesOnList(logValuesList, map);

		List<LogValue> stream = LogValueCollections.fileValueStream(logValuesList);

		Assert.assertEquals("https://woodenoyster.com.br - 7", stream.get(0).toString());
		Assert.assertEquals("https://solidstreet.net - 5", stream.get(1).toString());
		Assert.assertEquals("https://grimpottery.net.br - 4", stream.get(2).toString());
	}

	@Test
	public void should_get_all_status_response_quantities() throws IOException {
		getAllResponseStatusfromFile();

		LogValueCollections.addValuesOnMap(listStatus, map);
		LogValueCollections.addValuesOnList(logValuesList, map);

		List<LogValue> stream = LogValueCollections.fileValueStream(logValuesList);

		Assert.assertEquals("500 - 10", stream.get(0).toString());
		Assert.assertEquals("400 - 9", stream.get(1).toString());
		Assert.assertEquals("503 - 7", stream.get(2).toString());
		Assert.assertEquals("404 - 6", stream.get(3).toString());
		Assert.assertEquals("200 - 5", stream.get(4).toString());
		Assert.assertEquals("201 - 3", stream.get(5).toString());
		Assert.assertEquals("204 - 2", stream.get(6).toString());

	}

	private void getAllUrlsFromFile() throws IOException {
		BufferedReader br = ReadFileUtil.fromFile("arquivo.txt");
		String fileLine = br.readLine();

		while (fileLine != null) {
			if (!fileLine.equals("")) {
				listUrls.add(StringUtils.matchUrlPattern(fileLine));
			}
			fileLine = br.readLine();
		}
	}

	private void getAllResponseStatusfromFile() throws IOException {
		BufferedReader br = ReadFileUtil.fromFile("arquivo.txt");

		String fileLine = br.readLine();

		while (fileLine != null) {
			if (!fileLine.equals("")) {
				listStatus.add(StringUtils.matchStatusPattern(fileLine));
			}
			fileLine = br.readLine();
		}
	}

}

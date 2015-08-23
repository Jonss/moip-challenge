package jonss.moipchallenge.test;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jonss.moipchallenge.main.utils.ReadFileUtil;
import jonss.moipchallenge.main.utils.StringUtils;


public class StringTest {

	
	private String urlRegex;
	private List<String> listUrls;
	private List<Integer> listStatus;
	
	@Before
	public void setup(){
		urlRegex = "^(https?:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
		listUrls = new ArrayList<>();
		listStatus = new ArrayList<>();
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
		
		while(fileLine != null) {
			if(!fileLine.equals("")) {
				listUrls.add(fileLine);
			}
			fileLine = br.readLine();
		}
		
		Assert.assertEquals(42,listUrls.size());
	}
	
	@Test
	public void should_read_from_file_and_validate_the_tree_firstUrls() throws Exception {
		BufferedReader br = ReadFileUtil.fromFile("arquivo.txt");
		
		String fileLine = br.readLine();
		
		while(fileLine != null) {
			if(!fileLine.equals("")) {
				listUrls.add(StringUtils.matchUrlPattern(fileLine));
			}
			fileLine = br.readLine();
		}
		
		Assert.assertEquals("https://grotesquemoon.de", listUrls.get(0));
		Assert.assertEquals("https://woodenoyster.com.br", listUrls.get(1));
		Assert.assertEquals("https://severeleather.com", listUrls.get(2));
	}
	
	@Test
	public void should_read_from_file_and_validate_the_tree_firstStatus() throws Exception {
		BufferedReader br = ReadFileUtil.fromFile("arquivo.txt");
		
		String fileLine = br.readLine();
		
		
		while(fileLine != null) {
			if(!fileLine.equals("")) {
				listStatus.add(StringUtils.matchStatusPattern(fileLine));
			}
			fileLine = br.readLine();
		}
		
		Assert.assertEquals((Integer)201, listStatus.get(0));
		Assert.assertEquals((Integer)503, listStatus.get(1));
		Assert.assertEquals((Integer)500, listStatus.get(2));
	}
	
	
}

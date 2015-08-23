package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StringTest {

	
	private String urlRegex;
	private List<String> lista;
	
	@Before
	public void setup(){
		urlRegex = "^(https?:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
		lista = new ArrayList<>();
	}
	
	@Test
	public void should_assert_url_string_pattern() throws Exception {
		String google = "www.google.com";
		String notAUrl = "www.NotAUrl";
		
		Assert.assertFalse(notAUrl.matches(urlRegex));
		Assert.assertTrue(google.matches(urlRegex));
	}
	
	@Test
	public void should_read_from_file_and_count_all_rows() throws Exception {
		BufferedReader br = readFromFile();
		
		String fileLine = br.readLine();
		
		while(fileLine != null) {
			lista.add(fileLine);
			fileLine = br.readLine();
		}
		
		Assert.assertEquals(41,lista.size());
	}

	private BufferedReader readFromFile() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(
								new InputStreamReader(
									new FileInputStream("arquivo.txt")));
		return br;
	}
	
	
}

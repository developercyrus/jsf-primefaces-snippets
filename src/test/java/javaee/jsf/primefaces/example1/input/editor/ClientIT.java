package javaee.jsf.primefaces.example1.input.editor;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class ClientIT {
	private String url = "http://localhost:8080/jsf-primefaces-snippets/javaee/jsf/primefaces/example1/input/editor/index.xhtml";
	
	@Test
	public void test1() throws Exception {
		Document doc = Jsoup.parse(new URL(url), 5000);
		/*
			if using jquery, the colon can be escaped by "Form\\:Editor"			
			reference: http://www.mkyong.com/jsf2/primefaces/how-to-get-jsf-id-via-jquery/
		 */
		Element elements = doc.getElementById("form:editor");
		String actual = elements.text();

		System.out.println(actual);
		
	}
	
	@Test 
	public void test2() throws FailingHttpStatusCodeException, MalformedURLException, IOException {				
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);       
        webClient.getOptions().setThrowExceptionOnScriptError(true);
        webClient.getOptions().setCssEnabled(true);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        
		HtmlPage indexPage = webClient.getPage(url);
        HtmlSubmitInput submitButton = (HtmlSubmitInput)indexPage.getElementByName("form:submitButton1");        
        HtmlPage resultPage = submitButton.click();     
        String actual = resultPage.getElementById("result").asText();
        assertEquals("Hello World", actual);
        
        webClient.close();
	}
}

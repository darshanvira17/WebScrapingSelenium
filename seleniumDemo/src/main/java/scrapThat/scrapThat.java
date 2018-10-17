package scrapThat;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class scrapThat {
	public void CountryInfo(String countryName){
		try {
			//set system property for web driver
			System.setProperty("webdriver.gecko.driver","YOUR_PATH_TO_\\geckodriver.exe");
			//System.setProperty("webdriver.chrome.driver","O:\\worksapce\\seleniumDemo\\src\\test\\resources\\Drivers\\chromedriver.exe");
			// Create an instance of the driver	
			Thread.sleep(2000);
			WebDriver driver = new FirefoxDriver();
			//WebDriver driver = new ChromeDriver();
			// Navigate to a web page
			driver.get("http://example.webscraping.com/");
			driver.findElement(By.xpath("//body/div[contains(@class,'container')]/section/div/div/table/tbody/tr/td/div/a[text()='"+countryName+"']")).click();
			Thread.sleep(5000);
			//Get the Keys of the information about the country
			List<WebElement> Keys;
			Keys=driver.findElements(By.xpath("//body/div[contains(@class,'container')]/section/div/form/table/tbody/tr/td/label[contains(@class,'readonly')]"));
			//Get the corresponding values of the same
			List<WebElement> Values;
			Values=driver.findElements(By.xpath("//body/div[contains(@class,'container')]/section/div/form/table/tbody/tr/td[contains(@class,'w2p_fw')]"));
			
			// Can return as a Dictionary of Key-Value pairs
			//Dictionary<String, String> countryDetails = new Hashtable<String, String>();
			//for(int i =0;i < Keys.size(); i++)
			//{
			//countryDetails.put((Keys.get(i).getText()),(Values.get(i).getText()));	
			//}
			//System.out.println(countryDetails);
			
			PrintWriter writer = new PrintWriter("YOUR_PATH"+countryName.substring(1)+".txt", "UTF-8");
			for(int i =0;i < Keys.size(); i++)
			{
			writer.println((Keys.get(i).getText())+":"+(Values.get(i).getText()));	
			}
			writer.close();
			System.out.println("Check YOUR_PATH a .txt file is created with country name on it! e.g. Afghanistan.txt");
			driver.close();
		}catch(Exception e){
	e.printStackTrace();
	}
}
public static void main(String[] args) {
	scrapThat st=new scrapThat();
	ArrayList<String> countries= new ArrayList<String>();
	Scanner sc=new Scanner(System.in);
	boolean done=false;
	countries.add(" Afghanistan");
	countries.add(" Aland Islands");
	countries.add(" Albania");
	countries.add(" Algeria");
	countries.add(" American Samoa");
	countries.add(" Andorra");
	countries.add(" Angola");
	countries.add(" Anguilla");
	countries.add(" Antarctica");
	countries.add(" Antigua and Barbuda");
	while(done==false){
	String countryName=" "; //in source code the tag has " CountryName" an extra space
	//loop to constrain USER input.
	System.out.println("Enter the name of the Country you wanna get Information for:\n1)Afghanistan\n2)Alan Islands\n3)Albania\n4)Algeria\n5)American Samoa\n6)Andorra\n7)Angola\n8)Anguilla\n9)Antarctica\n10)Antigua and Barbuda");
	countryName+=(sc.nextLine()).trim();
	System.out.println("Your Input: "+countryName);
	if(countries.contains(countryName)){
	st.CountryInfo(countryName);
	System.out.println("Want info about another country? (Y/N): ");
	String next;
	next=sc.next().toLowerCase();
	System.out.println("Your call: "+next);
	if(next.equals("n") | next.equals("no")){
		done=true;
	}
	else if(next.equals("y")|next.equals("yes")){
		done=false;	
		}
	else{
		done=true; //if USER enters anything other than y/yes or n/no
	}
	}
	}
	sc.close();
}	
}

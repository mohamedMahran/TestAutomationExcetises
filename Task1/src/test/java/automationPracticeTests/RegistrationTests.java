package automationPracticeTests;
	
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import com.aventstack.extentreports.Status;

import Core.Driver;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.RegsitrationPage;
import utilis.ExtentTestManager;
	
	
	
	public class RegistrationTests extends BaseTest {

		

		@Test(dataProvider="accountInformation")
		public void validateNewAccount(String firstName,String surName,String passwod,String days,String month,String year,String company,
				String Address1,String Address2,String city,String state,String postcode,String other,String phone,String mobilePhone,String alias)throws ParserConfigurationException, SAXException, IOException 
			{
			try
			{
				
				HomePage homePage = new HomePage(Driver._browser);
				LoginPage loginPage = new LoginPage(Driver._browser);
				LandingPage landing = new LandingPage(Driver._browser);
				RegsitrationPage registerPage = new RegsitrationPage(Driver._browser);
				
				homePage.signIn();
					
				loginPage.enterNewAccount();
					
				loginPage.clickOnCreateAccountButton();
					
				registerPage.enterNewAccount(firstName, surName, passwod, days, month, year, company,
					 Address1, Address2, city, state, postcode, other, phone, mobilePhone, alias);
					registerPage.submit();
			
					
				assertEquals(landing.getHeaderText(), "MY ACCOUNT");
				assertEquals(firstName + " " + surName, landing.getFullName());
				assertTrue(landing.getAccountInfo().contains("Welcome to your account."));
				assertTrue(landing.doesLogOutAvailable());
				assertTrue(Driver.getCurrentUrl().contains("controller=my-account"));
			}	
			catch(Exception e)
				{
					ExtentTestManager.getTest().log(Status.INFO, ExceptionUtils.getStackTrace(e));
				}
			
		}
		
		@DataProvider(name="accountInformation")
		public Object[][] accountInformation()
		{
			return new Object[][] {{"Firstname","Lastname","Qwerty","1","10","2000","Company","Qwerty, 123","zxcvb",
				"Qwerty","Colorado","12345","Qwerty","12345123123","12345123123","hf"}};
		}
	
	}

package automationPracticeTests;
	
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import com.aventstack.extentreports.Status;

import core.WebDriverFacade;
import pages.HomePage;
import pages.LandingPage;
import pages.LoginPage;
import utilis.ExtentTestManager;
	public class LoginTests extends BaseTest {
		
		//WebDriver webDriver = WebDriverFacade.getWebDriver();
		@Test
		public void validateValidLogin() throws IOException, ParserConfigurationException, SAXException {
			try
			{

				Logger log = Logger.getLogger(LoginTests.class);
				HomePage homePage = new HomePage(webDriver);
				LoginPage loginPage = new LoginPage(webDriver);
				LandingPage landing = new LandingPage(webDriver);
				
				log.info(
					"****************************** Starting test cases execution  *****************************************");
				// one is inheritance
				// creating object to that class and invoke methods of it
			
				String fullName = "Joe Black";
		
			
				// 2. Click *Sign in* button (in the header)
				homePage.signIn();
	
				/*
				 * 3. Fill *Email address* in _Already registered_ block Fill *Password*
				 * in _Already registered_ block Then Click Sign in
				 */
				loginPage.enterUserCredentialsAndSignIn();
				//Assertions
			
				// My account page(?controller=my-account) is opened
				assertEquals("MY ACCOUNT", landing.getHeaderText());
				
				// 5. Proper username is shown in the header
				assertEquals(fullName, landing.getFullName());
				// 6. Log out action is available*/
				assertTrue(landing.doesLogOutAvailable());
			log.info("Navigation Bar is displayed");
			log.info("Test completed");
			}
			catch(Exception e)
			{
				ExtentTestManager.getTest().log(Status.INFO, ExceptionUtils.getStackTrace(e));
			}
	
		}
	}

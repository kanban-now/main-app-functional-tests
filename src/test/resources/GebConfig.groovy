/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/


import main.spec.MainSpec
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver

waiting {
	timeout = 2
}

environments {
	
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		driver = { new ChromeDriver() }
	}
	
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = { new FirefoxDriver() }
	}

    phantomJs {
        driver = { new PhantomJSDriver() }
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = readTestProperty('geb_base_url')
MainSpec.testUser1_id = readTestProperty('testuser1_id')
MainSpec.testUser1_password = readTestProperty('testuser1_password')
MainSpec.testUser1_internalId = readTestProperty('testUser1_internalId')


MainSpec.archiveCardServiceUrl = readTestProperty('archiveCardServiceUrl')
MainSpec.archiveCardServiceUserName = readTestProperty('archiveCardServiceUserName')
MainSpec.archiveCardServicePassword = readTestProperty('archiveCardServicePassword')



reportsDir = "build/geb-reports"

private static String readTestProperty(String key) {
	String result = System.getProperty(key)
	if(result == null) {
		result = System.getenv(key);
	}

	return result
}


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.net.MalformedURLException;
import java.net.URL;
public class mobile {

    AppiumDriver<MobileElement> driver = null;
    String PLATFORMNAME = PropertyReader.propertyReader("config.properties", "PLATFORM_NAME");
    @BeforeTest
    public void setup() throws InterruptedException {
        // Set desired capabilities for the Android device
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORMNAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel6_LambdaTestAppiumSeries");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/sishukla/Downloads/proverbial_android.apk");

        Thread.sleep(10000);
        // Create an instance of the AppiumDriver

        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @AfterTest
    public void quitDriver(){
        // Quit the driver and close the app
        driver.quit();
    }
    @Test(description = "Verify google logo is present after entering the URL")
    public void validateGoogleLogo() throws InterruptedException {
        // Perform some actions on the app
        MobileElement geoLocationElement = driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
        geoLocationElement.click();
        Thread.sleep(10000);
        System.out.println("clicked on geoLocation");
        MobileElement findButton = driver.findElementById("com.lambdatest.proverbial:id/find");
        Assert.assertEquals(findButton.isDisplayed(), true, "Find button is not displayed");
        System.out.println("Find button is displayed");
        Thread.sleep(10000);
        MobileElement editUrlText = driver.findElementById("com.lambdatest.proverbial:id/url");
        editUrlText.sendKeys("https://www.google.com");
        System.out.println("Entered the google URL");
        Thread.sleep(10000);
        findButton.click();
        System.out.println("clicked on find button");
        Thread.sleep(10000);
        MobileElement logo = driver.findElementByXPath("//android.webkit.WebView[@content-desc=\"Google\"]/android.view.View/android.view.View/android.view.View[2]");
        Assert.assertEquals(logo.isDisplayed(), true, "logo is not displayed");
        System.out.println("logo is displayed");
        Thread.sleep(5000);
    }
}

//com.lambdatest.proverbial:id/geoLocation
//com.lambdatest.proverbial:id/url
//com.lambdatest.proverbial:id/find
//android.webkit.WebView[@content-desc="Google"]/android.view.View/android.view.View/android.view.View[2]

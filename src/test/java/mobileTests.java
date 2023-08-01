import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class mobileTests {

    public static void main(String[] args) throws InterruptedException {
        // Set desired capabilities for the Android device
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel6_LambdaTestAppiumSeries");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/sishukla/Downloads/proverbial_android.apk");


        // Create an instance of the AppiumDriver

        AppiumDriver<MobileElement> driver = null;
        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Perform some actions on the app

        //click on the geolocation
        MobileElement geoLocationElement = driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
        geoLocationElement.click();
        System.out.println("clicked on geoLocation");
        Thread.sleep(10000);
        MobileElement findButton = driver.findElementById("com.lambdatest.proverbial:id/find");
        //validate Find is present
        Assert.assertEquals(findButton.isDisplayed(), true, "Find button is not displayed");
        System.out.println("Find button is displayed");
        //enter the url as google.com
        Thread.sleep(10000);
        MobileElement editUrlText = driver.findElementById("com.lambdatest.proverbial:id/url");
        editUrlText.sendKeys("https://www.google.com");
        System.out.println("Entered the google url");
        //click on find button
        Thread.sleep(10000);
        findButton.click();
        Thread.sleep(10000);
        System.out.println("clicked on Find button");
        //validate the logo
        MobileElement logo = driver.findElementByXPath("//android.webkit.WebView[@content-desc=\"Google\"]/android.view.View[1]/android.view.View/android.view.View[2]");
        Assert.assertEquals(logo.isDisplayed(), true, "Logo is not displayed");
        System.out.println("logo is displayed");
        Thread.sleep(10000);
        // Quit the driver and close the app
        driver.quit();
    }
}

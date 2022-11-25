package config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class BaseClass {

	public static AndroidDriver<AndroidElement> driver;

	public static AppiumDriverLocalService service;


	public AppiumDriverLocalService startServer(){
		boolean flag = checkIfServerIsRunnning(4723);
		if(!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	@BeforeMethod
	public void setup() throws MalformedURLException {
		service = startServer();

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("deviceName", "R58R44J2AMD");
		cap.setCapability("platformVersion", "12");
		cap.setCapability("appPackage", "us.zoom.videomeetings");
		cap.setCapability("appActivity", "com.zipow.videobox.LauncherActivity");

		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 150000);
		cap.setCapability("androidInstallTimeout",150000);

		cap.setCapability("autoGrantPermissions", "true");
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);

		driver = new AndroidDriver<>(new URL("http://127.0.0.2:4723/wd/hub"), cap);
		AppDriver.setDriver(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	public void putAppInBackGround(){
		driver.runAppInBackground(Duration.ofSeconds(5));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
		service.stop();

	}

}

package testcase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import config.BaseClass;
import pages.*;

public class TestCases extends BaseClass {
	HomePage home;

	Date date;

	@BeforeMethod
	public void BaseClass() {
		 home = new HomePage();
	}

	@Test
	public void Task1() throws InterruptedException {
		home.mobileTaskOne();
		Assert.assertFalse(home.is_join_button_disabled());
		home.enter_meeting_ID();
		Assert.assertTrue(home.is_join_button_disabled());
		home.toggle_my_video();
		home.join_meeting();
		putAppInBackGround();
		Assert.assertEquals(home.getErrorMessage(),"Invalid meeting ID. Please check and try again.");
	}
}

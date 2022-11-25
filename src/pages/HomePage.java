package pages;

import Utalities.Wait_Utail;
import config.AppDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Wait_Utail.explicit_Wait);

    public HomePage() {
        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
    }


    @AndroidFindBy(id = "us.zoom.videomeetings:id/btnJoinConf")
    public AndroidElement Join_Meeting_Button;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/btnJoin")
    public AndroidElement Join_Button_Disabled;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/edtConfNumber")
    public AndroidElement EnterMeetingID;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/chkNoVideo")
    public AndroidElement TurnOffMyVideo;

    @AndroidFindBy(id = "us.zoom.videomeetings:id/txtMsg")
    public AndroidElement TextMessagePopUp;

    public void mobileTaskOne() {

        int attempts = 0;
        while (attempts < 2) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(Join_Meeting_Button));
                Join_Meeting_Button.click();
                break;
            } catch (Exception e) {

            }
            attempts++;
        }

    }
    public boolean is_join_button_disabled(){
        wait.until(ExpectedConditions.visibilityOf(Join_Button_Disabled));
        boolean isEnabled = Join_Button_Disabled.isEnabled();

        return isEnabled;
    }

    public void enter_meeting_ID(){
        wait.until(ExpectedConditions.visibilityOf(TurnOffMyVideo));
        EnterMeetingID.sendKeys("524154785");
    }

    public void toggle_my_video(){
        wait.until(ExpectedConditions.elementToBeClickable(TurnOffMyVideo));
        TurnOffMyVideo.click();
    }
    public void join_meeting(){
        wait.until(ExpectedConditions.elementToBeClickable(Join_Button_Disabled));
        Join_Button_Disabled.click();
    }

    public String getErrorMessage(){
        wait.until(ExpectedConditions.elementToBeClickable(TextMessagePopUp));
        return TextMessagePopUp.getText();
    }

}

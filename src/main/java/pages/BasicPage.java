package pages;

import org.openqa.selenium.support.PageFactory;
import steps.BaseTest;

public class BasicPage {
    public BasicPage() {
        PageFactory.initElements(BaseTest.getDriver(), this);
    }

}

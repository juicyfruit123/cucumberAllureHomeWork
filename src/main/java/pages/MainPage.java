package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BaseTest;

public class MainPage {
    @FindBy(xpath = "//span")
    WebElement menu;

    public MainPage() {
        PageFactory.initElements(BaseTest.getDriver(), this);
    }

    public void selectMenuItem(String itemName) {
        WebElement element1 = BaseTest.getDriver().findElement(By.xpath("//a[@class='lg-menu__sub-link' and text()='" + itemName + "']"));
        Actions actions = new Actions(BaseTest.getDriver());
        actions.moveToElement(element1);
        actions.click().perform();
    }

    public void selectMenu(String itemName) {
        menu.findElement(By.xpath("//span[text()='" + itemName + "']")).click();
    }

}

package pages;

import cucumber.api.java.cs.A;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseTest;

public class CreditPage {
    @FindBy(xpath = "//iframe[@id='iFrameResizer0']")
    WebElement frame;
    @FindBy(xpath = "//div[text()='Стоимость недвижимости']/..//input")
    WebElement costHouse;
    @FindBy(xpath = "//div[text()='Первоначальный взнос']/..//input")
    WebElement firstPay;
    @FindBy(xpath = "//div[text()='Срок кредита']/..//input")
    WebElement creditContinue;
    @FindBy(xpath = "//div[text()='Есть зарплатная карта Сбербанка']/../.././/label")
    WebElement buttonKardSber;
    @FindBy(xpath = "//div[text()='Есть возможность подтвердить доход справкой']/../.././/label")
    WebElement button;
    @FindBy(xpath = "//div[text()='Молодая семья']/../.././/label")
    WebElement button1;
    @FindBy(xpath = "//span[@data-test-id='amountOfCredit']")
    WebElement amountOfCredit;
    @FindBy(xpath = "//span[@data-test-id='monthlyPayment']")
    WebElement monthlyPayment;
    @FindBy(xpath = "//span[@data-test-id='requiredIncome']")
    WebElement requiredIncome;
    @FindBy(xpath = "//span[@data-test-id='rate']")
    WebElement rate;
    JavascriptExecutor JAVASCRIPT_EXECUTOR = null;

    public CreditPage() {
        PageFactory.initElements(BaseTest.getDriver(), this);
    }

    public void createField() {
        BaseTest.getDriver().switchTo().frame(frame);
        JAVASCRIPT_EXECUTOR = (JavascriptExecutor) BaseTest.getDriver();
        JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", costHouse);
    }


    public void waitField(String monthly) {
        (new WebDriverWait(BaseTest.getDriver(), 10))
                .until((ExpectedCondition<Boolean>) d -> !monthlyPayment.getText().equals(monthly));
    }

    public void fillField(String name, String value) throws InterruptedException {
        if (name.equals("Стоимость недвижимости")) {
            createField();
        }

        switch (name) {
            case "Стоимость недвижимости":
                Thread.sleep(5000);
                costHouse.clear();
                (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> costHouse.getAttribute("value").equals(""));
                costHouse.sendKeys(value);
                waitField(monthlyPayment.getAttribute("value"));
             /*   (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> costHouse.getAttribute("value").replaceAll("\\D","").equals(value));
             */ //  Thread.sleep(5000);
                break;
            case "Первоначальный взнос":
                (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> !firstPay.getAttribute("value").equals(""));

             //   firstPay.sendKeys(Keys.LEFT_CONTROL+"a");
                Actions actions = new Actions(BaseTest.getDriver());
                actions.moveToElement(firstPay).build().perform();
                   /*    (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> firstPay.getAttribute("value").equals(""));
        */        firstPay.sendKeys(value);
                waitField(monthlyPayment.getText());
         /*       (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> firstPay.getAttribute("value").replaceAll("\\D","").equals(value));
         */       break;
            case "Срок кредита":
                (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> !firstPay.getAttribute("value").equals("value"));
                creditContinue.clear();
                (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> creditContinue.getAttribute("value").replaceAll("\\D", "").equals(""));
                creditContinue.sendKeys(value);
                waitField(monthlyPayment.getText());
                break;
            default:
                throw new AssertionError("Поле '" + name + "' не объявлено на странице");
        }

    }

    public void clickKardSberButton() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buttonKardSber.click();

    }

    public void clickYoungFamily() {
        JAVASCRIPT_EXECUTOR = (JavascriptExecutor) BaseTest.getDriver();
        JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", button);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        button1.click();
    }

    public void checkField(String field, String value) {
        switch (field) {
            case "Сумма кредита":
                Assert.assertEquals(value, amountOfCredit.getText());
                break;
            case "Ежемесячный платеж":
                Assert.assertEquals(value, monthlyPayment.getText());
                break;
            case "Необходимый доход":
                Assert.assertEquals(value, requiredIncome.getText());
                break;
            case "Процентная ставка":
                Assert.assertEquals(value, rate.getText());
            default:
                throw new AssertionError("Поле '" + field + "' не объявлено на странице");

        }
    }
}
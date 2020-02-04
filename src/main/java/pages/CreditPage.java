package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    WebElement confirmYourIncomeButton;
    @FindBy(xpath = "//div[text()='Молодая семья']/../.././/label")
    WebElement youngFamilyButton;
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

    public void switchToFrame() {
        BaseTest.getDriver().switchTo().frame(frame);
        JAVASCRIPT_EXECUTOR = (JavascriptExecutor) BaseTest.getDriver();
        JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", costHouse);
    }


    public void waitMonthlyPayment(String monthly) {
        (new WebDriverWait(BaseTest.getDriver(), 10))
                .until((ExpectedCondition<Boolean>) d -> !monthlyPayment.getText().equals(monthly));
    }

    public void fillField(String name, String value) {
        if (name.equals("Стоимость недвижимости")) {
            switchToFrame();
        }
        switch (name) {
            case "Стоимость недвижимости":
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                costHouse.clear();
                costHouse.sendKeys(value);
                waitMonthlyPayment(monthlyPayment.getText());
                break;
            case "Первоначальный взнос":
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                firstPay.clear();
                firstPay.sendKeys(value);
                waitMonthlyPayment(monthlyPayment.getText());
                break;
            case "Срок кредита":
                creditContinue.clear();
                (new WebDriverWait(BaseTest.getDriver(), 10))
                        .until((ExpectedCondition<Boolean>) d -> creditContinue.getAttribute("value").replaceAll("\\D", "").equals(""));
                creditContinue.sendKeys(value);
                waitMonthlyPayment(monthlyPayment.getText());
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
        waitMonthlyPayment(monthlyPayment.getText());
    }

    public void clickYoungFamily() {
        JAVASCRIPT_EXECUTOR = (JavascriptExecutor) BaseTest.getDriver();
        JAVASCRIPT_EXECUTOR.executeScript("return arguments[0].scrollIntoView(true);", confirmYourIncomeButton);
        (new WebDriverWait(BaseTest.getDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(youngFamilyButton));
        youngFamilyButton.click();
        waitMonthlyPayment(monthlyPayment.getText());
    }

    @Step("Проверка полей")
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
                Allure.addAttachment("fdagadfg", value);
                Assert.assertEquals(value, rate.getText());
            default:
                throw new AssertionError("Поле '" + field + "' не объявлено на странице");

        }
    }
}
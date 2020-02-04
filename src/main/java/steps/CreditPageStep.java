package steps;

import pages.CreditPage;
import ru.yandex.qatools.allure.annotations.Step;
import util.AllureReporter;

public class CreditPageStep {
    @Step("поле {0} заполняется значением {1}")
    public void fillField(String field, String value) {
        new CreditPage().fillField(field, value);
    }

    @Step("проверка значения поля {0}")
    public void checkField(String field, String value) {
        AllureReporter allureReporter = new AllureReporter();
        allureReporter.takeScreenshot();
        new CreditPage().checkField(field, value);

    }

    @Step("снятие галочки Есть зарплатная карта Сбербанка")
    public void clickButton() {
        new CreditPage().clickKardSberButton();
    }

    @Step("нажатие на галочку Молодая семья")
    public void clickYoungFamilyButton() {
        new CreditPage().clickYoungFamily();
    }

}

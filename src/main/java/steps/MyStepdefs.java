package steps;

import cucumber.api.DataTable;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import pages.CreditPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.exceptions.AllureException;
import util.AllureReporter;

public class MyStepdefs {
    private MainPageStep mainPageDef = new MainPageStep();
    CreditPageStep creditPageStep = new CreditPageStep();
    @Step("выбран пункт меню ")
    @Когда("^выбрано меню \"([^\"]*)\"$")
    public void selectMenu(String arg0) throws Throwable {
        mainPageDef.selectMenu(arg0);
    }

    @Тогда("^выбран вид ипотеки \"([^\"]*)\"$")
    public void selectMenuItem(String arg0) throws Throwable {
        mainPageDef.selectMenuItem(arg0);
    }

    @Когда("^заполняются поля:$")
    public void fillField(DataTable table) {
        table.asMap(String.class, String.class)
                .forEach((field, value) -> creditPageStep.fillField(field, value));
    }

    @Когда("^снята галочка Зарплатная карта$")
    public void buttonOff() {
        creditPageStep.clickButton();
    }

    @И("^поставлена галочка Молодая семья$")
    public void buttonOn() {
        creditPageStep.clickYoungFamilyButton();
    }

    @Тогда("^проверить значения полей:$")
    public void checkField(DataTable table) {
      BaseTest.takeScreenshot();
        table.asMap(String.class, String.class)
                .forEach((field, value) -> creditPageStep.checkField(field, value));
    }
}

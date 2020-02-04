package steps;

import pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

public class MainPageStep {
    @Step("выбран пункт меню {0}")
    public void selectMenu(String arg0) throws Throwable {
        new MainPage().selectMenu(arg0);
    }

    @Step("выбран вид ипотеки {0}")
    public void selectMenuItem(String arg0) throws Throwable {
        new MainPage().selectMenuItem(arg0);
    }

}

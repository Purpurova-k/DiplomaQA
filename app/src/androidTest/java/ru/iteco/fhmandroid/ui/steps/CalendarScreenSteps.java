package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentMonthPlusOneMonth;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentYear;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentYearMinusOneYear;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentYearPlusOneYear;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.settingDate;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.todayDay;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fhmandroid.ui.elements.CalendarScreenElements;

public class CalendarScreenSteps {

    CalendarScreenElements calendarScreenElements = new CalendarScreenElements();

    @Step("Нажатие на кнопку ОК")
    public void clickOnOkButton() {
        calendarScreenElements.getOkButton().perform(click());
    }

    @Step("Нажатие на кнопку Отмена")
    public void clickOnCancelButton() {
        calendarScreenElements.getCancelButton().perform(click());
    }

    @Step("Нажатие на кнопку выбора года")
    public void clickOnChooseYearButton() {
        calendarScreenElements.getChooseYearButton().perform(click());
    }

    @Step("Нажатие на следующий год")
    public void chooseNextYear() {
        onView(withText(String.valueOf(currentYearPlusOneYear()))).perform(click());
    }

    @Step("Нажатие на предыдущий год")
    public void choosePreviousYear() {
        onView(withText(String.valueOf(currentYearMinusOneYear()))).perform(click());
    }

    @Step("Нажатие на кнопку Следующий месяц")
    public void clickOnNextMonthButton() {
        calendarScreenElements.getNextMonthButton().perform(click());
    }

    @Step("Нажатие на кнопку Предыдущий месяц")
    public void clickOnPreviousMonthButton() {
        calendarScreenElements.getPreviousMonthButton().perform(click());
    }

    // Установка даты через месяц
    public void setDateInMonth() {
        settingDate(currentYear(), currentMonthPlusOneMonth(), todayDay());
    }
}

package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateAYearAgo;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateInOneWeek;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateTomorrow;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateYesterday;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithCyrillicSymbols;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.AdvancedFilterNewsScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AdvancedFilterNewsScreenSteps {

    AdvancedFilterNewsScreenElements advancedFilterNewsScreenElements = new AdvancedFilterNewsScreenElements();
    CalendarScreenSteps calendarScreenSteps = new CalendarScreenSteps();

    // Проверка отображения экрана Фильтрация новостей (расширенная)
    public void checkAdvancedFilterNewsScreenIsDisplayed() {
        advancedFilterNewsScreenElements.getFilterTitle().check(matches(isDisplayed()));
        SystemClock.sleep(2000);
    }

    // Выбор категории из выпадающего списка
    public void chooseCategory(@NonNull AppActivity activity, String category) {
        advancedFilterNewsScreenElements.getCategory().perform(click());
        advancedFilterNewsScreenElements.getCategory().perform(closeSoftKeyboard());
        onView(withText(category)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
        SystemClock.sleep(2000);
    }

    // Заполненение поля Категория значением не из выпадающего списка
    public void fillTheFieldCategoryNotFromDropdown() {
        advancedFilterNewsScreenElements.getCategory().perform(replaceText(textWithCyrillicSymbols(10)));
        SystemClock.sleep(2000);
    }

    // Заполненение поля Категория значением, состоящим из пробела
    public void fillTheFieldCategoryWhiteSpace() {
        advancedFilterNewsScreenElements.getCategory().perform(replaceText(" "));
        SystemClock.sleep(2000);
    }

    // Заполнение первой даты значением сегодняшнего дня
    public void fillTheStartDateToday() {
        advancedFilterNewsScreenElements.getDateStart().perform(click());
        calendarScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
    }

    // Заполнение первой даты значением завтрашнего дня
    public void fillTheStartDateTomorrow() {
        advancedFilterNewsScreenElements.getDateStart().perform(replaceText(dateTomorrow()));
        SystemClock.sleep(2000);
    }

    // Заполнение первой даты значением год назад
    public void fillTheStartDateAYearAgo() {
        advancedFilterNewsScreenElements.getDateStart().perform(click());
        calendarScreenSteps.clickOnChooseYearButton();
        calendarScreenSteps.choosePreviousYear();
        calendarScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
    }

    // Заполнение первой даты с последующей отменой
    public void fillTheStartDateAndCancel() {
        advancedFilterNewsScreenElements.getDateStart().perform(click());
        calendarScreenSteps.clickOnCancelButton();
        SystemClock.sleep(2000);
    }

    // Заполнение второй даты значением сегодняшнего дня
    public void fillTheEndDateToday() {
        advancedFilterNewsScreenElements.getDateEnd().perform(click());
        calendarScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
    }

    // Заполнение первой даты значением вчерашнего дня
    public void fillTheStartDateYesterday() {
        advancedFilterNewsScreenElements.getDateStart().perform(replaceText(dateYesterday()));
        SystemClock.sleep(2000);
    }

    // Заполнение второй даты значением вчерашнего дня
    public void fillTheEndDateYesterday() {
        advancedFilterNewsScreenElements.getDateEnd().perform(replaceText(dateYesterday()));
        SystemClock.sleep(2000);
    }

    // Заполнение второй даты значением завтрашнего дня дня
    public void fillTheEndDateTomorrow() {
        advancedFilterNewsScreenElements.getDateEnd().perform(replaceText(dateTomorrow()));
        SystemClock.sleep(2000);
    }

    // Заполнение второй даты значением через неделю
    public void fillTheEndDateInOneWeek() {
        advancedFilterNewsScreenElements.getDateEnd().perform(replaceText(dateInOneWeek()));
        SystemClock.sleep(2000);
    }

    // Заполнение второй даты с последующей отменой
    public void fillTheEndDateAndCancel() {
        advancedFilterNewsScreenElements.getDateEnd().perform(click());
        calendarScreenSteps.clickOnCancelButton();
        SystemClock.sleep(2000);
    }

    // Нажатие на чек-бокс Активна
    public void clickOnActiveCheckBox() {
        advancedFilterNewsScreenElements.getCheckBoxActive().perform(click());
        SystemClock.sleep(2000);
    }

    // Нажатие на чек-бокс Не активна
    public void clickOnInactiveCheckBox() {
        advancedFilterNewsScreenElements.getCheckBoxInactive().perform(click());
        SystemClock.sleep(2000);
    }

    // Нажатие на кнопку Фильтровать
    public void clickOnFilterButton() {
        advancedFilterNewsScreenElements.getFilterButton().perform(click());
        SystemClock.sleep(2000);
    }

    // Нажатие на кнопку Отмена
    public void clickOnCancelButton() {
        advancedFilterNewsScreenElements.getCancelButton().perform(click());
        SystemClock.sleep(2000);
    }

    // Проверка, что в поле первая дата отсутствует значение
    public void checkTheFieldStartDateIsEmpty() {
        startDateText().matches("");
    }

    // Проверка, что в поле вторая дата отсутствует значение
    public void checkTheFieldEndDateIsEmpty() {
        endDateText().matches("");
    }

    // Проверка отображения поп-апа с предупреждающим сообщением
    public void checkPopupIsVisible(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }


    // Получение текстовых данных из формы фильтрации новостей

    public String categoryText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_category_text_auto_complete_text_view)));
    }

    public String startDateText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
    }

    public String endDateText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
    }

}

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
import static org.junit.Assert.assertEquals;

import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentHour;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentMinute;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentTimeMinusOneHour;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentTimePlusOneHour;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateAYearAgo;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateInOneWeek;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateTomorrow;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateYesterday;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomInvalidHour;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomInvalidMinute;

import androidx.annotation.NonNull;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.CalendarScreenElements;
import ru.iteco.fhmandroid.ui.elements.CreateNewsScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CreateNewsScreenSteps {

    CreateNewsScreenElements createNewsScreenElements = new CreateNewsScreenElements();
    CalendarScreenElements calendarScreenElements = new CalendarScreenElements();
    CalendarScreenSteps calendarScreenSteps = new CalendarScreenSteps();
    WatchScreenSteps watchScreenSteps = new WatchScreenSteps();

    // Проверка отображения экрана создания новости
    public void checkCreateNewsScreenIsDisplayed() {
        createNewsScreenElements.getCreateTitle().check(matches(isDisplayed()));
        createNewsScreenElements.getNewsTitle().check(matches(isDisplayed()));
    }

    // Выбор категории из списка
    public void chooseCategoryFromDropdown(@NonNull AppActivity activity, String category) {
        createNewsScreenElements.getCategory().perform(click());
        createNewsScreenElements.getCategory().perform(closeSoftKeyboard());
        onView(withText(category)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
    }

    // Заполнение поля Категория
    public void fillTheFieldCategory(String category) {
        createNewsScreenElements.getCategory().perform(replaceText(category));
    }

    // Заполнение поля Заголовок
    public void fillTheFieldHeader(String text) {
        createNewsScreenElements.getHeader().perform(replaceText(text));
    }

    // Заполнение поля Дата публикации значением сегодняшнего дня
    public void fillTheFieldDateToday() {
        createNewsScreenElements.getPublicationDate().perform(click());
        calendarScreenSteps.clickOnOkButton();
    }

    // Заполнение поля Дата значением вчерашний день
    public void fillTheFieldDateYesterday() {
        createNewsScreenElements.getPublicationDate().perform(replaceText(dateYesterday()));
    }

    // Заполнение поля Дата публикации значением завтрашний день
    public void fillTheFieldDateTomorrow() {
        createNewsScreenElements.getPublicationDate().perform(replaceText(dateTomorrow()));
    }

    // Заполнение поля Дата значением через неделю
    public void fillTheFieldDateInOneWeek() {
        createNewsScreenElements.getPublicationDate().perform(replaceText(dateInOneWeek()));
    }

    // Заполнение поля Дата значением
    public void fillTheFieldDate(String date) {
        createNewsScreenElements.getPublicationDate().perform(replaceText(date));
    }

    // Заполнение поля Дата публикации значением год назад
    public void fillTheFieldDateAYearAgo() {
        createNewsScreenElements.getPublicationDate().perform(replaceText(dateAYearAgo()));
    }

    // Заполнение поля Время значением текущего времени
    public void fillTheFieldTimeCurrentTime() {
        createNewsScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(currentHour());
        watchScreenSteps.settingMinute(currentMinute());
        watchScreenSteps.clickOnOkButton();
    }

    // Заполнение поля Время значением времени на час вперед от текущего
    public void fillTheFieldTimeCurrentTimePlusOneHour() {
        createNewsScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(currentTimePlusOneHour());
        watchScreenSteps.settingMinute(currentMinute());
        watchScreenSteps.clickOnOkButton();
    }

    // Заполнение поля Время значением времени на час назад от текущего
    public void fillTheFieldTimeCurrentTimeMinusOneHour() {
        createNewsScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(currentTimeMinusOneHour());
        watchScreenSteps.settingMinute(currentMinute());
        watchScreenSteps.clickOnOkButton();
    }

    // Заполнение поля Время значением несуществующего времени
    public void fillTheFieldTimeInvalidTime() {
        createNewsScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(randomInvalidHour());
        watchScreenSteps.settingMinute(randomInvalidMinute());
        watchScreenSteps.clickOnOkButton();
    }

    // Заполнение поля Описание
    public void fillTheFieldDescription(String text) {
        createNewsScreenElements.getDescription().perform(replaceText(text));
    }

    // Нажатие на тумблер Активна
    public void clickOnSwitcher() {
        createNewsScreenElements.getSwitcher().perform(click());
    }

    // Проверка, что тумблер стоит на Активна
    public void checkSwitcherActive() {
        String status = DataHelper.Text.getText(onView(withId(R.id.switcher)));
        assertEquals("Активна", status);
    }

    // Нажатие на кнопку Сохранить
    public void clickOnSaveButton() {
        createNewsScreenElements.getSaveButton().perform(click());
    }

    // Нажатие на кнопку Отмена
    public void clickOnCancelButton() {
        createNewsScreenElements.getCancelButton().perform(click());
    }

    // Нажатие на кнопку ОК для подтверждения отмены создания новости
    public void clickOnOkExitButton() {
        createNewsScreenElements.getOkExitButton().perform(click());
    }

    // Проверка отображения снэка с предупреждающим сообщением
    public void checkSnackIsVisible(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }


    // Получение текстовых данных полей формы редактирования новости

    public String headerText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_title_text_input_edit_text)));
    }

    public String publicationDateText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_publish_date_text_input_edit_text)));
    }

    public String descriptionText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_description_text_input_edit_text)));
    }
}

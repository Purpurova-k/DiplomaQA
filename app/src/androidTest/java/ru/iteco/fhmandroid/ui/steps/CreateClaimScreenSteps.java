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
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateInOneMonth;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateTomorrow;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomInvalidHour;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Rand.randomInvalidMinute;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithCyrillicSymbols;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithLatinSymbols;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithSpecialSymbolsAndNumbers;

import android.os.SystemClock;

import androidx.annotation.NonNull;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.CreateClaimScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CreateClaimScreenSteps {

    CreateClaimScreenElements createClaimScreenElements = new CreateClaimScreenElements();

    CalendarScreenSteps calendarScreenSteps = new CalendarScreenSteps();
    WatchScreenSteps watchScreenSteps = new WatchScreenSteps();

    // Проверка отображения экрана создания заявки
    public void checkCreateClaimScreenIsDisplayed() {
        createClaimScreenElements.getCreateTitle().check(matches(isDisplayed()));
        createClaimScreenElements.getClaimsTitle().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Заполнение полей валидными данными
    public void fillTheFieldsWithValidData() {
        createClaimScreenElements.getTheme().perform(replaceText(textWithCyrillicSymbols(20)));
        SystemClock.sleep(3000);
        createClaimScreenElements.getDate().perform(click());
        calendarScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
        createClaimScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(DataHelper.DateTime.currentTimePlusOneHour());
        watchScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
        createClaimScreenElements.getDescription().perform(replaceText(textWithCyrillicSymbols(25)));
        SystemClock.sleep(3000);
    }

    // Заполнение поля Тема новым значением, состоящим из кириллических символов
    public void fillTheFieldThemeCyrillicSymbols(int numberOfSymbols) {
        createClaimScreenElements.getTheme().perform(replaceText(textWithCyrillicSymbols(numberOfSymbols)));
        SystemClock.sleep(3000);
    }

    // Заполнение поля Тема новым значением, состоящим из латинских символов
    public void fillTheFieldThemeLatinSymbols(int numberOfSymbols) {
        createClaimScreenElements.getTheme().perform(replaceText(textWithLatinSymbols(numberOfSymbols)));
        SystemClock.sleep(3000);
    }

    // Заполнение поля Тема новым значением, состоящим из цифр, знаков препинания и специальных символов
    public void fillTheFieldThemeSpecialSymbolsAndNumbers() {
        createClaimScreenElements.getTheme().perform(replaceText(textWithSpecialSymbolsAndNumbers(20)));
        SystemClock.sleep(3000);
    }

    // Заполнение поля Тема новым значением, состоящим из пробела
    public void fillTheFieldThemeWhiteSpace() {
        createClaimScreenElements.getTheme().perform(replaceText(" "));
        SystemClock.sleep(3000);
    }

    // Выбор исполнителя из списка
    public void chooseExecutorFromDropdown(@NonNull AppActivity activity, String executor) {
        createClaimScreenElements.getExecutor().perform(click());
        onView(withText(executor)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
        createClaimScreenElements.getExecutor().perform(closeSoftKeyboard());
        SystemClock.sleep(3000);
    }

    // Заполнение поля Исполнитель значением не из выпадающего списка
    public void fillTheFieldExecutorNotFromDropdown() {
        createClaimScreenElements.getExecutor().perform(replaceText("Ванов Иван Иванович"));
        SystemClock.sleep(3000);
    }

    // Заполнение поля Время значением текущего времени
    public void fillTheFieldTimeCurrentTime() {
        createClaimScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(currentHour());
        watchScreenSteps.settingMinute(currentMinute());
        watchScreenSteps.clickOnOkButton();
        SystemClock.sleep(3000);
    }

    // Заполнение поля Время значением прошедшего времени
    public void fillTheFieldTimePastTime() {
        createClaimScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(currentTimeMinusOneHour());
        watchScreenSteps.settingMinute(currentMinute());
        watchScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
    }

    // Заполнение поля Время значением несуществующего времени
    public void fillTheFieldTimeInvalidTime() {
        createClaimScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(randomInvalidHour());
        watchScreenSteps.settingMinute(randomInvalidMinute());
        watchScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
    }

    // Заполнение поля Дата значением завтрашний день
    public void fillTheFieldDateTomorrow() {
        createClaimScreenElements.getDate().perform(replaceText(dateTomorrow()));
        SystemClock.sleep(2000);
    }

    // Заполнение поля Дата значием даты через месяц
    public void fillTheFieldDateInOneMonth() {
        createClaimScreenElements.getDate().perform(click());
        calendarScreenSteps.clickOnNextMonthButton();
        calendarScreenSteps.setDateInMonth();
        calendarScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
        createClaimScreenElements.getDate().perform(replaceText(dateInOneMonth()));
        SystemClock.sleep(2000);
    }

    // Заполнение поля Дата значием даты через год
    public void fillTheFieldDateInOneYear() {
        createClaimScreenElements.getDate().perform(click());
        calendarScreenSteps.clickOnChooseYearButton();
        calendarScreenSteps.chooseNextYear();
        calendarScreenSteps.clickOnOkButton();
        SystemClock.sleep(2000);
    }

    // Заполнение поля Описание новым значением, состоящим из латинских символов
    public void fillTheFieldDescriptionLatinSymbols(int numberOfSymbols) {
        createClaimScreenElements.getDescription().perform(replaceText(textWithLatinSymbols(numberOfSymbols)));
        SystemClock.sleep(3000);
    }

    // Заполнение поля Описание новым значением, состоящим из цифр, знаков препинания и специальных символов
    public void fillTheFieldDescriptionSpecialSymbolsAndNumbers(int numberOfSymbols) {
        createClaimScreenElements.getDescription().perform(replaceText(textWithSpecialSymbolsAndNumbers(numberOfSymbols)));
        SystemClock.sleep(3000);
    }

    // Заполнение поля Описание новым значением, состоящим из пробела
    public void fillTheFieldDescriptionWhiteSpace() {
        createClaimScreenElements.getDescription().perform(replaceText(" "));
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопу сохранить
    public void clickOnSaveButton() {
        createClaimScreenElements.getSaveButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Отмена
    public void clickOnCancelButton() {
        createClaimScreenElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку ОК для подтверждения отмены создания заявки
    public void clickOnOkExitButton() {
        createClaimScreenElements.getOkExitButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Сравнение количества вводимых символов и символов в поле
    public void compareNumberOfEnteredSymbolsAndSymbolsInTheField() {
        String text = DataHelper.Text.getText(onView(withId(R.id.title_edit_text)));
        int textLength = text.length();
        assertEquals(50, textLength);
        SystemClock.sleep(3000);
    }

    // Проверка отображения поп-апа с предупреждающим сообщением
    public void checkPopupIsVisible(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Получение текстовых данных из формы создаваемой заявки

    public String themeText() {
        return DataHelper.Text.getText(onView(withId(R.id.title_edit_text)));
    }

    public String executorText() {
        return DataHelper.Text.getText(onView(withId(R.id.executor_drop_menu_auto_complete_text_view)));
    }

    public String dateText() {
        return DataHelper.Text.getText(onView(withId(R.id.date_in_plan_text_input_edit_text)));
    }

    public String timeText() {
        return DataHelper.Text.getText(onView(withId(R.id.time_in_plan_text_input_edit_text)));
    }

    public String descriptionText() {
        return DataHelper.Text.getText(onView(withId(R.id.description_edit_text)));
    }

}

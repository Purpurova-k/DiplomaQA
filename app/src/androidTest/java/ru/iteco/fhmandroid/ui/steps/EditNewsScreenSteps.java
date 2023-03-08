package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentMinute;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.currentTimePlusOneHour;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateTomorrow;
import static ru.iteco.fhmandroid.ui.data.DataHelper.DateTime.dateYesterday;
import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;

import androidx.annotation.NonNull;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.EditNewsScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class EditNewsScreenSteps {

    EditNewsScreenElements editNewsScreenElements = new EditNewsScreenElements();
    WatchScreenSteps watchScreenSteps = new WatchScreenSteps();

    // Проверка отображения экрана создания новости
    public void checkEditNewsScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.save_button, 3000));
        editNewsScreenElements.getEditTitle().check(matches(isDisplayed()));
        editNewsScreenElements.getNewsTitle().check(matches(isDisplayed()));
    }

    // Выбор категории из списка
    public void chooseCategoryFromDropdown(@NonNull AppActivity activity, String category) {
        editNewsScreenElements.getCategory().perform(click());
        editNewsScreenElements.getCategory().perform(closeSoftKeyboard());
        onView(withText(category)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
    }

    // Заполнение поля Категория новым значением
    public void fillTheFieldCategory(String category) {
        editNewsScreenElements.getCategory().perform(replaceText(category));
    }

    // Заполнение поля Заголовок новым значением
    public void fillTheFieldHeader(String text) {
        editNewsScreenElements.getHeader().perform(replaceText(text));
    }

    // Заполнение поля Дата значением завтрашний день
    public void fillTheFieldDateTomorrow() {
        editNewsScreenElements.getPublicationDate().perform(replaceText(dateTomorrow()));
    }

    // Заполнение поля Дата значением вчерашний день
    public void fillTheFieldDateYesterday() {
        editNewsScreenElements.getPublicationDate().perform(replaceText(dateYesterday()));
    }

    // Заполнение поля Время значением времени на час вперед от текущего
    public void fillTheFieldTimeCurrentTimePlusOneHour() {
        editNewsScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOpenKeyboardButton();
        watchScreenSteps.settingHour(currentTimePlusOneHour());
        watchScreenSteps.settingMinute(currentMinute());
        watchScreenSteps.clickOnOkButton();
    }

    // Заполнение поля Описание новым значением
    public void fillTheFieldDescription(String text) {
        editNewsScreenElements.getDescription().perform(replaceText(text));
    }

    // Нажатие на тумблер Активна
    public void clickOnSwitcher() {
        editNewsScreenElements.getSwitcher().perform(click());
    }

    // Нажатие на кнопку Сохранить
    public void clickOnSaveButton() {
        editNewsScreenElements.getSaveButton().perform(click());
    }

    // Нажатие на кнопку Отмена
    public void clickOnCancelButton() {
        editNewsScreenElements.getCancelButton().perform(click());
    }

    // Нажатие на кнопку ОК для подтверждения отмены редактирования
    public void clickOnOkExitButton() {
        editNewsScreenElements.getOkExitButton().perform(click());
    }

    // Проверка, что тумблер стоит на Активна
    public void checkSwitcherActive() {
        String status = DataHelper.Text.getText(onView(withId(R.id.switcher)));
        assertEquals("Активна", status);
    }

    // Проверка, что тумблер стоит на Не активна
    public void checkSwitcherInactive() {
        String status = DataHelper.Text.getText(onView(withId(R.id.switcher)));
        assertEquals("Не активна", status);
    }

    // Проверка появления снэка с ошибкой
    public void checkSnackIsVisible(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }


    // Получение текстовых данных полей формы редактирования новости

    public String categoryText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_category_text_auto_complete_text_view)));
    }

    public String headerText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_title_text_input_edit_text)));
    }

    public String publicationDateText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_publish_date_text_input_edit_text)));
    }

    public String timeText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_publish_time_text_input_edit_text)));
    }

    public String descriptionText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_description_text_input_edit_text)));
    }
}

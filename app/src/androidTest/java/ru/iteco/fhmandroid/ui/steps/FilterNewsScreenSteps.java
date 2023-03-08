package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;

import androidx.annotation.NonNull;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.FilterNewsScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class FilterNewsScreenSteps {

    FilterNewsScreenElements filterNewsScreenElements = new FilterNewsScreenElements();
    CalendarScreenSteps calendarScreenSteps = new CalendarScreenSteps();

    @Step("Проверка отображения экрана Фильтрация новостей")
    public void checkFilterNewsScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.filter_news_title_text_view, 3000));
        filterNewsScreenElements.getFilterTitle().check(matches(isDisplayed()));
    }

    @Step("Выбор категории из выпадающего списка")
    public void chooseCategory(@NonNull AppActivity activity, String category) {
        filterNewsScreenElements.getCategory().perform(click());
        filterNewsScreenElements.getCategory().perform(closeSoftKeyboard());
        onView(withText(category)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
    }

    @Step("Заполнение первой даты значением сегодняшнего дня")
    public void fillTheStartDateToday() {
        filterNewsScreenElements.getDateStart().perform(click());
        calendarScreenSteps.clickOnOkButton();
    }

    @Step("Заполнение второй даты значением сегодняшнего дня")
    public void fillTheEndDateToday() {
        filterNewsScreenElements.getDateEnd().perform(click());
        calendarScreenSteps.clickOnOkButton();
    }

    @Step("Нажатие на кнопку Фильтровать")
    public void clickOnFilterButton() {
        filterNewsScreenElements.getFilterButton().perform(click());
    }


    // Получение текстовых данных из формы фильтрации новостей

    public String categoryText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_category_text_auto_complete_text_view)));
    }

    public String dateEndText() {
        return DataHelper.Text.getText(onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
    }
}

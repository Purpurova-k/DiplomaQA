package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;
import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.NewsScreenElements;
import ru.iteco.fmhandroid.R;

public class NewsScreenSteps {

    NewsScreenElements newsScreenElements = new NewsScreenElements();

    @Step("Проверка отображения экрана Новости")
    public void checkNewsScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.news_list_recycler_view, 3000));
        newsScreenElements.getNewsSectionTitle().check(matches(isDisplayed()));
    }

    @Step("Нажатие на кнопку Сортировка")
    public void clickButtonSortNews() {
        onView(isRoot()).perform(waitUntilShown(R.id.sort_news_material_button, 3000));
        newsScreenElements.getSortNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку Фильтр")
    public void goToFilterNewsScreen() {
        onView(isRoot()).perform(waitUntilShown(R.id.filter_news_material_button, 3000));
        newsScreenElements.getFilterNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку Редактировать новости")
    public void goToControlPanelScreen() {
        onView(isRoot()).perform(waitUntilShown(R.id.edit_news_material_button, 3000));
        newsScreenElements.getEditNewsButton().perform(click());
    }

    @Step("Нажатие на кнопку свернуть/развернуть рядом с новостью")
    public void expandNewsDescription(int position) {
        onView(isRoot()).perform(waitUntilShown(R.id.view_news_item_image_view, 3000));
        newsScreenElements.getExpandNewsDescriptionButton().perform(actionOnItemAtPosition(position, click()));
    }

    @Step("Проверка, что видно описание новости")
    public void checkTextOfNewsDescriptionIsVisible(int position) {
        onView(withIndex(withId(R.id.news_item_description_text_view), position)).check(matches(isDisplayed()));
    }

    @Step("Проверка сортировки новостей от более старых к более новым и наоборот")
    public void checkSortingNewsIsSuccessful(String firstNewsNameBeforeSorting, String firstNewsDateBeforeSorting,
                                             String lastNewsNameBeforeSorting, String lastNewsDateBeforeSorting,
                                             String firstNewsNameAfterSorting, String firstNewsDateAfterSorting,
                                             String lastNewsNameAfterSorting, String lastNewsDateAfterSorting) {
        assertEquals(firstNewsNameBeforeSorting, lastNewsNameAfterSorting);
        assertEquals(firstNewsDateBeforeSorting, lastNewsDateAfterSorting);
        assertEquals(lastNewsNameBeforeSorting, firstNewsNameAfterSorting);
        assertEquals(lastNewsDateBeforeSorting, firstNewsDateAfterSorting);
    }

    @Step("Сравнение данных отфильтрованных новостей с введенными данными в фильтрации новостей")
    public void compareDataOfFilteredNewsAndDataForFiltering(String filterName, String filterDate, String filteredNewsName,
                                                             String filteredNewsDate) {
        assertEquals(filterName, filteredNewsName);
        assertEquals(filterDate, filteredNewsDate);
    }


    // Получение текстовых данных названия первой новости
    public String nameOfFirstNewsText() {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 0)));
    }

    // Получение текстовых данных названия последней новости
    public String nameOfLastNewsText() {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_title_text_view), 7)));
    }

    // Получение текстовых данных даты публикации первой новости
    public String dateOfFirstNewsText() {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_date_text_view), 0)));
    }

    // Получение текстовых данных даты публикации последней новости
    public String dateOfLastNewsText() {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.news_item_date_text_view), 7)));
    }
}

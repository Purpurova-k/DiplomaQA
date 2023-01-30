package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import android.os.SystemClock;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.NewsScreenElements;
import ru.iteco.fmhandroid.R;

public class NewsScreenSteps {

    NewsScreenElements newsScreenElements = new NewsScreenElements();

    // Проверка отображения экрана Новости
    public void checkNewsScreenIsDisplayed() {
        newsScreenElements.getNewsSectionTitle().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Сортировка
    public void clickButtonSortNews() {
        newsScreenElements.getSortNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Фильтр
    public void goToFilterNewsScreen() {
        newsScreenElements.getFilterNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Редактировать новости
    public void goToControlPanelScreen() {
        newsScreenElements.getEditNewsButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку свернуть/развернуть рядом с новостью
    public void expandNewsDescription(int position) {
        newsScreenElements.getExpandNewsDescriptionButton().perform(actionOnItemAtPosition(position, click()));
        SystemClock.sleep(3000);
    }

    // Проверка, что видно описание новости
    public void checkTextOfNewsDescriptionIsVisible(int position) {
        onView(withIndex(withId(R.id.news_item_description_text_view), position)).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Проверка сортировки новостей от более старых к более новым и наоборот
    public void checkSortingNewsIsSuccessful(String firstNewsNameBeforeSorting, String firstNewsDateBeforeSorting,
                                             String lastNewsNameBeforeSorting, String lastNewsDateBeforeSorting,
                                             String firstNewsNameAfterSorting, String firstNewsDateAfterSorting,
                                             String lastNewsNameAfterSorting, String lastNewsDateAfterSorting) {
        assertEquals(firstNewsNameBeforeSorting, lastNewsNameAfterSorting);
        assertEquals(firstNewsDateBeforeSorting, lastNewsDateAfterSorting);
        assertEquals(lastNewsNameBeforeSorting, firstNewsNameAfterSorting);
        assertEquals(lastNewsDateBeforeSorting, firstNewsDateAfterSorting);
    }

    // Сравнение данных отфильтрованных новостей с введенными данными в фильтрации новостей
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

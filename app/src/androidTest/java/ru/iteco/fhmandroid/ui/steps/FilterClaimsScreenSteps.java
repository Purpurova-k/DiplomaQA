package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.os.SystemClock;

import ru.iteco.fhmandroid.ui.elements.FilterClaimsScreenElements;

public class FilterClaimsScreenSteps {

    FilterClaimsScreenElements filterClaimsScreenElements = new FilterClaimsScreenElements();

    // Проверка отображения экрана Фильтрация заявок
    public void checkFilterClaimsScreenIsDisplayed() {
        filterClaimsScreenElements.getFilterTitle().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Фильтрация заявок по категории "В работе"
    public void filterClaimsInProgress() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Фильтрация заявок по категории "Открыта"
    public void filterClaimsOpened() {
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Фильтрация заявок по категории "Выполнена"
    public void filterClaimsExecuted() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getCheckBoxExecuted().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Фильтрация заявок по категории "Отменена"
    public void filterClaimsCancelled() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getCheckBoxCancelled().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Сделать все чек-боксы статусов неактивными
    public void uncheckAllStatuses() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Отмена
    public void clickOnCancelButton() {
        filterClaimsScreenElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }
}

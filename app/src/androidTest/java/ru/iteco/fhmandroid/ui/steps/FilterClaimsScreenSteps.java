package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;

import ru.iteco.fhmandroid.ui.elements.FilterClaimsScreenElements;
import ru.iteco.fmhandroid.R;

public class FilterClaimsScreenSteps {

    FilterClaimsScreenElements filterClaimsScreenElements = new FilterClaimsScreenElements();

    // Проверка отображения экрана Фильтрация заявок
    public void checkFilterClaimsScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.claim_filter_dialog_title, 3000));
        filterClaimsScreenElements.getFilterTitle().check(matches(isDisplayed()));
    }

    // Фильтрация заявок по категории "В работе"
    public void filterClaimsInProgress() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
    }

    // Фильтрация заявок по категории "Открыта"
    public void filterClaimsOpened() {
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
    }

    // Фильтрация заявок по категории "Выполнена"
    public void filterClaimsExecuted() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getCheckBoxExecuted().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
    }

    // Фильтрация заявок по категории "Отменена"
    public void filterClaimsCancelled() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getCheckBoxCancelled().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
    }

    // Сделать все чек-боксы статусов неактивными
    public void uncheckAllStatuses() {
        filterClaimsScreenElements.getCheckBoxOpened().perform(click());
        filterClaimsScreenElements.getCheckBoxInProgress().perform(click());
        filterClaimsScreenElements.getOkButton().perform(click());
    }

    // Нажатие на кнопку Отмена
    public void clickOnCancelButton() {
        filterClaimsScreenElements.getCancelButton().perform(click());
    }
}

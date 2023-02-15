package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Text.textWithCyrillicSymbols;

import androidx.annotation.NonNull;

import ru.iteco.fhmandroid.ui.elements.EditClaimScreenElements;
import ru.iteco.fmhandroid.ui.AppActivity;

public class EditClaimScreenSteps {

    EditClaimScreenElements editClaimScreenElements = new EditClaimScreenElements();
    CalendarScreenSteps calendarScreenSteps = new CalendarScreenSteps();
    WatchScreenSteps watchScreenSteps = new WatchScreenSteps();

    // Проверка отображения экрана редактирования заявки
    public void checkEditClaimScreenIsDisplayed() {
        editClaimScreenElements.getEditTitle().check(matches(isDisplayed()));
        editClaimScreenElements.getClaimsTitle().check(matches(isDisplayed()));
    }

    // Заполнение полей новыми валидными данными
    public void fillTheFieldsWithNewValidData() {
        editClaimScreenElements.getTheme().perform(replaceText(textWithCyrillicSymbols(20)));
        editClaimScreenElements.getDate().perform(click());
        calendarScreenSteps.clickOnOkButton();
        editClaimScreenElements.getTime().perform(click());
        watchScreenSteps.clickOnOkButton();
        editClaimScreenElements.getDescription().perform(replaceText(textWithCyrillicSymbols(25)));
    }

    // Выбор исполнителя из списка
    public void chooseExecutorFromDropdown(@NonNull AppActivity activity, String executor) {
        editClaimScreenElements.getExecutor().perform(click());
        onView(withText(executor))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).perform(click());
        editClaimScreenElements.getExecutor().perform(closeSoftKeyboard());
    }

    // Нажатие на кнопку Сохранить
    public void clickOnSaveButton() {
        editClaimScreenElements.getSaveButton().perform(click());
    }

    // Нажатие на кнопку Отмена
    public void clickOnCancelButton() {
        editClaimScreenElements.getCancelButton().perform(click());
    }

    // Нажатие на кнопку ОК для подтверждения отмены редактирования
    public void clickOnOkExitButton() {
        editClaimScreenElements.getOkExitButton().perform(click());
    }

    // Сравнение данных до редактирование и после
    public void compareDataBeforeEditingAndAfter(String themeClaimBefore, String themeClaimAfter, String executorClaimBefore,
                                                 String executorClaimAfter, String dateClaimBefore, String dateClaimAfter,
                                                 String timeClaimBefore, String timeClaimAfter, String descriptionClaimBefore,
                                                 String descriptionClaimAfter) {
        assertNotEquals(themeClaimBefore, themeClaimAfter);
        assertNotEquals(executorClaimBefore, executorClaimAfter);
        assertNotEquals(dateClaimBefore, dateClaimAfter);
        assertNotEquals(timeClaimBefore, timeClaimAfter);
        assertNotEquals(descriptionClaimBefore, descriptionClaimAfter);
    }

    // Сравнение данных до и после при отмененном редактировании
    public void compareDataBeforeAndAfterWhenCancelledEditing(String themeClaimBefore, String themeClaimAfter, String executorClaimBefore,
                                                 String executorClaimAfter, String dateClaimBefore, String dateClaimAfter,
                                                 String timeClaimBefore, String timeClaimAfter, String descriptionClaimBefore,
                                                 String descriptionClaimAfter) {
        assertEquals(themeClaimBefore, themeClaimAfter);
        assertEquals(executorClaimBefore, executorClaimAfter);
        assertEquals(dateClaimBefore, dateClaimAfter);
        assertEquals(timeClaimBefore, timeClaimAfter);
        assertEquals(descriptionClaimBefore, descriptionClaimAfter);
    }
}

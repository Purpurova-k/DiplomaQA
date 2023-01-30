package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Search.findTheCreatedClaim;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Search.tryToFindTheUncreatedComment;
import static ru.iteco.fhmandroid.ui.data.DataHelper.Search.tryToFindTheUncreatedClaim;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.ClaimsScreenElements;
import ru.iteco.fhmandroid.ui.elements.FilterClaimsScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class ClaimsScreenSteps {

    ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
    FilterClaimsScreenElements filterClaimsScreenElements = new FilterClaimsScreenElements();

    // Проверка отображения экрана детального описания заявки
    public void checkScreenOfDetailedDescriptionClaimIsDisplayed() {
        claimsScreenElements.getTitleLabel().check(matches(isDisplayed()));
        claimsScreenElements.getExecutorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getPlanDateLabel().check(matches(isDisplayed()));
        claimsScreenElements.getStatusLabel().check(matches(isDisplayed()));
        claimsScreenElements.getDescriptionLabel().check(matches(isDisplayed()));
        claimsScreenElements.getAuthorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getCreateDateLabel().check(matches(isDisplayed()));
        SystemClock.sleep(2000);
    }

    // Проверка отображения экрана Заявки
    public void checkClaimsScreenIsDisplayed() {
        claimsScreenElements.getClaimsSectionTitle().check(matches(isDisplayed()));
        claimsScreenElements.getFilterButton().check(matches(isDisplayed()));
        claimsScreenElements.getCreateButton().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Плюс (создать заявку)
    public void clickButtonCreateClaim() {
        claimsScreenElements.getCreateButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Фильтр
    public void goToFilterClaimsScreen() {
        claimsScreenElements.getFilterButton().perform(click());
        filterClaimsScreenElements.getFilterTitle().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Проверка статуса В работе
    public void checkStatusInProgress() {
        claimsScreenElements.getStatusInProgress().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Проверка статуса Открыта
    public void checkStatusOpened() {
        claimsScreenElements.getStatusOpened().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Проверка статуса Выполнена
    public void checkStatusExecuted() {
        claimsScreenElements.getStatusExecuted().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Проверка статуса Отменена
    public void checkStatusCanceled() {
        claimsScreenElements.getStatusCancelled().check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Нажатие на первую заявку в списке
    public void clickOnClaim() {
        claimsScreenElements.getListOfClaims().perform(actionOnItemAtPosition(0, click()));
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку добавления комментария
    public void clickButtonAddCommentToClaim() {
        claimsScreenElements.getAddCommentButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Редактировать рядом с комментарием
    public void clickButtonEditComment(int position) {
        claimsScreenElements.setEditCommentButton(position);
        claimsScreenElements.getEditCommentButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку редактирования статуса заявки
    public void clickButtonEditStatusOfClaim() {
        claimsScreenElements.getEditStatusOfClaimButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на Сбросить
    public void clickTrowOff() {
        claimsScreenElements.getThrowOff().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на Исполнить
    public void clickExecute() {
        claimsScreenElements.getExecute().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на В работу
    public void clickTakeToWork() {
        claimsScreenElements.getTakeToWork().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на Отменить
    public void clickCancel() {
        claimsScreenElements.getCancel().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку Редактировать заявку
    public void clickButtonEditClaim() {
        claimsScreenElements.getEditClaimButton().perform(click());
    }

    // Нажатие на стрелку Назад
    public void clickButtonReturn() {
        claimsScreenElements.getReturnButton().perform(click());
        SystemClock.sleep(3000);
    }

    //  Заполнение поля комментарий с последующим нажатием на ОК
    public void fillTheFieldCommentAndSave(String comment) {
        claimsScreenElements.getCommentField().perform(replaceText(comment));
        SystemClock.sleep(3000);
        claimsScreenElements.getOkButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Заполнение поля комментарий с последующим нажатием на Отмена
    public void fillTheFieldCommentAndCancel(String comment) {
        claimsScreenElements.getCommentField().perform(replaceText(comment));
        SystemClock.sleep(3000);
        claimsScreenElements.getCancelButton().perform(click());
        SystemClock.sleep(3000);
    }

    // Нажатие на кнопку ОК без заполнения значением поля комментарий
    public void clickOkButtonWithoutComment() {
        claimsScreenElements.getOkButton().perform(click());
    }

    // Проверка добавленного комментария
    public void checkAddedCommentIsVisible(String comment) {
        onView(allOf(withId(R.id.comment_description_text_view), withText(comment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(comment.trim()))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Проверка, что комментарий не создался
    public void checkNotAddedCommentNotVisible(String comment) {
        assertNotEquals(comment, tryToFindTheUncreatedComment(comment.trim()));
        SystemClock.sleep(3000);
    }

    // Проверка комментария до редактирования и после
    public void checkCommentWasEdited(String comment) {
        onView(allOf(withId(R.id.comment_description_text_view), withText(comment),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(comment))).check(matches(isDisplayed()));
    }

    // Проверка отсутствия комментария
    public void checkTheAbsenceOfComment(String comment) {
        ViewInteraction commentField = onView(allOf(withId(R.id.comment_description_text_view), withParent(
                withParent(allOf(withId(R.id.claim_comments_list_recycler_view), withChild(
                        withChild(allOf(withText(comment)))))))));
        claimsScreenElements.getLastComment().check(matches(not(withText(commentField.toString()))));
        SystemClock.sleep(3000);
    }

    // Поиск созданной претензии
    public void findCreatedClaim(String text) {
        findTheCreatedClaim(text);
        SystemClock.sleep(3000);
    }

    // Проверка отсутствия несозданной претензии
    public void checkTheAbsenceOfUncreatedClaim(String text) {
        assertNotEquals(text, tryToFindTheUncreatedClaim(text));
        SystemClock.sleep(3000);
    }

    // Сравнение данных у созданной и найденной претензий
    public void compareDataOfCreatedAndFoundClaims(String themeCreated, String themeFound, String executorCreated,
                                                   String executorFound,String dateCreated, String dateFound,
                                                   String timeCreated, String timeFound, String descriptionCreated,
                                                   String descriptionFound) {
        assertEquals(themeCreated, themeFound);
        assertEquals(executorCreated, executorFound);
        assertEquals(dateCreated, dateFound);
        assertEquals(timeCreated, timeFound);
        assertEquals(descriptionCreated, descriptionFound);
    }

    // Сравнение названий заявок до и после отмененной фильтрации
    public void compareDataBeforeAndAfterWhenCancelledFilter(String themeOneBefore, String themeOneAfter,
                                                             String themeTwoBefore, String themeTwoAfter,
                                                             String themeThreeBefore, String themeThreeAfter) {
        assertEquals(themeOneBefore, themeOneAfter);
        assertEquals(themeTwoBefore, themeTwoAfter);
        assertEquals(themeThreeBefore, themeThreeAfter);
    }

    // Свайп наверх в разделе комментарии
    public void swipeUpInComments() {
        claimsScreenElements.getCreateDateLabel().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        claimsScreenElements.getCreateDateLabel().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        claimsScreenElements.getCreateDateLabel().perform(swipeUp()).perform(swipeUp()).perform(swipeUp());
        SystemClock.sleep(3000);
    }

    // Свайп вниз в разделе комментарии
    public void swipeToTopInComments() {
        claimsScreenElements.getAddCommentButton().perform(swipeDown()).perform(swipeDown()).perform(swipeDown());
        claimsScreenElements.getCreateDateLabel().perform(swipeDown()).perform(swipeDown()).perform(swipeDown());
        SystemClock.sleep(3000);
    }

    // Проверка отображения снэка с ошибкой
    public void checkSnackIsVisible(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }

    // Проверка отображения экрана с надписью "Здесь пока ничего нет"
    public void checkEmptyScreenIsDisplayed() {
        onView(withText(startsWith("Здесь пока ничего нет"))).check(matches(isDisplayed()));
        SystemClock.sleep(3000);
    }


    // Получение текстовых данных из карточки заявки

    public String themeText() {
        return DataHelper.Text.getText(onView(withId(R.id.title_text_view)));
    }

    public String executorText() {
        return DataHelper.Text.getText(onView(withId(R.id.executor_name_text_view)));
    }

    public String planDateText() {
        return DataHelper.Text.getText(onView(withId(R.id.plane_date_text_view)));
    }

    public String planTimeText() {
        return DataHelper.Text.getText(onView(withId(R.id.plan_time_text_view)));
    }

    public String descriptionText() {
        return DataHelper.Text.getText(onView(withId(R.id.description_text_view)));
    }

    // Получение текстовых данных из карточки заявки в списке всех заявок
    public String nameClaimText(int position) {
        return DataHelper.Text.getText(onView(withIndex(withId(R.id.title_material_text_view), position)));
    }
}
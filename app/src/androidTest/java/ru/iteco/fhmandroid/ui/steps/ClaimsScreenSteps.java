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
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
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
import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fhmandroid.ui.data.DataHelper;
import ru.iteco.fhmandroid.ui.elements.ClaimsScreenElements;
import ru.iteco.fhmandroid.ui.elements.FilterClaimsScreenElements;
import ru.iteco.fhmandroid.ui.matchers.ToastMatcher;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class ClaimsScreenSteps {

    ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();
    FilterClaimsScreenElements filterClaimsScreenElements = new FilterClaimsScreenElements();

    // Проверка отображения экрана детального описания заявки
    public void checkScreenOfDetailedDescriptionClaimIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.title_label_text_view, 3000));
        claimsScreenElements.getTitleLabel().check(matches(isDisplayed()));
        claimsScreenElements.getExecutorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getPlanDateLabel().check(matches(isDisplayed()));
        claimsScreenElements.getStatusLabel().check(matches(isDisplayed()));
        claimsScreenElements.getDescriptionLabel().check(matches(isDisplayed()));
        claimsScreenElements.getAuthorLabel().check(matches(isDisplayed()));
        claimsScreenElements.getCreateDateLabel().check(matches(isDisplayed()));
    }

    // Проверка отображения экрана Заявки
    public void checkClaimsScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.claim_list_recycler_view, 3000));
        claimsScreenElements.getClaimsSectionTitle().check(matches(isDisplayed()));
        claimsScreenElements.getFilterButton().check(matches(isDisplayed()));
        claimsScreenElements.getCreateButton().check(matches(isDisplayed()));
    }

    // Нажатие на кнопку Плюс (создать заявку)
    public void clickButtonCreateClaim() {
        claimsScreenElements.getCreateButton().perform(click());
    }

    // Нажатие на кнопку Фильтр
    public void goToFilterClaimsScreen() {
        claimsScreenElements.getFilterButton().perform(click());
        filterClaimsScreenElements.getFilterTitle().check(matches(isDisplayed()));
    }

    // Проверка статуса В работе
    public void checkStatusInProgress() {
        claimsScreenElements.getStatusInProgress().check(matches(isDisplayed()));
    }

    // Проверка статуса Открыта
    public void checkStatusOpened() {
        claimsScreenElements.getStatusOpened().check(matches(isDisplayed()));
    }

    // Проверка статуса Выполнена
    public void checkStatusExecuted() {
        claimsScreenElements.getStatusExecuted().check(matches(isDisplayed()));
    }

    // Проверка статуса Отменена
    public void checkStatusCanceled() {
        claimsScreenElements.getStatusCancelled().check(matches(isDisplayed()));
    }

    // Нажатие на первую заявку в списке
    public void clickOnClaim() {
        SystemClock.sleep(1000);
        onView(isRoot()).perform(waitUntilShown(R.id.claim_list_recycler_view, 3000));
        claimsScreenElements.getListOfClaims().perform(actionOnItemAtPosition(0, click()));
    }

    // Нажатие на кнопку добавления комментария
    public void clickButtonAddCommentToClaim() {
        onView(isRoot()).perform(waitUntilShown(R.id.add_comment_image_button, 3000));
        claimsScreenElements.getAddCommentButton().perform(click());
    }

    // Нажатие на кнопку Редактировать рядом с комментарием
    public void clickButtonEditComment(int position) {
        claimsScreenElements.setEditCommentButton(position);
        claimsScreenElements.getEditCommentButton().perform(click());
    }

    // Нажатие на кнопку редактирования статуса заявки
    public void clickButtonEditStatusOfClaim() {
        onView(isRoot()).perform(waitUntilShown(R.id.edit_processing_image_button, 3000));
        claimsScreenElements.getEditStatusOfClaimButton().perform(click());
    }

    // Нажатие на Сбросить
    public void clickTrowOff() {
        claimsScreenElements.getThrowOff().perform(click());
    }

    // Нажатие на Исполнить
    public void clickExecute() {
        claimsScreenElements.getExecute().perform(click());
    }

    // Нажатие на В работу
    public void clickTakeToWork() {
        claimsScreenElements.getTakeToWork().perform(click());
    }

    // Нажатие на Отменить
    public void clickCancel() {
        claimsScreenElements.getCancel().perform(click());
    }

    // Нажатие на кнопку Редактировать заявку
    public void clickButtonEditClaim() {
        claimsScreenElements.getEditClaimButton().perform(click());
    }

    // Нажатие на стрелку Назад
    public void clickButtonReturn() {
        claimsScreenElements.getReturnButton().perform(click());
    }

    //  Заполнение поля комментарий с последующим нажатием на ОК
    public void fillTheFieldCommentAndSave(String comment) {
        onView(isRoot()).perform(waitUntilShown(R.id.editText, 3000));
        claimsScreenElements.getCommentField().perform(replaceText(comment));
        claimsScreenElements.getOkButton().perform(click());
    }

    // Заполнение поля комментарий с последующим нажатием на Отмена
    public void fillTheFieldCommentAndCancel(String comment) {
        onView(isRoot()).perform(waitUntilShown(R.id.editText, 3000));
        claimsScreenElements.getCommentField().perform(replaceText(comment));
        claimsScreenElements.getCancelButton().perform(click());
    }

    // Нажатие на кнопку ОК без заполнения значением поля комментарий
    public void clickOkButtonWithoutComment() {
        onView(isRoot()).perform(waitUntilShown(R.id.editText, 3000));
        claimsScreenElements.getOkButton().perform(click());
    }

    // Проверка добавленного комментария
    public void checkAddedCommentIsVisible(String comment) {
        onView(allOf(withId(R.id.comment_description_text_view), withText(comment.trim()),
                withParent(withParent(withId(R.id.claim_comments_list_recycler_view))))).check(matches(withText(comment.trim()))).check(matches(isDisplayed()));
    }

    // Проверка, что комментарий не создался
    public void checkNotAddedCommentNotVisible(String comment) {
        assertNotEquals(comment, tryToFindTheUncreatedComment(comment.trim()));
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
    }

    // Поиск созданной претензии
    public void findCreatedClaim(String text) {
        findTheCreatedClaim(text);
    }

    // Проверка отсутствия несозданной претензии
    public void checkTheAbsenceOfUncreatedClaim(String text) {
        assertNotEquals(text, tryToFindTheUncreatedClaim(text));
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
    }

    // Свайп вниз в разделе комментарии
    public void swipeToTopInComments() {
        claimsScreenElements.getAddCommentButton().perform(swipeDown()).perform(swipeDown()).perform(swipeDown());
        claimsScreenElements.getCreateDateLabel().perform(swipeDown()).perform(swipeDown()).perform(swipeDown());
    }

    // Проверка отображения снэка с ошибкой
    public void checkSnackIsVisible(String text) {
        onView(withText(text)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    // Проверка отображения экрана с надписью "Здесь пока ничего нет"
    public void checkEmptyScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.empty_claim_list_text_view, 3000));
        onView(withText(startsWith("Здесь пока ничего нет"))).check(matches(isDisplayed()));
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

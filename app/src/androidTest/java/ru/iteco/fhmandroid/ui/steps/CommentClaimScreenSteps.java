package ru.iteco.fhmandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;

import androidx.annotation.NonNull;

import ru.iteco.fhmandroid.ui.elements.CommentClaimScreenElements;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class CommentClaimScreenSteps {

    CommentClaimScreenElements commentClaimScreenElements = new CommentClaimScreenElements();

    // Проверка отображения экрана добавления комментария к заявке
    public void checkCommentClaimScreenIsDisplayed() {
        onView(isRoot()).perform(waitUntilShown(R.id.comment_text_input_layout, 3000));
        commentClaimScreenElements.getCommentField().check(matches(isDisplayed()));
        commentClaimScreenElements.getSaveButton().check(matches(isDisplayed()));
        commentClaimScreenElements.getCancelButton().check(matches(isDisplayed()));
    }

    // Заполнение значением поля комментарий с последующим сохранением
    public void fillTheFieldCommentAndSave(String comment) {
        commentClaimScreenElements.getCommentField().perform(replaceText(comment));
        commentClaimScreenElements.getSaveButton().perform(click());
    }

    // Заполнение значением поля комментарий с последующей отменой
    public void fillTheFieldCommentAndCancel(String comment) {
        commentClaimScreenElements.getCommentField().perform(replaceText(comment));
        commentClaimScreenElements.getCancelButton().perform(click());
    }

    // Нажатие на кнопку Сохранить без заполнения значением поля комментарий
    public void clickSaveButtonWithoutComment() {
        commentClaimScreenElements.getSaveButton().perform(click());
    }

    // Проверка отображения снэка с ошибкой "Поле не может быть пустым"
    public void checkSnackIsVisible(@NonNull AppActivity activity, String text) {
        onView(withText(text)).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}

package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fhmandroid.ui.data.DataHelper.childAtPosition;
import static ru.iteco.fhmandroid.ui.data.DataHelper.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class ClaimsScreenElements {

    private final ViewInteraction titleLabel = onView(withId(R.id.title_label_text_view));
    private final ViewInteraction executorLabel = onView(withId(R.id.executor_name_label_text_view));
    private final ViewInteraction planDateLabel = onView(withId(R.id.plane_date_label_text_view));
    private final ViewInteraction statusLabel = onView(withId(R.id.status_label_text_view));
    private final ViewInteraction descriptionLabel = onView(withId(R.id.description_text_view));
    private final ViewInteraction authorLabel = onView(withId(R.id.author_label_text_view));
    private final ViewInteraction createDateLabel = onView(withId(R.id.create_data_label_text_view));
    private final ViewInteraction listOfComments = onView(withId(R.id.claim_comments_list_recycler_view));

    private final ViewInteraction claimsSectionTitle = onView(withText("Заявки"));
    private final ViewInteraction filterButton = onView(withId(R.id.filters_material_button));
    private final ViewInteraction createButton = onView(withId(R.id.add_new_claim_material_button));

    private final ViewInteraction listOfClaims = onView(allOf(withId(R.id.claim_list_recycler_view),
                    childAtPosition(withClassName(is("android.widget.LinearLayout")),
                            withId(R.id.all_claims_cards_block_constraint_layout), 4)));

    private final ViewInteraction addCommentButton = onView(withId(R.id.add_comment_image_button));

    private final ViewInteraction statusOpened = onView(allOf(withId(R.id.status_label_text_view),
            withText("Открыта")));
    private final ViewInteraction statusInProgress = onView(allOf(withId(R.id.status_label_text_view),
            withText("В работе")));
    private final ViewInteraction statusExecuted = onView(allOf(withId(R.id.status_label_text_view),
            withText("Выполнена")));
    private final ViewInteraction statusCancelled = onView(allOf(withId(R.id.status_label_text_view),
            withText("Отменена")));

    private ViewInteraction editCommentButton = onView(
            allOf(withId(R.id.edit_comment_image_button), withContentDescription("кнопка редактирования комментария"),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")), withId(R.id.claim_comments_list_recycler_view),
                                    0),
                            1)));

    private final ViewInteraction lastComment = onView(withIndex(withId(R.id.claim_comments_list_recycler_view), 0));

    private final ViewInteraction editStatusOfClaimButton = onView(withId(R.id.status_processing_image_button));
    private final ViewInteraction editClaimButton = onView(withId(R.id.edit_processing_image_button));
    private final ViewInteraction returnButton = onView(withId(R.id.close_image_button));

    private final ViewInteraction throwOff = onView(allOf(withText("Сбросить"), childAtPosition(
            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                    withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                    0),
            0)));

    private final ViewInteraction execute = onView(allOf(withText("Исполнить"),
            childAtPosition(
                    withClassName(is("android.widget.LinearLayout")), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                            0),
                    0)));

    private final ViewInteraction takeToWork = onView(allOf(withId(android.R.id.title), withText("В работу"),
            childAtPosition(
                    withClassName(is("android.widget.LinearLayout")), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                            0),
                    0)));

    private final ViewInteraction cancel = onView(allOf(withId(android.R.id.title), withText("Отменить"),
            childAtPosition(
                    withClassName(is("android.widget.LinearLayout")), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(android.R.id.content),
                            0),
                    0)));

    private final ViewInteraction commentField = onView(withId(R.id.editText));
    private final ViewInteraction okButton = onView(withText("ОК"));
    private final ViewInteraction cancelButton = onView(withText("ОТМЕНА"));

    public ViewInteraction getTitleLabel() {
        return titleLabel;
    }
    public ViewInteraction getExecutorLabel() {
        return executorLabel;
    }
    public ViewInteraction getPlanDateLabel() {
        return planDateLabel;
    }
    public ViewInteraction getStatusLabel() {
        return statusLabel;
    }
    public ViewInteraction getDescriptionLabel() {
        return descriptionLabel;
    }
    public ViewInteraction getAuthorLabel() {
        return authorLabel;
    }
    public ViewInteraction getCreateDateLabel() {
        return createDateLabel;
    }
    public ViewInteraction getListOfComments() {
        return listOfComments;
    }
    public ViewInteraction getClaimsSectionTitle() {
        return claimsSectionTitle;
    }
    public ViewInteraction getFilterButton() {
        return filterButton;
    }
    public ViewInteraction getCreateButton() {
        return createButton;
    }
    public ViewInteraction getListOfClaims() {
        return listOfClaims;
    }
    public ViewInteraction getAddCommentButton() {
        return addCommentButton;
    }
    public ViewInteraction getStatusOpened() {
        return statusOpened;
    }
    public ViewInteraction getStatusInProgress() {
        return statusInProgress;
    }
    public ViewInteraction getStatusExecuted() {
        return statusExecuted;
    }
    public ViewInteraction getStatusCancelled() {
        return statusCancelled;
    }
    public ViewInteraction getEditCommentButton() {
        return editCommentButton;
    }
    public ViewInteraction getLastComment() {
        return lastComment;
    }
    public ViewInteraction getEditStatusOfClaimButton() {
        return editStatusOfClaimButton;
    }
    public ViewInteraction getEditClaimButton() {
        return editClaimButton;
    }
    public ViewInteraction getReturnButton() {
        return returnButton;
    }
    public ViewInteraction getThrowOff() {
        return throwOff;
    }
    public ViewInteraction getExecute() {
        return execute;
    }
    public ViewInteraction getTakeToWork() {
        return takeToWork;
    }
    public ViewInteraction getCancel() {
        return cancel;
    }
    public ViewInteraction getCommentField() {
        return commentField;
    }
    public ViewInteraction getOkButton() {
        return okButton;
    }
    public ViewInteraction getCancelButton() {
        return cancelButton;
    }
    public void setEditCommentButton(int position) {
        this.editCommentButton = onView(
                allOf(withId(R.id.edit_comment_image_button), withContentDescription("кнопка редактирования комментария"),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")), withId(R.id.claim_comments_list_recycler_view),
                                        position),
                                1)));
    }
}

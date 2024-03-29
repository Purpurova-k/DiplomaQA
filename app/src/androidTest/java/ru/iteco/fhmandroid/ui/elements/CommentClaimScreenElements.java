package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CommentClaimScreenElements {

    private final ViewInteraction commentField = onView(withHint("Комментарий"));
    private final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

    public ViewInteraction getCommentField() {
        return commentField;
    }
    public ViewInteraction getSaveButton() {
        return saveButton;
    }
    public ViewInteraction getCancelButton() {
        return cancelButton;
    }
}

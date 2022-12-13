package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainScreenElements {
    private final ViewInteraction logoImage = onView(withId(R.id.trademark_image_view));

    private final ViewInteraction avatarImageButton = onView(withId(R.id.authorization_image_button));

    private final ViewInteraction exitButton = onView(withText("Выйти"));

    public ViewInteraction getLogoImage() {
        return logoImage;
    }

    public ViewInteraction getAvatarImageButton() { return avatarImageButton; }

    public ViewInteraction getExitButton() { return exitButton; }
}

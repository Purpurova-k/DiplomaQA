package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutScreenElements {

    private final ViewInteraction version = onView(withId(R.id.about_version_title_text_view));
    private final ViewInteraction numberVersion = onView(withId(R.id.about_version_value_text_view));
    private final ViewInteraction privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    private final ViewInteraction termsOfUseLink = onView(withId(R.id.about_terms_of_use_value_text_view));
    private final ViewInteraction backButton = onView(withId(R.id.about_back_image_button));

    public ViewInteraction getVersion() { return version; }
    public ViewInteraction getNumberVersion() { return numberVersion; }
    public ViewInteraction getPrivacyPolicyLink() {
        return privacyPolicyLink;
    }
    public ViewInteraction getTermsOfUseLink() {
        return termsOfUseLink;
    }
    public ViewInteraction getBackButton() {
        return backButton;
    }

}

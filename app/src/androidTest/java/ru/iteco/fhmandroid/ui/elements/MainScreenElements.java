package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.is;
import static ru.iteco.fhmandroid.ui.data.DataHelper.childAtPosition;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainScreenElements {

    private final ViewInteraction logoImage = onView(withId(R.id.trademark_image_view));
    private final ViewInteraction avatarImageButton = onView(withId(R.id.authorization_image_button));
    private final ViewInteraction exitButton = onView(withText("Выйти"));
    private final ViewInteraction mainMenuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction sectionMain = onView(withText("Главная"));
    private final ViewInteraction sectionClaims = onView(withText("Заявки"));
    private final ViewInteraction sectionNews = onView(withText("Новости"));
    private final ViewInteraction sectionAbout = onView(withText("О приложении"));
    private final ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));

    private final ViewInteraction expandNewsFeedButton = onView(allOf(withId(R.id.expand_material_button),
            childAtPosition(withClassName(is("android.widget.LinearLayout")), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.container_list_news_include_on_fragment_main),
                            0),
                    4),
            isDisplayed()));

    private final ViewInteraction expandNewsDescriptionButton = onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(
                    withClassName(is("android.widget.LinearLayout")), withId(R.id.all_news_cards_block_constraint_layout),
                    0)));

    private final ViewInteraction allClaimsButton = onView(withId(R.id.all_claims_text_view));

    private final ViewInteraction expandClaimsFeedButton = onView(allOf(withId(R.id.expand_material_button),
            childAtPosition(withClassName(is("android.widget.LinearLayout")), childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.container_list_claim_include_on_fragment_main),
                            0),
                    3)));

    private final ViewInteraction createClaimButton = onView(withId(R.id.add_new_claim_material_button));

    private final ViewInteraction listOfClaims = onView(
            allOf(withId(R.id.claim_list_recycler_view),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), withId(R.id.all_claims_cards_block_constraint_layout),
                            4)));

    private final ViewInteraction quotesButton = onView(withId(R.id.our_mission_image_button));

    public ViewInteraction getLogoImage() {
        return logoImage;
    }
    public ViewInteraction getAvatarImageButton() {
        return avatarImageButton;
    }
    public ViewInteraction getExitButton() {
        return exitButton;
    }
    public ViewInteraction getMainMenuButton() {
        return mainMenuButton;
    }
    public ViewInteraction getSectionMain() {
        return sectionMain;
    }
    public ViewInteraction getSectionClaims() {
        return sectionClaims;
    }
    public ViewInteraction getSectionNews() {
        return sectionNews;
    }
    public ViewInteraction getSectionAbout() {
        return sectionAbout;
    }
    public ViewInteraction getAllNewsButton() {
        return allNewsButton;
    }
    public ViewInteraction getExpandNewsFeedButton() {
        return expandNewsFeedButton;
    }
    public ViewInteraction getExpandNewsDescriptionButton() {
        return expandNewsDescriptionButton;
    }
    public ViewInteraction getAllClaimsButton() {
        return allClaimsButton;
    }
    public ViewInteraction getExpandClaimsFeedButton() {
        return expandClaimsFeedButton;
    }
    public ViewInteraction getCreateClaimButton() {
        return createClaimButton;
    }
    public ViewInteraction getListOfClaims() {
        return listOfClaims;
    }
    public ViewInteraction getQuotesButton() {
        return quotesButton;
    }
}

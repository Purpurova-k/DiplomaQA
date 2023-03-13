package ru.iteco.fhmandroid.ui.custom;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fhmandroid.ui.data.DataHelper.validLoginAndPassword;
import static ru.iteco.fhmandroid.ui.data.DataHelper.waitUntilShown;

import androidx.test.espresso.PerformException;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fhmandroid.ui.steps.AuthorizationScreenSteps;
import ru.iteco.fhmandroid.ui.steps.MainScreenSteps;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public abstract class BaseAndroidTest {

    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule = new ActivityTestRule<>(AppActivity.class);

    AuthorizationScreenSteps authorizationScreenSteps = new AuthorizationScreenSteps();
    MainScreenSteps mainScreenSteps = new MainScreenSteps();

    @Before
    public void checkLogout() {
        onView(isRoot()).perform(waitUntilShown(R.id.container_custom_app_bar_include_on_fragment_main, 8000));
        try {
            mainScreenSteps.checkMainScreenIsDisplayed();
        } catch (PerformException e) {
            onView(isRoot()).perform(waitUntilShown(R.id.enter_button, 3000));
            authorizationScreenSteps.validLoginAndPasswordAuthorization(validLoginAndPassword());
        }
    }
}

package ru.iteco.fhmandroid.ui.elements;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;


public class AuthorizationScreenElements {

    private final ViewInteraction loginField = onView(withHint("Логин"));
    private final ViewInteraction passwordField = onView(withHint("Пароль"));
    private final ViewInteraction loginButton = onView(withId(R.id.enter_button));
    private final ViewInteraction authorizationHeader = onView(withText("Авторизация"));
//    private final ViewInteraction snackWrongLoginOrPassword = onView(withId(R.string.wrong_login_or_password));

    public ViewInteraction getLoginField() {
        return loginField;
    }
    public ViewInteraction getPasswordField() {
        return passwordField;
    }
    public ViewInteraction getLoginButton() {
        return loginButton;
    }
    public ViewInteraction getAuthorizationHeader() {
        return authorizationHeader;
    }
//    public ViewInteraction getSnackWrongLoginOrPassword() { return snackWrongLoginOrPassword; }
}

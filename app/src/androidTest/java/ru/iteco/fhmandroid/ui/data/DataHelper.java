package ru.iteco.fhmandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Description;

import java.util.Random;

import ru.iteco.fhmandroid.ui.elements.ClaimsScreenElements;
import ru.iteco.fmhandroid.R;

public class DataHelper {

    private DataHelper() {
    }

    public static class AuthInfo {
        private final String login;
        private final String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo validLoginAndPassword() {
        String login = "login2";
        String password = "password2";
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidLogin() {
        String login = "login3";
        String password = "password2";
        return new AuthInfo(login, password);
    }

    public static AuthInfo invalidPassword() {
        String login = "login2";
        String password = "password3";
        return new AuthInfo(login, password);
    }

    public static AuthInfo loginWithWhitespace() {
        String login = " login2 ";
        String password = "password2";
        return new AuthInfo(login, password);
    }

    public static AuthInfo passwordWithWhitespace() {
        String login = "login2";
        String password = " password2 ";
        return new AuthInfo(login, password);
    }

    public static AuthInfo emptyLogin() {
        String login = "";
        String password = " password2 ";
        return new AuthInfo(login, password);
    }

    public static AuthInfo emptyPassword() {
        String login = "login2";
        String password = "";
        return new AuthInfo(login, password);
    }

    public static String snackWrongLoginOrPassword = "Неверный логин или пароль";

    public static String snackEmptyLoginOrPassword = "Логин и пароль не могут быть пустыми";

    public static String snackEmptyField = "Поле не может быть пустым";


    public static class Text {

        public static String textWithCyrillicSymbols(int numberOfLetters) {
            Random random = new Random();
            String text;
            String[] symbols = {
                    "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о",
                    "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э", "ю", "я"
            };
            text = symbols[random.nextInt(symbols.length)];
            for (int i = 1; i < numberOfLetters; i++) {
                text = text.concat(symbols[random.nextInt(symbols.length)]);
            }
            return text;
        }

        public static String textWithLatinSymbols(int numberOfLetters) {
            Random random = new Random();
            String text;
            String[] symbols = {
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                    "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
            };
            text = symbols[random.nextInt(symbols.length)];
            for (int i = 1; i < numberOfLetters; i++) {
                text = text.concat(symbols[random.nextInt(symbols.length)]);
            }
            return text;
        }

        public static String textWithSpecialSymbolsAndNumbers(int numberOfLetters) {
            Random random = new Random();
            String text;
            String[] symbols = {
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "~", "`", "!", "@", "\"", "#", "№", "$",
                    ";", "%", ":", "^", "&", "?", "*", "(", ")", "_", "=", "+", "{", "}", "[", "]", "/",
                    "|", "\\", "'", "<", ">", ",", "."
            };
            text = symbols[random.nextInt(symbols.length)];
            for (int i = 1; i < numberOfLetters; i++) {
                text = text.concat(symbols[random.nextInt(symbols.length)]);
            }
            return text;
        }

        public static String getText(ViewInteraction matcher) {
            final String[] text = new String[1];
            ViewAction viewAction = new ViewAction() {
                @Override
                public String getDescription() {
                    return "Text of the view";
                }

                @Override
                public Matcher<View> getConstraints() {
                    return isAssignableFrom(TextView.class);
                }

                @Override
                public void perform(UiController uiController, View view) {
                    TextView textView = (TextView) view;
                    text[0] = textView.getText().toString();
                }
            };
            matcher.perform(viewAction);
            return text[0];
        }
    }

    public static Matcher<View> childAtPosition(Matcher<View> matcher, final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            protected boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }
        };
    }

    public static class Search {

        public static String searchUncreatedComment(String comment) {
            ClaimsScreenElements claimsScreenElements = new ClaimsScreenElements();

            boolean notFound = true;
            int position = 0;

            String textOfComment = Text.getText(onView(allOf(withId(R.id.comment_description_text_view),
                    withParent(withParent(withIndex(withId(R.id.claim_comments_list_recycler_view), position))))));
            while (notFound) {
                try {
                    onView(withText(comment)).check(matches(isDisplayed()));
                    notFound = false;
                } catch (NoMatchingViewException e) {
                }
                try {
                    claimsScreenElements.getListComments().check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, swipeUp()));
                    position += 1;
                } catch (PerformException e) {
                    break;
                }
            }
            return textOfComment;
        }
    }
}
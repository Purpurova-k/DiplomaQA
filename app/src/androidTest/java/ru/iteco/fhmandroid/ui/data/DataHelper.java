package ru.iteco.fhmandroid.ui.data;

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

    public static String snackWrong = "Неверный логин или пароль";

    public static String snackEmpty = "Логин и пароль не могут быть пустыми";
}


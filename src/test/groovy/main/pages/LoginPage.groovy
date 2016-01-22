package main.pages

import geb.Page

class LoginPage extends Page {

    static url = "/login"
    static at = { title == "Login" }

    static content = {
        userNameOrEmailInput { $("input", name: "login") }
        passwordInput { $("input", name: "password") }
        loginButton { $("button", text: 'Login') }


    }

    KanbanNowHomePage login(String username, String password) {
        userNameOrEmailInput.value(username)
        passwordInput.value(password)
        loginButton.click(KanbanNowHomePage)
        return browser.page
    }

}

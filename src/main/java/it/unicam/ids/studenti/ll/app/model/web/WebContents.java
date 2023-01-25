package it.unicam.ids.studenti.ll.app.model.web;

abstract class WebContents {
//    static final String indexButton = WebContents.button("/?%s", "Home");
    static final String ok = "<h1>Fatto</h1><br>";

    static String button(String href, String value) {
        return String.format(
                """
                        <button onclick="window.location.href='%s'">
                        %s
                        </button>
                        """,
                href,
                value);
    }

    static String loginParameters(String userName, String password) {
        return String.format(
                "?userName=%s&password=%s",
                userName,
                password
        );
    }
}

package md2html;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Parser {
    private final Map<String, String> tagOpen = new HashMap<>() {{
        put("*", "<em>");
        put("_", "<em>");
        put("**", "<strong>");
        put("__", "<strong>");
        put("--", "<s>");
        put("`", "<code>");
        put("''", "<q>");
    }};

    private final Map<String, String> tagClose = new HashMap<>() {{
        put("*", "</em>");
        put("_", "</em>");
        put("**", "</strong>");
        put("__", "</strong>");
        put("--", "</s>");
        put("`", "</code>");
        put("''", "</q>");
    }};

    public String parser(StringBuilder str) {
        StringBuilder result = new StringBuilder();
        int countHeader = 0;
        boolean is_title = false;
        if (str.charAt(0) == ' ') {
            result.append("<p>");
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '#') {
                    countHeader++;
                } else if (str.charAt(i) == ' ' && countHeader != 0) {
                    is_title = true;
                    result.append("<h").append(countHeader).append(">");
                    countHeader++;
                    break;
                } else {
                    result.append("<p>");
                    result.append(str.substring(0, countHeader));
                    break;
                }
            }
        }

        boolean is_emphasis = false;
        boolean is_strong = false;
        boolean is_strikeout = false;
        boolean is_code = false;
        boolean is_quote = false;

        for (int i = countHeader; i < str.length(); i++) {

            if (i + 2 < str.length() && (str.substring(i, i + 2).equals("''"))
            ) {
                if (!is_quote) {
                    if (i - 1 >= 0 && str.charAt(i - 1) == '\\') {
                        result.append("''");
                    } else {
                        is_quote = true;
                        result.append(tagOpen.get("''"));
                    }
                } else {
                    is_quote = false;
                    result.append(tagClose.get("''"));
                }
                i++;
                continue;
            }

            if (i + 2 < str.length() && is_quote && str.substring(i, i + 2).equals("''")){
                is_quote = false;
                result.append(tagClose.get("''"));
                i++;
                continue;
            }
            if (i + 2 < str.length() && (str.substring(i, i + 2).equals("**")
                    || str.substring(i, i + 2).equals("__"))
                    ) {
                if (!is_strong) {
                    if (i - 1 >= 0 && str.charAt(i - 1) == '\\') {
                        result.append(str.substring(i, i + 2));
                    } else {
                        is_strong = true;
                        result.append(tagOpen.get("**"));
                    }
                } else {
                    is_strong = false;
                    result.append(tagClose.get("**"));
                }
                i++;
                continue;
            }

            if (i + 2 < str.length() && is_strong && (str.substring(i, i + 2).equals("__")
                    || str.substring(i, i + 2).equals("**"))){
                is_strong = false;
                result.append(tagClose.get("__"));
                i++;
                continue;
            }

            if (i + 2 < str.length() && (str.substring(i, i + 2).equals("--"))
                    ) {
                if (!is_strikeout) {
                    if (i - 1 >= 0 && str.charAt(i - 1) == '\\') {
                        result.append("--");
                    } else {
                        is_strikeout = true;
                        result.append(tagOpen.get("--"));
                    }
                } else {
                    is_strikeout = false;
                    result.append(tagClose.get("--"));
                }
                i++;
                continue;
            }
            if (i + 2 < str.length() && is_strikeout && str.substring(i, i + 2).equals("--")){
                is_strikeout = false;
                result.append(tagClose.get("--"));
                i++;
                continue;
            }
            if (i + 1 != str.length() && (str.charAt(i) == '*' || str.charAt(i) == '_')
                    ) {
                if (!is_emphasis) {
                    if (i - 1 >= 0 && str.charAt(i - 1) == '\\') {
                        result.append(str.charAt(i));
                    } else {
                        if (!Character.isWhitespace(str.charAt(i + 1))) {
                            is_emphasis = true;
                            result.append(tagOpen.get("*"));
                        } else {
                            result.append(str.charAt(i));
                        }
                    }
                } else {
                    is_emphasis = false;
                    result.append(tagClose.get("*"));
                }
                continue;
            }

            if (str.charAt(i) == '`') {
                if (!is_code) {
                    is_code = true;
                    result.append(tagOpen.get("`"));
                } else {
                    is_code = false;
                    result.append(tagClose.get("`"));
                }
                continue;
            }
            if (str.charAt(i) == '<') {
                result.append("&lt;");
                continue;
            }
            if (str.charAt(i) == '>') {
                result.append("&gt;");
                continue;
            }
            if (str.charAt(i) == '&') {
                result.append("&amp;");
                continue;
            }
            if (i + 1 != str.length() && str.charAt(i) != '\\') {
                result.append(str.charAt(i));
            }
        }
        if (is_title) {
            result.append("</h").append(countHeader - 1).append(">");
        } else {
            result.append("</p>");
        }
        return result.append(System.lineSeparator()).toString();
    }
}

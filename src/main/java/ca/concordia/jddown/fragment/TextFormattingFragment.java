package ca.concordia.jddown.fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.concordia.jddown.utils.Tuple;

public class TextFormattingFragment extends Fragment {
    private static final Pattern PATTERN = Pattern.compile("(~~|__|\\*{1,3})(.*?)\\1");

    public static class Style {
        private boolean strikethrough;
        private boolean italic;
        private boolean bold;
        private boolean underline;

        public Style() {
            this.strikethrough = false;
            this.italic = false;
            this.bold = false;
            this.underline = false;
        }

        public boolean isStrikethrough() {
            return strikethrough;
        }

        public boolean isItalic() {
            return italic;
        }

        public boolean isBold() {
            return bold;
        }

        public boolean isUnderline() {
            return underline;
        }

        @Override
        public String toString() {
            return "STYLE{" + (bold ? "BOLD, " : "") + (strikethrough ? "STRIKETHROUGH, " : "")
                    + (italic ? "ITALIC, " : "") + (underline ? "UNDERLINE, " : "") + "}";
        }
    }

    private Style style;

    private String unformatedString;

    public TextFormattingFragment() {
    }

    private TextFormattingFragment(String string, int position, Style style, String unformatedString) {
        super(string, position);
        this.style = style;
        this.unformatedString = unformatedString;
    }

    public String getText() {
        return unformatedString;
    }

    public Style getStyle() {
        return style;
    }

    private Tuple<Style, String> styleBuilder(Matcher matcher) {
        return styleBuilder(new Style(), matcher);
    }

    private Tuple<Style, String> styleBuilder(Style style, Matcher matcher) {
        String markdown = matcher.group(1);

        if (markdown.equals("***")) {
            style.italic = true;
            style.bold = true;
        } else if (markdown.equals("**")) {
            style.bold = true;
        } else if (markdown.equals("*")) {
            style.italic = true;
        } else if (markdown.equals("__")) {
            style.underline = true;
        } else if (markdown.equals("~~")) {
            style.strikethrough = true;
        }

        Matcher nextMatcher = getPattern().matcher(matcher.group(2));

        if (!nextMatcher.find()) {
            return new Tuple<Style, String>(style, matcher.group(2));
        }

        return styleBuilder(style, nextMatcher);
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        Tuple<Style, String> styleText = styleBuilder(matcher);

        return new TextFormattingFragment(matcher.group(), matcher.start(), styleText.first(), styleText.second());
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    public String toString() {
        return "TEXT_FORMATTING{" + start() + ", \"" + getText() + "\", " + getStyle() + "}";
    }
}

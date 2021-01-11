package ca.concordia.jddown.fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLFragment extends Fragment {
    private static final Pattern PATTERN = Pattern.compile("(https?:\\/\\/...*?)(\\s|$)");

    public URLFragment() {
    }

    private URLFragment(String string, int position) {
        super(string, position);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        return new URLFragment(matcher.group(1), matcher.start());
    }

    @Override
    public String toString() {
        return "URL{" + start() + ", " + getText() + "}";
    }

    @Override
    public String getText() {
        return getRawText();
    }
}

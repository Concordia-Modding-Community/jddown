package ca.concordia.jddown.fragment;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Quote fragment?
 */
public class QuoteFragment extends Fragment {
    private static final Pattern PATTERN = Pattern.compile("^>\\s(.*?)$", Pattern.MULTILINE);

    private CompoundDiscordFragment fragments;

    public QuoteFragment() {}

    private QuoteFragment(String string, int position, CompoundDiscordFragment fragments) {
        super(string, position);

        this.fragments = fragments;
    }

    public List<Fragment> getChildren() {
        return fragments.getChildren();
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        return new QuoteFragment(matcher.group(), matcher.start(), new CompoundDiscordFragment(matcher.group(1)));
    }

    @Override
    public String getText() {
        String output = "";

        for(Fragment fragment : getChildren()) {
            output += fragment.getText();
        }

        return output;
    }

    @Override
    public String toString() {
        return "QUOTE{" + start() + ", " + fragments.getChildren() + "}";
    }
}

package ca.concordia.jddown.fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFragment extends Fragment {
    public TextFragment() {
    }

    public TextFragment(String string, int position) {
        super(string, position);
    }

    @Override
    protected Pattern getPattern() {
        throw new RuntimeException("TextFragment has not pattern.");
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        throw new RuntimeException("TextFragment has no builder.");
    }

    @Override
    public String toString() {
        return "TEXT{" + start() + ", \"" + getText() + "\"}";
    }

    @Override
    public String getText() {
        return getRawText();
    }
}

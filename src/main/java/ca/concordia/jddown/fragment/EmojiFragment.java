package ca.concordia.jddown.fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiFragment extends Fragment {
    private static final Pattern PATTERN = Pattern.compile(":([a-zA-Z_]*?):");

    private String emojiName;

    public EmojiFragment() {
    }

    public EmojiFragment(String string, int position, String emojiName) {
        super(string, position);

        this.emojiName = emojiName;
    }

    @Override
    public String getText() {
        return emojiName;
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        return new EmojiFragment(matcher.group(), matcher.start(), matcher.group(1));
    }

    @Override
    public String toString() {
        return "EMOJI{" + start() + ", " + emojiName + "}";
    }
}

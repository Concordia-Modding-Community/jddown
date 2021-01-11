package ca.concordia.jddown.fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtMentionFragment extends Fragment {
    private static final Pattern PATTERN = Pattern.compile("@(everyone|here)");

    private String tag;

    public AtMentionFragment() {}

    public AtMentionFragment(String string, int position, String tag) {
        super(string, position);

        this.tag = tag;
    }

    public boolean isEveryone() {
        return tag.equals("everyone");
    }

    public boolean isHere() {
        return tag.equals("here");
    }

    @Override
    public String getText() {
        return tag;
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        return new AtMentionFragment(matcher.group(), matcher.start(), matcher.group(1));
    }

    
    @Override
    public String toString() {
        return "@Mention{" + start() + ", " + tag + "}";
    }
}

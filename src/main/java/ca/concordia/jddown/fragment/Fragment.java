package ca.concordia.jddown.fragment;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Fragment {
    private String string;
    private int position;

    public Fragment() {
    }

    protected Fragment(String string, int position) {
        this.string = string;
        this.position = position;
    }

    public abstract String getText();

    protected abstract Pattern getPattern();

    protected abstract Fragment fragmentBuilder(Matcher matcher);

    public ArrayList<Fragment> parse(String text) {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        Matcher matcher = getPattern().matcher(text);

        while (matcher.find()) {
            fragments.add(fragmentBuilder(matcher));
        }

        return fragments;
    }

    public int length() {
        return string.length();
    }

    public int start() {
        return position;
    }

    public int end() {
        return position + length();
    }

    public boolean isBefore(Fragment fragment) {
        return position < fragment.position;
    }

    public boolean isAfter(Fragment fragment) {
        return position > fragment.position;
    }

    public boolean contains(Fragment fragment) {
        return start() < fragment.start() && end() > fragment.end();
    }

    public boolean isWithin(Fragment fragment) {
        return start() > fragment.start() && end() < fragment.end();
    }

    public String getRawText() {
        return string;
    }

    @Override
    public String toString() {
        return "FRAGMENT{" + start() + ", " + getRawText() + "}";
    }
}
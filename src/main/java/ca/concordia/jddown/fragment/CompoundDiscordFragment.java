package ca.concordia.jddown.fragment;

import java.util.ArrayList;
import java.util.List;

import ca.concordia.jddown.utils.Provider;

public class CompoundDiscordFragment extends CompoundFragment {
    private static List<Fragment> REGISTERED_FRAGMENTS = new ArrayList<Fragment>();

    public CompoundDiscordFragment() {
    }

    public CompoundDiscordFragment(String string) {
        this(string, 0);
    }

    public CompoundDiscordFragment(String string, int position) {
        super(string, position, staticParse(string));
    }

    private static ArrayList<Fragment> staticParse(String text) {
        if (text != null) {
            return new CompoundDiscordFragment(null).parse(text);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "DISCORD_FRAGMENT{" + start() + ", " + getChildren() + "}";
    }

    @Override
    public void register(Provider<Fragment> fragment) {
        REGISTERED_FRAGMENTS.add(fragment.get());
    }

    @Override
    protected List<Fragment> getRegisteredFragments() {
        return REGISTERED_FRAGMENTS;
    }
}

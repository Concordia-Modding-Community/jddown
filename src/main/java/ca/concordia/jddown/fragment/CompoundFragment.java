package ca.concordia.jddown.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.concordia.jddown.utils.Provider;

public abstract class CompoundFragment extends Fragment {
    private List<Fragment> fragments;

    public CompoundFragment() {
    }

    public CompoundFragment(String string, List<Fragment> fragments) {
        this(string, 0, fragments);
    }

    public CompoundFragment(String string, int position, List<Fragment> fragments) {
        super(string, position);

        this.fragments = fragments;
    }

    public abstract void register(Provider<Fragment> fragment);

    protected abstract List<Fragment> getRegisteredFragments();

    public List<Fragment> getChildren() {
        return fragments;
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
    protected Pattern getPattern() {
        return null;
    }

    @Override
    protected Fragment fragmentBuilder(Matcher matcher) {
        return null;
    }

    private static ArrayList<ArrayList<Fragment>> getParsedFragmentLists(CompoundFragment compoundFragment, String text) {
        ArrayList<ArrayList<Fragment>> fragmentLists = new ArrayList<ArrayList<Fragment>>();

        for (Fragment fragment : compoundFragment.getRegisteredFragments()) {
            fragmentLists.add(fragment.parse(text));
        }

        return fragmentLists;
    }

    @Override
    public ArrayList<Fragment> parse(String text) {
        ArrayList<ArrayList<Fragment>> fragmentLists = getParsedFragmentLists(this, text);

        ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        int position = 0;

        int[] listIndicies = new int[fragmentLists.size()];

        while (true) {
            int leastFragmentIndex = -1;
            Fragment leastFragment = null;

            for (int i = 0; i < listIndicies.length; i++) {
                ArrayList<Fragment> fragmentList = fragmentLists.get(i);

                if (listIndicies[i] >= fragmentList.size()) {
                    continue;
                }

                Fragment fragment = fragmentList.get(listIndicies[i]);

                if (fragment.end() < position) {
                    listIndicies[i]++;

                    continue;
                }

                if (leastFragment == null || leastFragment.isAfter(fragment)) {
                    leastFragment = fragment;

                    leastFragmentIndex = i;
                }
            }

            if (leastFragment == null) {
                break;
            }

            if (position < leastFragment.start()) {
                fragments.add(new TextFragment(text.substring(position, leastFragment.start()), position));
            }

            fragments.add(leastFragment);

            position = leastFragment.end();

            listIndicies[leastFragmentIndex]++;
        }

        if (position < text.length()) {
            fragments.add(new TextFragment(text.substring(position), position));
        }

        return fragments;
    }
}

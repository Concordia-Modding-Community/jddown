package ca.concordia.jddown.parser;

import java.util.List;
import java.util.function.Consumer;

import ca.concordia.jddown.fragment.CompoundFragment;
import ca.concordia.jddown.fragment.Fragment;
import ca.concordia.jddown.utils.Provider;

public abstract class TextParser<T extends CompoundFragment> {
    private T text;

    private int pointer;

    public TextParser() {
        this("");
    }

    public TextParser(String text) {
        try {
            this.text = getT().getDeclaredConstructor(String.class).newInstance(text);
            this.pointer = 0;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract Class<T> getT();

    public abstract void register();

    public void register(Provider<Fragment> fragment) {
        text.register(fragment);
    }

    public boolean isEmpty() {
        return this.pointer >= getRaw().size();
    }

    public Fragment peek() {
        return getRaw().get(pointer);
    }

    public Fragment next() {
        return getRaw().get(pointer++);
    }

    public TextParser<T> stream(Consumer<Fragment> consumer) {
        while (!isEmpty()) {
            consumer.accept(next());
        }

        return this;
    }

    public List<Fragment> getRaw() {
        return text.getChildren();
    }

    @Override
    public String toString() {
        return "TEXT_PARSER" + getRaw().toString();
    }
}

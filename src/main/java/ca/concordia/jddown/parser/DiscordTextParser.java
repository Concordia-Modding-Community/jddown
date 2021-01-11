package ca.concordia.jddown.parser;

import ca.concordia.jddown.fragment.AtMentionFragment;
import ca.concordia.jddown.fragment.CompoundDiscordFragment;
import ca.concordia.jddown.fragment.EmojiFragment;
import ca.concordia.jddown.fragment.MentionFragment;
import ca.concordia.jddown.fragment.TextFormattingFragment;
import ca.concordia.jddown.fragment.URLFragment;

public class DiscordTextParser extends TextParser<CompoundDiscordFragment> {
    @Override
    public void register() {
        // register(QuoteFragment::new);
        register(MentionFragment::new);
        register(EmojiFragment::new);
        register(AtMentionFragment::new);
        register(URLFragment::new);
        register(TextFormattingFragment::new);
    }

    static {
        new DiscordTextParser().register();
    }

    public DiscordTextParser() {}

    public DiscordTextParser(String text) {
        super(text);
    }

    @Override
    public Class<CompoundDiscordFragment> getT() {
        return CompoundDiscordFragment.class;
    }

    @Override
    public String toString() {
        return "DISCORD_PARSER" + getRaw().toString();
    }
}
package ca.concordia.jddown.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.concordia.jddown.fragment.AtMentionFragment;
import ca.concordia.jddown.fragment.MentionFragment;
import ca.concordia.jddown.fragment.TextFormattingFragment;

public class TestDiscordTextParser {
    @Test
    public void testLoremIpsum() {
        DiscordTextParser parser = new DiscordTextParser("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", parser.next().getText());

        assertTrue(parser.isEmpty());
    }

    @Test
    public void testSimpleTextFormatting() {
        DiscordTextParser parser = new DiscordTextParser("This is a **bold** test.");

        assertEquals("This is a ", parser.next().getText());

        assertTrue(((TextFormattingFragment) parser.next()).getStyle().isBold());

        assertEquals(" test.", parser.next().getText());

        assertTrue(parser.isEmpty());
    }

    @Test
    public void testTextFormatting() {
        DiscordTextParser parser = new DiscordTextParser("*__Very__* ~~Mixed~~ Message Of **Text**.");

        assertEquals("Very", parser.next().getText());

        assertEquals(" ", parser.next().getText());

        assertEquals("Mixed", parser.next().getText());

        assertEquals(" Message Of ", parser.next().getText());

        assertEquals("Text", parser.next().getText());

        assertEquals(".", parser.next().getText());

        assertTrue(parser.isEmpty());
    }

    @Test
    public void testSimpleMentionAndFormatting() {
        DiscordTextParser parser = new DiscordTextParser("Hey <@!86890631690977280>, are you having **fun**?");

        assertEquals("Hey ", parser.next().getText());

        assertEquals("86890631690977280", ((MentionFragment) parser.next()).getUUID());

        assertEquals(", are you having ", parser.next().getText());

        assertEquals("fun", parser.next().getText());

        assertEquals("?", parser.next().getText());

        assertTrue(parser.isEmpty());
    }

    @Test
    public void testSimpleMostInline() {
        DiscordTextParser parser = new DiscordTextParser(
                "<@!86890631690977280> did you go on <#222197033908436994>? https://discord.com/ __warned me about it__.");

        assertEquals("86890631690977280", ((MentionFragment) parser.next()).getUUID());

        assertEquals(" did you go on ", parser.next().getText());

        assertEquals("222197033908436994", ((MentionFragment) parser.next()).getUUID());

        assertEquals("? ", parser.next().getText());

        assertEquals("https://discord.com/", parser.next().getText());

        assertEquals(" ", parser.next().getText());

        assertEquals("warned me about it", parser.next().getText());

        assertEquals(".", parser.next().getText());

        assertTrue(parser.isEmpty());
    }

    @Test
    public void testAtMention() {
        DiscordTextParser parser = new DiscordTextParser("Hi @everyone. This is a **test** message.");

        assertEquals("Hi ", parser.next().getText());

        assertTrue(((AtMentionFragment) parser.next()).isEveryone());

        assertEquals(". This is a ", parser.next().getText());

        assertEquals("test", parser.next().getText());

        assertEquals(" message.", parser.next().getText());

        assertTrue(parser.isEmpty());
    }

    @Test
    public void testEmoji() {
        DiscordTextParser parser = new DiscordTextParser("Hi :smile:. Have *you* seen this http://localhost?");

        assertEquals("Hi ", parser.next().getText());

        assertEquals("smile", parser.next().getText());

        assertEquals(". Have ", parser.next().getText());

        assertEquals("you", parser.next().getText());

        assertEquals(" seen this ", parser.next().getText()); 

        assertEquals("http://localhost?", parser.next().getText());
    }
}

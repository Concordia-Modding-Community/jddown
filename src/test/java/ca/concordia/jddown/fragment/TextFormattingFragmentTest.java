package ca.concordia.jddown.fragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TextFormattingFragmentTest {
    @Test
    public void testItalicStyle() {
        TextFormattingFragment fragment = (TextFormattingFragment) new TextFormattingFragment().parse("*Italic*").get(0);

        assertTrue(fragment.getStyle().isItalic());
    }

    @Test
    public void testUnderlineStyle() {
        TextFormattingFragment fragment = (TextFormattingFragment) new TextFormattingFragment().parse("__Underline__").get(0);

        assertTrue(fragment.getStyle().isUnderline());
    }

    @Test
    public void testStrikethroughStyle() {
        TextFormattingFragment fragment = (TextFormattingFragment) new TextFormattingFragment().parse("~~Strikethrough~~").get(0);

        assertTrue(fragment.getStyle().isStrikethrough());
    }

    @Test
    public void testBoldStyle() {
        TextFormattingFragment fragment = (TextFormattingFragment) new TextFormattingFragment().parse("**Bold**").get(0);

        assertTrue(fragment.getStyle().isBold());
    }

    @Test
    public void testAllStyle() {
        TextFormattingFragment fragment = (TextFormattingFragment) new TextFormattingFragment().parse("~~__***All Style***__~~").get(0);

        assertTrue(fragment.getStyle().isBold());
        assertTrue(fragment.getStyle().isItalic());
        assertTrue(fragment.getStyle().isStrikethrough());
        assertTrue(fragment.getStyle().isUnderline());
    }

    @Test
    public void testSomeStyle() {
        TextFormattingFragment fragment = (TextFormattingFragment) new TextFormattingFragment().parse("__**All Style**__").get(0);

        assertTrue(fragment.getStyle().isBold());
        assertFalse(fragment.getStyle().isItalic());
        assertFalse(fragment.getStyle().isStrikethrough());
        assertTrue(fragment.getStyle().isUnderline());
    }

    @Test
    public void testMultipleFragments() {
        List<Fragment> fragments = new TextFormattingFragment().parse("__Underline__ ~~**Strikebold**~~");

        assertTrue(((TextFormattingFragment) fragments.get(0)).getStyle().isUnderline());
        assertTrue(((TextFormattingFragment) fragments.get(1)).getStyle().isStrikethrough());
        assertTrue(((TextFormattingFragment) fragments.get(1)).getStyle().isBold());
    }

    @Test
    public void testGetText() {
        Fragment fragment = new TextFormattingFragment().parse("~~**Strikebold**~~").get(0);

        assertEquals("Strikebold", fragment.getText());
    }

    @Test
    public void testNonFormattedText() {
        assertTrue(new TextFormattingFragment().parse("Lorem ipsum dolor sit amet, consectetur adipiscing elit. 12345 I've seen you there.").isEmpty());
    }
}

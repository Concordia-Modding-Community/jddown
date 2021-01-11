package ca.concordia.jddown.fragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestEmojiFragment {
    @Test
    public void testSimpleEmoji() {
        assertEquals("happy", new EmojiFragment().parse(":happy:").get(0).getText());
    }

    @Test
    public void testInvalidEmoji() {
        assertTrue(new EmojiFragment().parse(":1_2:").isEmpty());
    }

    @Test
    public void testMultiEmoji() {
        List<Fragment> fragments = new EmojiFragment().parse(":happy:__%%DAWDW@:sad: :mad:");

        assertEquals("happy", fragments.get(0).getText());
        assertEquals("sad", fragments.get(1).getText());
        assertEquals("mad", fragments.get(2).getText());
    }
}

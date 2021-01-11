package ca.concordia.jddown.fragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestAtMentionFragment {
    @Test
    public void testPosition() {
        Fragment fragment = new AtMentionFragment().parse("   @everyone   ").get(0);

        assertEquals(3, fragment.start());
    }

    @Test
    public void testEveryone() {
        assertTrue(((AtMentionFragment) new AtMentionFragment().parse("@everyone").get(0)).isEveryone());
    }

    @Test
    public void testHere() {
        assertTrue(((AtMentionFragment) new AtMentionFragment().parse("@here").get(0)).isHere());
    }

    @Test
    public void testMentionAround() {
        assertTrue(((AtMentionFragment) new AtMentionFragment().parse("blabla@everyoneblabla").get(0)).isEveryone());
    }

    /**
     * TODO: Added @ notation or keep it for outside?
     */
    @Test
    public void testUserMention() {
        assertEquals("random_user",
                ((AtMentionFragment) new AtMentionFragment().parse("@random_user").get(0)).getText());
    }

    @Test
    public void testMultipleMention() {
        List<Fragment> fragments = new AtMentionFragment().parse("@everyone  @here");

        assertTrue(((AtMentionFragment) fragments.get(0)).isEveryone());

        assertTrue(((AtMentionFragment) fragments.get(1)).isHere());
    }
}

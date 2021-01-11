package ca.concordia.jddown.fragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestMentionFragment {
    @Test
    public void testRoleMention() {
        MentionFragment fragment = (MentionFragment) new MentionFragment().parse("<@&134362454976102401>").get(0);

        assertEquals(MentionFragment.Type.ROLE, fragment.getType());
    }

    @Test
    public void testChannelMention() {
        MentionFragment fragment = (MentionFragment) new MentionFragment().parse("<#222197033908436994>").get(0);

        assertEquals(MentionFragment.Type.CHANNEL, fragment.getType());
    }

    @Test
    public void testNicknameMention() {
        MentionFragment fragment = (MentionFragment) new MentionFragment().parse("<@!86890631690977280>").get(0);

        assertEquals(MentionFragment.Type.USER, fragment.getType());
    }

    @Test
    public void testUserMention() {
        MentionFragment fragment = (MentionFragment) new MentionFragment().parse("<@86890631690977280>").get(0);

        assertEquals(MentionFragment.Type.USER, fragment.getType());
    }

    @Test
    public void getUUID() {
        MentionFragment fragment = (MentionFragment) new MentionFragment().parse("<#86890631690977280>").get(0);

        assertEquals("86890631690977280", fragment.getUUID());
    }

    @Test
    public void testFailAtEveryone() {
        assertTrue(new MentionFragment().parse("@everyone").isEmpty());
    }

    @Test
    public void testMultipleMentions() {
        List<Fragment> fragments = new MentionFragment().parse("<@!86890631690977280> <#222197033908436994>");

        assertEquals("86890631690977280", ((MentionFragment) fragments.get(0)).getUUID());
        assertEquals("222197033908436994", ((MentionFragment) fragments.get(1)).getUUID());
    }
}

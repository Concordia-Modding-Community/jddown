package ca.concordia.jddown.fragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestURLFragment {
    @Test
    public void testValidHTTPsURL() {
        URLFragment fragment = (URLFragment) new URLFragment().parse("https://discord.com/").get(0);

        assertEquals("https://discord.com/", fragment.getText());
    }

    @Test
    public void testValidHTTPURL() {
        URLFragment fragment = (URLFragment) new URLFragment().parse("http://localhost/?data=2").get(0);

        assertEquals("http://localhost/?data=2", fragment.getText());
    }

    @Test
    public void testTooShortURL() {
        assertTrue(new URLFragment().parse("http://u").isEmpty());
    }

    @Test
    public void testMultipleURL() {
        List<Fragment> fragments = new URLFragment().parse("http://localhost/?data=2 http://ur");

        assertEquals("http://localhost/?data=2", ((URLFragment) fragments.get(0)).getText());

        assertEquals("http://ur", ((URLFragment) fragments.get(1)).getText());
    }
}

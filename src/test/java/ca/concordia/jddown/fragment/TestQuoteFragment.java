package ca.concordia.jddown.fragment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestQuoteFragment {
    @Test
    public void testSimpleQuote() {
        QuoteFragment fragment = (QuoteFragment) new QuoteFragment().parse("> Simple text quote.").get(0);

        assertEquals("Simple text quote.", fragment.getText());
    }

    @Test
    public void testFormattedQuote() {
        QuoteFragment fragment = (QuoteFragment) new QuoteFragment().parse("> **Bold** message.").get(0);

        assertEquals("Bold", fragment.getChildren().get(0).getText());
    }
}

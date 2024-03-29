package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class JSONShowDiffTest extends BaseTest{

    @Test
    void testShowDiffWorking() {
        JSONShowDiff showDIff = new JSONShowDiff();
        String text1 = "Line 1\nLine 2";
        String text2 = "Line 1\nDifferent line\nAdditional line";
        Integer[] expectedDiff = {1, 2};
        assertArrayEquals(showDIff.compare(text1, text2), expectedDiff);
    }

    @Test
    void testEmptyOneString() {
        JSONShowDiff showDIff = new JSONShowDiff();
        String text1 = "Line 1\nLine 2\nLine 3";
        String text2 = "";
        Integer[] expectedDiff = {0, 1, 2};
        assertArrayEquals(showDIff.compare(text1, text2), expectedDiff);
    }

    @Test
    void testBothEmptyStrings() {
        JSONShowDiff showDIff = new JSONShowDiff();
        String text1 = "";
        String text2 = "";
        Integer[] expectedDiff = {};
        assertArrayEquals(showDIff.compare(text1, text2), expectedDiff);
    }

    @Test
    void testWhitespaceSensitive() {
        JSONShowDiff showDIff = new JSONShowDiff();
        String text1 = " ";
        String text2 = "";
        Integer[] expectedDiff = {0};
        assertArrayEquals(showDIff.compare(text1, text2), expectedDiff);
    }

    @Test
    void testEmojiSensitive() {
        JSONShowDiff showDIff = new JSONShowDiff();
        String text1 = "❤️";
        String text2 = "";
        Integer[] expectedDiff = {0};
        assertArrayEquals(showDIff.compare(text1, text2), expectedDiff);
    }
}
package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * The type Json show diff.
 */
public class JSONShowDiff {
    private final static Logger logger = LoggerFactory.getLogger(JSONShowDiff.class);

    public JSONShowDiff(){
    }

    /**
     * Compare two strings line by line and return line numbers where strings differ.
     *
     * @param text1 first string
     * @param text2 second string
     * @return integer array containing line numbers (starting at 0) where strings differ
     */
    public Integer[] compare(String text1, String text2){
        ArrayList<Integer> lineDiffs = new ArrayList<>();
        Integer lineNumber = 0;

        if (text1.isEmpty()) {
            if (text2.isEmpty()) {
                logger.info("Both strings are empty, returning nothing");
                return new Integer[0];
            }
            logger.info("text1 is empty");
            while (lineNumber < text2.lines().count()) {
                lineDiffs.add(lineNumber);
                lineNumber++;
            }
            return lineDiffs.toArray(new Integer[0]);
        }

        if (text2.isEmpty()) {
            logger.info("text2 is empty");
            while (lineNumber < text1.lines().count()) {
                lineDiffs.add(lineNumber);
                lineNumber++;
            }
            return lineDiffs.toArray(new Integer[0]);
        }

        logger.info("Comparing strings...");

        String[] lines1 = text1.split("\n", -1);
        logger.info("text1: " + Arrays.toString(lines1));

        String[] lines2 = text2.split("\n", -1);
        logger.info("text2: " + Arrays.toString(lines2));

        while (lineNumber < max(lines1.length, lines2.length)) {
            if (lineNumber >= min(lines1.length, lines2.length)) {
                logger.info("Line " + lineNumber + " is different");
                lineDiffs.add(lineNumber);
            } else {
                if (!Objects.equals(lines1[lineNumber], lines2[lineNumber])) {
                    logger.info("Line " + lineNumber + " is different");
                    lineDiffs.add(lineNumber);
                }
            }
            lineNumber++;
        }

        logger.info("Returning differences");
        return lineDiffs.toArray(new Integer[0]);
    }
}

package fi.tuni.tamk.tiko.objectorientedprogramming;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import fi.tuni.tamk.tiko.objectorientedprogramming.JsonParser;
import fi.tuni.tamk.tiko.objectorientedprogramming.JSONObject;

/**
 * Class used to test JsonParser functionality.
 * 
 * @author Lassi Markkinen
 * @version 2019.1216
 */
public class JsonParserTest {
    /**
     * Test for parsing a simple JSONObject into a json format String.
     */
    @Test
    public void basicParserOutputTest() {
        JSONObject testObject = new JSONObject("x", 1, "y", 2, "z", 3);
        String output = "{\"x\":1,\"y\":2,\"z\":3}";

        String parsedTestObject = JsonParser.parse(testObject);
        parsedTestObject = parsedTestObject.replaceAll(" ", "");
        parsedTestObject = parsedTestObject.replace("\n", "");
        assertEquals("Output should match " + output + ", was " + parsedTestObject + " instead", output, parsedTestObject);
    }
    /**
     * Test for parsing a JSONObject with an array inside into a json format String.
     */
    @Test
    public void jsonContainingArrayOutputTest() {
        JSONObject testObjectWithArray = new JSONObject("x", List.of(1, 2, 3));
        String output = "{\"x\":[1,2,3]}";

        String parsedTestObject = JsonParser.parse(testObjectWithArray);
        parsedTestObject = parsedTestObject.replaceAll(" ", "");
        parsedTestObject = parsedTestObject.replace("\n", "");
        assertEquals("Output should match " + output + ", was " + parsedTestObject + " instead", output, parsedTestObject);
    }
    /**
     * Test for parsing an empty JSONObject into a json format String.
     */
    @Test 
    public void parseEmptyJsonTest() {
        JSONObject testObjectWithEmptyJson = new JSONObject();
        String output = "{}";

        String parsedTestObject = JsonParser.parse(testObjectWithEmptyJson);
        parsedTestObject = parsedTestObject.replaceAll(" ", "");
        parsedTestObject = parsedTestObject.replace("\n", "");
        assertEquals("Output should match " + output + ", was " + parsedTestObject + " instead", output, parsedTestObject);
    }
    /**
     * Test for parsing a JSONObject that contains another JSONObject into a json format String.
     */
    @Test
    public void jsonContainingJsonTest() {
        JSONObject testObjectWithJson = new JSONObject("x", 1, "y", new JSONObject("x", 1, "y", 2), "z", 3);
        String output = "{\"x\":1,\"y\":{\"x\":1,\"y\":2},\"z\":3}";

        String parsedTestObject = JsonParser.parse(testObjectWithJson);
        parsedTestObject = parsedTestObject.replaceAll(" ", "");
        parsedTestObject = parsedTestObject.replace("\n", "");
        assertEquals("Output should match " + output + ", was " + parsedTestObject + " instead", output, parsedTestObject);
    }
    /**
     * Test for parsing multiple JSONObjects as an array into a json format String.
     */
    @Test
    public void parseMultipleTest() {
        JSONObject multipleTestObject1 = new JSONObject("x", 1);
        JSONObject multipleTestObject2 = new JSONObject("y", 2);
        JSONObject multipleTestObject3 = new JSONObject("z", 3);
        List testList = List.of(multipleTestObject1, multipleTestObject2, multipleTestObject3);

        String output = "[{\"x\":1},{\"y\":2},{\"z\":3}]";
        String parsedTestObject = JsonParser.parseMultipleIntoJsonArray(testList);
        parsedTestObject = parsedTestObject.replaceAll(" ", "");
        parsedTestObject = parsedTestObject.replace("\n", "");
        assertEquals("Output should match " + output + ", was " + parsedTestObject + " instead", output, parsedTestObject);
    }
    
}
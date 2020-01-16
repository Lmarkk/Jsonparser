package fi.tuni.tamk.tiko.objectorientedprogramming;

import java.util.*;
/**
 * Class from which objects containing json key and value data can be created. The class contains two lists for keys and values.
 * 
 * @author Lassi Markkinen
 * @version 2019.1612 
 */
public class JSONObject {
    static final String SPACE = "  ";

    private List<String> keys = new ArrayList<>();
    private List<Object> values = new ArrayList<>();

    private String indent = "";
    private String firstBracketIndent = "";
    /**
     * Variant of JSONObject contructor for inserting a single value and key.
     * 
     * @param key Key to insert.
     * @param value Value to insert.
     */
    public JSONObject(String key, Object value) {
        keys.add(key);
        values.add(value);
    }
    /**
     * Variant of JSONObject contructor for inserting a two pairs of value and key.
     * 
     * @param key1 The first key to insert.
     * @param value1 The first value to insert.
     * @param key2 The second key to insert.
     * @param value2 The second value to insert.
     */
    public JSONObject(String key1, Object value1, String key2, Object value2) {
        put(key1, value1);
        put(key2, value2); 
    }
    /**
     * Variant of JSONObject contructor for inserting three pairs of key and value.
     * 
     * @param key1 The first key to insert.
     * @param value1 The first value to insert.
     * @param key2 The second key to insert.
     * @param value2 The second value to insert.
     * @param key3 The third key to insert.
     * @param value3 The third value to insert.
     */
    public JSONObject(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        put(key1, value1);
        put(key2, value2);
        put(key3, value3);
    }
    /**
     * Empty constructor.
     */
    public JSONObject() {
	}
	/**
     * Method for inserting a key and value pair into the JSONObject. A try-catch block is used to potential JSONObjectExceptions regarding
     * duplicate keys. if no duplicates are found the key and value are added to the JSONObject.
     * 
     * @param key Key to insert.
     * @param value Value to insert.
     */
    public void put(String key, Object value) {
        try {
            if (!keys.contains(key)) {
                keys.add(key);
                values.add(value);
            } else {
                throw new JSONObjectException("JSON object already contains key. JSON Keys must be unique");
            } 
        } catch (JSONObjectException e) {  
            e.printStackTrace();  
        }
    }

    /**
     * Getter for keys list.
     * @return the keys
     */
    public List<String> getKeys() {
        return keys;
    }
    /**
     * Getter for values list.
     * @return the values
     */
    public List<Object> getValues() {
        return values;
    }
    /**
     * Utility method for returning the first value in the keys list. This is used in the shopping list application
     * by the TableColumns to display item names to the user. 
     * @return The first value in the keys array.
     */
    public String getKey() {
        return keys.get(0);
    }
    /**
     * Utility method for returning the first value in the values list. This is used in the shopping list application
     * by the tableColumns to display item amounts to the user.
     * @return The first value in the values array.
     */
    public String getValue() {
        return values.get(0).toString();
    }
    /**
     * Returns a String representation of this object. Uses the jsonParser.parse()-method. Potentially causes recursion.
     * 
     * @return A String representation of this object.
     */
    public String toString() {  
        return JsonParser.parse(this);
    }
    /**
     * Returns the indent to be added for formatting when parsing the JSONObject instance. 
     * @return The indent value.
     */
    public String getIndent() {
        return indent;
    }
    /**
     * Sets the indent for formatting when parsing the JSONObject instance.
     * @param indent Indent value to set.
     */
    public void setIndent(String indent) {
        this.indent = indent;
    }
    /**
     * Returns the first line indent to be added for formatting when parsing the JSONObject instance. 
     * @return The indent value.
     */
    public String getFirstBracketIndent() {
        return firstBracketIndent;
    }
    /**
     * Sets the first line indent for formatting when parsing the JSONObject instance.
     * @param firstBracketIndent Indent value to set.
     */
    public void setFirstBracketIndent(String firstBracketIndent) {
        this.firstBracketIndent = firstBracketIndent;
    }
  
}
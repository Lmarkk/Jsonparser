package fi.tuni.tamk.tiko.objectorientedprogramming;

import java.util.List;
import java.util.ArrayList;

/**
 * Class used to manipulate JSONObjects by parsing them into Strings and by parsing Strings into JSONObjects.
 * 
 * @author Lassi Markkinen
 * @version 2019.1216
 */
public class JsonParser {
    static final String QUOTATION = "\"";
    static final String LINEBREAK = "\n";

    /**
     * Method used to parse a json array String specifically produced by saving a shopping list application file.
     * The method loops through the json array String and collects the indices that contain specific symbols into arrays.
     * The array containing curly bracket indices is the looped through and using all indices, parts of the json String are used to create 
     * JSONObjects with keys and values matching the json formatting. 
     * 
     * @param json the json format String to parse.
     * @return An array of JSONObjects
     */
    public static ArrayList<JSONObject> parseShoppingList(String json) {
        ArrayList<JSONObject> returnValue = new ArrayList();

        ArrayList<Integer> curlyBracketStarts = new ArrayList();
        ArrayList<Integer> curlyBracketEnds = new ArrayList();
        ArrayList<Integer> colons = new ArrayList();

        json = json.replaceAll(" ", "");
        json = json.replace("\n", "");

        char c = ' ';
        for(int i = 0; i < json.length(); i++) {
            c = json.charAt(i);
            switch(c) {
                case '{':
                    curlyBracketStarts.add(i);
                    break;
                case '}':
                    curlyBracketEnds.add(i);
                    break;
                case ':':
                    colons.add(i);
                    break;
            }
        }

        for(int i = 0; i < curlyBracketStarts.size(); i++) {
            returnValue.add(new JSONObject(json.substring(curlyBracketStarts.get(i) + 2, colons.get(i) -1),
             Integer.parseInt(json.substring(colons.get(i) +1, curlyBracketEnds.get(i)))));
        }
        return returnValue;
    }


    /**
     * Method which takes a JSONObject and parses its contents into a String. Also formats the output into .json.
     * 
     * The method loops through the given JSONObject's list of keys and adds they key String from the index position as well as a ":"-symbol to signify 
     * the start of a value for the key that was just written.
     * The method uses if-checks to handle cases of lists or JSONObjects within JSONObjects.
     * 
     * In the case of JSONObjects as values, the method increases the indent that the object will get when written for clarity in the final output String.
     * The method then uses recursion to handle a potentially large amount of JSONObjects that all have the next JSONObject as one of their values.
     * 
     * In the case of lists as values, an if-check is used to determine whether the list has JSONOBjects inside. These will again have special treatment
     * in regards to increasing their indent parameter. If the list contains any other type of object, the method will simply format the objects with a 
     * comma in between.
     * 
     * @param json The JSONObject to parse into a json formatted String.
     * @return a String, formatted into json.
     */
    public static String parse(JSONObject json) {
        String returnValue =  json.getFirstBracketIndent() + "{" + LINEBREAK;
        for(int i = 0; i < json.getKeys().size(); i++) {
            returnValue +=  json.getIndent() + formatStrings(json.getKeys().get(i)) + ": ";
    
            if(json.getValues().get(i) instanceof JSONObject) {
                JSONObject jsonInsideJson = (JSONObject)json.getValues().get(i);
                jsonInsideJson.setIndent(json.getIndent() + JSONObject.SPACE);
                returnValue += jsonInsideJson;
    
            } else if (json.getValues().get(i) instanceof List) {
                returnValue += json.getIndent() + "[" + LINEBREAK;

                List listInJson = ((List)json.getValues().get(i));

                if(listInJson.get(0) instanceof JSONObject) {
            
                    for(int s = 0; s < listInJson.size(); s++ ) {
                        ((JSONObject)listInJson.get(s)).setIndent(json.getIndent() + JSONObject.SPACE);
                        ((JSONObject)listInJson.get(s)).setFirstBracketIndent(json.getFirstBracketIndent() + JSONObject.SPACE);
                        
                        returnValue += listInJson.get(s);
    
                        if(s != listInJson.size() - 1) {
                            returnValue += ",";
                        }

                    }    
                } else {
                    returnValue += json.getIndent() + JSONObject.SPACE;
                    for(int l = 0; l < listInJson.size(); l++) {
                        returnValue += formatStrings(listInJson.get(l));
                        if(l != listInJson.size() -1) {
                            returnValue += ",";
                        }
                    }
                    
                }
                returnValue += LINEBREAK + "]";
            } else {
                returnValue += formatStrings(json.getValues().get(i));
            }

            if (i != json.getKeys().size() - 1) {
                returnValue += ",";
            }

            returnValue += LINEBREAK;
        }
        return returnValue +  json.getIndent() + "}" + LINEBREAK;
    }
    /**
     * Method which parses multiple given JSONObjects in an ObservableList into a JSON array in text format.
     * 
     * @param jsonItems List of JSONObjects to parse.
     * @return String with formatted JSONObjects inside.
     */
    public static String parseMultipleIntoJsonArray(List<JSONObject> jsonItems) {
        String returnValue = "[ \n";
        for(Object item: jsonItems) {
            returnValue += parse((JSONObject)item) + "," + "\n";
        }
        returnValue = returnValue.substring(0, returnValue.length() -2) + "]";
        return returnValue;
    }

    /**
     * An utility method which takes an object and uses an if-check to determine whether it's a String. If a String is found, the same String is then 
     * returned with quotation marks around it. Otherwise the object's toString is called and the output returned.
     * @param o The given Object.
     * @return A String with quotations around it, or the resulting String from calling the object's toString method.
     */
    public static String formatStrings(Object o) {
        if(o instanceof String) {
            return QUOTATION + o.toString() + QUOTATION;
        }
        return o.toString();
    }
}
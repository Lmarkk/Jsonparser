package fi.tuni.tamk.tiko.objectorientedprogramming;
/**
 * A custom Exception created to signify user errors when manipulating JSONObjects.
 * 
 * @author Lassi Markkinen
 * @version 2019.1121
 */
public class JSONObjectException extends Exception {


	public JSONObjectException(String errorMessage) {
        super(errorMessage);
	}

}

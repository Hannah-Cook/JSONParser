package uk.ac.uos.s187765.Assignment;

import java.io.StringReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JSONParser {
	
public char currentValue;

public Reader reader = null;

public static void main (String[] args) {
	
// Line 23 used for inserting a string to test it	
	
	JSONParser parser = new JSONParser(new StringReader("{\"id\":\"0001\",\"type\":\"donut\",\"name\":\"Cake\",\"ppu\":55,\"batters\":{\"batter\":[{\"id\":\"1001\",\"type\":\"Regular\"},{\"id\":\"1002\",\"type\":\"Chocolate\"},{\"id\":\"1003\",\"type\":\"Blueberry\"},{\"id\":\"1004\",\"type\":\"Devil's Food\"}]},\"topping\":[{\"id\":\"5001\",\"type\":\"None\"},{\"id\":\"5002\",\"type\":\"Glazed\"},{\"id\":\"5005\",\"type\":\"Sugar\"},{\"id\":\"5007\",\"type\":\"Powdered Sugar\"},{\"id\":\"5006\",\"type\":\"Chocolate with Sprinkles\"},{\"id\":\"5003\",\"type\":\"Chocolate\"},{\"id\":\"5004\",\"type\":\"Maple\"}]}"));
	
// Example of this used to find specific value
	
	try {
		HashMap <String, Object> map = parser.parse();
		System.out.println("id is : " + ((HashMap<String, Object>) ((Object[])((HashMap<String,Object>) map.get("batters")).get("batter"))[3]).get("id"));
	} 
	catch (JSONFormatException e) {
		e.printStackTrace();
	}
	
}

public HashMap <String, Object> parse() throws JSONFormatException {
	if (!readNextExpected('{')) {
		return null; //EXCEPTION
	}
	return readObject();
}

/**
 * The reader method
 * @param reader
 */

public JSONParser(Reader reader) {
	this.reader = reader;
}


// This readValue is used for the switch statement

/**
 * Switch case which evaluates the currentValue which is being read and chooses an appropriate method to use. If an unexpected/invalid character is identified, the switch case will throw a default exception. 
 * @return
 * @throws JSONFormatException
 */

public Object readValue() throws JSONFormatException {
	
	switch (currentValue) {

	case 'n':
		if (readNull()) {
			return null;
		}
		throw new JSONFormatException("Failed to read null");

	case 't':
		if (readTrue()) {
			return true;
		}
		throw new JSONFormatException("Failed to read true");
		
	case 'f':
		if (readFalse()) {
			return false;
		}
		throw new JSONFormatException("Failed to read false");
			
	case '"':
		return readString();
		
	case '[':
		return readArray();
		
	case '{':
		return readObject();
	
	case '-':
	case '1':
	case '2':
	case '3':
	case '4':
	case '5':
	case '6':
	case '7':
	case '8':
	case '9':
	case '0':
	return readNumber();
	
	default: 
		throw new JSONFormatException("Unexpected character: " + currentValue);
	}
}


// This method has 'u', 'l' and 'l' as a fixed input. It is used to read a null value. It utilises the readNextExpected function and compares the current character with the one which is expected next
// in this example the 'array' ('u', 'l' and'l') is passed into 'arrayToCompare', and if the next expected character (t) is true

/**
 * Method for reading a null value. The characters 'u', 'l' and 'l' will be compared to the currentValue, and if it is the expected character it will continue to read to the next value. If it is not, it will return false.
 * @return
 */
public boolean readNull() {
	char[] array = new char[] {
			'u', 'l', 'l'
	};
	
	if(readNextExpected(array) == true) {
		readNext();
		return true;
	}
	else {
		return false;
	}
}

/**
 * Method for reading a true value. The characters 'r', 'u' and 'e' will be compared to the currentValue, and if it is the expected character it will continue to read to the next value. If it is not, it will return false.
 * @return
 */
public boolean readTrue() {
	char[] array = new char[] {
			'r', 'u', 'e'
	};
	
	if(readNextExpected(array) == true) {
		readNext();
		return true;
	}
	else {
		return false;
	}
}

/**
 * Method for reading a false value. The characters 'a', 'l', 's' and 'e' will be compared to the currentValue, and if it is the expected character it will continue to read to the next value. If it is not, it will return false. 
 * @return 
 */
public boolean readFalse() {
	char[] array = new char[] {
			'a', 'l', 's', 'e'
	};
	
	if(readNextExpected(array) == true) {
		readNext();
		return true;
	}
	else {
		return false;
	}
}

/**
 * Method for reading a String. This method creates a StringBuilder to which the characters in the String are appended individually. 
 * The method continues to read the string until the closing set of double quotes is found. The method then returns the String which has been read. 
 * The 'if' statement identifies escape characters, and throws and exception if one is found. 
 * @return String of characters
 * @throws JSONFormatException
 */
public String readString() throws JSONFormatException {
	
	StringBuilder theTextString = new StringBuilder();
	readNext();
	
	while (currentValue != '"') {
		theTextString.append(currentValue);
		readNext();
	}
	
	readNext();
	
	if (currentValue == '\\') {
		readNext();
		if (currentValue == '"' || currentValue == '\\' || currentValue == '/' || currentValue == 'b' || currentValue == 'f' || currentValue == 'n' || currentValue == 'r' || currentValue == 't' || currentValue == 'u') {
				throw new JSONFormatException("Escape character: " + currentValue);
	}
	}
	return theTextString.toString();
}


/**
 * This method reads numbers. While the currentValue is an integer the method will append it to a StringBuilder, and then read the next character. 
 * If the currentValue is not an integer, the method will be terminated. 
 * @return Returns the String of numbers as an integer (so that it can be manipulated as an integer at a later stage).
 */

public int readNumber() {
	
	StringBuilder theNumberString = new StringBuilder();
	theNumberString.append(currentValue);
	readNext();
	
		while (currentValue == '0' || currentValue == '1' || currentValue == '2' || currentValue == '3' || currentValue == '4' || currentValue == '5' || currentValue == '6' || currentValue == '7' || currentValue == '8' || currentValue == '9' || currentValue == '-') {
				
			theNumberString.append(currentValue);
			readNext();
			
		}
		
	return Integer.valueOf(theNumberString.toString());

}

/**
 * The readKey method reads the key of the key/value pairs in the JSON document. 
 * A StringBuilder is used to append the characters of the key to a String, while the current value is not the closing double quote.
 * @return Returns a String of the key which has been identified. 
 */

public String readKey() {
	
	StringBuilder theTextString = new StringBuilder();
	readNext();
	
	while (currentValue != '"') {
		
		theTextString.append(currentValue);
		readNext();
	}
	
	return theTextString.toString();
	
	}

/**
 * The readObject method uses a HashMap to read an object in a JSON document. 
 * When the character '{' is identified, this method is called. 
 * When the character '}' is identified, the method hits a break. 
 * 
 * @return Returns the key.value pairs in the object
 * @throws JSONFormatException
 */

public HashMap <String, Object> readObject() throws JSONFormatException {
	HashMap <String, Object> map = new HashMap <String, Object> ();
	do {
		readWhitespace();
		if (currentValue == '}') {
			break;
		}
		String key = readKey();
		readWhitespace();
		if (currentValue != ':') {
			throw new JSONFormatException ("Incorrect format - no colon exists");
		}
		readWhitespace();
		Object value = readValue();
		readWhitespace(false);
		map.put(key, value);
	}
	
	while(currentValue == ',');
	readWhitespace(false);
	
	if (currentValue != '}' ) {
		throw new JSONFormatException ("Incorrect object format.");
		
	}
	readNext();
	return map;
}


// Read Array

/**
 * This method reads the values in arrays in the JSON document.
 * An ArrayList is created, in which the values are added when they are read. 
 * Values are read until the currentValue is ']' (which signifies the end of the array), at which point the method will hit a break. 
 * Commas separate list items, and are followed by whitespace. The method reads the whitespace while the currentValue is a comma ','.
 * If the final value is not a ']', the method will throw an exception. 
 * @return Returns the list of values in the array in an array list. 
 * @throws JSONFormatException 
 */
public Object[] readArray() throws JSONFormatException {
	List <Object> list = new ArrayList<Object> ();
	do {
		readWhitespace();
		try {
			if (currentValue == ']') {
				break;
			}
			Object o = readValue();
			readWhitespace(false);
			list.add(o);
		} catch (JSONFormatException e) {
			e.printStackTrace();
		} 
	}
	
	while (currentValue == ',');
	
	if (currentValue != ']') {
		throw new JSONFormatException ("Incorrect array format.");
	}
	
	readNext();
	return list.toArray();
	
}


/**
 * The readWhitespace method is used to read whitespace throughout the JSON document. 
 * It is called in various other methods in the parser where appropriate. 
 * The .isWhitespace method is used to determine whether the currentValue is whitespace. 
 * A boolean has been used here so that when readNext is true the method will readWhitespace. 
 */

// Read Whitespace

public void readWhitespace() {
	readWhitespace(true);
}


public void readWhitespace(boolean readNext) {
	if (readNext == true) {
	readNext();
	}
	while (Character.isWhitespace(currentValue)) {
	readNext();
	}
}


/**
 * This method is used to read the next character is the document.
 * It uses a try/catch block in which it will read the currentValue (thus progressing to the next value) as long as the character is a char). 
 */

public void readNext() {
	try {
		currentValue = (char) reader.read();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

/**
 * This method compares the next character to that which is expects to see based on the inputs in the methods above. 
 * The method returns true if the character is equal to that which is expected, and false if it is not. 
 * @param toCompare
 * @return
 */

// Compares the NextExpected character with the one written in the methods above. If the currentValue is equal to the one you are comparing it with, it will return true. If it is not, it will return false. 
public boolean readNextExpected(char toCompare) {
	readNext();
	if (currentValue == toCompare) {
		return true;
	}
	
	else {
		return false;
	}
}

/**
 * This method compares the next character in an array to that which is expected. 
 * @param arrayToCompare
 * @return
 */
public boolean readNextExpected(char[] arrayToCompare) {
	for (char nextChar: arrayToCompare) {
		if (readNextExpected(nextChar) == false) {
			return false;
		}
	}
	
	return true;
}

}

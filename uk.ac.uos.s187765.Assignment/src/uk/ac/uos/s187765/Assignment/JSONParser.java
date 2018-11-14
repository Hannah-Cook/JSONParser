package uk.ac.uos.s187765.Assignment;

import java.io.StringReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class JSONParser {
	
public char currentValue;

public Reader reader = null;

public static void main (String[] args) {
	
// Line 20 used for inserting a string to test it	
	
	JSONParser parser = new JSONParser(new StringReader("False"));
	
// Example of this used for readFalse	
	
	System.out.println("Is it false: " + parser.readFalse());

//StringReader stringReader = new StringReader(currentValue);
//char currentValue = stringReader.readObject();
//
//if (currentValue == '{') {
//	then: stringReader.readNext();
//}
//
//// Object exception
//
//else {
//	throw new JSONformatexception ("Not a valid JSON file");
//}
//
//
////StringReader for null
//
//StringReader stringReader = new StringReader(currentValue);
//char currentValue = stringReader.readNull();
//
////Throw exceptions for each if statement
//
//if (currentValue == 'n') {
//	then: stringReader.readNext();
//}
//if (currentValue == 'u') {
//	then: stringReader.readNext();
//}
//
//// Exception
//
//else {
//	throw new JSONformatexception("Not valid JSON - Read Null");
//}
//
//
//if (currentValue == 'l') {
//	then: stringReader.readNext();
//}
//if (currentValue == 'l') {
//	then: System.out.print("null");
//}
//
//
////StringReader for false
//
//StringReader stringReader = new StringReader(currentValue);
//char currentValue = stringReader.readFalse();
//
//
//if (currentValue == 'f') {
//	then: stringReader.readNext();
//}
//if (currentVaule == 'a') {
//	then: stringReader.readNext();
//}
//
//// Exception
//
//else {
//	throw new JSONformatexception("Not valid JSON - Read False");
//}
//
//if (currentValue == 'l') {
//	then: stringReader.readNext();
//}
//if (currentValue == 's') {
//	then: stringReader.readNext();
//}
//if (currentValue == 'e') {
//	then: System.out.printf("false");
//}
//
////StringReader for true
//
//StringReader stringReader = new StringReader(currentValue);
//char currentValue = stringReader.readTrue();
//
//
//if (currentValue == 't') {
//	then: stringReader.readNext();
//}
//if (currentVaule == 'r') {
//	then: stringReader.readNext();
//}
//// Exception
//
//else {
//	throw new JSONFormatException("Not valid JSON - Read True");
//}
//
//if (currentValue == 'u') {
//	then: stringReader.readNext();
//}
//if (currentValue == 'e') {
//	then: System.out.printf("true");
//}
//
//// StringReader for String
//
//StringReader stringReader = new StringReader(currentValue);
//char currentValue = stringReader.readString();
//
//if (currentValue == '"') {
//	then: stringReader.ReadNext();
//}
//
//if (currentValue)
//
//
//	//PushbackReader reader;
//	//Read using char
//JSONParser parser = JSONParser.createParser(new StringReader("[]"));
//
////Call the character something like currentValue (DONE??)
//
////In switch case
//
////Need a reader, readNext and readNext expected to start off. Start with checking for true, false and null values.
//
////readNext (call reader, call read, and read it as a character (set c as a character)
////readNextExpected (takes parameter of a character (the one found previously) and checks if the next value is equal to the one expected, can use multiple ones e.g to find null readNextException ('u') && readNextException ('l') && readNextException('l'), then return 'null'). 
////These must throw a JSONException (rename), and in these exceptions make it clear what the issue is e.g ("Not valid JSON - Read null") (e.g throw new JSONException("Not valid JSON - Read null")
//
////readValue
}



// Initiate reader - look up this.

public JSONParser(Reader reader) {
	this.reader = reader;
}


// This readValue is used for the switch statement

public Object readValue() throws JSONFormatException {
	
	switch (currentValue) {

	case 'n':
		return readNull();

	case 't':
		return readTrue();
		
	case 'f':
		return readFalse();
		
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
	
// The default value used for when an unexpected character is found, throws exception. 	
	
	default: 
		throw new JSONFormatException("Unexpected character: " + currentValue);
	}
}


// This method has 'u', 'l' and 'l' as a fixed input. It is used to read a null value. It utilises the readNextExpected function and compares the current character with the one which is expected next
// in this example the 'array' ('u', 'l' and'l') is passed into 'arrayToCompare', and if the next expected character (t) is true
public boolean readNull() {
	char[] array = new char[] {
			'u', 'l', 'l'
	};
	
	if(readNextExpected(array) == true) {
		return true;
	}
	else {
		return false;
	}
	
	
}

public boolean readTrue() {
	char[] array = new char[] {
			'r', 'u', 'e'
	};
	
	if(readNextExpected(array) == true) {
		return true;
	}
	else {
		return false;
	}
	
}

public boolean readFalse() {
	char[] array = new char[] {
			'a', 'l', 's', 'e'
	};
	
	if(readNextExpected(array) == true) {
		return true;
	}
	else {
		return false;
	}
}

public String readString() {
	return "";
}

public Object[] readArray() {
	return null;
}

public Object readObject() {
	return null; 
}

public double readNumber() {
	
		if (currentValue == '-' || currentValue == 0 || currentValue == 1 || currentValue == 2 || currentValue == 3 || currentValue == 4 || currentValue == 5 || currentValue == 6 || currentValue == 7 || currentValue == 8 || currentValue == 9 || currentValue == '.') {
			
		try {
			currentValue = (char) reader.read();
			
StringBuilder numberString = new StringBuilder().append(currentValue);			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	return 0.0d;
	return String.valueOf(numberString);

}


// Reads the next currentValue

public void readNext() {
	try {
		currentValue = (char) reader.read();
	} catch (IOException e) {
		// TODO Auto-generated catch block //Look up IOException
		e.printStackTrace();
	}
}

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

public boolean readNextExpected(char[] arrayToCompare) {
	for (char t: arrayToCompare) {
		if (readNextExpected(t) == false) {
			return false;
		}
	}
	
	return true;
}

}

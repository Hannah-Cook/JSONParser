package uk.ac.uos.s187765.Assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class JSONParserTest {

	@Test
	void testMain() {
		
	}

	@Test
	void testJSONParser() {

	}

	@Test
	void testReadValue() {

	}
	
// ReadNull Tests

	@Test
	void testReadNullCorrectInput() {
		
		JSONParser parser = new JSONParser(new StringReader("ull"));
		
		Assert.assertEquals(true, parser.readNull());

	}
	
	@Test
	void testReadNullIncorrectInput() {
		
        JSONParser parser = new JSONParser(new StringReader("ULL"));
		
		Assert.assertEquals(false, parser.readNull());

	}
	
	@Test
	void testReadNullIncorrectInput2() {
		
        JSONParser parser = new JSONParser(new StringReader("uul"));
		
		Assert.assertEquals(false, parser.readNull());

	}
	
	
// ReadTrue Tests	
	

	@Test
	void testReadTrueCorrectInput() {
		
        JSONParser parser = new JSONParser(new StringReader("rue"));
		
		Assert.assertEquals(true, parser.readTrue());

	}
	
	
	@Test
	void testReadTrueIncorrectInput() {
		
        JSONParser parser = new JSONParser(new StringReader("RUE"));
		
		Assert.assertEquals(false, parser.readTrue());

	}
	
	
	@Test
	void testReadTrueIncorrectInput2() {
		
        JSONParser parser = new JSONParser(new StringReader("roo"));
		
		Assert.assertEquals(false, parser.readTrue());
	}

	
// ReadFalse Tests
	
	
	@Test
	void testReadFalseCorrectInput() {
		
		JSONParser parser = new JSONParser(new StringReader("alse"));
			
		Assert.assertEquals(true, parser.readFalse());
	}

	
	@Test
	void testReadFalseIncorrectInput() {
		
		JSONParser parser = new JSONParser(new StringReader("ALSE"));
			
		Assert.assertEquals(false, parser.readFalse());
	}
	
	@Test
	void testReadFalseIncorrectInput2() {
		
		JSONParser parser = new JSONParser(new StringReader("aalse"));
			
		Assert.assertEquals(false, parser.readFalse());
	}
	
	
// ReadString Tests
	
	@Test
	void testReadStringCorrectInput() throws JSONFormatException {
		
		JSONParser parser = new JSONParser(new StringReader("\"Hello\""));
		
		Assert.assertEquals(true, parser.readString());
	}
	
	@Test
	void testReadStringIncorrectInput() throws JSONFormatException {
		
		JSONParser parser = new JSONParser(new StringReader(""));
		
		Assert.assertEquals(false, parser.readString());
	}
	
	
//ReadArray Tests	

	
	@Test
	void testReadArrayCorrectInput() throws JSONFormatException {
		
		JSONParser parser = new JSONParser(new StringReader("["));
		
		Assert.assertEquals(true, parser.readArray());
		
	}

	@Test
	void testReadArrayInorrectInput() throws JSONFormatException {
		
		JSONParser parser = new JSONParser(new StringReader("abc"));
		
		Assert.assertEquals(false, parser.readArray());
		
	}
	
	@Test
	void testReadArrayInorrectInput2() throws JSONFormatException {
		
		JSONParser parser = new JSONParser(new StringReader("123"));
		
		Assert.assertEquals(false, parser.readArray());
		
	}
	
	@Test
	void testReadArrayIncorrectInput3() throws JSONFormatException {
		
		JSONParser parser = new JSONParser(new StringReader("{"));
		
		Assert.assertEquals(false, parser.readArray());
		
	}
	
	
//ReadObject Tests	
	
	@Test
	void testReadObjectCorrectInput() throws JSONFormatException {
		
		JSONParser parser = new JSONParser(new StringReader("{"));
		
		Assert.assertEquals(true, parser.readObject());
		
		}
	

// ReadNumber Tests
	@Test
	void testReadNumberCorrectInput() {
		
		JSONParser parser = new JSONParser(new StringReader("264"));
		
		Assert.assertEquals(true, parser.readNumber());
	}
	
	@Test
	void testReadNumberIncorrectInput() {
		
		JSONParser parser = new JSONParser(new StringReader("2t4"));
		
		Assert.assertEquals(false, parser.readNumber());
	}
	
	@Test
	void testReadNumberIncorrectInput2() {
		
		JSONParser parser = new JSONParser(new StringReader("t64"));
		
		Assert.assertEquals(false, parser.readNumber());
	}
	

	@Test
	void testReadNext() {
		fail("Not yet implemented");
	}

	@Test
	void testReadNextExpectedChar() {
		fail("Not yet implemented");
	}

	@Test
	void testReadNextExpectedCharArray() {
		fail("Not yet implemented");
	}

}

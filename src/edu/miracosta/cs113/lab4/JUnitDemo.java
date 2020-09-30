package edu.miracosta.cs113.lab4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitDemo {
	static private Directory directory = new Directory();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		String s = directory.addOrChangeEntry("AB", "000-000-0000");
		s = directory.addOrChangeEntry("BB", "000-000-0001");
		s = directory.addOrChangeEntry("CB", "000-000-0001");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddOrChangeEntry() {
		assertEquals(directory.addOrChangeEntry("DA",  "000-000-0004"), null); // Checking Add
		assertEquals(directory.addOrChangeEntry("AB",  "000-000-0004"), "000-000-0000"); // Checking Change
		
	//	fail("Not yet implemented");
	}
	
	@Test
	void testRemove() {
		System.out.println("Running testRemove() on DirectoryEntry.");
		//DirectoryEntry dE_removeEntry_return;
		DirectoryEntry dE_Mike = new DirectoryEntry("Mike", "000-000-1502");		
		System.out.println("This is the Mike dE Obj: " + dE_Mike);
		
		directory.addOrChangeEntry("Mike","000-000-1502");		
		System.out.println("addOrChangeEntry method has been executed!!");	
		
		
		
		
		//assertEquals(directory.removeEntry("Mike").getName(),"Mike");		
		assertEquals(directory.removeEntry("Mike").getNumber(),"000-000-1502");
		

		
	}

}

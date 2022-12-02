package p4Hash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClosedHashTableTest {

	@Test
	void addtest() {
		
		ClosedHashTable<Integer> tabla = new ClosedHashTable<>(13, 0);
		assertEquals(true, tabla.add(5));
		assertEquals(true, tabla.add(8));
		assertEquals(true, tabla.add(11));
		assertEquals(true, tabla.add(9));
		assertEquals(true, tabla.add(5));
		assertEquals(true, tabla.add(7));
		assertEquals(true, tabla.add(8));
		assertEquals(true, tabla.add(6));
		assertEquals(true, tabla.add(14));
		System.out.println(tabla.toString());
		
		
		assertEquals(7, tabla.find(7));
		assertEquals(null, tabla.find(45));
		assertEquals(6, tabla.find(6));

	}
	
	@Test
	void deletetest() {
		
		ClosedHashTable<Integer> tabla = new ClosedHashTable<>(5, 0);
		assertEquals(true, tabla.add(4));
		assertEquals(true, tabla.add(13));
		assertEquals(true, tabla.add(24));
		assertEquals(true, tabla.add(3));
		assertEquals(true, tabla.remove(24));
		assertEquals(true, tabla.remove(3));
		System.out.println(tabla.toString());
		assertEquals(true, tabla.add(15));
		System.out.println(tabla.toString());

	}
}

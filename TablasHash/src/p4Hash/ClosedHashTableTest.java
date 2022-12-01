package p4Hash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClosedHashTableTest {

	@Test
	void addtest() {
		
		ClosedHashTable<Integer> tabla = new ClosedHashTable<>(5, 0);
		assertEquals(true, tabla.add(1));
		assertEquals(true, tabla.add(3));
		assertEquals(true, tabla.add(4));
		assertEquals(true, tabla.add(5));
		assertEquals(false, tabla.add(6));
		
		assertEquals(1, tabla.find(1));

	}

}

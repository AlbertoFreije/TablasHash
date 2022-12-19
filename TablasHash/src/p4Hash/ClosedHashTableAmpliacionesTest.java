package p4Hash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClosedHashTableAmpliacionesTest {

	@Test
	public void testAdd() {
		ClosedHashTableAmpliaciones<Integer> a = new ClosedHashTableAmpliaciones<>(5, 0);//LINEAL
		
		assertThrows(NullPointerException.class, () -> {a.add(null);}); // Añade null
		
		assertEquals(true, a.add(1)); // Añade correctamente
		assertEquals(true, a.add(2));
		assertEquals(true, a.add(3));
		assertEquals("{_E_};{1};{2};{3};{_E_};[Size: 5 Num.Elems.: 3]", a.toString());
		
		
		assertEquals(true, a.add(4)); // Añade correctamente
		assertEquals(true, a.add(9)); // Añade correctamente con conflicto en posicion
		assertEquals("{9};{1};{2};{3};{4};[Size: 5 Num.Elems.: 5]", a.toString());
		
		assertEquals(false, a.add(6)); // Añade con tabla llena
		assertEquals("{9};{1};{2};{3};{4};[Size: 5 Num.Elems.: 5]", a.toString());
		
		assertEquals(true, a.remove(1)); // Añade correctamente
		assertEquals(true, a.remove(2));
		assertEquals(true, a.remove(3));
	}
}

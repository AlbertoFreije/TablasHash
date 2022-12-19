package p4Hash;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class ClosedHashTableNoRedispTest {

	
	
	@Test
	public void testAdd() {
		ClosedHashTable<Integer> a = new ClosedHashTable<>(5, 0);//LINEAL
		
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
	}

	@Test
	public void testRemove() {
		ClosedHashTable<Integer> a = new ClosedHashTable<>(5, 0);//LINEAL
		assertEquals(true, a.add(0));  // Añado todos los elementos
		assertEquals(true, a.add(1));
		assertEquals(true, a.add(5));		
		assertEquals(true, a.add(4)); assertEquals(true, a.add(9));
		assertEquals("{0};{1};{5};{9};{4};[Size: 5 Num.Elems.: 5]", a.toString());
		  
		  
		
		assertThrows(NullPointerException.class, () -> {a.remove(null);}); // Borra null
		  
			  
		assertEquals(true, a.remove(1)); // Borra correctamente 
		
		assertEquals(false,  a.remove(1)); // Borra uno ya eliminado 
		assertEquals(false, a.remove(20)); //  Borra uno inexistente
		assertEquals("{0};{_D_};{5};{9};{4};[Size: 5 Num.Elems.: 4]", a.toString());
		  
		assertEquals(true, a.remove(5)); // Borra correctamente un elemento desplazado por conflicto de posicion
		assertEquals(true, a.remove(9));
		assertEquals("{0};{_D_};{_D_};{_D_};{4};[Size: 5 Num.Elems.: 2]", a.toString());
		  
		assertEquals(true, a.remove(0)); // Borra correctamente un elemento
		assertEquals("{_D_};{_D_};{_D_};{_D_};{4};[Size: 5 Num.Elems.: 1]",  a.toString());
		 
		 
	}
	
}

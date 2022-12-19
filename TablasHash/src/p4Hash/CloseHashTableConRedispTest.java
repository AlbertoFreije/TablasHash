package p4Hash;
import static org.junit.Assert.*;

import org.junit.Test;


public class CloseHashTableConRedispTest {

	@Test
	public void test() {
		//Crea una tabla del tamaño 4 (numero no primo)
		ClosedHashTable<Integer> tabla = new ClosedHashTable<Integer>(4,0.5, 0.16,0);  //Lineal
		//Muestra la tabla. Debera estar vacia y ser de tamaño 5
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 0]",tabla.toString());
				
		//Inserta elementos
		assertEquals(true,tabla.add(8));
		assertEquals(true,tabla.add(10));
		System.out.println(tabla.toString());
		assertEquals("{10};{_E_};{_E_};{8};{_E_};[Size: 5 Num.Elems.: 2]",tabla.toString());
		
		//Inserta y redispersión
		
		System.out.println ("Redispersion"); 
		assertEquals(true,tabla.add(66));
		System.out.println(tabla.toString());		
		assertEquals("{66};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 3]",tabla.toString());
		
		//Sigue insertando elementos
		assertEquals(true,tabla.add(77));
		System.out.println(tabla.toString());
		assertEquals("{66};{77};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 4]", tabla.toString());
		assertEquals(true,tabla.add(7));
						
		//Inserta y redispersión
		assertEquals(true,tabla.add(9));
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{77};{8};{9};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 6]",tabla.toString());
			
		//Borrar el 77
		assertEquals(true,tabla.remove(77));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{_D_};{8};{9};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 5]",tabla.toString());
		
		//Borrar el 9
		assertEquals(true,tabla.remove(9));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{_D_};{8};{_D_};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 4]",tabla.toString());
		
		//Borro un elmento que no existe
		assertEquals(false,tabla.remove(27));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{_D_};{8};{_D_};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 4]",tabla.toString());
		
		//Borro el 10--> redispersión inversa
		assertEquals(true,tabla.remove(10));
		System.out.println(tabla.toString());
		assertEquals("{66};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{8};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",tabla.toString());
		
		assertEquals(true,tabla.remove(66));
		System.out.println(tabla.toString());
		assertEquals("{_D_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{8};{_E_};{_E_};[Size: 11 Num.Elems.: 2]",tabla.toString());
		
		assertEquals(true,tabla.remove(8));
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{7};{_E_};{_E_};[Size: 5 Num.Elems.: 1]",tabla.toString());
		
		
		//Es el ultimo elemento. Nestor dice de no redispersar inversamente (no lo entiendo)
		assertEquals(true,tabla.remove(7));
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};[Size: 3 Num.Elems.: 0]",tabla.toString());
		
		
	}

}

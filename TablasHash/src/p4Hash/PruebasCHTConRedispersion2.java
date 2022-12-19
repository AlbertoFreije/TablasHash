package p4Hash;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PruebasCHTConRedispersion2 {

	@Test
	public void test1() {
		System.out.println("Pruebas con enteros");
		//Crea una tabla del tamaño 4 (numero no primo)
		ClosedHashTable<Integer> tabla = new ClosedHashTable<Integer>(4,0.5,0.16,1);
		//Muestra la tabla. Debera estar vacia y ser de tamaño 5
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 5 Num.Elems.: 0]",tabla.toString());

		//Inserta elementos
		assertEquals(true,tabla.add(8));
		assertEquals(true,tabla.add(10));
		System.out.println(tabla.toString());
		assertEquals("{10};{_E_};{_E_};{8};{_E_};[Size: 5 Num.Elems.: 2]",tabla.toString());

		
		//Inserta y redispersión
		assertEquals(true,tabla.add(66));
		System.out.println(tabla.toString());		
		assertEquals("{66};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{8};{_E_};{10};[Size: 11 Num.Elems.: 3]",tabla.toString());
		
		//Sigue insertando elementos
		assertEquals(true,tabla.add(77));
		assertEquals(true,tabla.add(7));
		
		//Inserta y redispersión
		assertEquals(true,tabla.add(9));
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{77};{8};{9};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 6]",tabla.toString());
		
		//Sigue insertando elementos
		assertEquals(true,tabla.add(88));
		System.out.println(tabla.toString());
				
		//Borra un elemento que existe
		assertEquals(true,tabla.remove(8));
		System.out.println(tabla.toString());
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{7};{77};{_D_};{9};{10};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{88};{66};{_E_};{_E_};[Size: 23 Num.Elems.: 6]",tabla.toString());
		
		
		//Sigue insertando elementos
		assertEquals(true,tabla.add(13));
		assertEquals(true,tabla.add(19));
		
		//Inserta elemetos que ya existen
		assertEquals(true,tabla.add(66));
		assertEquals(true,tabla.add(88));
		
		//Borra un elemento que no existe
		assertEquals(false,tabla.remove(2));
		

		//Borrar elementos
		assertEquals(true,tabla.remove(19));
		assertEquals(true,tabla.remove(7));
		assertEquals(true,tabla.remove(77));
		assertEquals(true,tabla.remove(9));
		System.out.println(tabla.toString());	
	
		
		//Borra 
		assertEquals(true,tabla.remove(10));
		assertEquals(true,tabla.remove(13));
		assertEquals(true,tabla.remove(88));
		System.out.println(tabla.toString());	
		assertEquals("{88};{66};{_E_};{_E_};{66};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};[Size: 11 Num.Elems.: 3]",tabla.toString());
		
		assertEquals(true,tabla.add(-3));
		System.out.println(tabla.toString());	
		assertEquals("{88};{66};{_E_};{_E_};{66};{_E_};{_E_};{_E_};{-3};{_E_};{_E_};[Size: 11 Num.Elems.: 4]",tabla.toString());
}
	

}

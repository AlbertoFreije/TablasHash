package p4Hash;

/**
 * @author Profesores ED
 * @version 2022-23 distribuible
 *
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
// IMPORTANTE
//	No cambiar el nombre ni visibilidad de los atributos protected

	protected HashNode<T> tabla[]; // vecto tabla de tipo hashNode

	protected int hashSize; // tamaño de la tabla, debe ser un numero primo, el B
	protected int numElems; // numero de elementos en la tabla en cada momento.

	// Estos tres estados son por el borrado perezoso
	public static final int LINEAL = 0; // Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	protected int tipoExploracion; // exploracion que se realizara en caso de colision (LINEAL por defecto)

	// REDISPERSION aumentar el tama�o de la tabla hash para evitar colisiones LF
	// >=0.5
	// REDISPERSION INVERSA disminuye el tama�o si el factor balance < 0.16

	/**
	 * Constructor para fijar el tamano al numero primo >= que el parametro y el
	 * tipo de exploración al indicado el tipo de exploracion(LINEAL=0,
	 * CUADRATICA=1, ...), si invalido LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int expl) {
		if (!isPositivePrime(tam)) {
			hashSize = nextPrimeNumber(tam);// Establece un tamaño valido si tam no es primo
		} else {
			hashSize = tam;
		}

		tabla = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		// Completar lo que falta...
		if (expl != 0) {
			this.tipoExploracion = expl;
		} else {
			this.tipoExploracion = LINEAL;
		}
		this.numElems = 0;

		for (int i = 0; i < hashSize; i++) {
			tabla[i] = new HashNode<>();
		}

	}

	/**
	 * Constructor para fijar el tamaño al numero primo >= que el parametro Se le
	 * pasa el tamaño de la table Hash, si no es un numero primo lo ajusta al primo
	 * superior el factor de carga limite, por encima del cual hay que redispersar
	 * (directa) el factor de carga limite, por debajo del cual hay que redispersar
	 * (inversa) el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido
	 * LINEAL
	 */
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) { // Para la segunda clase
		// Completar lo que falta...

	}

	@Override
	public int getNumOfElems() {

		return numElems;

	}

	@Override
	public int getSize() {
		return hashSize;
	}

	public int funcionDistribucion(T info, int intento) {

		int posicion = fHash(info);
		int posicionAInsertar = 0;

		// funcion = (fhash(info)+intentos) % B
		if (tipoExploracion == LINEAL) {
			posicionAInsertar = (posicion + intento) % hashSize;
		}
		// funcion = (fhash(info)+i^2) % B
		else if (tipoExploracion == CUADRATICA) {
			posicionAInsertar = (posicion + (intento ^ 2)) % hashSize;
		}
		// R = numero primo antecesor de B
		// h2 = r - fhash(x) % R
		// funcion = (fhash(info)+i * ) % B
		else {

			int r = previousPrimeNumber(hashSize);
			int h2 = r - fHash(info) % r;
			posicionAInsertar = ((posicion + intento) * h2) % hashSize;

		}

		return posicionAInsertar;

	}

	// inserto elemento si encuentro vacio o borrado
	// si encuentro lleno salto y realizo otro intento mas
	// al insertar a la celda le pongo la info del elemento y lo pongo a lleno
	// num elementos ++
	@Override
	public boolean add(T elem) {

		if (elem == null) {
			throw new NullPointerException();
		}

		int intento = 0;
		int pos = funcionDistribucion(elem, intento);

		while (intento <= hashSize) {

			if (tabla[pos].getStatus() == 0 || tabla[pos].getStatus() == 2) {
				if (numElems < hashSize) {
					tabla[pos].setInfo(elem);

					numElems++;

					return true;
				}
			} else {
				intento++;
				pos = funcionDistribucion(elem, intento);
			}

		}

		return false;

	}

	// coge la posicion donde deberia estar
	// el find pare cuando encuentra una casilla vacia
	// el numerp de intentos sea igual al numero de elementos de la tabla hash para
	// devuelve getInfo de la tabla hash
	// sigue si encuentra un lleno que no es el o un borrado
	//
	@Override
	public T find(T elem) {

		int iteracion = 0;
		int posicion = funcionDistribucion(elem, iteracion);
		while ((tabla[posicion].getStatus() !=0 && !tabla[posicion].getInfo().equals(elem))) {

			if (iteracion != numElems) {

				if (tabla[posicion].getStatus() == 0) {
					return null;

				}
				if (tabla[posicion].getInfo() != elem || tabla[posicion].getStatus() == 2) {

					iteracion++;
					posicion = funcionDistribucion(elem, iteracion);

				}

			}

		}

		if (tabla[posicion].getInfo() == elem) {
			return tabla[posicion].getInfo();
		}

		return null;

	}

	@Override
	public boolean remove(T elem) {
		
		if (elem == null) {
			throw new NullPointerException();
		}
		
		if(find(elem)== null) {
			return false;
		}
		
		int iteracion = 0;
		
		int pos = funcionDistribucion(elem, iteracion);
		
		while(tabla[pos].getStatus()==1 && tabla[pos].getInfo()!=elem) {
			iteracion++;
			pos =  funcionDistribucion(elem, iteracion);
		}
		
		tabla[pos].remove();
		numElems--;
		
		return true;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion() {
		return false; // Para la segunda clase
		// Completar lo que falta...
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() {
		return false; // Para la segunda clase
		// Completar lo que falta...
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < getSize(); i++) {
			cadena.append(tabla[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}

}

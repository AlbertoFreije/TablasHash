package p4Hash;


/**
 * @author Profesores ED
 * @version 2022-23 distribuible
 *
 */
public class ClosedHashTable<T> extends AbstractHash<T> {
// IMPORTANTE
//	No cambiar el nombre ni visibilidad de los atributos protected
	
	protected HashNode<T> tabla[]; 

	protected int hashSize;	// tamaño de la tabla, debe ser un numero primo
	protected int numElems;	// numero de elementos en la tabla en cada momento.

	public static final int LINEAL = 0;	// Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;
	

	protected int exploracion; //exploracion que se realizara en caso de colision (LINEAL por defecto)
	
	private double minlf;//factor de carga minimo
	private double maxlf;//factor de carga maximo
	
	public static final double MINIMUM_LF=0.16;
	public static final double MAXIMUN_LF=0.5;

	/**
	 * Constructor para fijar el tamano al numero primo >= que el parametro y el tipo de exploración al indicado
	 * el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int expl) {
		
		if(!isPositivePrime(tam)) {
			
			hashSize=nextPrimeNumber(tam);
			
		}else {
			hashSize=tam;// Establece un tamaño valido si tam no es primo
		}
		
		tabla = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		numElems = 0;
		if (expl != 0) {
			this.exploracion = expl;
		} else {
			this.exploracion = LINEAL;
		}
		
		for (int i = 0; i < hashSize; i++) {
			tabla[i] = new HashNode<>();
		}
		
		
		this.maxlf = MAXIMUN_LF;
		this.minlf = MINIMUM_LF;
	
	}

	/**
	 * Constructor para fijar el tamaño al numero primo >= que el parametro
	 * Se le pasa el tamaño de la table Hash, si no es un numero primo lo ajusta al primo superior
	 * el factor de carga limite, por encima del cual hay que redispersar (directa)
	 * el factor de carga limite, por debajo del cual hay que redispersar (inversa)
	 * el tipo de exploracion(LINEAL=0, CUADRATICA=1, ...), si invalido LINEAL
	 */
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) { // Para la segunda clase
		
		if(!isPositivePrime(tam)) {
			
			hashSize=nextPrimeNumber(tam);
			
		}else {
			hashSize=tam;// Establece un tamaño valido si tam no es primo
		}
		
		tabla = (HashNode<T>[]) new HashNode[hashSize]; // Crea el array de HashNode's
		numElems = 0;
		if (expl != 0) {
			this.exploracion = expl;
		} else {
			this.exploracion = LINEAL;
		}
		
		for (int i = 0; i < hashSize; i++) {
			tabla[i] = new HashNode<>();
		}
		
		
		this.maxlf = fcUP;
		this.minlf = fcDOWN;

	}

	@Override
	public int getNumOfElems() {
		
		return numElems;
		
	}

	/**
	 * devuelve tama�o tabla
	 */
	@Override
	public int getSize() {
		
		return tabla.length;
		
	}
	
	/**
	 * Devuelve donde deberia ir el nodo a insertar en funcion de la distribucion
	 * @param info
	 * @param i
	 * @return
	 */
	public int funcionDistribucion(T info, int i) {
		
		int b = getSize();
		int pos = fHash(info);
		int R = previousPrimeNumber(b);
		
		 //lineal
		if(exploracion==LINEAL){
			
			return (pos+i)%b; 
		}
		
		//cuadratica
		else if(exploracion==CUADRATICA){
		
			return(pos+i*i)%b;
		}
		
		//dispersion doble
		else if(exploracion==DOBLEHASH){
			
			return (pos+i *(R-pos%R))%b;
		}
		return 0;
	}

	/**
	 * Inserta un nuevo elemento en la tabla hash (que se le pasa) y devuelve true si lo ha insertado 
	 * o false en caso contrario (si no encuentra sitio). Y NullPointer si el elemento es null
	 */
	@Override
	public boolean add(T elem) {
	
		if(elem==null) {
			throw new NullPointerException();
		}
	
		
		if(numElems<getSize()) {
			
			insert(elem);
			numElems++;
			//reDispersion();
			return true;
			
		}
		
		return false;
		
		
	}



	private void insert(T elem) {
		
		int i = 0;
		int pos = 0;
		do {
			pos = funcionDistribucion(elem, i++);
			
		}
		while(tabla[pos].getStatus()==HashNode.LLENO);
		
		tabla[pos].setInfo(elem);
		
	}

	@Override
	public T find(T elem) {
	
		if(elem==null) {
			throw new NullPointerException();
		}
		
		if(numElems==0) {
			return null;
		}
		
		T elemento = getSlot(elem);
		
		return elemento;
		
	}

	private T getSlot(T elem) {

		int pos = 0;
		HashNode<T> resultado;
		for (int i = 0; i < getSize(); i++) {

			pos = funcionDistribucion(elem, i);
			resultado = tabla[pos];

			if (tabla[pos].getStatus() == 0) {
				return null;
			} else {
				if (resultado.getStatus() == HashNode.LLENO && resultado.getInfo() == elem) {
					return resultado.getInfo();
				}
			}

		}

		return null;

	}

	@Override
	public boolean remove(T elem) {
		
		if(elem==null) {
			throw new NullPointerException();
		}
		
		if(find(elem)==null) {
			return false;
		}
		
		delete(elem);
		numElems--;
		//inverseReDispersion();
		return true;
	
	}

	private boolean delete(T elem) {

		int pos = 0;
		HashNode<T> resultado;

		for (int i = 0; i < getSize(); i++) {

			pos = funcionDistribucion(elem, i);
			resultado = tabla[pos];

			if (resultado.getStatus() == HashNode.LLENO && resultado.getInfo() == elem) {
				resultado.remove();
				return true;
			}

		}
		
		return false;

	}
	
	/**
	 * @return factor de carga
	 */
	private double getFc(){
		
		return numElems/(getSize()*1.0); //se multiplica por 1.0 para que el resultado final sea double
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion () {
		
		HashNode<T>[] aux;
		
		if(getFc()>maxlf) {
			
			aux = tabla;
			
			int tam = nextPrimeNumber(getSize()*2);
			tabla= nuevoTamanoTabla(tam);
			llenarTabla(aux);
			
			return true;
			
		}
		
		return false;
	   
	}

	private void llenarTabla(HashNode<T>[] aux) {
		
		for(int i=0;i<aux.length;i++) {
			if(aux[i].getStatus()==HashNode.LLENO) {
				insert(aux[i].getInfo());
			}
		}
		
	}

	private HashNode<T>[] nuevoTamanoTabla(int tam) {
		
		HashNode<T>[] tablaNueva = (HashNode<T>[]) new HashNode[tam];
		for (int i = 0; i < tam; i++) {
			tablaNueva[i] = new HashNode<>();
		}
		
		return tablaNueva;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() {
	
		HashNode<T>[] aux;
		
		if(getFc()<minlf) {
			
			aux = tabla;
			int tam=0;
			tam= previousPrimeNumber(getSize()/2);
			tabla= nuevoTamanoTabla(tam);
			
			llenarTabla(aux);
			
			return true;
			
		}
		
		return false;
	       
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString (){
		StringBuilder cadena=new StringBuilder();
		for (int i=0;i< getSize();i++){
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

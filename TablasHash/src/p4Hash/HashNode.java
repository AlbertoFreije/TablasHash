package p4Hash;

/**
 * @author Profesores ED
 * @version 2022-23 distribuible
 *
 * @param <T>
 */
public class HashNode<T> {
	private T info;
	private int status;

	
	public static final int BORRADO = -1;
	public static final int VACIO = 0;
	public static final int LLENO = 1;

	public HashNode () {
		this.info = null;
		status = VACIO;
	}
	
	public T getInfo() {
		
		return info;
		
	}
	
	public void remove (){
		this.status = BORRADO;	
	}
	
	public void setInfo(T elem){
		
		this.info = elem;
		this.status= LLENO;
		
	}
	
	public int getStatus() {
		
		return status;
		
	}

	public String toString (){
		StringBuilder cadena=new StringBuilder("{");
		switch (getStatus()){
		case LLENO:
			cadena.append(info);
			break;
		case VACIO:
			cadena.append("_E_");
			break;
		case BORRADO:
			cadena.append("_D_");
		}
		cadena.append("}");
		return cadena.toString();
	}

}

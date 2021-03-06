package tablasHash;

public class TablaHashEncadenada<K,V> implements ITablaHash<K,V> {
	
	private NodoHash<K,V>[] tabla;
	private int numeroElementosEnTabla;
	
	public TablaHashEncadenada(int tam){
		tabla=new NodoHash[tam];
		numeroElementosEnTabla=0;
	}
	
	public int getNumeroElementosEnTabla() {
		return numeroElementosEnTabla;
	}
	public boolean isEmpty() {
		return numeroElementosEnTabla==0;
	}
	/**
	 * llave!=null
	 */
	public boolean insert(K llave, V valor) {
		int pos=hash(llave);
		if(tabla[pos]!=null){
			NodoHash<K,V> first=tabla[pos];
			NodoHash<K,V> nuevo=new NodoHash<K,V>(llave,valor);
			nuevo.setSgt(first);
			
			tabla[pos]=nuevo;
		}else{
			NodoHash<K,V> nuevo=new NodoHash<K,V>(llave,valor);
			tabla[pos]=nuevo;
		}
		numeroElementosEnTabla++;
		return true;
	}
	/**
	 * llave!=null
	 */
	public V find(K llave) {
		int pos=hash(llave);
		NodoHash<K,V> current=(NodoHash<K, V>) tabla[pos];
		while(current!=null&&!current.getLlave().equals(llave)){
			current=(NodoHash<K, V>) current.getSgt();
		}
		if(current==null){
			return null;
		}else{
			return current.getValor();			
		}
	}

	public V delete(K llave){
		int pos=hash(llave);
		NodoHash<K,V>ant=null;
		NodoHash<K,V>current=(NodoHash<K, V>) tabla[pos];
		while(current!=null&&!current.getLlave().equals(llave)){
			ant=current;
			current=(NodoHash<K, V>) current.getSgt();
		}
		if(current==null){
			return null;
		}else{
			if(ant==null){
				tabla[pos]=ant;
			}else{
				ant.setSgt(current.getSgt());
			}
			numeroElementosEnTabla--;
			return current.getValor();
		}
	}
	
	//Why strings have NegativeHashCodes
	public int hash(K llave) {
//		System.out.println((llave.hashCode()&0x7fffffff)%tabla.length);
		return (llave.hashCode()&0x7fffffff)%tabla.length;
	}
	
}

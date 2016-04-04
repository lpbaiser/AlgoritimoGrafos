package grafo;

public class Aresta<U extends Vertice, V extends Vertice> {
	private U v1 = null;
	private V v2 = null;
	
	public Aresta(U v1, V v2){
		this.v1 = v1;
		this.v2 = v2;
	}

	public U getVertice1() {
		return v1;
	}

	public void setVertice1(U v1) {
		this.v1 = v1;
	}

	public V getVertice2() {
		return v2;
	}

	public void setVertice2(V v2) {
		this.v2 = v2;
	}
	
	
}

package factory;


import grafo.Grafo;

public class GrafoFactory implements Factory {
	public static Grafo constroiGrafo(Representacao r, int tipo ){
		switch(r){
			case LISTA_ADJACENCIA:
				return new GrafoListaAdjacencia();
			case MATRIZ_ADJACENCIA:
				return new GrafoMatrizAdjacencia(tipo);
		}
		return null;
	}
}

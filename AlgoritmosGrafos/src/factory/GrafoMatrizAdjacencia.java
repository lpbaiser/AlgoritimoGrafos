package factory;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;
import java.util.Iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//TODO Exercício 2.1 - Implementar os métodos para o grafo representado
//por matriz de adjacências
public class GrafoMatrizAdjacencia implements Grafo<Vertice, Aresta<Vertice, Vertice>> {

    int tipo;
    
    public GrafoMatrizAdjacencia(int tipo) {
        this.tipo = tipo;
    }

    

    int tamanho = 0;

    int matriz[][] = new int[tamanho][tamanho];

    HashMap<Vertice, Integer> verticeToInt = new HashMap<>();

    HashMap<Integer, Vertice> intToVertice = new HashMap<>();

    @Override
    public Iterator<Vertice> getVerticesAdjacentes(Vertice u) {
        getVertices();

        int linha = verticeToInt.get(u);
        //itera sobre as colunas da matriz
        for (int j = 0; j < matriz.length; j++) {
            //tem vertice?
            if (tipo == 0 && (matriz[linha][j] == 1 || matriz[j][linha] == 1)) {
                //itera sobre o Map do verticeToInt
                for (Map.Entry<Vertice, Integer> entry : verticeToInt.entrySet()) {
                    //o valor da entrada é igual ao valor do id do vértice procurado?
                    // se sim put o vertice no Map intToVertice
                    if (entry.getValue() == j) {
                        intToVertice.put(j, entry.getKey());
                    }
                }
            } else if (matriz[linha][j] == 1 && tipo == 1) {
                //itera sobre o Map do verticeToInt
                for (Map.Entry<Vertice, Integer> entry : verticeToInt.entrySet()) {
                    //o valor da entrada é igual ao valor do id do vértice procurado?
                    // se sim put o vertice no Map intToVertice
                    if (entry.getValue() == j) {
                        intToVertice.put(j, entry.getKey());
                    }
                }
            }
        }
        return intToVertice.values().iterator();
    }

    @Override
    public Iterator<Vertice> getVertices() {
        return this.verticeToInt.keySet().iterator();
    }

    @Override
    public Iterator<Aresta<Vertice, Vertice>> getArestas() {
        Set<Aresta<Vertice, Vertice>> arestas = new HashSet<>();
        getVertices();

        for (Map.Entry<Vertice, Integer> entry : verticeToInt.entrySet()) {
            intToVertice = new HashMap<>();
            getVerticesAdjacentes(entry.getKey());
            for (Map.Entry<Integer, Vertice> entry1 : intToVertice.entrySet()) {
                arestas.add(new Aresta<Vertice, Vertice>(entry.getKey(), entry1.getValue()));
            }
        }
        return arestas.iterator();
    }

    @Override
    public Vertice getVertice(String idVertice) {
        for (Map.Entry<Vertice, Integer> entry : verticeToInt.entrySet()) {
            if (entry.getKey().getId().equals(idVertice)) {
                return entry.getKey();
            }

        }
        return null;
    }

    @Override
    public void adicionaVertice(Vertice verticeNoGrafo, Vertice verticeAdicionado) {
        Vertice v = getVertice(verticeNoGrafo.getId());

        if (v == null) {
            throw new RuntimeException("O vértice com identificador "
                    + verticeNoGrafo.getId()
                    + " precisa necessariamente estar no grafo.");
        } // else -> vertice esta no grafo !
        else {
            Vertice v2 = getVertice(verticeAdicionado.getId());
            int idV1 = verticeToInt.get(v);
            //vertice adicionado não esta no grafo? se estiver no else apenas insere a aresta,
            //caso não, insere o vertice e aresta
            if (v2 == null && matriz.length == tamanho) {
                matriz = copyMatriz(tamanho + 1, matriz);
                tamanho++;
                matriz[idV1][tamanho - 1] = 1;
                verticeToInt.put(verticeAdicionado, tamanho - 1);
            } else {
                int idV2 = verticeToInt.get(v2);
                matriz[idV1][idV2] = 1;
            }
        }
    }

    @Override
    public void adicionaVertice(Vertice verticeAdicionado) {
        if (matriz.length == tamanho) {
            matriz = copyMatriz(tamanho + 1, matriz);
            tamanho++;
        }
        matriz[tamanho - 1][tamanho - 1] = 0;
        verticeToInt.put(verticeAdicionado, tamanho - 1);
    }

    @Override
    public void adicionaAresta(Aresta<Vertice, Vertice> arestaAdicionada) {
        if (arestaAdicionada.getVertice1() == null || arestaAdicionada.getVertice2() == null) {
            throw new RuntimeException("Não é possível adicionar uma aresta com vértice nulos no grafo");
        } else {
            // vertice 1 da aresta já existe no grafo ?
            // se não, adiciona o vertice na matriz grafo
            Vertice v1 = getVertice(arestaAdicionada.getVertice1().getId());
            if (v1 == null) {
                adicionaVertice(arestaAdicionada.getVertice1());
            }
            // vertice 2 da aresta já existe no grafo ?
            // se não, adiciona o vertice na matriz grafo
            Vertice v2 = getVertice(arestaAdicionada.getVertice2().getId());
            if (v2 == null) {
                adicionaVertice(arestaAdicionada.getVertice2());
            }

            int line = 0, col = 0;
            v1 = getVertice(arestaAdicionada.getVertice1().getId());
            v2 = getVertice(arestaAdicionada.getVertice2().getId());
            for (Map.Entry<Vertice, Integer> entry : verticeToInt.entrySet()) {
                if (entry.getKey() == v1) {
                    line = entry.getValue();
                }
                if (entry.getKey() == v2) {
                    col = entry.getValue();
                }
            }

            matriz[line][col] = 1;

        }
    }

    public int[][] copyMatriz(int tamanho, int[][] matrizOld) {
        int[][] newMatriz = new int[tamanho][tamanho];
        for (int j = 0; j < matrizOld.length; j++) {
            for (int k = 0; k < matrizOld.length; k++) {
                newMatriz[j][k] = matrizOld[j][k];
            }

        }
        return newMatriz;
    }

    @Override
    public int[][] retMatriz() {
        return matriz;
    }

}

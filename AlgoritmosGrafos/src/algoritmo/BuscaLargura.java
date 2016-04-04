package algoritmo;

import entidades.Cor;
import entidades.CorVertice;
import entidades.VerticeBuscaLargura;
import grafo.Aresta;
import grafo.Grafo;
import java.util.Iterator;
import java.util.PriorityQueue;

public class BuscaLargura implements Algoritmo {
    
    private Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g;
    private VerticeBuscaLargura s;
    PriorityQueue<VerticeBuscaLargura> queue;
    
    public BuscaLargura(Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g, VerticeBuscaLargura verticeInicial) {
        if (g.getVertice(verticeInicial.getId()) == null) {
            throw new RuntimeException("O vértice de índice " + verticeInicial.getId() + " não pertence ao grafo " + g.toString() + ". "
                    + "Utilize um vértice válido como argumento do construtor da classe " + this.getClass().getName());
        } else {
            this.g = g;
            this.s = verticeInicial;
            this.queue = new PriorityQueue<>();
            inicializaGrafo();
        }
    }

    //Algoritmo para inicialização do grafo na BFS 
    public void inicializaGrafo() {
        VerticeBuscaLargura u;
        Iterator<VerticeBuscaLargura> iteratorVertice = g.getVertices();
        while (iteratorVertice.hasNext()) {
            u = iteratorVertice.next();
            if (u != this.s) {
                u.setCor(new CorVertice(Cor.Branco));
                u.setDistancia((int) Float.POSITIVE_INFINITY);
                u.setPai(null);
            }
        }
        s.setDistancia(0);
        s.setPai(null);
        s.setCor(new CorVertice(Cor.Cinza));
        this.queue.add(s);
    }

    //TODO Exercicio 3.1 - Implementar a busca em largura
    @Override
    public void executar() {
        while (!this.queue.isEmpty()) {
            VerticeBuscaLargura u = queue.poll();
            Iterator<VerticeBuscaLargura> iteratorVertice = g.getVerticesAdjacentes(u);
            while (iteratorVertice.hasNext()) {
                VerticeBuscaLargura v = iteratorVertice.next();
                if (v.getCor().equals(Cor.Branco)) {
                    v.setCor(new CorVertice(Cor.Cinza));
                    v.setDistancia(u.getDistancia() + 1);
                    v.setPai(u);
                    queue.add(v);
                }
            }
            u.setCor(new CorVertice(Cor.Preto));
        }
//            imprimeGrafo(g, s, g.getVertice("y"));
    }
    
    public void imprimeGrafo(Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g, VerticeBuscaLargura s, VerticeBuscaLargura v) {
        if (v.equals(s)) {
            System.out.print(s);
        } else if (v.getPai() == null) {
            System.out.print("não há caminho entre " + s + " e " + v);
        } else {
            imprimeGrafo(g, s, v.getPai());
            System.out.print(v);
        }
    }
}

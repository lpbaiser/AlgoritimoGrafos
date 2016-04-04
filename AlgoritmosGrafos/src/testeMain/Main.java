/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeMain;

import algoritmo.BuscaLargura;
import entidades.VerticeBuscaLargura;
import factory.GrafoFactory;
import factory.Representacao;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;
import java.util.HashMap;

/**
 *
 * @author leonardo
 */
public class Main {
    
    public static void main(String[] args) {
        HashMap<Vertice, Integer> distanciasMinimas = new HashMap<Vertice, Integer>();

        Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g;
        g = GrafoFactory.constroiGrafo(Representacao.LISTA_ADJACENCIA, Grafo.NAO_DIRECIONADO);

        //vértice inicial
        VerticeBuscaLargura s = new VerticeBuscaLargura("s");
        g.adicionaVertice(s);

        //armazena a distancia de 's' até 's
        distanciasMinimas.put(s, 0);

        //criação dos vértices
        VerticeBuscaLargura r = new VerticeBuscaLargura("r");
        VerticeBuscaLargura t = new VerticeBuscaLargura("t");
        VerticeBuscaLargura u = new VerticeBuscaLargura("u");
        VerticeBuscaLargura v = new VerticeBuscaLargura("v");
        VerticeBuscaLargura w = new VerticeBuscaLargura("w");
        VerticeBuscaLargura x = new VerticeBuscaLargura("x");
        VerticeBuscaLargura y = new VerticeBuscaLargura("y");

        //adição dos vértices
        g.adicionaVertice(s, r); //s-r
        g.adicionaVertice(s, w); //s-w
        g.adicionaVertice(r, v); //r-v
        g.adicionaVertice(w, t); //w-t
        g.adicionaVertice(w, x); //w-x
        g.adicionaVertice(x, u); //x-u
        g.adicionaVertice(x, y); //x-y
        g.adicionaVertice(x, t); //x-t
        g.adicionaVertice(t, u); //t-u
        g.adicionaVertice(u, y); //u-y

        BuscaLargura bl = new BuscaLargura(g, s);
        bl.executar();
//        System.out.println("S.d = "+ g.getVertice("s").getDistancia());
        
    }
    
}

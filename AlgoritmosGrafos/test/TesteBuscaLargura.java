/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import algoritmo.BuscaLargura;
import entidades.VerticeBuscaLargura;
import factory.GrafoFactory;
import factory.Representacao;
import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author leonardo
 */
public class TesteBuscaLargura {

//    public TesteBuscaLargura() {
//    }
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
    HashMap<Vertice, Integer> distanciasMinimas = new HashMap<Vertice, Integer>();

    Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g;

    public void constroiGrafo() {
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
    }

    @Test
    public void Lema1() {
        constroiGrafo();

        //TODO Exercício 3.3
        //Testar se, para toda aresta (u,v) a menor distância
        //entre 's' e 'v' é menor ou igual a menor distância 
        //entre 's' e 'u + 1'
        assertTrue(g.getVertice("s").getDistancia() == 0);
        //assert se a distancia de <s,r> for menor ou igual a distancia entre <s, w>
        assertTrue(g.getVertice("r").getDistancia() <= g.getVertice("w").getDistancia());
        assertTrue(g.getVertice("t").getDistancia() <= g.getVertice("w").getDistancia());
        assertTrue(g.getVertice("u").getDistancia() <= g.getVertice("w").getDistancia());
        assertTrue(g.getVertice("v").getDistancia() <= g.getVertice("w").getDistancia());
        assertTrue(g.getVertice("w").getDistancia() <= g.getVertice("w").getDistancia());
        assertTrue(g.getVertice("x").getDistancia() <= g.getVertice("w").getDistancia());
        assertTrue(g.getVertice("y").getDistancia() <= g.getVertice("w").getDistancia());

    }

    @Test
    public void Lema2() {
        constroiGrafo();
        assertTrue(g.getVertice("r").getDistancia() >= g.getVertice("s").getDistancia());
        assertTrue(g.getVertice("t").getDistancia() >= g.getVertice("s").getDistancia());
        assertTrue(g.getVertice("u").getDistancia() >= g.getVertice("s").getDistancia());
        assertTrue(g.getVertice("v").getDistancia() >= g.getVertice("s").getDistancia());
        assertTrue(g.getVertice("w").getDistancia() >= g.getVertice("s").getDistancia());
        assertTrue(g.getVertice("x").getDistancia() >= g.getVertice("s").getDistancia());
        assertTrue(g.getVertice("y").getDistancia() >= g.getVertice("s").getDistancia());
    }
//
//    @Test
//    public void Lema3() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void TeoremaCorretude() {
//        fail("Not yet implemented");
//    }

}

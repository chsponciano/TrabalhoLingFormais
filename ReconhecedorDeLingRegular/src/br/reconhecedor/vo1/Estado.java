/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.reconhecedor.vo1;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Estado {

    private List<NoEstado> noEstados;
    private static Estado object;
    public static boolean isErro = false;

    private Estado() {
        construirAutomato();
    }

    public static Estado getInstance() {
        if (object == null) {
            object = new Estado();
        }

        return object;
    }

    private NoEstado estadoInicial() {
        for (NoEstado no : noEstados) {
            if (no.isEstadoInicial()) {
                return no;
            }
        }

        return null;
    }

    private String estadoQErro() {
        isErro = true;
        return "qErro";
    }

    private String estadoQFinal(NoEstado no) {
        return (no.isEstadoFinal()) ? "" : ", " + this.estadoQErro();
    }

    private void construirAutomato() {
        //Inicializando estados     Desc   Final  Inicial
        NoEstado q0 = new NoEstado("q0", false, true);
        NoEstado q15 = new NoEstado("q15", true, false);
        NoEstado q7 = new NoEstado("q7", true, false);
        NoEstado q16 = new NoEstado("q16", true, false);
        NoEstado q2 = new NoEstado("q2", false, false);
        NoEstado q8 = new NoEstado("q8", false, false);
        NoEstado q27 = new NoEstado("q27", true, false);
        NoEstado q3 = new NoEstado("q3", true, false);
        NoEstado q4 = new NoEstado("q4", false, false);

        //Caminhos q0
        q0.addCaminhos('a', q15);
        q0.addCaminhos('b', q7);
        q0.addCaminhos('c', q7);

        //Caminhos q15
        q15.addCaminhos('a', q16);
        q15.addCaminhos('b', q2);
        q15.addCaminhos('c', q2);

        //Caminhos q7
        q7.addCaminhos('b', q8);
        q7.addCaminhos('c', q8);

        //Caminhos q16
        q16.addCaminhos('a', q15);
        q16.addCaminhos('b', q27);
        q16.addCaminhos('c', q27);

        //Caminhos q2
        q2.addCaminhos('a', q3);

        //Caminhos q8
        q8.addCaminhos('b', q7);
        q8.addCaminhos('c', q7);

        //Caminhos q27
        q27.addCaminhos('a', q3);
        q27.addCaminhos('b', q8);
        q27.addCaminhos('c', q8);

        //Caminhos q3
        q3.addCaminhos('a', q4);
        q3.addCaminhos('b', q2);
        q3.addCaminhos('c', q2);

        //Caminhos q4
        q4.addCaminhos('a', q3);

        this.noEstados = new ArrayList<>();
        this.noEstados.add(q0);
        this.noEstados.add(q15);
        this.noEstados.add(q7);
        this.noEstados.add(q16);
        this.noEstados.add(q2);
        this.noEstados.add(q8);
        this.noEstados.add(q27);
        this.noEstados.add(q3);
        this.noEstados.add(q4);
    }

    public String validarPercuso(String palavra) {
        isErro = false;
        NoEstado no = estadoInicial();
        return no.getDescricao() + this.validarPercuso(no, 0, palavra);
    }

    private String validarPercuso(NoEstado no, int posicao, String palavra) {
        if (palavra.length() == posicao) {
            return estadoQFinal(no);
        }

        no = no.getCaminhos().get(palavra.charAt(posicao));

        if (no != null) {
            posicao++;
            return ", " + no.getDescricao() + this.validarPercuso(no, posicao, palavra);
        }

        return ", " + this.estadoQErro();
    }
}

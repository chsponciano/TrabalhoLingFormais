package br.reconhecedor.vo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */

public class NoEstado {
    private String descricao;
    private boolean estadoFinal;
    private boolean estadoInicial;
    private Map<Character, NoEstado> caminhos;

    public NoEstado(String descricao, boolean estadoFinal, boolean estadoInicial) {
        this.setDescricao(descricao);
        this.setEstadoFinal(estadoFinal);
        this.setEstadoInicial(estadoInicial);
        this.caminhos =  new HashMap<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao.isEmpty() || descricao == null || descricao.equals(" ")){
            throw new IllegalArgumentException("Descrição vazia");
        }
        this.descricao = descricao;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(boolean estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Map<Character, NoEstado> getCaminhos() {
        return caminhos;
    }

    public boolean addCaminhos(char chave, NoEstado valor){
        if(this.caminhos.containsKey(chave)){
            return false;
        }
        
        return (this.caminhos.put(chave, valor) != null);
    }

    @Override
    public String toString() {
        return this.descricao;
    }    
}

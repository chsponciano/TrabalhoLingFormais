package br.reconhecedor.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Henrique Ponciano da Silva && Vinicius Luis da Silva
 */

public class LexicoOld {

    public static LexicoOld object;

    private List<String> palavras_reservadas;
    private List<String> caracteres_especiais;
    private char caracter;
    private boolean isCorrect;
    private int letras_palavra;
    private String palavra_analisa;

    private LexicoOld() {
        this.palavras_reservadas = new ArrayList<String>();
        this.caracteres_especiais = new ArrayList<String>();

        this.palavras_reservadas.add("abaaa");
        this.palavras_reservadas.add("acaaa");
        this.palavras_reservadas.add("aaaba");
        this.palavras_reservadas.add("aaaca");

        this.caracteres_especiais.add(";");
        this.caracteres_especiais.add(",");
        this.caracteres_especiais.add(".");
    }

    public static LexicoOld getInstance() {
        if (object == null) {
            object = new LexicoOld();
        }
        return object;
    }

    public void reconhecimento(JTextArea entrada, JTable saida) {
        List lista = montaListaDePalvras(entrada.getText());
        Iterator iterator = lista.iterator();
        int linha;
        String palavra;
        String[] dados;

        DefaultTableModel dtm = (DefaultTableModel) saida.getModel();
        dtm.setRowCount(0);

        while (iterator.hasNext()) {
            linha = Integer.parseInt(String.valueOf(iterator.next()));
            palavra = String.valueOf(iterator.next());
            dados = valida_palavra(palavra);

            dtm.addRow(new Object[]{linha, dados[0], palavra, dados[1]});
            System.out.println(linha + " - " + dados[0] + " - " + palavra + " - " + dados[1]);
        }
    }

    public String[] valida_palavra(String palavra) {
        if (this.caracteres_especiais.contains(palavra)) {
            return new String[]{"Simbolo Especial", "q0, qEspecial"};
        }
        if (this.palavras_reservadas.contains(palavra)) {
            return new String[]{"Palavra Reservada", vericacao_estado(palavra)};
        }
        if ((palavra.charAt(0) != 'a') && (palavra.charAt(0) != 'b') && (palavra.charAt(0) != 'c')) {
            return new String[]{"Erro: simbolo(s) inválido(s)", "q0, qErro"};
        }

        String reconhecimento = vericacao_estado(palavra);
        return new String[]{(this.isCorrect) ? "Palavra válida" : "Erro: palavra inválida", reconhecimento};
    }

    private List montaListaDePalvras(String conteudo) {
        List<Object> palavras = new ArrayList<>();
        int linha = 1;
        String aux = "";

        for (char c : conteudo.toCharArray()) {
            if (this.caracteres_especiais.contains(String.valueOf(c))) {//Caracter Especial
                if (aux.length() > 0) {
                    palavras.add(linha);
                    palavras.add(aux);
                    aux = "";
                }
                palavras.add(linha);
                palavras.add(c);
            } else if (c == '\n') {//Pula linha
                if (aux.length() > 0) {
                    palavras.add(linha);
                    palavras.add(aux);
                    aux = "";
                }
                linha++;
            } else if (Character.getType(c) == 15 || Character.getType(c) == 12) {//Tabulacao ou Espaco em branco
                if (aux.length() > 0) {
                    palavras.add(linha);
                    palavras.add(aux);
                    aux = "";
                }
            } else {
                aux += c;
            }
        }

        if (aux.length() > 0) {
            palavras.add(linha);
            palavras.add(aux);
        }

        return palavras;
    }

    private String estadoQ0() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q0, " + estadoQErro();
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'a':
                return "q0, " + estadoQ15();
            case 'b':
                return "q0, " + estadoQ7();
            case 'c':
                return "q0, " + estadoQ7();
            default:
                return "q0, " + estadoQErro();
        }
    }

    private String estadoQ15() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q15";
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'a':
                return "q15, " + estadoQ16();
            case 'b':
                return "q15, " + estadoQ2();
            case 'c':
                return "q15, " + estadoQ2();
            default:
                return "q15, " + estadoQErro();
        }
    }

    private String estadoQ7() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q7";
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'b':
                return "q7, " + estadoQ8();
            case 'c':
                return "q7, " + estadoQ8();
            default:
                return "q7, " + estadoQErro();
        }
    }

    private String estadoQ16() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q16";
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'a':
                return "q16, " + estadoQ15();
            case 'b':
                return "q16, " + estadoQ27();
            case 'c':
                return "q16, " + estadoQ27();
            default:
                return "q16, " + estadoQErro();
        }
    }

    private String estadoQ2() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q2, " + estadoQErro();
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'a':
                return "q2, " + estadoQ3();
            default:
                return "q2, " + estadoQErro();
        }
    }

    private String estadoQ8() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q8, " + estadoQErro();
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'b':
                return "q8, " + estadoQ3();
            case 'c':
                return "q8, " + estadoQ3();
            default:
                return "q8, " + estadoQErro();
        }
    }

    private String estadoQ27() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q27";
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'a':
                return "q27, " + estadoQ3();
            case 'b':
                return "q27, " + estadoQ8();
            case 'c':
                return "q27, " + estadoQ8();
            default:
                return "q27, " + estadoQErro();
        }
    }

    private String estadoQ3() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q3";
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'a':
                return "q3, " + estadoQ4();
            case 'b':
                return "q3, " + estadoQ2();
            case 'c':
                return "q3, " + estadoQ2();
            default:
                return "q3, " + estadoQErro();
        }
    }

    private String estadoQ4() {
        if (this.letras_palavra == this.palavra_analisa.length()) {
            return "q4, " + estadoQErro();
        }
        this.caracter = this.palavra_analisa.charAt(this.letras_palavra);
        this.letras_palavra++;

        switch (this.caracter) {
            case 'a':
                return "q4, " + estadoQ3();
            default:
                return "q4, " + estadoQErro();
        }
    }

    private String estadoQErro() {
        this.isCorrect = false;
        return "qErro";
    }

    private String vericacao_estado(String palavra) {
        this.palavra_analisa = palavra;
        this.isCorrect = true;
        this.letras_palavra = 0;
        return estadoQ0();
    }
}

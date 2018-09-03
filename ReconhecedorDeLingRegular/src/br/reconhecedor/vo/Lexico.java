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

public class Lexico {

    public static Lexico OBJETO;
    private List<String> palavrasReservadas;
    private List<String> caracteresEspeciais;
    private final char[] caracteresTecladoEspeciais = new char[]{'\'','!','@','#','$','%','¨','&','*','(',')','_','+','=','§','-','¹','²','³','£','¢','¬','[',']','{','}','º','°','?','/','|','\\','´','`','~','^'};

    private Lexico() {
        this.palavrasReservadas  = new ArrayList<String>();
        this.caracteresEspeciais = new ArrayList<String>();

        this.palavrasReservadas.add("abaaa");
        this.palavrasReservadas.add("acaaa");
        this.palavrasReservadas.add("aaaba");
        this.palavrasReservadas.add("aaaca");

        this.caracteresEspeciais.add(";");
        this.caracteresEspeciais.add(",");
        this.caracteresEspeciais.add(".");
    }

    public static Lexico getInstance() {
        if (OBJETO == null) {
            OBJETO = new Lexico();
        }
        return OBJETO;
    }

    //Pega o texto da textArea e verifica as palavras em seguida as valida e monta a saida na table
    public void reconhecimento(JTextArea entrada, JTable saida) {
        List lista = montaListaDePalvras(entrada.getText());
        Iterator iterator = lista.iterator();

        DefaultTableModel dtm = (DefaultTableModel) saida.getModel();
        dtm.setRowCount(0);
        
        while (iterator.hasNext()) {
            dtm.addRow(validaPalavra(Integer.parseInt(String.valueOf(iterator.next())), String.valueOf(iterator.next())));
        }
    }

    private Object[] validaPalavra(int linha, String palavra) {
        if (this.caracteresEspeciais.contains(palavra)) {
            return new Object[]{linha, "Simbolo Especial", palavra, "q0, qEspecial"};
        }
        if (this.palavrasReservadas.contains(palavra)) {
            return new Object[]{linha, "Palavra Reservada", palavra, Estado.getInstance().validarPercuso(palavra)};
        }
        if ((palavra.charAt(0) != 'a') && (palavra.charAt(0) != 'b') && (palavra.charAt(0) != 'c')) {
            return new Object[]{linha, "Erro: simbolo(s) inválido(s)", palavra, "q0, qErro"};
        }

        String reconhecimento = Estado.getInstance().validarPercuso(palavra);
        return new Object[]{linha, (!Estado.isErro) ? "Palavra válida" : "Erro: palavra inválida", palavra, reconhecimento};
    }

    private List montaListaDePalvras(String conteudo) {
        List<Object> palavras = new ArrayList<>();
        int linha = 1;
        String aux = "";

        for (char c : conteudo.toCharArray()) {
            if(Character.isLetterOrDigit(c) || this.isCaracterEspecialTeclado(c)){
                aux += c;
            }else{
                if (aux.length() > 0) {
                    palavras.add(linha);
                    palavras.add(aux);
                    aux = "";
                }
                if(this.caracteresEspeciais.contains(String.valueOf(c))){
                    palavras.add(linha);
                    palavras.add(c);
                    continue;
                }
                
                if(c == '\n'){
                    linha++;
                    continue;
                }
            }
        }

        if (aux.length() > 0) {
            palavras.add(linha);
            palavras.add(aux);
        }

        return palavras;
    }
    
    private boolean isCaracterEspecialTeclado(char c){
        for (char caracter : this.caracteresTecladoEspeciais) {
            if(c == caracter){
                return true;
            }
        }
        return false;
    }
}

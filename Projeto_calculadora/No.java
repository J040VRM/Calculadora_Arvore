//Nome João Vitor Rocha Miranda RA 10427273
// Nome Matheus Veiga Bacetic Joaquim RA 10425638

// Classe base para todos os tipos de nós
public class No {
    String valor;
    No esquerda;
    No direita;

    public No(String valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    // Metodo visitar() na classe base
    public double visitar() {
        return Double.NaN; // Comportamento padrão: retorna NaN
    }
}
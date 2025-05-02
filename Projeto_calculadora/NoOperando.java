//Nome João Vitor Rocha Miranda RA 10427273
// Nome Matheus Veiga Bacetic Joaquim RA 10425638

/*Utilizado para representar os valores double (numeros) da arvore*/
public class NoOperando extends No {
    public NoOperando(String valor) {
        super(valor);
    }

    @Override
    public double visitar() {
        try {
            return Double.parseDouble(valor); // Converte o valor para double
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor inválido para operando: " + valor);
        }
    }
}


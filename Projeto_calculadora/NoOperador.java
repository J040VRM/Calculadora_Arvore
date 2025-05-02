//Nome João Vitor Rocha Miranda RA 10427273
// Nome Matheus Veiga Bacetic Joaquim RA 10425638

/*Classe que representa os operadores validos.*/
public class NoOperador extends No {
    public NoOperador(String valor) {
        super(valor);
    }

    @Override
    public double visitar() {
        //Para que a expressao seja valida, ela precisa de 2 numeros
        if (esquerda == null || direita == null) {
            throw new IllegalStateException("Operador sem operandos válidos!");
        }

        double esquerdo = esquerda.visitar();
        double direito = direita.visitar();

        switch (valor) {
            case "+": return esquerdo + direito;
            case "-": return esquerdo - direito;
            case "*": return esquerdo * direito;
            case "/":
                if (direito == 0) {
                    throw new ArithmeticException("Divisão por zero!");
                }
                return esquerdo / direito;
            default:
                throw new IllegalArgumentException("Operador inválido: " + valor);
        }
    }
}
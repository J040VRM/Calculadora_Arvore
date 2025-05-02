//Nome João Vitor Rocha Miranda RA 10427273
// Nome Matheus Veiga Bacetic Joaquim RA 10425638


import java.util.*;

public class Conv {
    /*Dicionario que define a prioridade das operacoes matematicas*/
    private static final Map<String, Integer> precedencia = new HashMap<>();
    static {
        precedencia.put("+", 1);
        precedencia.put("-", 1);
        precedencia.put("*", 2);
        precedencia.put("/", 2);
    }
    public boolean operadorValido(String c) {
        return precedencia.containsKey(c);
    }

    /*Metodo que separa a expressao token a token e insere em uma lista*/
    public List<String> tokenize(String expressao) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean decimalUsed = false;

        for (char c : expressao.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            } else if (c == '.') {
                if (decimalUsed) {
                    System.out.println("Erro: Número decimal inválido!");
                    return new ArrayList<>();
                }
                decimalUsed = true; //aceita numeros com apenas um ponto flutuante
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    tokens.add(sb.toString());
                    sb.setLength(0);
                    decimalUsed = false;
                }
                if (!Character.isWhitespace(c)) {
                    tokens.add(String.valueOf(c)); // Adiciona operadores e parênteses
                }
            }
        }

        if (sb.length() > 0) tokens.add(sb.toString());
        return tokens;
    }


    public boolean validarExpressao(String expressao) {
        /* Verifica se a expressão está vazia */
        if (expressao.isEmpty()) {
            System.out.println("Erro: Expressão vazia!");
            return false;
        }

        /*Verifica se os parênteses estão balanceados*/
        int contadorParenteses = 0;
        for (char c : expressao.toCharArray()) {
            if (c == '(') {
                contadorParenteses++;
            } else if (c == ')') {
                contadorParenteses--;
            }
            if (contadorParenteses < 0) {
                System.out.println("Erro: Parênteses desbalanceados!");
                return false;
            }
        }
        if (contadorParenteses != 0) {
            System.out.println("Erro: Parênteses desbalanceados!");
            return false;
        }

        /*Verifica se há operadores consecutivos ou finalizando a expressão*/
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if ("+-*/".indexOf(c) != -1) { // É um operador
                if (i == 0 || i == expressao.length() - 1) {
                    System.out.println("Erro: Operador fora de posição!");
                    return false;
                }
                char proximo = expressao.charAt(i + 1);
                if ("+-*/".indexOf(proximo) != -1) {
                    System.out.println("Erro: Dois operadores consecutivos!");
                    return false;
                }
            }
        }

        return true;
    }

    /*Utiliza as outras funcoes para de fato converter a expressao */
    public List<String> infixToPostfix(String expressao) {
        List<String> saida = new ArrayList<>();
        Stack<String> pilha = new Stack<>();
        List<String> tokens = tokenize(expressao);

        for (String token : tokens) {
            if (isNumero(token)) { // Número válido
                saida.add(token);
            } else if (operadorValido(token)) { // Operador válido
                while (!pilha.isEmpty() && operadorValido(pilha.peek()) &&
                        precedencia.get(pilha.peek()) >= precedencia.get(token)) {
                    saida.add(pilha.pop());
                }
                pilha.push(token);
            } else if (token.equals("(")) { // Abertura de parêntese
                pilha.push(token);
            } else if (token.equals(")")) { // Fechamento de parêntese
                while (!pilha.isEmpty() && !pilha.peek().equals("(")) {
                    saida.add(pilha.pop());
                }
                if (pilha.isEmpty()) {
                    System.out.println("Erro: Parênteses desbalanceados!");
                    return new ArrayList<>(); // Retorna lista vazia para expressão inválida
                }
                pilha.pop(); // Remove '(' da pilha
            } else {
                System.out.println("Erro: Token inválido encontrado: " + token);
                return new ArrayList<>(); // Retorna lista vazia para expressão inválida
            }
        }

        // Verifica parênteses não fechados
        while (!pilha.isEmpty()) {
            String elemento = pilha.pop();
            if (elemento.equals("(")) {
                System.out.println("Erro: Parênteses desbalanceados!");
                return new ArrayList<>();
            }
            saida.add(elemento);
        }

        return saida;
    }

    public boolean isNumero(String token) {
        return token.matches("\\d+(\\.\\d+)?"); // Permite números inteiros ou decimais
    }
}
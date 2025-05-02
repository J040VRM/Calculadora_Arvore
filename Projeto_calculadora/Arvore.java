import java.util.*;

public class Arvore {
    public No raiz;

    // Constrói a árvore binária a partir da expressão pós-fixa
    public No construirArvore(List<String> posfixa) {
        Stack<No> pilha = new Stack<>();

        for (String token : posfixa) {
            if (!isOperador(token)) {  // Se for um operando, cria um nó e empilha
                pilha.push(new NoOperando(token));
            } else {  // Se for um operador, cria um nó e define filhos
                No novoNo = new NoOperador(token);
                novoNo.direita = pilha.pop();  // O último elemento é o filho direito
                novoNo.esquerda = pilha.pop(); // O penúltimo é o filho esquerdo
                pilha.push(novoNo); // Empilha o nó operador
            }
        }

        raiz = pilha.pop();  // O último elemento na pilha é a raiz da árvore
        return raiz;
    }

    // Verifica se o token é um operador
    private boolean isOperador(String token) {
        return "+-*/".contains(token);
    }

    // Resolve a expressão percorrendo a árvore
    public double resolverExpressao() {
        if (raiz == null) {
            throw new IllegalStateException("Árvore vazia!");
        }
        return raiz.visitar(); // Chama o método visitar() da raiz
    }

    // Exibe a árvore de forma hierárquica
    public void exibirArvore() {
        if (raiz == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        imprimirArvore(raiz, 0);
    }

    // Método auxiliar para exibir a árvore
    private void imprimirArvore(No no, int nivel) {
        if (no != null) {
            imprimirArvore(no.direita, nivel + 1);

            for (int i = 0; i < nivel; i++) {
                System.out.print("   |");
            }
            System.out.println("-- " + no.valor);

            imprimirArvore(no.esquerda, nivel + 1);
        }
    }

    // Percorre a árvore em pós-ordem
    public void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.print(no.valor + " ");
        }
    }

    // Percorre a árvore em pré-ordem
    public void preOrdem(No no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    // Percorre a árvore em ordem
    public void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            emOrdem(no.direita);
        }
    }
}
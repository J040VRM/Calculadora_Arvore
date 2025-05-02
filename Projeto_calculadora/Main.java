//Nome João Vitor Rocha Miranda RA 10427273
// Nome Matheus Veiga Bacetic Joaquim RA 10425638

//links usados:
//https://favtutor-com.translate.goog/blogs/infix-to-postfix-conversion?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=wa
//https://www.ime.usp.br/~pf/mac0122-2002/aulas/trees.html
//https://youtu.be/T4okc8dDlek

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conv conversor = new Conv();
        Arvore arvore = new Arvore();
        List<String> ultimaExpressao = new ArrayList<>();


        boolean rodando = true;
        while (rodando) {
            System.out.println("\nMENU");
            System.out.println("1 - Inserir expressão matemática");
            System.out.println("2 - Criação da árvore binária de expressão aritmética");
            System.out.println("3 - Mostrar árvore");
            System.out.println("4 - Calcular expressões");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            double resultado = 0;

            switch (opcao) {
                case 1:
                    System.out.print("Digite a expressão matemática: ");
                    String expressao = scanner.nextLine();

                    if (!conversor.validarExpressao(expressao)) {
                        System.out.println("Erro: Expressão inválida!");
                        break;
                    }

                    List<String> posfixa = conversor.infixToPostfix(expressao);

                    if (posfixa.isEmpty()) {
                        System.out.println("Erro: Expressão inválida!");
                        break;
                    }

                    boolean expressaoValida = true;
                    for (String token : posfixa) {
                        if (!conversor.isNumero(token) && !conversor.operadorValido(token) &&
                                !token.equals("(") && !token.equals(")")) {
                            System.out.println("Erro: Token inválido encontrado: " + token);
                            expressaoValida = false;
                            break;
                        }
                    }

                    if (expressaoValida) {
                        try {
                            // Tenta construir e resolver a árvore para detectar divisões por zero ou outros erros
                            Arvore testeArvore = new Arvore();
                            testeArvore.construirArvore(posfixa);
                            double testeResultado = testeArvore.resolverExpressao();

                            // Se passou, salva como última expressão válida
                            System.out.println("\nExpressão inserida na árvore");
                            ultimaExpressao = posfixa;
                        } catch (ArithmeticException e) {
                            System.out.println("Erro durante avaliação da expressão: " + e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro ao construir a árvore: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Erro inesperado: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    if (ultimaExpressao == null || ultimaExpressao.isEmpty()) {
                        System.out.println("Erro: Nenhuma expressão válida foi inserida ainda!");
                        break;
                    }

                    // Constrói a árvore binária a partir da expressão pós-fixa
                    arvore.construirArvore(ultimaExpressao);
                    System.out.println("Árvore criada com sucesso!");
                    break;

                case 3:
                    if (arvore.raiz == null) {
                        System.out.println("Nenhuma árvore foi criada ainda!");
                        break;
                    }

                    // Exibe a árvore gerada
                    System.out.println("\nÁrvore gerada:");
                    arvore.exibirArvore();

                    // Exibe os percursos da árvore
                    System.out.println("\nPercurso Pré-ordem:");
                    arvore.preOrdem(arvore.raiz);
                    System.out.println();

                    System.out.println("Percurso Em ordem:");
                    arvore.emOrdem(arvore.raiz);
                    System.out.println();

                    System.out.println("Percurso Pós-ordem:");
                    arvore.posOrdem(arvore.raiz);
                    System.out.println();

                    break;

                case 4:
                    if (arvore.raiz == null) {
                        System.out.println("Nenhuma árvore foi criada ainda!");
                        break;
                    }

                    // Calcula o resultado da expressão
                    resultado = arvore.resolverExpressao(); // Apenas atribui valor

                    // Exibe o resultado
                    System.out.println("Resultado do cálculo da árvore: " + resultado);
                    break;

                case 5:
                    rodando = false;
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        }
        scanner.close();
    }
}

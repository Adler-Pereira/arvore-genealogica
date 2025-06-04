// Participantes:
// Adler Pereira de Lima;
// Isabelly Morandin Batista;
// Giovanni Bertho Paschoal.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore<>();
        Scanner scanner = new Scanner(System.in);
        String opcao;
        boolean exe = true;

        while (exe) {
            System.out.println(".______________________________________________________.");
            System.out.println("|                                                      |");
            System.out.println("|                  ÁRVORE GENEALÓGICA                  |");
            System.out.println("|                                                      |");
            System.out.println("| Digite o algarismo correspondente ao seu objetivo:   |");
            System.out.println("|                                                      |");
            System.out.println("| 1 - Consultar CPF;                                   |");
            System.out.println("| 2 - Inserir uma pessoa raíz;                         |");
            System.out.println("| 3 - Inserir um conjuge;                              |");
            System.out.println("| 4 - Inserir um filho.                                |");
            System.out.println("| 5 - Sair.                                            |");
            System.out.println("|______________________________________________________|");
            System.out.println();

            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("Insira um CPF para consulta:");
                    String cpf = scanner.nextLine();

                    Pessoa pessoa = arvore.buscarPessoa(cpf);

                    if (pessoa == null) System.out.println("Erro. Não há pessoas com esse CPF.");
                    else {
                        Pessoa pessoaAsc1 = arvore.buscarAsc1(cpf);
                        Pessoa pessoaAsc2 = arvore.buscarAsc2(cpf);
                        Pessoa conjuge = arvore.buscarConjuge(cpf);

                        String nomeAsc1;
                        String nomeAsc2;
                        String nomeConjuge;

                        if (conjuge != null) nomeConjuge = conjuge.getNome();
                        else nomeConjuge = "Indefinido";

                        if (pessoaAsc1 != null) nomeAsc1 = pessoaAsc1.getNome();
                        else nomeAsc1 = "Indefinido";

                        if (pessoaAsc2 != null) nomeAsc2 = pessoaAsc2.getNome();
                        else nomeAsc2 = "Indefinido";

                        System.out.println("Pessoa encontrada:");
                        System.out.println("Nome: " + pessoa.getNome());
                        System.out.println("Idade: " + pessoa.getIdade() + " ano(s)");
                        System.out.println("Cônjuge: " + nomeConjuge);
                        System.out.println("Ascendente 1: " + nomeAsc1);
                        System.out.println("Ascendente 2: " + nomeAsc2);
                    }

                    break;
                case "2":
                    if (arvore.getRaiz() == null) {
                        System.out.println("Insira o CPF da pessoa:");
                        cpf = scanner.nextLine();

                        System.out.println("Insira o nome da pessoa:");
                        String nome = scanner.nextLine();

                        System.out.println("Insira a idade da pessoa:");
                        int idade = lerIdade();

                        arvore.insereRaiz(new Pessoa(cpf, nome, idade));
                    } else System.out.println("Já existe uma pessoa raíz registrada.");
                    break;
                case "3":
                    System.out.println("Insira o CPF da pessoa, a qual receberá um cônjuge:");
                    cpf = scanner.nextLine();

                    Node<Pessoa> pessoaNode = arvore.buscarNo(cpf);

                    if (pessoaNode != null) {
                        Node<Pessoa> conjugeNode = pessoaNode.getConjuge();

                        if (conjugeNode == null) {
                            System.out.println("Insira o CPF do cônjuge:");
                            String cpfConj = scanner.nextLine();

                            while (true) {
                                Node<Pessoa> checarNo = arvore.buscarNo(cpfConj);

                                if (checarNo != null) {
                                    System.out.println("Já existe esse CPF na árvore. Por favor, insira outro:");
                                    cpfConj = scanner.nextLine();
                                } else break;
                            }

                            System.out.println("Insira o nome do cônjuge:");
                            String nome = scanner.nextLine();

                            System.out.println("Insira a idade do cônjuge:");
                            int idade = lerIdade();

                            arvore.insereConjuge(new Pessoa(cpfConj, nome, idade), cpf);
                        } else System.out.println("Já existe um cônjuge associado a pessoa do CPF informado");
                    } else System.out.println("Não há uma pessoa com o CPF " + cpf + " na árvore.");
                    break;
                case "4":
                    System.out.println("Insira o CPF da pessoa, a qual receberá um filho:");
                    cpf = scanner.nextLine();

                    pessoaNode = arvore.buscarNo(cpf);

                    if (pessoaNode != null) {
                        if (pessoaNode.getConjuge() != null) {
                            System.out.println("Insira o CPF do filho:");
                            String cpfFilho = scanner.nextLine();

                            while (true) {
                                Node<Pessoa> checarNo = arvore.buscarNo(cpfFilho);

                                if (checarNo != null) {
                                    System.out.println("Já existe esse CPF na árvore. Por favor, insira outro:");
                                    cpfFilho = scanner.nextLine();
                                } else break;
                            }

                            System.out.println("Insira o nome do filho:");
                            String nome = scanner.nextLine();

                            System.out.println("Insira a idade do filho:");
                            int idade = lerIdade();

                            arvore.insereFilho(new Pessoa(cpfFilho, nome, idade), cpf);
                        } else System.out.println("Associe um cônjuge a pessoa para inserir um filho.");
                    } else System.out.println("Não há uma pessoa com o CPF " + cpf + " na árvore.");
                    break;
                case "5":
                    System.out.println(".______________________________________________________.");
                    System.out.println("|                                                      |");
                    System.out.println("| Tem certeza que deseja finalizar o programa? Todos   |");
                    System.out.println("|       os dados de sua árvore serão perdidos.         |");
                    System.out.println("|                                                      |");
                    System.out.println("|             1 - SIM             2 - NÃO              |");
                    System.out.println("|______________________________________________________|");

                    opcao = scanner.nextLine();

                    if (opcao.equals("1")) exe = false;
                    break;
                default:
                    System.out.println("Erro: opção inválida.");
            }
        }
    }

    private static Integer lerIdade(){
        Scanner scanner = new Scanner(System.in);
        int idade;
        while (true) {
            String input = scanner.nextLine();
            try {
                idade = Integer.parseInt(input);
                if (idade >= 0) return idade;
                else System.out.println("Erro ao atribuir idade. Insira um número natural:");
            } catch (Exception e) {
                System.out.println("Erro ao atribuir idade. Insira um número natural:");
            }
        }
    }
}
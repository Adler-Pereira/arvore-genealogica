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
                        System.out.println("Pessoa encontrada:");
                        System.out.println("Nome: " + pessoa.getNome());
                        System.out.println("Idade: " + pessoa.getIdade() + " anos");
                    }

                    break;
                case "2":
                    if (arvore.getRaiz() == null) {
                        System.out.println("Insira o CPF da pessoa:");
                        cpf = scanner.nextLine();

                        System.out.println("Insira o nome da pessoa:");
                        String nome = scanner.nextLine();

                        System.out.println("Insira a idade da pessoa:");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // Limpa o buffer e evita erro no scanner.

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

                            System.out.println("Insira o nome do cônjuge:");
                            String nome = scanner.nextLine();

                            System.out.println("Insira a idade do cônjuge:");
                            int idade = scanner.nextInt();
                            scanner.nextLine(); // Limpa o buffer e evita erro no scanner.

                            arvore.insereConjuge(new Pessoa(cpfConj, nome, idade), cpf);
                        } else System.out.println("Já existe um cônjuge associado a pessoa do CPF informado");
                    } else System.out.println("Não há uma pessoa com o CPF " + cpf + " na árvore.");
                    break;
                case "4":
                    System.out.println("Insira o CPF da pessoa, a qual receberá um filho:");
                    cpf = scanner.nextLine();

                    pessoaNode = arvore.buscarNo(cpf);

                    if (pessoaNode != null) {
                        System.out.println("Insira o CPF do filho:");
                        String cpfFilho = scanner.nextLine();

                        System.out.println("Insira o nome do filho:");
                        String nome = scanner.nextLine();

                        System.out.println("Insira a idade do filho:");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // Limpa o buffer e evita erro no scanner.

                        arvore.insereFilho(new Pessoa(cpfFilho, nome, idade), cpf);
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
}
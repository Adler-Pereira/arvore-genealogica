public class Arvore<T extends Comparable<T>> {
    private Node<T> raiz;
    private int elementos;

    public Arvore() {
        raiz = null;
        elementos = 0;
    }

    public boolean isVazio() {
        return raiz == null;
    }

    // retorna o numero de pessoas
    public int size() {
        return elementos;
    }

    public Node<T> getRaiz() {
        return raiz;
    }

    public Node<T> buscarNo(String cpf) {
        Node<T> pessoaRaiz = raiz;
        Node<T> nodeAux;
        Lista<T> filhos = new Lista<>();

        while (true) {
            // checa se o nó da Pessoa Raiz existe. Se sim, captura o objeto Pessoa de dentro dele.
            if (pessoaRaiz != null) {
                Pessoa pessoa = (Pessoa) pessoaRaiz.getInfo();

                // Verifica se já ouve uma passagem pelo nó, verificando se os filhos foram verificados.
                filhos = pessoaRaiz.getFilhos();
                if (filhos.getIndice() == 0) {

                    // compara o CPF da Pessoa Raíz com o CPF informado pelo parâmetro.
                    if (pessoa.getCpf().equals(cpf)) {
                        filhos.setIndice(0);
                        return pessoaRaiz;
                    }

                    // Se a Pessoa Raíz não for igual ao cpf informado no parâmetro:
                    // Executa o mesmo processo, porém verificando se o CPF do cônjuge é igual ao CPF do parâmetro.
                    else if (pessoaRaiz.getConjuge() != null) {
                        nodeAux = pessoaRaiz.getConjuge();
                        pessoa = (Pessoa) nodeAux.getInfo();
                        if (pessoa.getCpf().equals(cpf)) {
                            filhos.setIndice(0);
                            return nodeAux;
                        }
                    }
                }
                // Se a Pessoa Raíz tiver filhos e o indice (usado para apontar um filho) for menor que o tamanho:
                // Executa o mesmo processo de verificação de CPF com um filho.
                if (!filhos.estaVazia() && filhos.getIndice() < filhos.tamanho()) {
                    for (; filhos.getIndice() < filhos.tamanho(); filhos.setIndice(filhos.getIndice() + 1)) {
                        pessoa = (Pessoa) filhos.vasculha(filhos.getIndice());
                        if (pessoa.getCpf().equals(cpf)) {
                            nodeAux = filhos.vasculhaNo(filhos.getIndice());
                            filhos.setIndice(0);
                            return nodeAux;
                        } else {
                            // Caso a raíz, o conjuge e o filho apontado pelo indice não possuam o CPF informado, o filho vira a
                            // a nova raíz e tudo volta a se repetir (checa raiz, depois conjuge e por fim um filho.
                            pessoaRaiz = filhos.vasculhaNo(filhos.getIndice());
                            break;
                        }
                    }
                } else {
                    // Se a raíz são possuir filhos ou todos eles já foram checados, o ascendente (pai ou mãe) vira
                    // a nova raíz.
                    filhos.setIndice(0); // Reseta o indice, possibilitando a função ser chamada inumeras vezes sem erro de execução na parte dos filhos.
                    pessoaRaiz = pessoaRaiz.getAscendente1();
                    if (pessoaRaiz == null)
                        return null; // Caso o ascendente seja nulo, chegamos a raiz, ou seja, ao vasculhar toda a arvore, não há nenhuma pessoa com o CPF informado.
                    filhos = pessoaRaiz.getFilhos();
                    filhos.setIndice(filhos.getIndice() + 1);
                }
            } else {
                filhos.setIndice(0);
                return null;
            }
        }
    }

    // Mesma coisa que o buscarNo, porém retorna Pessoa.
    public Pessoa buscarPessoa(String cpf) {
        Node<T> pessoaRaiz = raiz;
        Node<T> nodeAux;
        Lista<T> filhos = new Lista<>();

        while (true) {
            if (pessoaRaiz != null) {
                Pessoa pessoa = (Pessoa) pessoaRaiz.getInfo();
                filhos = pessoaRaiz.getFilhos();
                if (filhos.getIndice() == 0) {
                    if (pessoa.getCpf().equals(cpf)) {
                        filhos.setIndice(0);
                        return pessoa;
                    } else if (pessoaRaiz.getConjuge() != null) {
                        nodeAux = pessoaRaiz.getConjuge();
                        pessoa = (Pessoa) nodeAux.getInfo();
                        if (pessoa.getCpf().equals(cpf)) {
                            filhos.setIndice(0);
                            return pessoa;
                        }
                    }
                }
                if (!filhos.estaVazia() && filhos.getIndice() < filhos.tamanho()) {
                    for (; filhos.getIndice() < filhos.tamanho(); filhos.setIndice(filhos.getIndice() + 1)) {
                        pessoa = (Pessoa) filhos.vasculha(filhos.getIndice());
                        if (pessoa.getCpf().equals(cpf)) {
                            filhos.setIndice(0);
                            return pessoa;
                        } else {
                            pessoaRaiz = filhos.vasculhaNo(filhos.getIndice());
                            break;
                        }
                    }
                } else {
                    filhos.setIndice(0);
                    pessoaRaiz = pessoaRaiz.getAscendente1();
                    if (pessoaRaiz == null) return null;
                    filhos = pessoaRaiz.getFilhos();
                    filhos.setIndice(filhos.getIndice() + 1);

                }
            } else {
                filhos.setIndice(0);
                return null;
            }
        }
    }

    public void insereRaiz(T info) {
        if (isVazio()) {
            raiz = new Node<>(info);
            elementos++;
            Pessoa pessoa = (Pessoa) info;
            System.out.println(pessoa.getNome() + " inserido(a) com sucesso.");
        } else System.out.println("Já existe uma pessoa raíz registrada.");
    }

    public void insereConjuge(T conjuge, String cpf) {
        Node<T> nodePessoa = buscarNo(cpf);
        insereConjuge(conjuge, nodePessoa);
        elementos++;
    }

    private void insereConjuge(T conjuge, Node<T> no) {
        Node<T> conjugeNo;
        Lista<T> filhos;
        if (no.getConjuge() == null) {
            no.setConjuge(new Node<>(conjuge));
            conjugeNo = no.getConjuge();
            filhos = no.getFilhos();
            conjugeNo.setFilhos(filhos);

            Pessoa pessoa = (Pessoa) conjugeNo.getInfo();
            System.out.println(pessoa.getNome() + " inserido(a) com sucesso.");
        } else System.out.println("Erro. Essa pessoa já possui um conjuge.");
    }

    public void insereFilho(T filho, String cpf) {
        Node<T> nodePessoa = buscarNo(cpf);
        insereFilho(filho, nodePessoa);
        elementos++;
    }

    private void insereFilho(T info, Node<T> no) {
        Lista<T> filhos = no.getFilhos();
        Node<T> conjuge = no.getConjuge();
        Node<T> filhoNo;

        if (filhos == null) filhos = new Lista<>();

        filhos.inserir(info);

        filhoNo = filhos.vasculhaNo(filhos.tamanho() - 1);
        filhoNo.setAscendente1(no);

        no.setFilhos(filhos);

        if (conjuge != null) {
            conjuge.setFilhos(filhos);
            filhoNo.setAscendente2(conjuge);
        }

        Pessoa pessoa = (Pessoa) info;
        System.out.println(pessoa.getNome() + " inserido(a) com sucesso.");
    }
}
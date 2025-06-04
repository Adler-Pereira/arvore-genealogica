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

    public Node<T> getRaiz() {
        return raiz;
    }

    public Node<T> buscarNo(String cpf) {
        Node<T> pessoaRaiz = raiz;
        Node<T> nodeAux;
        Node<T> nodeAsc;
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

                            // zera o indice da lista de filhos dos pais ascendentes
                            nodeAsc = pessoaRaiz;
                            do {
                                nodeAsc = nodeAsc.getAscendente1();
                                if (nodeAsc != null){
                                    filhos = nodeAsc.getFilhos();
                                    filhos.setIndice(0);
                                    nodeAsc.setFilhos(filhos);
                                }
                            } while (nodeAsc != null);

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
                    if (pessoaRaiz == null) return null; // Caso o ascendente seja nulo, chegamos a raiz, ou seja, ao vasculhar toda a arvore, não há nenhuma pessoa com o CPF informado.
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
        Node<T> pessoaNo = buscarNo(cpf);

        if (pessoaNo == null) return null;

        return (Pessoa) pessoaNo.getInfo();
    }

    //  Mesma coisa que o buscarNo, porém retorna cônjuge.
    public Pessoa buscarConjuge(String cpf) {
        Node<T> noPessoa = buscarNo(cpf);
        Node<T> noConjuge = noPessoa.getConjuge();
        if (noConjuge != null){
            Pessoa conjuge = (Pessoa) noConjuge.getInfo();
            return conjuge;
        } else return null;
    }

    //  Mesma coisa que o buscarNo, porém retorna o ascendente1.
    public Pessoa buscarAsc1(String cpf) {
        Node<T> noFilho = buscarNo(cpf);
        Node<T> noAsc1 = noFilho.getAscendente1();
        if (noAsc1 != null){
            Pessoa pessoaAsc1 = (Pessoa) noAsc1.getInfo();
            return pessoaAsc1;
        } else return null;
    }

    //  Mesma coisa que o buscarNo, porém retorna o ascendente1.
    public Pessoa buscarAsc2(String cpf) {
        Node<T> noFilho = buscarNo(cpf);
        Node<T> noAsc2 = noFilho.getAscendente2();
        if (noAsc2 != null){
            Pessoa pessoaAsc2 = (Pessoa) noAsc2.getInfo();
            return pessoaAsc2;
        } else return null;
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
            conjugeNo = new Node<>(conjuge);

            filhos = no.getFilhos();
            conjugeNo.setFilhos(filhos);

            conjugeNo.setConjuge(no);
            no.setConjuge(conjugeNo);

            Pessoa pessoa = (Pessoa) conjugeNo.getInfo();
            System.out.println(pessoa.getNome() + " inserido(a) com sucesso.");
        } else System.out.println("Erro. Essa pessoa já possui um cônjuge.");
    }

    public void insereFilho(T filho, String cpf) {
        Node<T> nodePessoa = buscarNo(cpf);

        insereFilho(filho, nodePessoa);
        elementos++;
    }

    private void insereFilho(T info, Node<T> no) {
        Lista<T> filhos = no.getFilhos();
        Node<T> conjuge = no.getConjuge();
        Node<T> filhoNo = new Node<>(info);
        if (filhos == null) filhos = new Lista<>();

        filhoNo.setAscendente1(no);

        if (conjuge != null) {
            filhoNo.setAscendente2(conjuge);
            filhos.inserir(filhoNo);
            conjuge.setFilhos(filhos);
            no.setConjuge(conjuge);
            no.setFilhos(filhos);
        } else {
            filhos.inserir(filhoNo);
            no.setFilhos(filhos);
        }

        Pessoa pessoa = (Pessoa) info;
        System.out.println(pessoa.getNome() + " inserido(a) com sucesso.");
    }
}
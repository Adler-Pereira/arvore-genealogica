public class Lista<S> {
    private Node<S> primeiro;
    private Node<S> ultimo;
    private int tamanho;
    private int indice;

    public Lista() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    public Node<S> getPrimeiro() {
        return primeiro;
    }

    public Node<S> getUltimo() {
        return ultimo;
    }

    public int tamanho() {
        return tamanho;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void inserir(Node<S> novo) {
        if (estaVazia()) {
            primeiro = novo;
        }
        else {
            ultimo.setProximo(novo);
        }
        ultimo = novo;
        tamanho++;
    }

    public S vasculha(int indice) {
        Node<S> procurado = vasculhaNo(indice);

        if (procurado == null) return null;

        return procurado.getInfo();
    }

    public Node<S> vasculhaNo(int indice) {
        Node<S> procurado;
        if (indice == 0) {
            procurado = primeiro;
            return procurado;
        } else {
            Node<S> anterior = primeiro;
            for (int i = 1; i <= indice; i++) {
                anterior = anterior.getProximo();
            }
            return anterior;
        }
    }
}

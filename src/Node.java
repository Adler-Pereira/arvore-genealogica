public class Node<T>{
    private T info;
    private Node<T> ascendente1;
    private Node<T> ascendente2;
    private Node<T> conjuge;
    private Node<T> proximo;
    private Lista<T> filhos = new Lista<>();

    public Node(){
        ascendente1 = null; // pai ou mãe
        ascendente2 = null; // pai ou mãe
        conjuge = null;
        proximo = null; // usado pela lista para procurar o próximo nó
    }

    public Node(T info){
        this();
        this.info = info;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Node<T> getAscendente1() {
        return ascendente1;
    }

    public void setAscendente1(Node<T> ascendente1) {
        this.ascendente1 = ascendente1;
    }

    public Node<T> getAscendente2() {
        return ascendente2;
    }

    public void setAscendente2(Node<T> ascendente2) {
        this.ascendente2 = ascendente2;
    }

    public Node<T> getConjuge() {
        return conjuge;
    }

    public void setConjuge(Node<T> conjuge) {
        this.conjuge = conjuge;
    }

    public Node<T> getProximo() {
        return proximo;
    }

    public void setProximo(Node<T> proximo) {
        this.proximo = proximo;
    }

    public Lista<T> getFilhos() {
        return filhos;
    }

    public void setFilhos(Lista<T> filhos) {
        this.filhos = filhos;
    }
}

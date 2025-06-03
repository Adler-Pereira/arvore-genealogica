public class Pessoa implements Comparable<Pessoa>{
    private String cpf;
    private String nome;
    private int idade;

    public Pessoa(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    // permite criar um "new Pessoa" diretamente nas funções de inserir
    @Override
    public int compareTo(Pessoa o) {
        return 0;
    }
    
}

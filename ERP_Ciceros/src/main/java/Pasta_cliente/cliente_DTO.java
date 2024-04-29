package Pasta_cliente;


public class cliente_DTO {
    private String nome;
    private String endereco;
    private String telefone;
    private int chave_Primaria;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the chave_Primaria
     */
    public int getChave_Primaria() {
        return chave_Primaria;
    }

    /**
     * @param chave_Primaria the chave_Primaria to set
     */
    public void setChave_Primaria(int chave_Primaria) {
        this.chave_Primaria = chave_Primaria;
    }
}

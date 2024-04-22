package Pasta_Login;

public class usuario_DTO {
    private int chave_primario;
    private String usuario, senha, pergunta, resposta;
    
    public void getUsusario(String usuario){
        this.usuario = usuario;
    }
    
    public void getSenha(String senha){
        this.senha = senha;
    }
    
    public void getChave_primaria(int chave_primario){
        this.chave_primario = chave_primario;
    }
    
    public void getPergunta(String pergunta){
        this.pergunta = pergunta;
    }
    
    public void getResposta(String resposta){
        this.resposta = resposta;
    }
    
     public String setUsusario(){
        return usuario;
    }
    
    public String setSenha(){
        return senha;
    
    }
    
    public int setChave_primaria(){
        return chave_primario;
    }
    
    public String setPergunta(){
        return pergunta;
    }
    
    public String setResposta(){
        return resposta;
    }
}



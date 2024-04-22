package Pasta_Login;

import Pasta_conexao_Banco.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class usuario_DAO {
    
    
    
    
    public ResultSet autenticacaoUsuario(usuario_DTO usuariodto){
        
        
        try {
            String sql = "SELECT * FROM tb_login WHERE usuario =? and senha = ?";
             Connection conn = new ConexaoDAO().conectaBD();
            PreparedStatement pstm  = conn.prepareStatement(sql);
            
            pstm.setString(1, usuariodto.setUsusario());
            pstm.setString(2, usuariodto.setSenha());
            
            ResultSet rs =pstm.executeQuery();
            return rs;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"autenticacaoUsuario deu B.O: " +  erro);
            return null;
        }
    }
    
    public void CadastrarUsuario(usuario_DTO objusuariodto){
        
        try {
            String sql = "INSERT INTO tb_login (usuario, senha, pergunta, resposta) VALUES (?,?,?,?)";
            
             Connection conn = new ConexaoDAO().conectaBD();
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, objusuariodto.setUsusario());
            pstm.setString(2, objusuariodto.setSenha());
            pstm.setString(3, objusuariodto.setPergunta());
            pstm.setString(4, objusuariodto.setResposta());
            
            pstm.execute();
            pstm.close();
            
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro na usuario_DAO ao salvar os dados: " +  erro);
        }
    }
    
    
    public String consultarUsuario(String usuario){
        
        //Testar se esse código funciona caso não arrumar
        
        String sql = "select pergunta from tb_login where usuario =?";
        Connection conn = new ConexaoDAO().conectaBD();
        
        try {
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,usuario);
            ResultSet rs =pstm.executeQuery();
            String resultado_do_Banco = "";
            
            while(rs.next()){
                String pergunta="";
                pergunta = rs.getString(pergunta);
                
                resultado_do_Banco = pergunta;
            }
            
            return resultado_do_Banco;
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Erro na usuario_DAO ao salvar os dados: " +  erro);
            return null;
        }
        

        
    }
}

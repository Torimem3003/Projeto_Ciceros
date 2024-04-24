package Pasta_Login;

import Pasta_conexao_Banco.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class usuario_DAO {
    Connection conn = new ConexaoDAO().conectaBD();
    PreparedStatement pstm;
    
    
    public ResultSet autenticacaoUsuario(usuario_DTO usuariodto){
        
        
        try {
            String sql = "SELECT * FROM tb_login WHERE usuario =? and senha = ?";
             
             pstm  = conn.prepareStatement(sql);
            
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
            
             
            pstm = conn.prepareStatement(sql);
            
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
    
    
    public usuario_DTO consultarUsuario(String usuario){
        
        //Testar se esse código funciona caso não arrumar
        usuario_DTO objdto = new usuario_DTO();
        
        String sql = "select * from tb_login where usuario = ?";
        
        try {
         pstm = conn.prepareStatement(sql);
         pstm.setString(1, usuario);
         
         ResultSet rs = pstm.executeQuery();
         if(rs.next()){
         objdto.getPergunta(rs.getString("pergunta"));  
         objdto.getResposta(rs.getString("resposta"));
         objdto.getChave_primaria(rs.getInt("id_log_pk"));
         }
         return objdto;
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Erro na usuario_DAO ao consultar o usuario: " +  erro);
            return null;
        }
        

        
    }
    
    
    public void alterarSenha(usuario_DTO objDTO){
        try {
         String sql = "UPDATE tb_login SET senha=? WHERE id_log_pk =?";
         
         pstm = conn.prepareStatement(sql);
         pstm.setString(1, objDTO.setSenha());
         pstm.setInt(2, objDTO.setChave_primaria());
         pstm.executeUpdate();
         
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro na usuario_DAO ao alterar a senha: " +  erro);
        }
    }
    
}

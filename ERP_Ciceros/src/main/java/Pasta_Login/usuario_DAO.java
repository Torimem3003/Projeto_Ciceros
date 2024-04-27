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
    ResultSet rs;
    
    
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
         objdto.getUsusario(rs.getString("usuario"));
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
    
    
    public ArrayList<usuario_DTO> ConsultaGeral(){
        String sql ="SELECT * FROM tb_login";
        conn = new ConexaoDAO().conectaBD();
        ArrayList<usuario_DTO> listaDTO = new ArrayList<usuario_DTO>();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                usuario_DTO objDTO = new usuario_DTO();
                objDTO.getChave_primaria(rs.getInt("id_log_pk"));
                objDTO.getUsusario(rs.getString("usuario"));
                objDTO.getSenha(rs.getString("senha"));
                objDTO.getPergunta(rs.getString("pergunta"));
                objDTO.getResposta(rs.getString("resposta"));
                listaDTO.add(objDTO);
                
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro na usuario_DAO ao consulta geral: " +  erro);
            
        }
        return listaDTO;
    }
    
    public void alterar_login_Completo(usuario_DTO objDTO){
        String sql ="UPDATE tb_login SET usuario =?, senha=?, pergunta=?,resposta=? WHERE id_log_pk = ?";
        
        try {
             pstm = conn.prepareStatement(sql);
             pstm.setString(1,objDTO.setUsusario());
             pstm.setString(2,objDTO.setSenha());
             pstm.setString(3,objDTO.setPergunta());
             pstm.setString(4,objDTO.setResposta());
             pstm.setInt(5,objDTO.setChave_primaria());
             
             pstm.executeUpdate();  
             
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar os dados do Login "+ erro);
        }
        
    }
    
    
       
}

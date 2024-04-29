package Pasta_cliente;

import Pasta_conexao_Banco.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class cliente_DAO {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conn = new ConexaoDAO().conectaBD();
    
    
    public void adicionar(cliente_DTO objDTO) {
        String sql = "insert into tb_cliente(nome,telefone,endereco)values(?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, objDTO.getNome());
            pst.setString(2, objDTO.getTelefone());
            pst.setString(3,objDTO.getEndereco());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso.");
        
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar o cliente"+ erro);
            
        }

    }
    
    public ArrayList<cliente_DTO> consultar (String filtro){
        
        String sql ="SELECT * FROM tb_cliente where telefone like ? ";
        conn = new ConexaoDAO().conectaBD();
        ArrayList<cliente_DTO> listaDTO = new ArrayList<cliente_DTO>();
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+filtro+"%");
            rs = pst.executeQuery();
            
            while(rs.next()){
                cliente_DTO objDTO = new cliente_DTO();
                objDTO.setChave_Primaria(rs.getInt("id_cli_pk"));
                objDTO.setNome(rs.getString("nome"));
                objDTO.setEndereco(rs.getString("endereco"));
                objDTO.setTelefone(rs.getString("telefone"));
                listaDTO.add(objDTO);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro na consulta de Cliente: " +  erro);
        }
        return listaDTO;
    }
    
    public void Alterar(cliente_DTO objDTO){
        String sql ="UPDATE tb_cliente SET nome =?, endereco=?, telefone=? WHERE id_cli_pk = ?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,objDTO.getNome());
            pst.setString(2,objDTO.getEndereco());
            pst.setString(3,objDTO.getTelefone());
            pst.setInt(4,objDTO.getChave_Primaria());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Alteração feita com sucesso ");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar os dados do cliente " + erro);
        }
    
    }
    
    
    public void Excluir(int ID){
        String sql = "DELETE FROM tb_cliente WHERE id_cli_pk = ?";
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, ID);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Exclusão realizada. ");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir um usuario: " +  erro);
        }
    
    }
}

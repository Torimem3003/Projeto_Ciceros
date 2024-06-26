
package Pasta_estoque;

import Pasta_conexao_Banco.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class Estoque_DAO {
  Connection conn = new ConexaoDAO().conectaBD();
  ResultSet Rs;  
  PreparedStatement Pstm;
    
    public void cadastraProduto(estoque_DTO objDTO){
        try {
            String sql = "INSERT INTO tb_cardapio (nome, descricao, preco ) VALUES (?, ?, ?)";
            
            Pstm = conn.prepareStatement(sql);
            
            Pstm.setString(1,objDTO.getNome() );
            Pstm.setString(2, objDTO.getDescricao());
            Pstm.setDouble(3, objDTO.getPreco());
            
            Pstm.execute();
            Pstm.close();
        } catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null,"Erro na estoqueDAO ao cadastrar produtos "+ erro);
        }
    }
    
    public ArrayList<estoque_DTO> Consultaproduto(){
        String sql = "SELECT * FROM tb_cardapio";
        conn = new  ConexaoDAO().conectaBD();
         ArrayList<estoque_DTO> estoDTO = new  ArrayList<estoque_DTO>(); 
        try {
           
            Pstm = conn.prepareStatement(sql);
            Rs = Pstm.executeQuery();
            while(Rs.next()){
                estoque_DTO Obj_DTO = new estoque_DTO();
                Obj_DTO.setId(Rs.getInt("id_car_pk"));
                Obj_DTO.setNome(Rs.getString("nome"));
                Obj_DTO.setDescricao(Rs.getString("descricao"));
                Obj_DTO.setPreco(Rs.getDouble("preco"));
                
                estoDTO.add(Obj_DTO);
                
            }  
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Erro de Consulta "+ erro);
            
        }
        return estoDTO;
    }
    
    public estoque_DTO ConsultarID(int ID){
        
        estoque_DTO objDTO = new estoque_DTO();
        String SQL = "SELECT * FROM tb_cardapio WHERE id_car_pk = ?";
        
        try {
            Pstm = conn.prepareStatement(SQL);
            
            Pstm.setInt(1, ID);
            
            ResultSet rs = Pstm.executeQuery();
            
            if(rs.next()){
                
                objDTO.setNome(rs.getString("nome"));
                objDTO.setDescricao(rs.getString("descricao"));
                objDTO.setPreco(rs.getDouble("preco"));
                
                
                
            }
            
            return objDTO;
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null,"Erro na estoque_DAO ao consultar por id: " + erro);
            return null;
        }
    }
    
}

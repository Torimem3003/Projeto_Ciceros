/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pasta_pagamento;

import Pasta_conexao_Banco.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.time.LocalDate;

public class pagamentoDAO {

    Connection conn = new ConexaoDAO().conectaBD();
    PreparedStatement pstm;
    ResultSet rs;

    public void save(pagamento_DTO pagamento_DTO) {

          
        try {
            String sql = "INSERT INTO tb_pagamento (forma_Pagamento,valor,dia_compra,pedido)  VALUES (?,?,?,?)";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, pagamento_DTO.getFormaDePagamento());
            pstm.setDouble(2, pagamento_DTO.getValorPedido());
            pstm.setString(3, pagamento_DTO.getData().toString());
            pstm.setInt(4, pagamento_DTO.getIdPedido());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso ");


        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na usuario_DAO ao salvar os dados: " + erro);
        }
    }
    
     public ArrayList<pagamento_DTO> getRgVendas(String Data) {

        String sql = "SELECT * FROM tb_pagamento where dia_compra like ?";

        ArrayList<pagamento_DTO> listaPagamentos = new ArrayList<pagamento_DTO>();
       
        try {
            conn = new ConexaoDAO().conectaBD();
            pstm =  conn.prepareStatement(sql);
            pstm.setString(1, "%"+Data+"%");
            rs = pstm.executeQuery();
            
            
            
            while (rs.next()) {
                pagamento_DTO pedido = new pagamento_DTO();

                pedido.setIdPagamento(rs.getInt("id_pag_pk"));
                pedido.setFormaDePagamento(rs.getString("forma_pagamento"));
                pedido.setValorPedido(rs.getInt("valor"));
                pedido.setData(rs.getDate("dia_compra").toLocalDate());
                pedido.setIdPedido(rs.getInt("pedido"));
                listaPagamentos.add(pedido);

            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro na usuario_DAO ao consulta geral: " +  erro);
            
        }
        return listaPagamentos;
     }
}
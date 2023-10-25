/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;

import br.com.curso.model.Pedido;
import br.com.curso.model.Cliente;
import br.com.curso.model.Pedido;
import br.com.curso.model.Pedido;
import br.com.curso.model.Servico;
import br.com.curso.model.Veiculo;
import static br.com.curso.utils.Conversao.data2String;
import static br.com.curso.utils.Conversao.valorDinheiro;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class PedidoDAO implements GenericDAO{
    private Connection conexao;
    
    public PedidoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    

    @Override
    public Boolean cadastrar(Object objeto) {
        Pedido oPedido = (Pedido) objeto;
        Boolean retorno=false;
        if (oPedido.getIdPedido()== 0) {
            retorno = this.inserir(oPedido);
        }else {
            retorno = this.alterar(oPedido);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Pedido oPedido = (Pedido) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into pedido (nomepedido, datapedido, valortotal, "
                + "pago, idcliente, idservico, idveiculo) values (?,?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPedido.getNomePedido());
            stmt.setDate(2,new java.sql.Date(oPedido.getDataPedido().getTime()));
            stmt.setDouble(3, oPedido.getValorTotal());
            stmt.setString(4, oPedido.getPago());
            stmt.setInt(5, oPedido.getCliente().getIdCliente());
            stmt.setInt(6, oPedido.getServico().getIdServico());
            stmt.setInt(7, oPedido.getVeiculo().getIdVeiculo());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            try {
                System.out.println("Problemas ao cadastrar a Pedido! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Problema ao executar rollback"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
       Pedido oPedido = (Pedido) objeto;
       PreparedStatement stmt = null;
       String sql = "update pedido set nomepedido=?, datapedido=?, valortotal=?, "
                + "pago=?, idcliente=?, idservico=?, idveiculo=? where idpedido=?";
       try {
           stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPedido.getNomePedido());
            stmt.setDate(2,new java.sql.Date(oPedido.getDataPedido().getTime()));
            stmt.setDouble(3, oPedido.getValorTotal());
            stmt.setString(4, oPedido.getPago());
            stmt.setInt(5, oPedido.getCliente().getIdCliente());
            stmt.setInt(6, oPedido.getServico().getIdServico());
            stmt.setInt(7, oPedido.getVeiculo().getIdVeiculo());
            stmt.setInt(8, oPedido.getIdPedido());
           stmt.execute();
           conexao.commit();
           return true;
       } catch (Exception ex) {
           try {
               System.out.println("Problemas ao alterar a Pedido! Erro: "+ex.getMessage());
               ex.printStackTrace();
               conexao.rollback();
           } catch (SQLException e) {
               System.out.println("Problemas ao executar rollback"+e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Boolean excluir(int numero) {
        int idPedido = numero;
        PreparedStatement stmt= null;
        
        String sql = "delete from pedido where idpedido=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao excluir a Pedido! Erro: "
                    +ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rolback "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        } 
    }

    @Override
    public Object carregar(int numero) {
        int idPedido = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Pedido oPedido = null;
        String sql="select * from pedido where idPedido=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oPedido = new Pedido();
                oPedido.setIdPedido(rs.getInt("idPedido"));
                oPedido.setNomePedido(rs.getString("nomepedido"));
                oPedido.setDataPedido(rs.getDate("datapedido"));
                oPedido.setValorTotal(rs.getDouble("valortotal"));
                oPedido.setPago(rs.getString("pago"));
                
                ClienteDAO oClienteDAO = new ClienteDAO();
               oPedido.setCliente((Cliente) oClienteDAO.carregar(rs.getInt("idcliente")));
               
               ServicoDAO oServicoDAO = new ServicoDAO();
               oPedido.setServico((Servico) oServicoDAO.carregar(rs.getInt("idservico")));
               
               VeiculoDAO oVeiculoDAO = new VeiculoDAO();
               oPedido.setVeiculo((Veiculo) oVeiculoDAO.carregar(rs.getInt("idveiculo")));
                
            }
            return oPedido;
        } catch (Exception ex) {
            System.out.println("Problema ao carregar Pedido! Erro:"+ex.getMessage());
            return false;
        }
    }


    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from pedido";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Pedido oPedido = new Pedido();
                oPedido.setIdPedido(rs.getInt("idPedido"));
                oPedido.setNomePedido(rs.getString("nomepedido"));
                oPedido.setDataPedido(rs.getDate("datapedido"));
                oPedido.setValorTotal(rs.getDouble("valortotal"));
                oPedido.setPago(rs.getString("pago"));
                
                ClienteDAO oClienteDAO = null;
               try{
                   oClienteDAO = new ClienteDAO();
                
                } catch (Exception ex){
                       System.out.println("Erro buscar cliente "+ex.getMessage());
                       ex.printStackTrace();
                }
                oPedido.setCliente((Cliente) oClienteDAO.carregar(rs.getInt("idcliente")));
                
                ServicoDAO oServicoDAO = null;
               try{
                   oServicoDAO = new ServicoDAO();
                
                } catch (Exception ex){
                       System.out.println("Erro buscar servico "+ex.getMessage());
                       ex.printStackTrace();
                }
                oPedido.setServico((Servico) oServicoDAO.carregar(rs.getInt("idservico")));
                
                VeiculoDAO oVeiculoDAO = null;
               try{
                   oVeiculoDAO = new VeiculoDAO();
                
                } catch (Exception ex){
                       System.out.println("Erro buscar veiculo "+ex.getMessage());
                       ex.printStackTrace();
                }
                oPedido.setVeiculo((Veiculo) oVeiculoDAO.carregar(rs.getInt("idveiculo")));
                
                resultado.add(oPedido);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Pedido! Erro: "+ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public String listarJSON(){
        String strJson="";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Pedido oPedido = null;
        String sql = "select * from pedido";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            strJson = "[";
            int i = 0;
            while(rs.next()){
                if (i>0) strJson+=",";
                strJson += "{\"idPedido\":"+rs.getInt("idpedido")+","
                        + "\"nomePedido\":\""+rs.getString("nomepedido")+"\","
                        + "\"dataPedido\":\""+data2String(rs.getDate("datapedido"))+"\","
                        + "\"valorTotal\":\""+valorDinheiro(rs.getDouble("valortotal"),"BR")+"\","
                        + "\"pago\":\""+rs.getString("pago")+"\","
                        + "\"idcliente\":\""+rs.getInt("idcliente")+"\","
                        + "\"idservico\":\""+rs.getInt("idservico")+"\","
                        + "\"idveiculo\":\""+rs.getInt("idveiculo")+"\",";
                        
                i++;
            }
            strJson += "]";
        } catch (Exception e){
            System.out.println("Problemas ao Listar Pedido!Erro: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println(strJson);
        return strJson;
    }
    public Boolean pago(int numero) {
        int idPedido = numero;
        PreparedStatement stmt = null;

        String sql = "update pedido set pago=? where idpedido=?";
        try{
            Pedido oPedido = (Pedido) this.carregar(idPedido);
            stmt = conexao.prepareStatement(sql);
            if (oPedido.getPago().equals("A"))
                stmt.setString(1, "I");
            else stmt.setString(1, "A");
            stmt.setInt(2, idPedido);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir Pedido! Erro: "+ex.getMessage());
            try {
                conexao.rollback(); 
            } catch (SQLException e) {
                System.out.println("Erro rolback "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }
}

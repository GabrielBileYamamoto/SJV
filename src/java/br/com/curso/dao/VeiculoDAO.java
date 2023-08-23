/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;

import br.com.curso.model.Marca;
import br.com.curso.model.Modelo;
import br.com.curso.model.Tipo;
import br.com.curso.model.Veiculo;
import static br.com.curso.utils.Conversao.data2String;
import static br.com.curso.utils.Conversao.valorDinheiro;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class VeiculoDAO implements GenericDAO{
    private Connection conexao;
    
    public VeiculoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    


    @Override
    public Boolean cadastrar(Object objeto) {
        Veiculo oVeiculo = (Veiculo) objeto;
        Boolean retorno=false;
        if (oVeiculo.getIdVeiculo()== 0) {
            retorno = this.inserir(oVeiculo);
        }else {
            retorno = this.alterar(oVeiculo);
        }
        return retorno;
    }

    @Override
     public Boolean inserir(Object objeto) {
        Veiculo oVeiculo = (Veiculo) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into veiculo (placaveiculo, cor, ano, idtipo, idmodelo, idmarca, imagemveiculo)"  
                +"values (?,?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oVeiculo.getPlacaVeiculo());
            stmt.setString(2, oVeiculo.getCor());
            stmt.setDate(3,new java.sql.Date(oVeiculo.getAno().getTime()));
            stmt.setInt(4, oVeiculo.getTipo().getIdTipo());
            stmt.setInt(5, oVeiculo.getModelo().getIdModelo());
            stmt.setInt(6, oVeiculo.getMarca().getIdMarca());
            stmt.setString(7, oVeiculo.getImagemVeiculo());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            try {
                System.out.println("Problemas ao cadastrar a Veiculo! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
         Veiculo oVeiculo = (Veiculo) objeto;
       PreparedStatement stmt = null;
       String sql = "update veiculo set placaveiculo=?, cor=?, ano=?, idtipo=?, idmodelo=?, idmarca=?, imagemveiculo=? where idVeiculo=?";
       try {
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oVeiculo.getPlacaVeiculo());
            stmt.setString(2, oVeiculo.getCor());
            stmt.setDate(3,new java.sql.Date(oVeiculo.getAno().getTime()));
            stmt.setInt(4, oVeiculo.getTipo().getIdTipo());
            stmt.setInt(5, oVeiculo.getModelo().getIdModelo());
            stmt.setInt(6, oVeiculo.getMarca().getIdMarca());
            stmt.setString(7, oVeiculo.getImagemVeiculo());
            stmt.setInt(8, oVeiculo.getIdVeiculo());
           stmt.execute();
           conexao.commit();
           return true;
       } catch (Exception ex) {
           try {
               System.out.println("Problemas ao alterar a Veiculo! Erro: "+ex.getMessage());
               ex.printStackTrace();
               conexao.rollback();
           } catch (SQLException e) {
               System.out.println("Erro:"+e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Boolean excluir(int numero) {
        int idVeiculo = numero;
        PreparedStatement stmt= null;
        
        String sql = "delete from veiculo where idVeiculo=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao excluir a Veiculo! Erro: "
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
        int idVeiculo = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Veiculo oVeiculo = null;
        String sql="select * from veiculo where idVeiculo=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oVeiculo = new Veiculo();
                oVeiculo.setIdVeiculo(rs.getInt("idveiculo"));
                oVeiculo.setPlacaVeiculo(rs.getString("placaveiculo"));
                oVeiculo.setCor(rs.getString("cor"));
                oVeiculo.setAno(rs.getDate("ano"));
                oVeiculo.setImagemVeiculo(rs.getString("Imagemveiculo"));
                
                TipoDAO oTipoDAO = new TipoDAO();
               oVeiculo.setTipo((Tipo) oTipoDAO.carregar(rs.getInt("idtipo")));  
               
                ModeloDAO oModeloDAO = new ModeloDAO();
               oVeiculo.setModelo((Modelo) oModeloDAO.carregar(rs.getInt("idmodelo")));
               
                MarcaDAO oMarcaDAO = new MarcaDAO();
               oVeiculo.setMarca((Marca) oMarcaDAO.carregar(rs.getInt("idmarca")));
               
            }
            return oVeiculo;
        } catch (Exception ex) {
            System.out.println("Problema ao carregar Veiculo! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
     public List<Object> listar() {
       List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from veiculo order by idVeiculo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Veiculo oVeiculo = new Veiculo();
                oVeiculo.setIdVeiculo(rs.getInt("idveiculo"));
                oVeiculo.setPlacaVeiculo(rs.getString("placaveiculo"));
                oVeiculo.setCor(rs.getString("cor"));
                oVeiculo.setAno(rs.getDate("ano"));
                oVeiculo.setImagemVeiculo(rs.getString("imagemveiculo"));
                   
                TipoDAO oTipoDAO = null;
               try{
                   oTipoDAO = new TipoDAO();
                
                } catch (Exception ex){
                       System.out.println("Erro buscar estado "+ex.getMessage());
                       ex.printStackTrace();
                }
                oVeiculo.setTipo((Tipo) oTipoDAO.carregar(rs.getInt("idtipo")));
                
                 ModeloDAO oModeloDAO = null;
               try{
                   oModeloDAO = new ModeloDAO();
                
                } catch (Exception ex){
                       System.out.println("Erro buscar estado "+ex.getMessage());
                       ex.printStackTrace();
                }
                oVeiculo.setModelo((Modelo) oModeloDAO.carregar(rs.getInt("idmodelo")));
                
                MarcaDAO oMarcaDAO = null;
               try{
                   oMarcaDAO = new MarcaDAO();
                
                } catch (Exception ex){
                       System.out.println("Erro buscar estado "+ex.getMessage());
                       ex.printStackTrace();
                }
                oVeiculo.setMarca((Marca) oMarcaDAO.carregar(rs.getInt("idmarca")));
                resultado.add(oVeiculo);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Veiculo! Erro: "+ex.getMessage());
        }
        return resultado;
    }
    
    public String listarJSON(){
        String strJson="";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Veiculo oVeiculo = null;
        String sql = "select * from veiculo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            strJson = "[";
            int i = 0;
            while(rs.next()){
                if (i>0) strJson+=",";
                strJson += "{\"idVeiculo\":"+rs.getInt("idveiculo")+","
                        + "\"placaVeiculo\":\""+rs.getString("placaveiculo")+"\","
                        + "\"cor\":\""+rs.getString("cor")+"\","
                        + "\"ano\":\""+data2String(rs.getDate("ano"))+"\","
                        + "\"idtipo\":\""+rs.getInt("idtipo")+"\","
                        + "\"idmodelo\":\""+rs.getInt("idmodelo")+"\","
                        + "\"idmarca\":\""+rs.getInt("idmarca")+"\",";
                i++;
            }
            strJson += "]";
        } catch (Exception e){
            System.out.println("Problemas ao Listar Veiculo!Erro: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println(strJson);
        return strJson;
    }
    public int verificarPlaca(String placaveiculo){
        PreparedStatement stmt = null;
        ResultSet rs= null;
        int idVeiculo = 0;
        String sql = "Select * from veiculo";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, placaveiculo);
            rs=stmt.executeQuery();
            while(rs.next()){
                idVeiculo = rs.getInt("idveiculo");
            }
            return idVeiculo;
        }catch(SQLException ex){
            System.out.println("Problemas au carregar veiculo! Erro: "+ex.getMessage());
            return idVeiculo;
        }
    
}
    
}
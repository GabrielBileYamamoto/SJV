/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;



import br.com.curso.model.Modelo;
import br.com.curso.model.Modelo;
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
public class ModeloDAO  implements GenericDAO{
    private Connection conexao;
    
     public ModeloDAO() throws Exception{
       conexao = SingleConnection.getConnection();
    }
    
    @Override
    public Boolean cadastrar(Object objeto) {
       Modelo oModelo = (Modelo) objeto;
        Boolean retorno=false;
        if (oModelo.getIdModelo()== 0) {
            retorno = this.inserir(oModelo);
        }else {
            retorno = this.alterar(oModelo);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Modelo oModelo = (Modelo) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into modeloveiculo (nomemodelo) values (?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oModelo.getNomeModelo());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            try {
                System.out.println("Problemas ao cadastrar a Modelo! Erro: "+ex.getMessage());
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
         Modelo oModelo = (Modelo) objeto;
       PreparedStatement stmt = null;
       String sql = "update modeloveiculo set nomemodelo=? where idmodelo=?";
       try {
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oModelo.getNomeModelo());
           stmt.setInt(2, oModelo.getIdModelo());
           stmt.execute();
           conexao.commit();
           return true;
       } catch (Exception ex) {
           try {
               System.out.println("Problemas ao alterar a Modelo! Erro: "+ex.getMessage());
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
        int idModelo = numero;
        PreparedStatement stmt= null;
        
        String sql = "delete from modeloveiculo where idmodelo=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idModelo);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao excluir a Modelo! Erro: "
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
        int idModelo = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Modelo oModelo = null;
        String sql="select * from modeloveiculo where idModelo=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idModelo);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oModelo = new Modelo();
                oModelo.setIdModelo(rs.getInt("idModelo"));
                oModelo.setNomeModelo(rs.getString("nomemodelo"));
            }
            return oModelo;
        } catch (Exception ex) {
            System.out.println("Problema ao carregar Modelo! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
       List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from modeloveiculo order by idModelo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Modelo oModelo = new Modelo();
                oModelo.setIdModelo (rs.getInt("idModelo"));
                oModelo.setNomeModelo (rs.getString("nomemodelo"));
                resultado.add(oModelo);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Modelo! Erro: "+ex.getMessage());
        }
        return resultado;
    }
     public String listarJSON(){
        String strJson="";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Modelo oModelo = null;
        String sql = "select * from modeloveiculo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            strJson = "[";
            int i = 0;
            while(rs.next()){
                if (i>0) strJson+=",";
                strJson += "{\"idModelo\":"+rs.getInt("idmodelo")+","
                        + "\"nomeModelo\":\""+rs.getString("nomemodelo")+"\",";
                i++;
            }
            strJson += "]";
        } catch (Exception e){
            System.out.println("Problemas ao Listar Modelo!Erro: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println(strJson);
        return strJson;
    }
    
}
    


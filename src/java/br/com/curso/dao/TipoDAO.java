/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;

import br.com.curso.model.Tipo;
import br.com.curso.model.Tipo;
import br.com.curso.model.Tipo;
import static br.com.curso.utils.Conversao.data2String;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class TipoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public TipoDAO() throws Exception{
     conexao = SingleConnection.getConnection();
    }

   @Override
    public Boolean cadastrar(Object objeto) {
       Tipo oTipo = (Tipo) objeto;
        Boolean retorno=false;
        if (oTipo.getIdTipo()== 0) {
            retorno = this.inserir(oTipo);
        }else {
            retorno = this.alterar(oTipo);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Tipo oTipo = (Tipo) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into tipoveiculo (categoria) values (?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipo.getCategoria());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            try {
                System.out.println("Problemas ao cadastrar a Tipo! Erro: "+ex.getMessage());
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
         Tipo oTipo = (Tipo) objeto;
       PreparedStatement stmt = null;
       String sql = "update tipoveiculo set categoria=? where idtipo=?";
       try {
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oTipo.getCategoria());
           stmt.setInt(2, oTipo.getIdTipo());
           stmt.execute();
           conexao.commit();
           return true;
       } catch (Exception ex) {
           try {
               System.out.println("Problemas ao alterar a Tipo! Erro: "+ex.getMessage());
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
        int idTipo = numero;
        PreparedStatement stmt= null;
        
        String sql = "delete from tipoveiculo where idtipo=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipo);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao excluir a Tipo! Erro: "
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
        int idTipo = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Tipo oTipo = null;
        String sql="select * from tipoveiculo where idtipo=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipo);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oTipo = new Tipo();
                oTipo.setIdTipo(rs.getInt("idTipo"));
                oTipo.setCategoria(rs.getString("categoria"));
            }
            return oTipo;
        } catch (Exception ex) {
            System.out.println("Problema ao carregar Tipo! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
       List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from tipoveiculo order by idTipo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Tipo oTipo = new Tipo();
                oTipo.setIdTipo (rs.getInt("idTipo"));
                oTipo.setCategoria (rs.getString("categoria"));
                resultado.add(oTipo);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Tipo! Erro: "+ex.getMessage());
        }
        return resultado;
    }
     public String listarJSON(){
        String strJson="";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Tipo oTipo = null;
        String sql = "select * from tipoveiculo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            strJson = "[";
            int i = 0;
            while(rs.next()){
                if (i>0) strJson+=",";
                strJson += "{\"idTipo\":"+rs.getInt("idtipo")+","
                        + "\"categoria\":\""+rs.getString("categoria")+"\",";
                i++;
            }
            strJson += "]";
        } catch (Exception e){
            System.out.println("Problemas ao Listar Tipo!Erro: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println(strJson);
        return strJson;
    }
    
}
    


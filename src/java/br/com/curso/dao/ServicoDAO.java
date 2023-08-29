/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;

import br.com.curso.model.Servico;
import br.com.curso.utils.Conversao;
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
 * @author Aluno
 */
public class ServicoDAO implements GenericDAO{
    private Connection conexao;
    
    public ServicoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    


    @Override
    public Boolean cadastrar(Object objeto) {
        Servico oServico = (Servico) objeto;
        Boolean retorno=false;
        if (oServico.getIdServico()== 0) {
            retorno = this.inserir(oServico);
        }else {
            retorno = this.alterar(oServico);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Servico oServico = (Servico) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into servico (descricao, valorServico, valorPago, "
                + "datadocumento, imagemdocumento) values (?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oServico.getDescricao());
            stmt.setDouble(2, oServico.getValorServico());
            stmt.setDouble(3, oServico.getValorPago());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            try {
                System.out.println("Problemas ao cadastrar a Servico! Erro: "+ex.getMessage());
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
       Servico oServico = (Servico) objeto;
       PreparedStatement stmt = null;
       String sql = "update servico set descricao=?, valorServico=?, valorPago=?, "
                + "datadocumento=?, imagemdocumento=? where idservico=?";
       try {
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oServico.getDescricao());
            stmt.setDouble(2, oServico.getValorServico());
            stmt.setDouble(3, oServico.getValorPago());
            stmt.setDate(4,new java.sql.Date(oServico.getDataDocumento().getTime()));
            stmt.setString(5, oServico.getImagemDocumento());
            stmt.setInt(6, oServico.getIdServico());
           stmt.execute();
           conexao.commit();
           return true;
       } catch (Exception ex) {
           try {
               System.out.println("Problemas ao alterar a Servico! Erro: "+ex.getMessage());
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
        int idServico = numero;
        PreparedStatement stmt= null;
        
        String sql = "delete from servico where idservico=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idServico);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problema ao excluir a Servico! Erro: "
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
        int idServico = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Servico oServico = null;
        String sql="select * from servico where idServico=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idServico);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oServico = new Servico();
                oServico.setIdServico(rs.getInt("idServico"));
                oServico.setDescricao(rs.getString("descricao"));
                oServico.setValorServico(rs.getDouble("valorServico"));
                oServico.setValorPago(rs.getDouble("valorPago"));
                oServico.setDataDocumento(rs.getDate("datadocumento"));
                oServico.setImagemDocumento(rs.getString("imagemdocumento"));
            }
            return oServico;
        } catch (Exception ex) {
            System.out.println("Problema ao carregar Servico! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from servico";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Servico oServico = new Servico();
                oServico.setIdServico(rs.getInt("idServico"));
                oServico.setDescricao(rs.getString("descricao"));
                oServico.setValorServico(rs.getDouble("valorServico"));
                oServico.setValorPago(rs.getDouble("valorPago"));
                oServico.setDataDocumento(rs.getDate("datadocumento"));
                oServico.setImagemDocumento(rs.getString("imagemdocumento"));
                resultado.add(oServico);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Servico! Erro: "+ex.getMessage());
        }
        return resultado;
    }
    
    public String listarJSON(){
        String strJson="";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> resultado = new ArrayList<>();
        Servico oServico = null;
        String sql = "select * from servico";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            strJson = "[";
            int i = 0;
            while(rs.next()){
                if (i>0) strJson+=",";
                strJson += "{\"idServico\":"+rs.getInt("idservico")+","
                        + "\"descricao\":\""+rs.getString("descricao")+"\","
                        + "\"dataDocumento\":\""+data2String(rs.getDate("datadocumento"))+"\","
                        + "\"valorServico\":\""+valorDinheiro(rs.getDouble("valorServico"),"BR")+"\","
                        + "\"valorPago\":\""+valorDinheiro(rs.getDouble("valorPago"),"BR")+"\"}";
                i++;
            }
            strJson += "]";
        } catch (Exception e){
            System.out.println("Problemas ao Listar Servico!Erro: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println(strJson);
        return strJson;
    }
    
}

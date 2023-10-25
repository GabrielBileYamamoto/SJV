/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.dao;

import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public class SomarDAO extends EstadoDAO{
    public double Somar(int codigoDoServico)
    {
        
         try 
        {
            PreparedStatement preparedStatement = null;
            preparedStatement = conexao.prepareStatement("SELECT SUM(s.valorservico) as soma from servico s inner join pedido p on s.idservico = p.idservico where idservico=?");
            preparedStatement.setInt(1, codigoDoServico);
            ResultSet rs = null;
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                double somaDeServicos = rs.getDouble("soma");
                return somaDeServicos;

            }
        } 
        catch (SQLException exception) 
        {
            System.out.println("Erro somar serviços." + exception.getMessage());
            return 0;
        }
        return 0;
    }
     public SomarDAO() throws Exception 
    {
        
    }
   
}

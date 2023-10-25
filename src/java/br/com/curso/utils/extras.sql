/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Aluno
 * Created: 17/10/2023
 */

<td align="center"><fmt:formatNumber value = "${pedido.valorTotal}" type = "currency"/></td>

    <td align="center">
                                <a href="#" id="gerarPDF" title="GerarPDF" onclick="gerarPDF(${pedido.idPedido})">
                                        <button class="btn btn-group-lg btn-info"/>Gerar PDF</button></a>
                            </td> 
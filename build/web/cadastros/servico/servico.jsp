<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menuLogado.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Servicos</h1>
    <p class="mb-4">Planilha de Registros</p>
    
    <a class="btn btn-success mb-4" href="${pageContext.request.contextPath}/ServicoNovo">
        <i class="fas fa-sticky-note"></i>
        <strong>Novo</strong>
    </a>
    
    <div class="card shadow">
        <div class="card-body">
            <table id="datatable" class="display">
                <thead>
                    <tr>
                        <th align="right">ID</th>
                        <th align="left">Nome do Servico</th>
                        <th align="left">Descri��o</th>
                        <th align="right">ValorServico</th>
                        <th Align="center">Excluir</th>
                        <th Align="center">Alterar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="servico" items="${servicos}"> 
                        <tr>
                            <td align="right">${servico.idServico}</td>
                             <td align="left">${servico.nomeServico}</td>
                            <td align="left">${servico.descricao}</td>
                            <td align="right"><fmt:formatNumber value = "${servico.valorServico}" type = "currency"/></td>
                            <td align="center">
                                <a href="#" id="deletar" title="Excluir" onclick="deletar(${servico.idServico})">
                                    <button>Excluir</button>
                                </a>
                            </td>                        
                            <td align="center">
                                <a href="${pageContext.request.contextPath}/ServicoCarregar?idServico=${servico.idServico}">
                                   <button>Alterar</button>
                                </a>
                            </td>             
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>      
<script>
    $(document).ready(function() {
            console.log('entrei ready');
            //Carregamos a datatable
            //$("#datatable").DataTable({});
            $('#datatable').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 at� 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "�ltimo"
                    }
                }
            });
        });
    
    function deletar(codigo){
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Voc� tem certeza?',
            text: "Voc� n�o poder� recuperar depois!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, apague a servico!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/ServicoExcluir',
                    data:{
                        idServico: id
                    },
                    success:
                        function(data){
                            if(data == 1){
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'success',
                                    title: 'Sucesso',
                                    text: 'Servico exclu�da com sucesso!',
                                    showConfirmButton: false,
                                    timer: 2000
                                })
                            } else {
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: 'N�o foi poss�vel excluir a servico!',
                                    showConfirmButton: false,
                                    timer: 2000
                                })
                            }
                            window.location.href = "${pageContext.request.contextPath}/ServicoListar";
                        },
                    error:
                        function(data){
                            window.location.href = "${pageContext.request.contextPath}/ServicoListar";
                        }
                });
            };
        });
    }
</script>
 <%@include file="/footer.jsp"%>
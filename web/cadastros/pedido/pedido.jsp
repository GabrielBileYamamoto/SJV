<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix= "fmt" uri= "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType= "text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menuLogado.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Pedidos</h1>
    <p class="mb-4">Planilha de Registros</p>

    <a class="btn btn-success mb-4" href="${pageContext.request.contextPath}/PedidoNovo">
        <i class="fas fa-sticky-note"></i>
        <strong>Novo</strong>
    </a>
        
     <a class="btn btn-success mb-4" href="${pageContext.request.contextPath}/PedidoGerar?usuario=${sessionScope.nomeusuario}" target="_blank">
    <i class="fas fa-sticky-note"></i>
    <strong>Gerar PDF</strong>
</a>
        
 


    <div class="card shadow">
        <div class="card-body">
            <table id="datatable" class="display">
                <thead>
                    <tr>
                        <th align="left">ID</th>
                        <th align="left">Cliente</th>
                        <th align="left">Serviços</th>
                        <th align="left">Placa-Veiculo</th>
                        <th align="left">Pedido</th>
                        <th align="left">DATA</th>
                        <th align="left">Valor Total</th>
                        <th Align="left">Excluir</th>
                        <th Align="left">Alterar</th>
                        <th Align="left">Pago</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pedido" items="${pedidos}"> 
                        <tr>
                            <td align="center">${pedido.idPedido}</td>  
                            <td align="left">${pedido.cliente.nome}</td>
                            <td align="left">${pedido.servico.nomeServico}</td>
                            <td align="left">${pedido.veiculo.placaVeiculo}</td>
                            <td align="left">${pedido.nomePedido}</td>
                            <td align="left"><fmt:formatDate pattern = "dd/MM/yyyy" value = "${pedido.dataPedido}" />
                            </td>
                            <td align="left"><fmt:formatNumber value = "${pedido.servico.valorServico}" type = "currency"/></td>
                            <td align="right">
                                <a href="#" id="deletar" title="Excluir" onclick="deletar(${pedido.idPedido})">
                                    <button class="btn btn-group-lg btn-danger"/>Excluir</button></a>
                            </td>                        
                            <td align="right">
                                <a href="${pageContext.request.contextPath}/PedidoCarregar?idPedido=${pedido.idPedido}">
                                    <button class="btn btn-group-lg btn-warning">Alterar</button>
                                </a>
                            </td> 
                            <td align="right">
                                <a href="#" onclick="pagar(${pedido.idPedido})">
                                    <button class="btn 
                                            <c:out value="${pedido.pago == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <i class="fas fa-fw 
                                           <c:out value="${pedido.pago == 'A' ? 'fa-money-bill' : ' fa-money-bill'}"/>"></i>
                                        <Strong>
                                            <c:out value="${pedido.pago == 'A' ? 'Não Pago': 'Pago'}"/>
                                        </Strong>
                                    </button></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>      
<script>
    $(document).ready(function () {
        console.log('entrei ready');
        //Carregamos a datatable
        //$("#datatable").DataTable({});
        $('#datatable').DataTable({
            "oLanguage": {
                "sProcessing": "Processando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "Nenhum registro encontrado.",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sInfoPostFix": "",
                "sSearch": "Buscar:",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext": "Seguinte",
                    "sLast": "Último"
                }
            }
        });
    });
    function deletar(codigo) {
        console.log("Entrei em função deletar");
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            text: "Você não pode recuperar depois!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, apague o pedido!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/PedidoExcluir',
                    data: {
                        idPedido: id
                    },
                    success:
                            function (data) {
                                if (data == 1) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: 'Sucesso',
                                        text: 'Pedido excluído com sucesso!',
                                        showConfirmButton: false,
                                        timer: 2000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                    })
                                } else if (data == 2) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'warning',
                                        title: 'Atenção',
                                        text: 'Há pedidos vinculadas a esse pedido! Não é possível excluí-lo!',
                                        showConfirmButton: false,
                                        timer: 3500
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                    })
                                } else {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'error',
                                        title: 'Erro',
                                        text: 'Não foi possível excluir o pedido!',
                                        showConfirmButton: false,
                                        timer: 2000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                    })
                                }
                            },
                    error:
                            function (data) {
                                window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                            }
                });
            }
            ;
        });
    }

    function pagar(codigo) {
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, altere a situação!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/PedidoPago',
                    data: {
                        idPedido: id
                    },
                    success:
                            function (data) {
                                if (data == 1) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: 'Sucesso',
                                        text: 'Situação Alterada com sucesso!',
                                        showConfirmButton: false,
                                        timer: 2000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                    })
                                } else {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'error',
                                        title: 'Erro',
                                        text: 'Não foi possível alterar a pedido!',
                                        showConfirmButton: false,
                                        timer: 2000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                    })
                                }

                            },
                    error:
                            function (data) {
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: '',
                                    showConfirmButton: false,
                                    timer: 2000
                                }).then(function () {
                                    window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                })
                            }
                });
            }
            ;
        });
    }

    function gerar(codigo) {
        var id = codigo;
        console.log(codigo);
        Swal.fire({
            title: 'Você tem certeza?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim, altere a situação!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: 'post',
                    url: '${pageContext.request.contextPath}/PedidoGerar',
                    data: {
                        idPedido: id
                    },
                    success:
                            function (data) {
                                if (data == 1) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: 'Sucesso',
                                        text: 'Situação Alterada com sucesso!',
                                        showConfirmButton: false,
                                        timer: 2000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                    })
                                } else {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'error',
                                        title: 'Erro',
                                        text: 'Não foi possível alterar a pedido!',
                                        showConfirmButton: false,
                                        timer: 2000
                                    }).then(function () {
                                        window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                    })
                                }

                            },
                    error:
                            function (data) {
                                Swal.fire({
                                    position: 'top-end',
                                    icon: 'error',
                                    title: 'Erro',
                                    text: '',
                                    showConfirmButton: false,
                                    timer: 2000
                                }).then(function () {
                                    window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                                })
                            }
                });
            }
            ;
        });
    }

</script>
<%@include file="/footer.jsp"%>
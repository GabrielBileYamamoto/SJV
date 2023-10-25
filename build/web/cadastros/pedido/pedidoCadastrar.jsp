<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menuLogado.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Pedidos</h1>
    <p class="mb-4">Formulário de Cadastro</p>

    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/PedidoListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>
    <div class="row">
        <!-- Imagem do Documento --> 
        <!-- Campos de cadastramento -->        
        <div class="col-lg-9">
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="form-group">
                        <label>Id</label>
                        <input class="form-control" type="text" name="idPedido" id="idpedido" 
                               value="${pedido.idPedido}" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <label>Nome do Pedido</label>
                        <input class="form-control" type="text" name="nomepedido" id="nomepedido" 
                               value="${pedido.nomePedido}" size="100" maxlength="100"/>
                    </div>
                    <div class="col-sm">
                        <label>Data</label>
                        <input class="form-control" type="date" name="datapedido" id="datapedido" 
                               value="${pedido.dataPedido}"/>
                    </div>

                    <div class="form-group">
                      <label>Veiculo</label>
                    <select name="idveiculo" id="idveiculo">
                        <option value="">Selecione</option>
                        <c:forEach var="veiculo" items="${veiculos}">
                            <option value="${veiculo.idVeiculo}" ${pedido.veiculo.idVeiculo == veiculo.idVeiculo ? "selected" : ""}>
                                ${veiculo.placaVeiculo}
                            </option>
                        </c:forEach>
                    </select>
                  </div>

                    <div class="form-group">
                      <label>Servico</label>
                    <select name="idservico" id="idservico">
                        <option value="">Selecione</option>
                        <c:forEach var="servico" items="${servicos}">
                            <option value="${servico.idServico}" ${pedido.servico.idServico == servico.idServico ? "selected" : ""}>
                                ${servico.nomeServico}
                            </option>
                        </c:forEach>
                    </select>
                  </div>



                    <div class="form-group">
                        <label>Cliente</label>
                        <select name="idcliente" id="idcliente">
                            <option value="">Selecione</option>
                            <c:forEach var="cliente" items="${clientes}">
                                <option value="${cliente.idCliente}" ${pedido.cliente.idPessoa == cliente.idCliente ? "selected" : ""}>
                                    ${cliente.nome}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="hidden" name="valorTotal" id="valortotal" value="${pedido.valorTotal}" readonly="readonly"/>
                    <input type="hidden" name="pago" id="pago" value="${pedido.pago}" readonly="readonly"/>
                    
                    <div class="form-group">
                        <label>Valor</label>
                        <input class="form-control" type="text" name="valorTotal" id="valortotal" 
                               value="${pedido.servico.valorServico}" readonly="readonly"/>
                    </div>
                    
                    <!-- Botão de Confirmação --> 
                    <div class="form-group">
                        <button class="btn btn-success" type="submit" id="submit" onclick="validarCampos()">
                            Salvar Pedido</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style type="text/css">
    .inputfile {
        /* visibility: hidden etc. wont work */
        width: 0.1px;
        height: 0.1px;
        opacity: 0;
        overflow: hidden;
        position: absolute;
        z-index: -1;    }
    .inputfile:focus + label {
        /* keyboard navigation */
        outline: 1px dotted #000;
        outline: -webkit-focus-ring-color auto 5px;    }
    .inputfile + label * {
        pointer-events: none;    }
    .borda{
        position: relative;
        margin: 0 20px 30px 0;
        padding: 10px;
        border: 1px solid #e1e1e1;
        border-radius: 3px;
        background: #fff;
        -webkit-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        -moz-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        box-shadow: 0px 0px 3px rgba(0,0,0,0.06);    }
</style>

<script>
    $(document).ready(function () {
        console.log("entrei na ready do documento");
        $("#valorpedido").maskMoney({
            prefix: 'R$',
            suffix: '',
            allowZero: false,
            allowNegative: true,
            allowEmpty: false,
            doubleClickSelection: true,
            selectAllOnFocus: true,
            thousands: '.',
            decimal: ",",
            precision: 2,
            affixesStay: true,
            bringCareAtEndOnFocus: true
        })

        $("#valorpago").maskMoney({
            prefix: 'R$',
            suffix: '',
            allowZero: false,
            allowNegative: true,
            allowEmpty: false,
            doubleClickSelection: true,
            selectAllOnFocus: true,
            thousands: '.',
            decimal: ",",
            precision: 2,
            affixesStay: true,
            bringCareAtEndOnFocus: true
        })
    })

    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nomepedido").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o nome do pedido!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#nomePedido").focus();
        } else if (document.getElementById("datapedido").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a data do pedido!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#dataPedido").focus();
        } else if (document.getElementById("idveiculo").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o veiculo do pedido!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idveiculo").focus();
        } else if (document.getElementById("idservico").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o servico do pedido!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idservico").focus();
        } else if (document.getElementById("idcliente").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o cliente do pedido!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idcliente").focus();
        } else {
            gravarDados();
        }
    }

    function gravarDados() {
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'PedidoCadastrar',
            data: {
                idpedido: $('#idpedido').val(),
                nomepedido: $('#nomepedido').val(),
                datapedido: $('#datapedido').val(),
                valortotal: $('#valortotal').val(),
                pago: $('#pago').val(),
                idcliente: $('#idcliente').val(),
                idservico: $('#idservico').val(),
                idveiculo: $('#idveiculo').val(), 
            },
            success:
                    function (data) {
                        console.log("reposta servlet->");
                        console.log(data);
                        if (data == 1) {
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'Pedido gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function () {
                                window.location.href = "${pageContext.request.contextPath}/PedidoListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar a pedido!',
                                showConfirmButton: true,
                                timer: 10000
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


</script>   
<jsp:include page="/footer.jsp"/>
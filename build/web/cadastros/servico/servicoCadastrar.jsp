<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menuLogado.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Servicos</h1>
    <p class="mb-4">Formulário de Cadastro</p>

    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/ServicoListar">
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
                        <input class="form-control" type="text" name="idServico" id="idservico" 
                               value="${servico.idServico}" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <label>Nome do Servico</label>
                        <input class="form-control" type="text" name="nomeservico" id="nomeservico" 
                               value="${servico.nomeServico}" size="100" maxlength="100"/>
                    </div>
                    <div class="form-group">
                        <label>Descrição do Servico</label>
                        <input class="form-control" type="text" name="descricao" id="descricao" 
                               value="${servico.descricao}" size="100" maxlength="100"/>
                    </div>
                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Valor do Servico</label>
                                <input class="form-control" type="text" style="text-align:right;" 
                                           name="valorservico" id="valorservico" 
                                           value="<fmt:formatNumber value='${servico.valorServico}' type='currency'/>" />
                            </div>
                        </div>
                    </div>
                    <!-- Botão de Confirmação --> 
                    <div class="form-group">
                        <button class="btn btn-success" type="submit" id="submit" onclick="validarCampos()">
                            Salvar Documento</button>
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
        $("#valorservico").maskMoney({
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
        if (document.getElementById("nomeservico").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a descrição da servico!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#nomeservico").focus();
        } else if (document.getElementById("descricao").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a Data da servico!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#descricao").focus();
        } else if (document.getElementById("valorservico").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o valor da servico!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#valorservico").focus();
        } else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        $.ajax({
            type: 'post',
            url: 'ServicoCadastrar',
            data: {
                idservico: $('#idservico').val(),
                nomeservico: $('#nomeservico').val(),
                descricao: $('#descricao').val(),
                valorservico: $('#valorservico').val(),
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
                                text: 'Servico gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/ServicoListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar a servico!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/ServicoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/ServicoListar";
                    }
        });
    }

    
</script>   
<jsp:include page="/footer.jsp"/>
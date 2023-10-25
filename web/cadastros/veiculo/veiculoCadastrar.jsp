<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menuLogado.jsp"/>

<div class="container-fluid">
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Veiculos</h1>
    <p class="mb-4">Formulário de Cadastro</p>

    <a class="btn btn-secondary mb-4" href="${pageContext.request.contextPath}/VeiculoListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>
    <div class="row">
         <!-- Imagem do Documento --> 
        <div class="col">
            <div class="card shadow mb-4"> 
                <div class="card-body">
                    <div class="form-group">
                        <center>
                            <img alt="imagem" class="img-thumbnail" 
                                 src="${veiculo.imagemVeiculo}" 
                                 name="target" id="target" width="170" heigth="200">
                            <br></br>
                            <input type="file" id="gallery-photo-add" 
                                   class="inputfile" onchange="uploadFile();"/>
                            <label for="gallery-photo-add" class="btn btn-success">
                                <i class="fas fa-file-upload"></i> 
                                Selecionar Documento...
                            </label>
                        </center>
                    </div>
                </div>
            </div>
        </div>
        <!-- Campos de cadastramento -->        
          <div class="col-lg-9">
            <div class="card shadow mb-4">
                <div class="card-body">               
                    <div class="form-group">
                        <label>Id</label>
                        <input class="form-control" type="text" name="idveiculo" id="idveiculo" 
                               value="${veiculo.idVeiculo}" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <label>Placa do Veiculo</label>
                        <input class="form-control" type="text" name="placaveiculo" id="placaveiculo" 
                               value="${veiculo.placaVeiculo}" size="7" maxlength="7"/>
                    </div>
                    <div class="form-group">
                        <label>Cor Do Veiculo</label>
                        <input class="form-control" type="text" name="cor" id="cor" 
                               value="${veiculo.cor}" size="100" maxlength="100"/>
                    </div>
                    <div class="form-group">
                        <div class="form-line row">
                            <div class="col-sm">
                                <label>Data do Veiculo</label>
                                <input class="form-control" type="date" name="ano" id="ano" 
                                       value="${veiculo.ano}"/>
                            </div>
                          
                        </div>
                    </div>
                  <div class="form-group">
                      <label>Tipo de Veiculo</label>
                    <select name="idtipo" id="idtipo">
                        <option value="">Selecione</option>
                        <c:forEach var="tipo" items="${tipos}">
                            <option value="${tipo.idTipo}" ${veiculo.tipo.idTipo == tipo.idTipo ? "selected" : ""}>
                                ${tipo.categoria}
                            </option>
                        </c:forEach>
                    </select>
                  </div>
                            <div class="form-group">
                      <label>Modelo</label>
                    <select name="idmodelo" id="idmodelo">
                        <option value="">Selecione</option>
                        <c:forEach var="modelo" items="${modelos}">
                            <option value="${modelo.idModelo}" ${veiculo.modelo.idModelo == modelo.idModelo ? "selected" : ""}>
                                ${modelo.nomeModelo}
                            </option>
                        </c:forEach>
                    </select>
                  </div>
                            
                  <div class="form-group">
                      <label>Marca de Veiculo</label>
                    <select name="idmarca" id="idmarca">
                        <option value="">Selecione</option>
                        <c:forEach var="marca" items="${marcas}">
                            <option value="${marca.idMarca}" ${veiculo.marca.idMarca == marca.idMarca ? "selected" : ""}>
                                ${marca.nomeMarca}
                            </option>
                        </c:forEach>
                    </select>
                  </div>
                    <!-- Botão de Confirmação --> 
                    <div class="form-group">
                        <button class="btn btn-success" type="submit" id="submit" onclick="validarCampos()">
                            Salvar Veiculo</button>
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
     $(document).ready(function() {
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
    
    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("placaveiculo").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a placa do veiculo!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#placaVeiculo").focus();
        } 
        else if (document.getElementById("cor").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a cor do veiculo!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#cor").focus();
        } else if (document.getElementById("ano").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o ano do veiculo!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#ano").focus();
        } else if (document.getElementById("idtipo").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o tipo do veiculo!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idtipo").focus();
        } else if (document.getElementById("idmodelo").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o modelo do veiculo!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idmodelo").focus();
        } else if (document.getElementById("idmarca").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o marca do veiculo!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#idmarca").focus();
        }  else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        var target = document.getElementById("target").src;
        $.ajax({
            type: 'post',
            url: 'VeiculoCadastrar',
            data: {
                idveiculo: $('#idveiculo').val(),
                placaveiculo: $('#placaveiculo').val(),
                cor: $('#cor').val(),
                ano: $('#ano').val(),
                idtipo: $('#idtipo').val(),
                idmodelo: $('#idmodelo').val(),
                idmarca: $('#idmarca').val(),
                imagemveiculo: target
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
                                text: 'Veiculo gravada com sucesso!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/VeiculoListar";
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar a veiculo!',
                                showConfirmButton: true,
                                timer: 10000
                            }).then(function(){
                                window.location.href = "${pageContext.request.contextPath}/VeiculoListar";
                            })
                        }
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/VeiculoListar";
                    }
        });
    }
    
   function uploadFile() {
        //pega o componente html image 
        var target = document.getElementById("target");
        //limpa o image
        target.src = "";
        //abre a janela para seleção do arquivo.
        var file = document.querySelector("input[type='file']").files[0];
        //verifica se o arquivo existe
        if (file) {
            //faz a leitura do arquivo da imagem
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onloadend = function () {
                //atribui a imagem do arquivo ao componente html image
                target.src = reader.result;
            };
        } else {
            target.src = "";
        }
    }
    
</script>   
<jsp:include page="/footer.jsp"/>
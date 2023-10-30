<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menuLogado.jsp"/>

        <!-- Page Heading -->
        <div class="row">
            <div class="col-12">
                <div class="page-title-box">
                    <h4 class="page-title float-left">Tipo</h4>

                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li class="breadcrumb-item"><a href="#">Cadastros</a></li>
                        <!--<li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/TipoListar">Tipos</a></li>-->
                        <li class="breadcrumb-item active">Tipos</li>
                    </ol>

                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Tipos</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered basic-datatable" id="datatable" width="98%" cellspacing="0">
                        <thead>                        
                            <tr>
                                <th align="left">ID</th>                    
                                <th align="left">Categoria</th>
                                <th align="left">Imagem</th>
                                <th align="right"></th>
                                <th align="right"></th>
                            </tr>
                        </thead>                                 
                        <tbody>
                            <c:forEach var="tipo" items="${tipos}">                    
                                <tr>
                                    <td align="left">${tipo.idTipo}</td>                    
                                    <td align="left">${tipo.categoria}</td>
                                    <td aling="left">
                            <img alt="imagem" class="img-thumbnail" 
                                 src="${tipo.imagem}" 
                                 name="target" id="target" width="170" heigth="200">
                            </td>
                                    <td align="center">
                                <a href="#" id="deletar" title="Excluir" onclick="deletar(${tipo.idTipo})">
                                        <button class="btn btn-group-lg btn-danger"/>Excluir</button></a>
                            </td>    
                                    <td align="center">
                                        <a href=
                      "${pageContext.request.contextPath}/TipoCarregar?idTipo=${tipo.idTipo}">
                                            <button class="btn btn-warning">Alterar</button></a>
                                    </td>
                                </tr>                
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>    
        </div>        
        
       <a class="btn btn-success mb-4" href="${pageContext.request.contextPath}/TipoNovo">
        <i class="fas fa-sticky-note"></i>
        <strong>Novo</strong>
    </a>    

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
    
     function deletar(codigo) {
         console.log("Entrei em fun��o deletar");
                            var id = codigo;
                            console.log(codigo);
                            Swal.fire({
                                title: 'Voc� tem certeza?',
                                text: "Voc� n�o pode recuperar depois!",
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonColor: '#3085d6',
                                cancelButtonColor: '#d33',
                                confirmButtonText: 'Sim, apague o tipo!',
                                cancelButtonText: 'Cancelar'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    $.ajax({
                                        type: 'post',
                                        url: '${pageContext.request.contextPath}/TipoExcluir',
                                        data: {
                                            idTipo: id
                                        },
                                        success:
                                            function (data) {
                                                if (data == 1) {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'success',
                                                        title: 'Sucesso',
                                                        text: 'Tipo exclu�do com sucesso!',
                                                        showConfirmButton: false,
                                                        timer: 2000
                                                    }).then(function(){
                                                        window.location.href = "${pageContext.request.contextPath}/TipoListar";
                                                    })
                                                }
                                                else if (data == 2) {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'warning',
                                                        title: 'Aten��o',
                                                        text: 'H� cidades vinculadas a esse tipo! N�o � poss�vel exclu�-lo!',
                                                        showConfirmButton: false,
                                                        timer: 3500
                                                    }).then(function(){
                                                        window.location.href = "${pageContext.request.contextPath}/TipoListar";
                                                    })
                                                }
                                                else {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'error',
                                                        title: 'Erro',
                                                        text: 'N�o foi poss�vel excluir o tipo!',
                                                        showConfirmButton: false,
                                                        timer: 2000
                                                    }).then(function(){
                                                        window.location.href = "${pageContext.request.contextPath}/TipoListar";
                                                    })
                                                }
                                            },
                                        error:
                                            function (data) {
                                                window.location.href = "${pageContext.request.contextPath}/TipoListar";
                                            }
                                    });
                                };
                            });
                        }
                </script>
 <%@include file="/footer.jsp"%>
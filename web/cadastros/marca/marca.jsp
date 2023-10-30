<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menuLogado.jsp"/>

        <!-- Page Heading -->
        <div class="row">
            <div class="col-12">
                <div class="page-title-box">
                    <h4 class="page-title float-left">Marca</h4>

                    <ol class="breadcrumb float-right">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/">Home</a></li>
                        <li class="breadcrumb-item"><a href="#">Cadastros</a></li>
                        <!--<li class="breadcrumb-item">
                    <a href="${pageContext.request.contextPath}/MarcaListar">Marcas</a></li>-->
                        <li class="breadcrumb-item active">Marcas</li>
                    </ol>

                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Marcas</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered basic-datatable" id="datatable" width="98%" cellspacing="0">
                        <thead>                        
                            <tr>
                                <th align="left">ID</th>                    
                                <th align="left">Nome</th>
                                <th align="left">Imagem</th>
                                <th align="right"></th>
                                <th align="right"></th>
                            </tr>
                        </thead>                                 
                        <tbody>
                            <c:forEach var="marca" items="${marcas}">                    
                                <tr>
                                    <td align="left">${marca.idMarca}</td>                    
                                    <td align="left">${marca.nomeMarca}</td>
                                    <td aling="left">
                                        <img alt="imagem" class="img-thumbnail" 
                                             src="${marca.imagemMarca}" 
                                             name="target" id="target" width="170" heigth="200">
                                    </td>
                                    <td align="center">
                                <a href="#" id="deletar" title="Excluir" onclick="deletar(${marca.idMarca})">
                                        <button class="btn btn-group-lg btn-danger"/>Excluir</button></a>
                            </td>    
                                    <td align="center">
                                        <a href=
                      "${pageContext.request.contextPath}/MarcaCarregar?idMarca=${marca.idMarca}">
                                            <button class="btn btn-warning">Alterar</button></a>
                                    </td>
                                </tr>                
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>    
        </div>        
        
       <a class="btn btn-success mb-4" href="${pageContext.request.contextPath}/MarcaNovo">
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
                                confirmButtonText: 'Sim, apague o marca!',
                                cancelButtonText: 'Cancelar'
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    $.ajax({
                                        type: 'post',
                                        url: '${pageContext.request.contextPath}/MarcaExcluir',
                                        data: {
                                            idMarca: id
                                        },
                                        success:
                                            function (data) {
                                                if (data == 1) {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'success',
                                                        title: 'Sucesso',
                                                        text: 'Marca excluído com sucesso!',
                                                        showConfirmButton: false,
                                                        timer: 2000
                                                    }).then(function(){
                                                        window.location.href = "${pageContext.request.contextPath}/MarcaListar";
                                                    })
                                                }
                                                else if (data == 2) {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'warning',
                                                        title: 'Atenção',
                                                        text: 'Há cidades vinculadas a esse marca! Não é possível excluí-lo!',
                                                        showConfirmButton: false,
                                                        timer: 3500
                                                    }).then(function(){
                                                        window.location.href = "${pageContext.request.contextPath}/MarcaListar";
                                                    })
                                                }
                                                else {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'error',
                                                        title: 'Erro',
                                                        text: 'Não foi possível excluir o marca!',
                                                        showConfirmButton: false,
                                                        timer: 2000
                                                    }).then(function(){
                                                        window.location.href = "${pageContext.request.contextPath}/MarcaListar";
                                                    })
                                                }
                                            },
                                        error:
                                            function (data) {
                                                window.location.href = "${pageContext.request.contextPath}/MarcaListar";
                                            }
                                    });
                                };
                            });
                        }
                </script>
 <%@include file="/footer.jsp"%>
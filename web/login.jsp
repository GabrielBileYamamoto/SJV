<!DOCTYPE html>
<html lang="pt-br">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> Login - SJV </title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-danger">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-5 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Bem vindo ao SJV!</h1>
                            </div>
                            <form class="user">
                                <div class="form-group">
                                    <input type="text" id="login" name="login" required=""
                                        onblur="BuscarUsuariosPorNome()" input type="email" class="form-control form-control-user"
                                        aria-describedby="emailHelp"
                                        placeholder="Digite seu Login:">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-user" required="" name="senha" id="senha"
                                        id="exampleInputPassword" placeholder="Digite sua senha:">
                                </div>
                                <div>
                                    <label for="tipo">Tipo Usuário</label>
                                    <select name="tipo" id="tipo" tabindex="3">
                                        <option value="">Selecione</option>
                                    </select>
                                </div>
                                <div><button id="submit"class="btn btn-danger btn-user btn-block">Login</button></div>
                                </br>
                                <div id="erro"></div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

<script>
           $(document).ready(function () {
                console.log("entrei função");
                $("#submit").on("click", function () {
                    console.log("entrei click submit");
                    if ($('#login').val() === "") {
                        $("#login").focus();
                        $("#erro").html("<div>Por favor, preencher o campo usuário.</div>").show();
                        tempo();
                        return false;
                    }
                    if ($('#senha').val() === "") {
                        $("#senha").focus();
                        $("#erro").html("<div>Por favor, preencher o campo senha.</div>").show();
                        tempo();
                        return false;
                    }
                    $("#submit").prop("disabled", true);
                    $("#submit").html('<i class="fa fa-spinner" aria-hidden="true"></i> Aguarde...');

                    $.ajax({
                        type: 'post',
                        url: 'UsuarioLogar',
                        data: {
                            acao: "login",
                            login: $('#login').val(),
                            senha: $('#senha').val(),
                            tipo: $('#tipo').val()
                        },
                        success:
                                function (data) {
                                    if (data == 'ok') {
                                        window.location.href = "${pageContext.request.contextPath}/homeLogado.jsp";
                                    } else {
                                        $('#submit').removeAttr('disabled');
                                        $("#submit").html('Entrar');
                        $("#wrapper_error").html("<div class='alert alert-danger'>Usuário ou senha incorreto.</div>").show();
                                        tempo();
                                    }
                                },
                        error:
                                function (data) {
                                    RefreshTable();
                                }
                    });
                });
                function tempo() {
                    setTimeout(function () {
                        $("#wrapper_error").hide();
                    }, 3000); // 3 segundos
                }
            });
            function BuscarUsuariosPorNome() 
            {
                usuario = '';
                console.log("entrei na function");
                $('#tipo').empty(); //..limpa select de tipo de usuario.
                loginUsuario = $('#login').val();
                console.log(loginUsuario);
                if (loginUsuario != 'null')
                {
                    console.log("vai rodar o ajax");
                    //console.log(idEst);
                    url = "UsuarioBuscarPorLogin?loginusuario="+loginUsuario;
                    //console.log(url);
                    $.getJSON(url, function (result) {
                        //alert(result);
                        $.each(result, function (index, value) {
                            $('#tipo').append(
                                    '<option id="usuario_' + value.idUsuario 
                                    + '"value="' + value.tipo + '">' 
                                    + value.tipo + '</option>'
                                    );
                            if(usuario !== ''){
                                $('#usuario_'+usuario).prop({selected: true});
                            }else{
                                $('#usuario_').prop({selected: true});
                            }
                        });
                        console.log("montou o select");
                    }
                    ).fail(function (obj, textStatus, error) {
                        alert('Erro do servidor: ' + textStatus + ', ' + error);
                    });
                }
            }
        </script>

</html>
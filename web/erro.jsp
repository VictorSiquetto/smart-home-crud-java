<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Erro - Smart Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">

        <div class="card shadow text-center" style="width: 100%; max-width: 500px;">
            <div class="card-header bg-danger text-white">
                <h4>Erro no Sistema</h4>
            </div>
            <div class="card-body py-5">

                <h3 class="text-secondary mb-4"><%= request.getAttribute("message") %></h3>

                <div class="d-grid gap-2 d-md-block">
                    <a href="index.html" class="btn btn-outline-primary">Voltar ao Cadastro</a>
                    <a href="controle_dispositivo?op=CONSULTAR+TODOS" class="btn btn-primary">Ver Lista de Aparelhos</a>
                </div>

            </div>
        </div>

    </div>
</body>
</html>

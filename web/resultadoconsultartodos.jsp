<%-- 
    Document   : resultadoconsultartodos
    Created on : 22 de mai. de 2026, 21:58:43
    Author     : User
--%>

<%@page import="model.Dispositivo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Lista de Dispositivos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function buscarTabela() {
            var input = document.getElementById("inputBusca");
            var filter = input.value.toUpperCase();
            var table = document.getElementById("tabelaDispositivos");
            var tr = table.getElementsByTagName("tr");

            for (var i = 1; i < tr.length; i++) {
                var mostrarLinha = false;
                var td = tr[i].getElementsByTagName("td");
                for (var j = 0; j < td.length; j++) {
                    if (td[j] && td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                        mostrarLinha = true;
                        break;
                    }
                }
                tr[i].style.display = mostrarLinha ? "" : "none";
            }
        }
    </script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Dispositivos Monitorados (Smart Home)</h2>
            <a href="index.html" class="btn btn-secondary">Voltar ao Cadastro</a>
        </div>
        
        <input type="text" id="inputBusca" onkeyup="buscarTabela()" class="form-control mb-3 shadow-sm" placeholder="Digite para pesquisar por aparelho, cômodo, marca...">

        <table class="table table-bordered table-hover shadow-sm bg-white" id="tabelaDispositivos">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Aparelho</th>
                    <th>Cômodo</th>
                    <th>Marca</th>
                    <th>Potência</th>
                    <th>Uso Diário</th>
                    <th>Voltagem</th>
                    <th>Status</th>
                    <th>Consumo Mensal</th>
                    <th>Observação</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Dispositivo> lista = (List<Dispositivo>) request.getAttribute("lista");
                    if (lista != null) {
                        for (Dispositivo d : lista) {
                            String classeLinha = (d.getConsumoMensal() > 50.0) ? "table-danger" : "table-success";
                %>
                <tr class="<%= classeLinha %>">
                    <td><%= d.getId() %></td>
                    <td><strong><%= d.getNome() %></strong></td>
                    <td><%= d.getComodo() %></td>
                    <td><%= d.getMarca() != null ? d.getMarca() : "" %></td>
                    <td><%= d.getPotencia() %> W</td>
                    <td><%= d.getHorasUso() %> h/dia</td>
                    <td><%= d.getVoltagem() %>v</td>
                    <td><%= d.getStatus() %></td>
                    <td><strong><%= String.format("%.2f", d.getConsumoMensal()) %> kWh</strong></td>
                    <td><%= d.getObservacao() != null ? d.getObservacao() : "" %></td>
                    <td>
                        <div class="d-flex gap-2">
                            <form action="controle_dispositivo" method="POST">
                                <input type="hidden" name="txtId" value="<%= d.getId() %>">
                                <input type="submit" name="op" value="ATUALIZAR" class="btn btn-sm btn-warning">
                            </form>
                            
                            <form action="controle_dispositivo" method="POST" onsubmit="return confirm('Tem certeza que deseja deletar este dispositivo?');">
                                <input type="hidden" name="txtId" value="<%= d.getId() %>">
                                <input type="submit" name="op" value="DELETAR" class="btn btn-sm btn-danger">
                            </form>
                        </div>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="11" class="text-center text-muted">Nenhum dispositivo encontrado ou lista vazia.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

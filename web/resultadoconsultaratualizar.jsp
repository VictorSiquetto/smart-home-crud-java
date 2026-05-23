<%-- 
    Document   : resultadoconsultaratualizar
    Created on : 22 de mai. de 2026, 22:06:52
    Author     : User
--%>

<%@page import="model.Dispositivo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar Dispositivo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-warning text-dark">
                <h2>Atualizar Dispositivo</h2>
            </div>
            <div class="card-body">
                <% Dispositivo d = (Dispositivo) request.getAttribute("d"); %>
                <form action="controle_dispositivo" method="POST">
                    
                    <input type="hidden" name="txtId" value="<%= d.getId() %>">
                    
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label>Nome do Aparelho:</label>
                            <input type="text" name="txtNome" class="form-control" required value="<%= d.getNome() %>">
                        </div>
                        <div class="col-md-6">
                            <label>Cômodo:</label>
                            <input type="text" name="txtComodo" class="form-control" required value="<%= d.getComodo() %>">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <label>Marca:</label>
                            <input type="text" name="txtMarca" class="form-control" value="<%= d.getMarca() != null ? d.getMarca() : "" %>">
                        </div>
                        <div class="col-md-4">
                            <label>Potência (Watts):</label>
                            <input type="number" step="1" name="txtPotencia" class="form-control" required value="<%= d.getPotencia() %>">
                        </div>
                        <div class="col-md-4">
                            <label>Horas de uso/dia:</label>
                            <input type="number" step="1" name="txtHorasUso" class="form-control" required value="<%= d.getHorasUso() %>">
                        </div>
                    </div>
                    <div class="row mb-4">
                        <div class="col-md-4">
                            <label>Voltagem:</label>
                            <select name="txtVoltagem" class="form-select">
                                <option value="110" <%= d.getVoltagem() == 110 ? "selected" : "" %>>110v</option>
                                <option value="220" <%= d.getVoltagem() == 220 ? "selected" : "" %>>220v</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label>Status Inicial:</label>
                            <select name="txtStatus" class="form-select">
                                <option value="Ligado" <%= "Ligado".equals(d.getStatus()) ? "selected" : "" %>>Ligado</option>
                                <option value="Desligado" <%= "Desligado".equals(d.getStatus()) ? "selected" : "" %>>Desligado</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label>Observação:</label>
                            <input type="text" name="txtObservacao" class="form-control" value="<%= d.getObservacao() != null ? d.getObservacao() : "" %>">
                        </div>
                    </div>
                    <hr>
                    <input type="submit" name="op" value="EFETIVAR ATUALIZACAO" class="btn btn-warning">
                    <a href="controle_dispositivo?op=CONSULTAR+TODOS" class="btn btn-secondary">Cancelar</a>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

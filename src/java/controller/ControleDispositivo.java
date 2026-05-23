package controller;

import DAO.DispositivoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dispositivo;

@WebServlet(name = "ControleDispositivo", urlPatterns = {"/controle_dispositivo"})
public class ControleDispositivo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String op = request.getParameter("op");
        DispositivoDAO dao = new DispositivoDAO();
        Dispositivo d = new Dispositivo();

        if ("CADASTRAR".equals(op)) {
            d.setNome(request.getParameter("txtNome"));
            d.setComodo(request.getParameter("txtComodo"));
            d.setMarca(request.getParameter("txtMarca"));
            d.setPotencia(Double.parseDouble(request.getParameter("txtPotencia")));
            d.setHorasUso(Double.parseDouble(request.getParameter("txtHorasUso")));
            d.setVoltagem(Integer.parseInt(request.getParameter("txtVoltagem")));
            d.setStatus(request.getParameter("txtStatus"));
            d.setObservacao(request.getParameter("txtObservacao"));
            d.calcularConsumoMensal();

            try {
                dao.cadastrar(d);
                request.setAttribute("message", "Dispositivo cadastrado com sucesso!");
                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                request.setAttribute("message", "Erro ao cadastrar: " + ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

        } else if ("DELETAR".equals(op)) {
            d.setId(Integer.parseInt(request.getParameter("txtId")));

            try {
                dao.deletar(d);
                List<Dispositivo> lista = dao.consultarTodos();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("resultadoconsultartodos.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                request.setAttribute("message", "Erro ao deletar: " + ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

        } else if ("CONSULTAR TODOS".equals(op)) {
            try {
                List<Dispositivo> lista = dao.consultarTodos();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("resultadoconsultartodos.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                request.setAttribute("message", "Erro ao consultar: " + ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

        } else if ("ATUALIZAR".equals(op)) {
            int id = Integer.parseInt(request.getParameter("txtId"));

            try {
                d = dao.consultarById(id);
                request.setAttribute("d", d);
                request.getRequestDispatcher("resultadoconsultaratualizar.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                request.setAttribute("message", "Erro ao buscar dispositivo: " + ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }

        } else if ("EFETIVAR ATUALIZACAO".equals(op)) {
            d.setId(Integer.parseInt(request.getParameter("txtId")));
            d.setNome(request.getParameter("txtNome"));
            d.setComodo(request.getParameter("txtComodo"));
            d.setMarca(request.getParameter("txtMarca"));
            d.setPotencia(Double.parseDouble(request.getParameter("txtPotencia")));
            d.setHorasUso(Double.parseDouble(request.getParameter("txtHorasUso")));
            d.setVoltagem(Integer.parseInt(request.getParameter("txtVoltagem")));
            d.setStatus(request.getParameter("txtStatus"));
            d.setObservacao(request.getParameter("txtObservacao"));
            d.calcularConsumoMensal();

            try {
                dao.atualizar(d);
                request.setAttribute("message", "Dispositivo atualizado com sucesso!");
                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                request.setAttribute("message", "Erro ao atualizar: " + ex.getMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controle de Dispositivos da Smart Home";
    }
}

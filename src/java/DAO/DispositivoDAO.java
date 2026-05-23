package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Dispositivo;
import util.Conexao;

public class DispositivoDAO {

    public void cadastrar(Dispositivo d) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO dispositivos (nome, comodo, marca, potencia, horas_uso, voltagem, status, consumo_mensal, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, d.getNome());
            comando.setString(2, d.getComodo());
            comando.setString(3, d.getMarca());
            comando.setDouble(4, d.getPotencia());
            comando.setDouble(5, d.getHorasUso());
            comando.setInt(6, d.getVoltagem());
            comando.setString(7, d.getStatus());
            comando.setDouble(8, d.getConsumoMensal());
            comando.setString(9, d.getObservacao());
            comando.executeUpdate();
        }
    }

    public void deletar(Dispositivo d) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM dispositivos WHERE id = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, d.getId());
            comando.executeUpdate();
        }
    }

    public void atualizar(Dispositivo d) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE dispositivos SET nome=?, comodo=?, marca=?, potencia=?, horas_uso=?, voltagem=?, status=?, consumo_mensal=?, observacao=? WHERE id=?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setString(1, d.getNome());
            comando.setString(2, d.getComodo());
            comando.setString(3, d.getMarca());
            comando.setDouble(4, d.getPotencia());
            comando.setDouble(5, d.getHorasUso());
            comando.setInt(6, d.getVoltagem());
            comando.setString(7, d.getStatus());
            comando.setDouble(8, d.getConsumoMensal());
            comando.setString(9, d.getObservacao());
            comando.setInt(10, d.getId());
            comando.executeUpdate();
        }
    }

    public List<Dispositivo> consultarTodos() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM dispositivos";
        List<Dispositivo> lista = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql);
             ResultSet rs = comando.executeQuery()) {

            while (rs.next()) {
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setNome(rs.getString("nome"));
                d.setComodo(rs.getString("comodo"));
                d.setMarca(rs.getString("marca"));
                d.setPotencia(rs.getDouble("potencia"));
                d.setHorasUso(rs.getDouble("horas_uso"));
                d.setVoltagem(rs.getInt("voltagem"));
                d.setStatus(rs.getString("status"));
                d.setConsumoMensal(rs.getDouble("consumo_mensal"));
                d.setObservacao(rs.getString("observacao"));
                lista.add(d);
            }
        }
        return lista;
    }

    public Dispositivo consultarById(int id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM dispositivos WHERE id = ?";
        Dispositivo d = new Dispositivo();

        try (Connection con = Conexao.getConexao();
             PreparedStatement comando = con.prepareStatement(sql)) {

            comando.setInt(1, id);

            try (ResultSet rs = comando.executeQuery()) {
                if (rs.next()) {
                    d.setId(rs.getInt("id"));
                    d.setNome(rs.getString("nome"));
                    d.setComodo(rs.getString("comodo"));
                    d.setMarca(rs.getString("marca"));
                    d.setPotencia(rs.getDouble("potencia"));
                    d.setHorasUso(rs.getDouble("horas_uso"));
                    d.setVoltagem(rs.getInt("voltagem"));
                    d.setStatus(rs.getString("status"));
                    d.setConsumoMensal(rs.getDouble("consumo_mensal"));
                    d.setObservacao(rs.getString("observacao"));
                }
            }
        }
        return d;
    }
}

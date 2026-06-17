package bloco_notas.model;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class NotasDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirNota(Notas nota) {

        String sql =
                "INSERT INTO nota(titulo, conteudo, usuario_id) VALUES (?, ?, ?::uuid)";

        Object[] obj = new Object[3];

        obj[0] = nota.getTitulo();
        obj[1] = nota.getConteudo();
        obj[2] = nota.getUsuarioId();

        jdbc.update(sql, obj);
    }

    public Notas mostrarNotas(String uuid) {

        String sql =
                "SELECT * FROM nota WHERE id=?::uuid";

        return Notas.converter(
                jdbc.queryForMap(sql, uuid));
    }

    public ArrayList<Notas> listarNotas(String usuarioId) {

        String sql =
                "SELECT * FROM nota WHERE usuario_id=?::uuid ORDER BY id DESC";

        return Notas.converterTodos(
                jdbc.queryForList(sql, usuarioId));
    }

    public void atualizarNota(Notas nota) {

        String sql =
                "UPDATE nota SET titulo=?, conteudo=? WHERE id=?::uuid";

        Object[] obj = new Object[3];

        obj[0] = nota.getTitulo();
        obj[1] = nota.getConteudo();
        obj[2] = nota.getId();

        jdbc.update(sql, obj);
    }

    public void excluirNota(String id) {

        String sql =
                "DELETE FROM nota WHERE id=?::uuid";

        jdbc.update(sql, id);
    }

    public void favoritarNota(String id) {

    String sql =
            "UPDATE nota SET favorita = NOT favorita WHERE id=?::uuid";

    jdbc.update(sql, id);
    }

    public ArrayList<Notas> listarFavoritas(String usuarioId) {

    String sql =
        """
        SELECT *
        FROM nota
        WHERE usuario_id=?::uuid
        AND favorita = true
        ORDER BY id DESC
        """;

    return Notas.converterTodos(
            jdbc.queryForList(sql, usuarioId));
    }
}
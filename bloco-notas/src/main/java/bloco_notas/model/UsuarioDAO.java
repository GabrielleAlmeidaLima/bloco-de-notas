package bloco_notas.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class UsuarioDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuario(nome, senha) VALUES (?, ?)";

        jdbc.update(
                sql,
                usuario.getNome(),
                usuario.getSenha()
        );
    }

    public Usuario buscarPorNome(String nome) {

        String sql = "SELECT * FROM usuario WHERE nome=?";

        try {
            return Usuario.converter(
                    jdbc.queryForMap(sql, nome)
            );
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validarLogin(String nome, String senha) {

        String sql = """
                SELECT COUNT(*)
                FROM usuario
                WHERE nome = ?
                AND senha = ?
                """;

        Integer qtd = jdbc.queryForObject(
                sql,
                Integer.class,
                nome,
                senha
        );

        return qtd != null && qtd > 0;
    }
}
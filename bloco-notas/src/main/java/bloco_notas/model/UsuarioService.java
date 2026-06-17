package bloco_notas.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    public void cadastrar(Usuario usuario) {
        usuarioDAO.cadastrar(usuario);
    }

    public Usuario buscarPorNome(String nome) {
        return usuarioDAO.buscarPorNome(nome);
    }

    public boolean validarLogin(String nome, String senha) {
        return usuarioDAO.validarLogin(nome, senha);
    }
}
package bloco_notas.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class notasService {

    @Autowired
    NotasDAO NotasDAO;

    public void inserirNota(Notas notas) {
        NotasDAO.inserirNota(notas);
    }

    public Notas mostrarNotas(String uuid) {
        return NotasDAO.mostrarNotas(uuid);
    }

    public ArrayList<Notas> listarNotas(String usuarioId) {
        return NotasDAO.listarNotas(usuarioId);
    }

    public ArrayList<Notas> listarFavoritas(String usuarioId) {
        return NotasDAO.listarFavoritas(usuarioId);
    }

    public void atualizarNota(Notas nota) {
        NotasDAO.atualizarNota(nota);
    }

    public void excluirNota(String id) {
        NotasDAO.excluirNota(id);
    }

    public void favoritarNota(String id) {
        NotasDAO.favoritarNota(id);
    }
}
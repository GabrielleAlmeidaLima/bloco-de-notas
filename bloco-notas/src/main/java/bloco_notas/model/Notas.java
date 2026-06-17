package bloco_notas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Notas {

    private String id;
    private String titulo;
    private String conteudo;
    private String usuarioId;
    private boolean favorita;

    public Notas() {
    }

    // SELECT
    public Notas(
            String id,
            String titulo,
            String conteudo,
            String usuarioId) {

        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuarioId = usuarioId;
    }

    // INSERT
    public Notas(
            String titulo,
            String conteudo,
            String usuarioId) {

        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuarioId = usuarioId;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public static Notas converter(
            Map<String, Object> registro) {

        String titulo =
                (String) registro.get("titulo");

        String conteudo =
                (String) registro.get("conteudo");

        UUID id =
                (UUID) registro.get("id");

        UUID usuarioId =
                (UUID) registro.get("usuario_id");

        Boolean favorita =
                (Boolean) registro.get("favorita");

        Notas nota = new Notas(
                id.toString(),
                titulo,
                conteudo,
                usuarioId.toString());

        if (favorita != null) {
            nota.setFavorita(favorita);
        }

        return nota;
    }

    public static ArrayList<Notas> converterTodos(
            List<Map<String, Object>> registros) {

        ArrayList<Notas> aux =
                new ArrayList<>();

        for (Map<String, Object> registro : registros) {
            aux.add(converter(registro));
        }

        return aux;
    }
}
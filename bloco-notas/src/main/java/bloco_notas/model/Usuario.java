package bloco_notas.model;

import java.util.UUID;

public class Usuario {

    private String id;
    private String nome;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Usuario converter(java.util.Map<String, Object> registro) {

        UUID id = (UUID) registro.get("id");
        String nome = (String) registro.get("nome");
        String senha = (String) registro.get("senha");

        return new Usuario(
                id.toString(),
                nome,
                senha
        );
    }
} 
package bloco_notas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bloco_notas.model.Notas;
import bloco_notas.model.Usuario;
import bloco_notas.model.UsuarioService;
import bloco_notas.model.notasService;
import jakarta.servlet.http.HttpSession;

@Controller
public class paginaController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute(
                "usuario",
                new Usuario());

        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(
            @ModelAttribute Usuario usuario,
            HttpSession session,
            Model model) {

        UsuarioService service =
                context.getBean(UsuarioService.class);

        boolean valido =
                service.validarLogin(
                        usuario.getNome(),
                        usuario.getSenha());

        if (valido) {

            Usuario usuarioLogado =
                    service.buscarPorNome(
                            usuario.getNome());

            session.setAttribute(
                    "usuarioLogado",
                    usuarioLogado);

            return "redirect:/notas";
        }

        model.addAttribute(
                "erro",
                "Usuário ou senha inválidos");

        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {

        model.addAttribute(
                "usuario",
                new Usuario());

        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarCadastro(
            @ModelAttribute Usuario usuario,
            Model model) {

        UsuarioService service =
                context.getBean(UsuarioService.class);

        if (service.buscarPorNome(
                usuario.getNome()) != null) {

            model.addAttribute(
                    "erro",
                    "Usuário já existe");

            return "cadastro";
        }

        service.cadastrar(usuario);

        return "redirect:/login";
    }

    @GetMapping("/notas")
    public String formanotacao(
            HttpSession session,
            Model model) {

        Usuario usuarioLogado =
                (Usuario) session.getAttribute(
                        "usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        notasService cs =
                context.getBean(notasService.class);

        ArrayList<Notas> notas =
                cs.listarNotas(
                        usuarioLogado.getId());

        model.addAttribute(
                "anotacao",
                new Notas());

        model.addAttribute(
                "notas",
                notas);

        return "notas";
    }

    @PostMapping("/notas")
    public String postCliente(
            @ModelAttribute Notas notas,
            HttpSession session,
            Model model) {

        Usuario usuarioLogado =
                (Usuario) session.getAttribute(
                        "usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        notas.setUsuarioId(
                usuarioLogado.getId());

        notasService cs =
                context.getBean(notasService.class);

        cs.inserirNota(notas);

        return "redirect:/notas";
    }

    @GetMapping("/favoritos")
    public String favoritos(
            HttpSession session,
            Model model) {

        Usuario usuarioLogado =
                (Usuario) session.getAttribute(
                        "usuarioLogado");

        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        notasService cs =
                context.getBean(notasService.class);

        ArrayList<Notas> favoritas =
                cs.listarFavoritas(
                        usuarioLogado.getId());

        model.addAttribute(
                "notas",
                favoritas);

        return "favoritos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirNota(
            @PathVariable String id) {

        notasService cs =
                context.getBean(notasService.class);

        cs.excluirNota(id);

        return "redirect:/notas";
    }

    @GetMapping("/editar/{id}")
    public String editarNota(
            @PathVariable String id,
            HttpSession session,
            Model model) {

        notasService cs =
                context.getBean(notasService.class);

        Notas nota =
                cs.mostrarNotas(id);

        Usuario usuarioLogado =
                (Usuario) session.getAttribute(
                        "usuarioLogado");

        ArrayList<Notas> notas =
                cs.listarNotas(
                        usuarioLogado.getId());

        model.addAttribute(
                "anotacao",
                nota);

        model.addAttribute(
                "notas",
                notas);

        model.addAttribute(
                "modoEdicao",
                true);

        return "notas";
    }

    @PostMapping("/atualizar")
    public String atualizarNota(
            @ModelAttribute Notas nota) {

        notasService cs =
                context.getBean(notasService.class);

        cs.atualizarNota(nota);

        return "redirect:/notas";
    }

    @GetMapping("/favoritar/{id}")
    public String favoritarNota(
            @PathVariable String id) {

        notasService cs =
                context.getBean(notasService.class);

        cs.favoritarNota(id);

        return "redirect:/notas";
    }
}
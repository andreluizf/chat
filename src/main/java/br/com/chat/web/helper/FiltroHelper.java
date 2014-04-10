package br.com.chat.web.helper;

import br.com.webchat.model.Usuario;
import br.com.webchat.repository.usuario.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class FiltroHelper {

    @Inject
    UsuarioRepository repository;

    public List<String> filtroNome(String nome) {
        List<String> lists = new ArrayList<>();
        for (Map.Entry<String, Usuario> en : repository.findAll().entrySet()) {
            Object object = en.getKey();
            if (en.getValue().getNome().contains(nome)) {
                lists.add(en.getValue().getNome());
            }
        }
        return lists;
    }

    public List<String> filtroCidade(String nome) {
        List<String> lists = new ArrayList<>();
        for (Map.Entry<String, Usuario> en : repository.findAll().entrySet()) {
            Object object = en.getKey();
            if (en.getValue().getCidade().contains(nome)) {
                lists.add(en.getValue().getNome());
            }
        }
        return lists;
    }

    public List<String> filtroApelido(String apelido) {
        List<String> lists = new ArrayList<>();
        for (Map.Entry<String, Usuario> en : repository.findAll().entrySet()) {
            Object object = en.getKey();
            if (en.getValue().getApelido().contains(apelido)) {
                lists.add(en.getValue().getNome());
            }
        }
        return lists;
    }

    public List<String> filtroEmail(String email) {
        List<String> lists = new ArrayList<>();
        for (Map.Entry<String, Usuario> en : repository.findAll().entrySet()) {
            Object object = en.getKey();
            if (en.getValue().getEmail().contains(email)) {
                lists.add(en.getValue().getNome());
            }
        }
        return lists;
    }
}

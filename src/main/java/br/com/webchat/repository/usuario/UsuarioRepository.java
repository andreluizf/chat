package br.com.webchat.repository.usuario;

import br.com.webchat.model.Usuario;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "usuarioRepository")
@LocalBean
public class UsuarioRepository {

    static Map<String, Usuario> lists = new HashMap<String, Usuario>();

    public UsuarioRepository() {
    }

    public Map<String, Usuario> findAll() {
        return lists;
    }

    public Usuario findUser(String nome) {
        return lists.get(nome);
    }

    public void add(Usuario p) {
        lists.put(p.getNome(), p);
    }
}

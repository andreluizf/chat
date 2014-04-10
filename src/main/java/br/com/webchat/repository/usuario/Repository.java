

package br.com.webchat.repository.usuario;

import br.com.webchat.model.Usuario;
import java.util.List;
import javax.ejb.Remote;

public interface Repository {

    public void saveOrUpdate(Usuario modelo);

    public void delete(Usuario modelo);

    public List<Usuario> findAll();

    public Usuario findByApelido(String apelido);
    
}
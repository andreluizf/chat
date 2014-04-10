
package br.com.webchat.repository.usuario;

import br.com.webchat.model.Usuario;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "usuarioImpl")
@LocalBean
public class UsuarioImpl implements Repository{
    @PersistenceContext(unitName = "webChat")
    EntityManager em;
  
    @Override
    public void saveOrUpdate(Usuario modelo) {
        try {
            em.persist(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    @Override
    public void delete(Usuario modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario findByApelido(String nome) {
        try {
             return (Usuario) em.createQuery("select c from Usuario c where c.nome= :nome",Usuario.class).setParameter("nome", nome.trim()).getSingleResult();
        } catch (Exception e) {
            return null;
        }
      
    }
   

    
}

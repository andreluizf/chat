package br.com.webchat.repository.message;

import br.com.webchat.model.Message;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "messageImpl")
@LocalBean
public class MessageImpl implements RepositoryMessage {

    @PersistenceContext(unitName = "webChat")
    EntityManager em;

    @Override
    public void saveOrUpdate(Message modelo) {
        em.persist(modelo);
    }

    @Override
    public void delete(Message modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> findAllByUser(Integer id) {
       return em.createQuery("select uTo.nome from Message m join m.fromUser  ufrom join m.toUser uTo where ufrom.id = :id GROUP BY uTo.nome ,m.id ORDER BY m.id DESC").setParameter("id", id).getResultList();
    }

}

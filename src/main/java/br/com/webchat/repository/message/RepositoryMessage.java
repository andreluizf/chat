

package br.com.webchat.repository.message;

import br.com.webchat.model.Message;
import java.util.List;


public interface RepositoryMessage {
     public void saveOrUpdate(Message modelo);

    public void delete(Message modelo);

    public List<String> findAllByUser(Integer id);
}

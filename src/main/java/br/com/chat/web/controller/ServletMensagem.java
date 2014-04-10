package br.com.chat.web.controller;

import br.com.webchat.model.Message;
import br.com.webchat.model.Usuario;
import br.com.webchat.repository.message.MessageImpl;
import br.com.webchat.repository.usuario.UsuarioImpl;
import br.com.webchat.repository.usuario.UsuarioRepository;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/mensagem", asyncSupported = true)
public class ServletMensagem extends HttpServlet {

    @Inject
    UsuarioRepository repository;
    @Inject
    Message message;

    @EJB(beanName = "usuarioImpl")
    UsuarioImpl ui;
    @EJB(beanName = "messageImpl")
    MessageImpl impl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toNome = req.getParameter("toNome");
        String fromNome = req.getParameter("fromNome");
        String msg = req.getParameter("msg");
        Usuario userTo = ui.findByApelido(toNome);
        Usuario userFrom = ui.findByApelido(fromNome);
        message.setFromUser(userFrom);
        message.setToUser(userTo);
        message.setMessage(msg);
        impl.saveOrUpdate(message);
        List<String> lists = impl.findAllByUser(userFrom.getId());
        String json = new Gson().toJson(lists);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);

    }

}

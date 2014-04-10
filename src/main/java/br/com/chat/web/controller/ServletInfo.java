package br.com.chat.web.controller;

import br.com.webchat.model.Usuario;
import br.com.webchat.repository.usuario.UsuarioRepository;
import com.google.gson.Gson;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/informacao", asyncSupported = true)
public class ServletInfo extends HttpServlet {

    @Inject
    UsuarioRepository repository;
    @Inject
    Usuario user ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        user = repository.findUser(nome);
        user.getData();
        String json = new Gson().toJson(user);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}

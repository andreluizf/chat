/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.web.controller;

import br.com.webchat.model.Usuario;
import br.com.webchat.repository.message.MessageImpl;
import br.com.webchat.repository.usuario.UsuarioImpl;
import br.com.webchat.repository.usuario.UsuarioRepository;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cadastro", asyncSupported = true)
public class ServletCadastro extends HttpServlet {

    @Inject
    UsuarioRepository repository;

    @EJB(beanName = "usuarioImpl")
    UsuarioImpl ui;
    @Inject
    Usuario user;
    @EJB(beanName = "messageImpl")
    MessageImpl impl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String data = request.getParameter("data");
        Date dt = null;
        if (!data.equals("")) {
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");

            try {
                dt = d.parse(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String apelido = request.getParameter("apelido");
        String frase = request.getParameter("frase");
        String cidade = request.getParameter("cidade");
        String email = request.getParameter("email");
        List<String> lists = new ArrayList<>();
        if (repository.findUser(nome) == null) {
            user = ui.findByApelido(nome);
            if (user == null) {
                user = new Usuario(nome, apelido, email, dt, cidade, frase);
                try {
                    ui.saveOrUpdate(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                lists = impl.findAllByUser(user.getId());

            }

            repository.add(user);

            String json = new Gson().toJson(lists);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            String json = new Gson().toJson("500");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

    }
}

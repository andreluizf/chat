package br.com.chat.web.controller;

import br.com.chat.web.helper.FiltroHelper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/filtro", asyncSupported = true)
public class ServletFiltro extends HttpServlet {

    @Inject
    FiltroHelper filtroHelper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String txt = req.getParameter("txt");
        String filtro = req.getParameter("filtro");
        List<String> lists = new ArrayList<>();
        switch (filtro.trim()) {
            case "nome":
                lists = filtroHelper.filtroNome(txt);
                break;
            case "apelido":
                lists = filtroHelper.filtroApelido(txt);
                break;
            case "email":
                lists = filtroHelper.filtroEmail(txt);
                break;
            case "cidade":
                lists = filtroHelper.filtroCidade(txt);
                break;
        }
        String json = new Gson().toJson(lists);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}

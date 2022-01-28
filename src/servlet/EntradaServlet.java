package servlet;

import acao.Acao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebServlet("/entrada")
public class EntradaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeParametro = request.getParameter("acao");
        HttpSession session = request.getSession();
        Boolean statusUsuario =(session.getAttribute("usuarioLogado") == null);
        Boolean acaoProjegida = !(nomeParametro.equals("Login") || nomeParametro.equals("LoginForm"));
        if(acaoProjegida && statusUsuario){//Se o usuário não estiver logado, redireciona para tela de login
            response.sendRedirect("entrada?acao=LoginForm");
            return;
        }

        String nomeClasse = "acao."+nomeParametro;
        String nome;
        try {
            Class classe = Class.forName(nomeClasse);
            Acao acao= (Acao) classe.newInstance();
            nome = acao.executar(request, response);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            throw  new ServletException();
        }

        String[] tipoEuri = nome.split(":");
        if (tipoEuri[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/"+tipoEuri[1]);
            rd.forward(request, response);
        }else{
            response.sendRedirect(tipoEuri[1]);
        }

    }

}

package servlet;

import acao.Acao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ControladorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String nomeParametro = request.getParameter("acao");

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

    @Override
    public void destroy() {

    }
}

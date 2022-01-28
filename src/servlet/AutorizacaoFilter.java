package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/entrada")
public class AutorizacaoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String nomeParametro = request.getParameter("acao");

        HttpSession session = request.getSession();
        Boolean statusUsuario =(session.getAttribute("usuarioLogado") == null);
        Boolean acaoProjegida = !(nomeParametro.equals("Login") || nomeParametro.equals("LoginForm"));
        if(acaoProjegida && statusUsuario){//Se o usuário não estiver logado, redireciona para tela de login
            response.sendRedirect("entrada?acao=LoginForm");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

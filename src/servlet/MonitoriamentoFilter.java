package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
/*Todas as chamadas para /entrada(Entradaservlet) passam por
este filtro porque ele está com a mesma uri no @WebFilter*/
//@WebFilter("/entrada")
public class MonitoriamentoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Long antes = System.currentTimeMillis();
        String acao = request.getParameter("acao");
        chain.doFilter(request, response);
        Long depois = System.currentTimeMillis();
        System.out.println("Tempo de execução da ação: "+ acao +" - "+(depois-antes));
    }

    @Override
    public void destroy() {

    }
}

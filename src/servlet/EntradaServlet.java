package servlet;

import service.EmpresaService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/entrada")
public class EntradaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpresaService empresaService = new EmpresaService();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String uri = "";


        if(acao.equals("listar")){
           uri = empresaService.listar(request,response);
        }else if(acao.equals("cadastrar")){
            uri = empresaService.cadastrar(request,response);
        }else if(acao.equals("editar")){
            uri = empresaService.alterar(request,response);
        }else if(acao.equals("remover")){
            uri = empresaService.remover(request,response);
        }else if(acao.equals("buscar")){
           uri = empresaService.buscar(request,response);
        }

        String[] tipoEuri = uri.split(":");
        if (tipoEuri[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher(tipoEuri[1]);
            rd.forward(request, response);
        }else{
            response.sendRedirect(tipoEuri[1]);
        }

    }

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
}

package servlet;

import service.EmpresaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/entrada")
public class EntradaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpresaService empresaService = new EmpresaService();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");


        if(acao.equals("listar")){
            empresaService.listar(request,response);
        }else if(acao.equals("cadastrar")){
            empresaService.cadastrar(request,response);
        }else if(acao.equals("editar")){
            empresaService.alterar(request,response);
        }else if(acao.equals("remover")){
            empresaService.remover(request,response);
        }else if(acao.equals("buscar")){
            empresaService.buscar(request,response);
        }

    }

    public EmpresaService getEmpresaService() {
        return empresaService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
}

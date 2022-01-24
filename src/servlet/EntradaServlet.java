package servlet;

import modelo.Empresa;
import repository.EmpresaRepository;
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
        String nomeClasse = "service.EmpresaService";
        String nome;
        try {
            Class classe = Class.forName(nomeClasse);
            EmpresaRepository empresaRepository = (EmpresaRepository) classe.newInstance();
            nome = empresaRepository.executar(request, response, acao);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            throw  new ServletException();
        }

        String[] tipoEuri = nome.split(":");
        if (tipoEuri[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/empresa/"+tipoEuri[1]);
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

package servlet;

import repository.EmpresaRepository;
import repository.UsuarioRepository;
import service.EmpresaService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/entrada")
public class EntradaUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntradaServlet entradaServlet = new EntradaServlet();
    private EmpresaService empresaService = new EmpresaService();

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String nomeClasse = "service.LoginService";
        String nome;
        try {
            Class classe = Class.forName(nomeClasse);
            UsuarioRepository usuarioRepository = (UsuarioRepository) classe.newInstance();
            nome = usuarioRepository.executar(request, response, acao);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            throw  new ServletException();
        }

        String[] tipoEuri = nome.split(":");
        if (tipoEuri[0].equals("forward")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/usuario/"+tipoEuri[1]);
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

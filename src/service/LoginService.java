package service;

import modelo.Empresa;
import modelo.Usuario;
import repository.UsuarioRepository;
import servlet.EntradaServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginService implements UsuarioRepository {

    private EntradaServlet entradaServlet = new EntradaServlet();

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, String acao) throws ServletException, IOException {
        if (acao.equals("logar")) {
            logar(request, response);
            return "";
        } else{
            return login();
        }
    }

    private String login() {
        return "forward:login.jsp";
    }

    private void logar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("login");
        String senha = request.getParameter("senha");
        String acao="";
        Usuario usuario1 = new Usuario();

        request.setAttribute("listar", acao);
        entradaServlet.service(request,response);
       // return empresaService.listar(request);
    }

    public EntradaServlet getEntradaServlet() {
        return entradaServlet;
    }

    public void setEntradaServlet(EntradaServlet entradaServlet) {
        this.entradaServlet = entradaServlet;
    }
}

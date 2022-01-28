package acao;

import modelo.Banco;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login implements Acao {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("login");
        String senha = request.getParameter("senha");

        Banco banco = new Banco();

        Usuario usuario = banco.autenticaUsuario(user, senha);


        if (usuario!= null){
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:entrada?acao=ListarEmpresas";
        }
        return "redirect:entrada?acao=LoginForm";


    }
}

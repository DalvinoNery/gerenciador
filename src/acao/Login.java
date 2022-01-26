package acao;

import modelo.Banco;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements Acao {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("login");
        String senha = request.getParameter("senha");


        return "redirect:entrada?acao=ListarEmpresas";
    }
}

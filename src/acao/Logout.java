package acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Acao {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //session.removeAttribute("usuarioLogado");
        session.invalidate();
        return "redirect:entrada?acao=LoginForm";
    }
}

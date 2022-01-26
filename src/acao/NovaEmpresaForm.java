package acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NovaEmpresaForm implements Acao {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "forward:cadastrarEmpresa.jsp";
    }
}

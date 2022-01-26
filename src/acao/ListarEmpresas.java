package acao;

import modelo.Banco;
import modelo.Empresa;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ListarEmpresas implements Acao {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("listando empresas em ordem alfabetica");

        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();

        lista.sort(Comparator.comparing(Empresa::getNome));

        request.setAttribute("empresas", lista);

        return "forward:listarEmpresa.jsp";
    }
}

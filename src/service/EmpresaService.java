package service;

import modelo.Banco;
import modelo.Empresa;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmpresaService {

    /*Método de listagem de empresas*/
    public String listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();

        request.setAttribute("empresas", lista);

        return "forward:/listaEmpresas.jsp";
    }

    /*Método de cadastro de empresas*/
    public  String cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String nomeEmpresa = request.getParameter("nome");
        String paramDataEmpresa = request.getParameter("data");

        Date dataAbertura = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataAbertura = sdf.parse(paramDataEmpresa);
        } catch (ParseException e) {
            throw new ServletException(e);
        }

        Empresa empresa = new Empresa();
        empresa.setNome(nomeEmpresa);
        empresa.setDataAbertura(dataAbertura);

        Banco banco = new Banco();
        banco.adiciona(empresa);

        request.setAttribute("empresa", empresa.getNome());

        return "redirect:entrada?acao=listar";
    }

    /*Método de edição de empresas*/
    public String alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Alterando empresa");
        String nomeEmpresa = request.getParameter("nome");
        String paramDataEmpresa = request.getParameter("data");
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);

        Date dataAbertura = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataAbertura = sdf.parse(paramDataEmpresa);
        } catch (ParseException e) {
            throw new ServletException(e);
        }

        Banco banco = new Banco();
        Empresa empresa = banco.buscaEmpresaPelaId(id);
        empresa.setNome(nomeEmpresa);
        empresa.setDataAbertura(dataAbertura);

       return "redirect:entrada?acao=listar";
    }

    /*Método de exclusão de empresas*/
    public String remover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);

        System.out.println(id);

        Banco banco = new Banco();
        banco.removeEmpresa(id);


        return "redirect:entrada?acao=listar";
    }

    public String buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);

        Banco banco = new Banco();

        Empresa empresa = banco.buscaEmpresaPelaId(id);

        System.out.println(empresa.getNome());

        request.setAttribute("empresa", empresa);

        return "forward:/formAlteraEmpresa.jsp";
    }
}

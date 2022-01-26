package service;

import acao.Acao;
import modelo.Banco;
import modelo.Empresa;
import repository.EmpresaRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EmpresaService implements EmpresaRepository {


    public String executar(HttpServletRequest request, HttpServletResponse response, String acao) throws ServletException, IOException {
        if (acao.equals("listar")) {
            return listar(request);
        } else if (acao.equals("listaEmpresasComOrdem")) {
            return listaEmpresasComOrdem(request);
        } else if (acao.equals("cadastrar")) {
            return cadastrar(request);
        } else if (acao.equals("editar")) {
            return alterar(request);
        } else if (acao.equals("remover")) {
            return remover(request);
        } else if (acao.equals("buscar")) {
            return buscar(request);
        } else {
            return formCadastrar();
        }
    }


    /*Método de listagem de empresas*/
    public String listar(HttpServletRequest request) {
        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();

        request.setAttribute("empresas", lista);

        return "redirect:entrada?acao=listar";
    }


    public String listaEmpresasComOrdem(HttpServletRequest request ){

        System.out.println("listando empresas em ordem alfabetica");

        Banco banco = new Banco();
        List<Empresa> lista = banco.getEmpresas();

        lista.sort(Comparator.comparing(Empresa::getNome));
        
        request.setAttribute("empresas", lista);

        return "forward:listarEmpresa.jsp";
    }


    /*Método de cadastro de empresas*/
    public String cadastrar(HttpServletRequest request) throws ServletException {
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

    public String formCadastrar() {

        return "forward:login.jsp";
    }

    /*Método de edição de empresas*/
    public String alterar(HttpServletRequest request) throws ServletException {
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
    public String remover(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);

        System.out.println(id);

        Banco banco = new Banco();
        banco.removeEmpresa(id);


        return "redirect:entrada?acao=listar";
    }

    public String buscar(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        Integer id = Integer.valueOf(paramId);

        Banco banco = new Banco();

        Empresa empresa = banco.buscaEmpresaPelaId(id);

        System.out.println(empresa.getNome());

        request.setAttribute("empresa", empresa);

        return "forward:alterarEmpresa.jsp";
    }
}

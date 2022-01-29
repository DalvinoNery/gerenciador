package servlet;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import modelo.Banco;
import modelo.Empresa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/empresas")
public class EmpresasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empresa> lista = new Banco().getEmpresas();
        String cabecalho = request.getHeader("Accept");

        if (cabecalho.contains("json")) {
            //enviando uma lista em json
            //Necessário incluir a lib Gson da google
            Gson gson = new Gson();
            String json = gson.toJson(lista);

            //definindo o tipo de retorno
            response.setContentType("application/json");
            response.getWriter().print(json);
        } else if (cabecalho.contains("xml")) {

            //emviando uma lista em xml
            /*Necessário incluir a lib Gson da google*/
            XStream xStream = new XStream();
            xStream.alias("empresa", Empresa.class);
            String xml = xStream.toXML(lista);

            /*definindo o tipo de retorno*/
            response.setContentType("application/xml");
            response.getWriter().print(xml);
        } else {
            response.setContentType("application/json");
            response.getWriter().print("'message': 'No content'");
        }

    }
}

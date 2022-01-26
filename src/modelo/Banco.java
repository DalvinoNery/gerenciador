package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
	private static List<Empresa> listaEmpresas = new ArrayList<>();
	private static List<Usuario> listaUsuarios = new ArrayList<>();
	private static Integer chaveSequencial = 1;
	
	static {
		Empresa empresa = new Empresa();
		Empresa empresa2 = new Empresa();

		empresa.setId(chaveSequencial++);
		empresa.setNome("Alura");

		empresa2.setId(chaveSequencial++);
		empresa2.setNome("Caelum");

		listaEmpresas.add(empresa);
		listaEmpresas.add(empresa2);

		Usuario usuario = new Usuario();
		Usuario usuario2 = new Usuario();

		usuario.setLogin("rodrigo");
		usuario.setSenha("123456");

		usuario.setLogin("olivia");
		usuario.setSenha("987654");

		listaUsuarios.add(usuario);
		listaUsuarios.add(usuario2);

	}

	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		Banco.listaEmpresas.add(empresa);
	}
	
	public List<Empresa> getEmpresas(){
		return Banco.listaEmpresas;
	}

	public void removeEmpresa(Integer id) {
		
		Iterator<Empresa> it = listaEmpresas.iterator();
		
		while(it.hasNext()) {
			Empresa emp = it.next();
			
			if(emp.getId() == id) {
				it.remove();
			}
		}
	}

	public Empresa buscaEmpresaPelaId(Integer id) {
		for (Empresa empresa : listaEmpresas) {
			if(empresa.getId() == id) {
				return empresa;
			}
		}
		return null;
	}

}

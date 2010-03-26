package test.persistencia;

import org.junit.Test;

import persistence.daos.UsuarioDao;
import persistence.utils.UtilDBInitializer;
import seguridad.Perfil;
import seguridad.Usuario;

public class TestUsuario {

	@Test
	public void test(){
		UtilDBInitializer.initDropCreate();
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario= new Usuario();
		usuario.setPassword("1234");
		usuario.setUsername("pablo");
		Perfil perfil= new Perfil();
		perfil.setNombre("perfil1");
		perfil.setRecurso("recurso1");
		usuario.getPerfiles().add(perfil);
		usuarioDao.create(usuario);
	}
	@Test
	public void test2(){
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.find(1L);
		Perfil perfil= new Perfil();
		perfil.setNombre("perfil1");
		Perfil perfil2= new Perfil();
		perfil2.setNombre("perfil2");
		usuario.getPerfiles().add(perfil);
		usuario.getPerfiles().add(perfil2);
		usuarioDao.update(usuario);
	}
}

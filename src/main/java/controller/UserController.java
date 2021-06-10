package controller;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.Usuario;
import conectorManager.UsuarioManager;

@Path("/user")

public class UserController {

	@GET

	@Path("/ping")

	public Response ping() {
		return Response.ok().entity("Service online").build();

	}

	@GET
	@Path("/findUser/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response findByID(@PathParam("id") int id) {
		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioManager.findById(id);
			System.out.println(usuario);
		} catch (SQLTimeoutException e) {
			System.out.println("Error database");
		} catch (SQLException e) {
			System.out.println("Error database");
		}
		System.out.println(usuario);
		return Response.ok().entity(usuario).build();

	}
	

	@GET
	@Path("/findUser/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response findByName(@PathParam("name") String name) {
		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioManager.findByNombre(name);
			
		} catch (SQLTimeoutException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(usuario);
		return Response.ok().entity(usuario).build();

	}
	
	@GET
	@Path("/findUser")
	@Produces(MediaType.APPLICATION_JSON)

	public Response findByEmailQuery(@QueryParam("email") String email) {
		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioManager.findByEmail(email);
			
		} catch (SQLTimeoutException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(usuario);
		return Response.ok().entity(usuario).build();

	}
	
	@POST

	@Path("/createUser/")

	@Consumes(MediaType.APPLICATION_JSON)

	@Produces(MediaType.APPLICATION_JSON)

	public Response postNotification(Usuario usuarioNuevo) {
		
		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioManager.create(usuarioNuevo);
			
		} catch (SQLTimeoutException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return Response.ok().entity(usuario).build();

	}
	
	@DELETE
	@Path("/deleteUser/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response deleteByID(@PathParam("id") int id) {
		String delete="";
		try {
			delete = UsuarioManager.deleteByID(id);
		} catch (SQLTimeoutException e) {
			System.out.println("Error database");
		} catch (SQLException e) {
			System.out.println("Error database");
		}
		return Response.ok().entity(delete).build();

	}

}

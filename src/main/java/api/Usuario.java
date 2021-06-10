package api;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;

@Getter

@Setter

@AllArgsConstructor

@NoArgsConstructor

public class Usuario {

	private int idUsuario;

	private String nombreUsuario;

	private String password;

	private String email;

	public Usuario(int idUsuario, String nombreUsuario, String email) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.email = email;

	}

	public Usuario(String nombreUsuario, String password, String email) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.email = email;
	}

}

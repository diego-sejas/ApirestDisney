package com.example.challengeAlkemy.util;

public class Constantes {
	// Security
	public static final String CLAVE_SECRETA = "SecretKey";
	public static final String PREFIJO_TOKEN = "Bearer ";
	public static final String CABECERA = "Authorization";
	public static final long TIEMPO_EXPIRACION = 864_000_000; // 10 días

	// Uris
	public static final String V1 = "/v1";

	// Parámetros de las uris
	public static final String DEFAULT_PAGE = "0";
	public static final String DEFAULT_SIZE = "6";

	// Entidades
	public static final String PER = "Persona";
	public static final String PER_PLURAL = "Personas";
	public static final String USU = "Usuario";
	public static final String USU_PLURAL = "Usuarios";
	public static final String ROL = "Rol";
	public static final String CHARACTER = "Personaje";
	public static final String CHARACTER_PLURAL = "Personajes";
	public static final String MOVIE = "Pelicula";
	public static final String MOVIE_PLURAL = "Peliculas";
	public static final String GENERE = "Genero";
	public static final String GENERE_PLURAL = "Generos";
	

	// Excepciones
	public static final String CON_ID = " con id ";
	public static final String CON_NOMBRE = " con nombre ";
	public static final String NO_ENCONTRADO = " no encontrado";
	public static final String NO_ENCONTRADA = " no encontrada";
	public static final String YA_EXISTE = " ya existe";

	// OpenApi
	public static final String OA_ENDPOINT = "Endpoint de ";
	public static final String OA_OBTENER_TODOS = "Recuperar la lista completa de ";
	public static final String OA_OBTENER = "Recuperar un ";
	public static final String OA_CREAR = "Crear un ";
	public static final String OA_ACTUALIZAR = "Actualizar un ";
	public static final String OA_ELIMINAR = "Eliminar un ";
	public static final String OA_AUTENTICAR = "Autenticar un ";
	public static final String OA_REGISTRAR = "Registrar un ";

}

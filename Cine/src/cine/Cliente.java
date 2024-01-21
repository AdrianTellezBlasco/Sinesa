package cine;

import java.util.List;
import java.util.Arrays;

public class Cliente implements Runnable {

	private String nombre;
	private String apellidos;
	private String correo;
	private String cuentaBancaria;
	private int fondos;
	private GestorDeReservas gestorDeReservas;
	private Cine cine;
	private int filaDeseada;
	private int columnaDeseada;
	List<String> nombreLista = Arrays.asList("Adrián", "Sofía", "Víctor", "Alfonso", "Raúl", "Juan", "David", "Pablo",
			"Alejandro", "Henry", "Eustaquio");
	List<String> apellidosLista = Arrays.asList("Téllez", "Hernandez", "Sánchez", "Carmena", "Portillo", "Ospina",
			"Rodriguez", "Kuzmin", "San Ildefonso", "Catunta", "Blasco", "García", "Stein", "Díaz", "Escobar",
			"Carrión", "Minguez", "Mahmoud", "Cantos", "Perez", "Elustondo", "Cornejo");

	public Cliente(GestorDeReservas gestorDeReservas, Cine cine, int fondos) {
		this.nombre = generarNombre();
		this.apellidos = generarApellidos();
		this.correo = generarCorreo();
		this.cuentaBancaria = generarCuentaBancaria();
		this.fondos = generarFondos(fondos);
		this.gestorDeReservas = gestorDeReservas;
		this.cine = cine;
		this.filaDeseada = generarFila();
		this.columnaDeseada = generarColumna();
	}

	public String generarNombre() {
		int numAleatorio = (int) Math.floor(Math.random() * nombreLista.size());
		return this.nombreLista.get(numAleatorio);
	}

	public String generarApellidos() {
		int numAleatorio = (int) Math.floor(Math.random() * apellidosLista.size());
		String apellido1 = this.apellidosLista.get(numAleatorio);
		int numAleatorio2 = (int) Math.floor(Math.random() * apellidosLista.size());
		String apellido2 = this.apellidosLista.get(numAleatorio2);
		return apellido1 + " " + apellido2;
	}

	public String generarCorreo() {
		String correo = this.nombre;
		String correoCompleto = correo.concat(this.apellidos + "@gmail.com").replaceFirst(" ", "");
		return correoCompleto;
	}

	public String generarCuentaBancaria() {
		String cuentaBancaria = "ES";
		for (int i = 0; i < 10; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * 10);
			cuentaBancaria = cuentaBancaria.concat(String.valueOf(numAleatorio));
		}

		return cuentaBancaria;
	}

	public int generarFondos(int dineroCliente) {

		int numAleatorio = (int) Math.floor(Math.random() * dineroCliente);

		return numAleatorio;
	}

	public int generarFila() {

		int numAleatorio = (int) Math.floor(Math.random() * this.cine.getNumFilas());

		return numAleatorio;
	}

	public int generarColumna() {

		int numAleatorio = (int) Math.floor(Math.random() * this.cine.getNumColumnas());

		return numAleatorio;
	}

	@Override
	public void run() {
		
		synchronized (this.gestorDeReservas.getCine()) {
			this.gestorDeReservas.intentarReservar(this, filaDeseada, columnaDeseada);

			this.gestorDeReservas.getCine().notifyAll();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public int getFondos() {
		return fondos;
	}

	public void setFondos(int fondos) {
		this.fondos = fondos;
	}

	public GestorDeReservas getGestorDeReservas() {
		return gestorDeReservas;
	}

	public void setGestorDeReservas(GestorDeReservas gestorDeReservas) {
		this.gestorDeReservas = gestorDeReservas;
	}

	public int getFilaDeseada() {
		return filaDeseada;
	}

	public void setFilaDeseada(int filaDeseada) {
		this.filaDeseada = filaDeseada;
	}

	public int getColumnaDeseada() {
		return columnaDeseada;
	}

	public void setColumnaDeseada(int columnaDeseada) {
		this.columnaDeseada = columnaDeseada;
	}

}

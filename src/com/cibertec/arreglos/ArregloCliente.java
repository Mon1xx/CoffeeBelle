package com.cibertec.arreglos;

import java.util.ArrayList;

import com.cibertec.clases.Cliente;

public class ArregloCliente {

	private static ArrayList<Cliente> arreglo;

	public ArregloCliente() {
		arreglo = new ArrayList<Cliente>();
	}

	public void agregar(Cliente pro) {
		arreglo.add(pro);
	}

	public int tamano() {
		return arreglo.size();
	}

	public static ArrayList<Cliente> obtenerTodo() {
		return arreglo;
	}

	public Cliente obtener(int posicion) {
		return arreglo.get(posicion);
	}

	public Cliente buscar(int codigo) {
		Cliente pro;
		for (int i = 0; i < tamano(); i++) {
			pro = obtener(i);
			if (pro.getCodigoCliente() == codigo) {
				return pro;
			}
		}
		return null;
	}
}

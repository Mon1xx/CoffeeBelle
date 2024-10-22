package com.cibertec.arreglos;

import java.util.ArrayList;

import com.cibertec.clases.Producto;
public class ArregloProducto {
	
	private static ArrayList<Producto> arreglo;
	
	public ArregloProducto() {
		arreglo = new ArrayList<Producto>();
	}
	
	public void agregar(Producto pro) {
		arreglo.add(pro);
	}
	
	public int tamano() {
		return arreglo.size();
	}
	
	public static ArrayList<Producto> obtenerTodo(){
		return arreglo;
	}
	
	public Producto obtener(int posicion) {
		return arreglo.get(posicion);
	}
	
	
	public Producto buscar(int codigo) {
		Producto pro;
		for(int i=0; i<tamano(); i++) {
			pro = obtener(i);
			if (pro.getCodigoProducto()==codigo) {
				return pro;
			}
		}
		return null;
	}
}

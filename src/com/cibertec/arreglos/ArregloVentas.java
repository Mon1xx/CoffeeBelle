package com.cibertec.arreglos;

import java.util.ArrayList;

import com.cibertec.clases.Venta;

public class ArregloVentas {
	
	private static ArrayList<Venta> arreglo;
	
	public ArregloVentas() {
		arreglo = new ArrayList<Venta>();
	}
	
	public void agregar(Venta venta) {
		arreglo.add(venta);
	}
	
	public int tamano() {
		return arreglo.size();
	}
	
	public static ArrayList<Venta> obtenerTodo(){
		return arreglo;
	}
	
	public Venta obtener(int posicion) {
		return arreglo.get(posicion);
	}
	
	
	public Venta buscar(int codigo) {
		Venta venta;
		for(int i=0; i<tamano(); i++) {
			venta = obtener(i);
			if (venta.getCodigoVenta()==codigo) {
				return venta;
			}
		}
		return null;
	}
	
	

}

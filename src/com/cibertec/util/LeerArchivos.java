package com.cibertec.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.clases.Cliente;
import com.cibertec.clases.Producto;

public class LeerArchivos {

    // Método para obtener una ruta relativa o adaptada al sistema
    private String obtenerRutaArchivo(String nombreArchivo) {
        // Obtener el directorio de trabajo del proyecto
        String userDir = System.getProperty("user.dir");
        return userDir + File.separator + "log" + File.separator + nombreArchivo;
    }

    public List<Cliente> cargarClientes() {
        List<Cliente> lista = new ArrayList<Cliente>();
        File archivo = new File(obtenerRutaArchivo("cliente.txt"));

        // Verificar si el archivo y directorio existen, si no, crearlos
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs(); // Crea el directorio si no existe
                archivo.createNewFile(); // Crea el archivo si no existe
                System.out.println("Archivo cliente.txt creado.");
            } catch (IOException e) {
                System.out.println("Error al crear archivo: " + e.getMessage());
                return lista;
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Cliente objCli;
            while ((linea = br.readLine()) != null) {
                String[] linea_fila = linea.split(";");
                int codigo = Integer.parseInt(linea_fila[0]);
                String nombre = linea_fila[1];
                String apellido = linea_fila[2];
                objCli = new Cliente(codigo, nombre, apellido);
                lista.add(objCli);
            }
        } catch (Exception ex) {
            System.out.println("cargarClientes- Error : " + ex.getMessage());
        }
        return lista;
    }
    public void guardarProductos(List<Producto> listaProductos) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            String rutaArchivo = obtenerRutaArchivo("producto.txt");  // Ruta dinámica
            fw = new FileWriter(rutaArchivo);  // Abrimos el archivo
            pw = new PrintWriter(fw);

            for (Producto producto : listaProductos) {
                // Guardamos todos los campos del producto en el archivo
                String linea = producto.getCodigoProducto() + ";" + producto.getNombre() + ";" +
                               producto.getPrecio() + ";" + producto.getStockActual() + ";" + 
                               producto.getStockMinimo() + ";" + producto.getStockMaximo() + ";" +
                               producto.getTipoProducto() + ";" + producto.getTamaño();
                pw.println(linea);  // Escribimos la línea completa en el archivo
            }

        } catch (Exception ex) {
            System.out.println("Error al guardar productos: " + ex.getMessage());
        } finally {
            try {
                if (pw != null) pw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el archivo: " + ex.getMessage());
            }
        }
    }


    public List<Producto> cargarProductos() {
        List<Producto> lista = new ArrayList<Producto>();
        File archivo = new File(obtenerRutaArchivo("producto.txt"));

        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();  // Crea el directorio si no existe
                archivo.createNewFile();  // Crea el archivo si no existe
                System.out.println("Archivo producto.txt creado.");
            } catch (IOException e) {
                System.out.println("Error al crear archivo: " + e.getMessage());
                return lista;
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Producto objPro;
            while ((linea = br.readLine()) != null) {
                String[] linea_fila = linea.split(";");
                int codigo = Integer.parseInt(linea_fila[0]);
                String nombre = linea_fila[1];
                double precio = Double.parseDouble(linea_fila[2]);
                int stockActual = Integer.parseInt(linea_fila[3]);
                int stockMinimo = Integer.parseInt(linea_fila[4]);
                int stockMaximo = Integer.parseInt(linea_fila[5]);
                String tipoProducto = linea_fila[6];
                String tamanio = linea_fila[7];

                objPro = new Producto(codigo, nombre, precio, stockActual, stockMinimo, stockMaximo, tipoProducto, tamanio);
                lista.add(objPro);
            }
        } catch (Exception ex) {
            System.out.println("cargarProductos - Error: " + ex.getMessage());
        }
        return lista;
    }

}

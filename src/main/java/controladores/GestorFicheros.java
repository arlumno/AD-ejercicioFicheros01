/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ar.csdam.pr.libreriaar.Entradas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * @author a20armandocb
 */
public class GestorFicheros {

    private Scanner lector;

    public GestorFicheros(Scanner lector) {
        this.lector = lector;
    }

    public void ejercicio01() {
        StringBuilder copia = new StringBuilder();
        String resultado = "";
        String ruta = "src/main/java/ficheros/";
        String nombrefichero = "fichero01.txt";
        String linea;
        try {
            File fichero = new File(ruta + nombrefichero);
            if (fichero.exists()) {
                FileReader contenidoFichero = new FileReader(fichero, Charset.forName("UTF-8"));
                BufferedReader br = new BufferedReader(contenidoFichero);
                while ((linea = br.readLine()) != null) {
                    copia.append(linea + "\n");
                }
                resultado = copia.toString().replace(" ", "");
            }

        } catch (FileNotFoundException e) {
            System.out.println("errorcillo al leer el ficher \n" + e.toString());
        } catch (Exception e) {
            System.out.println("errorcillo " + e.toString());
        }
        System.out.println(resultado);
    }

    public void ejercicio02() {
//        String rutaArchivo1 = "src/main/java/ficheros/archivo01.txt";
//        String rutaArchivo2 = "src/main/java/ficheros/archivo02.txt";
//      String rutaDestino = "src/main/java/ficheros/resultado/";

        String nombreResultado;

        StringBuilder textoResultado = new StringBuilder();
        String linea;
        BufferedReader br;

        try {
//            File archivo1 = new File(rutaArchivo1);
//            File archivo2 = new File(rutaArchivo2);
            File archivo1 = Entradas.pedirArchivo(true, false);
            File archivo2 = Entradas.pedirArchivo(true, false);
            nombreResultado = archivo1.getName().replace(".txt", "") + "_" + archivo2.getName();
            br = new BufferedReader(new FileReader(archivo1, Charset.forName("UTF-8")));
            while ((linea = br.readLine()) != null) {
                textoResultado.append(linea);
            }

            br = new BufferedReader(new FileReader(archivo2, Charset.forName("UTF-8")));
            while ((linea = br.readLine()) != null) {
                textoResultado.append(linea);
            }
            File rutaDestino = Entradas.pedirArchivo(false, true);
//            System.out.println(rutaDestino.toString());                        

            File archifoFinal = new File(rutaDestino.toPath() + "\\" + nombreResultado);
//            System.out.println(archifoFinal.toString());
            BufferedWriter bw = new BufferedWriter(new FileWriter(archifoFinal, Charset.forName("UTF-8")));
            bw.write(textoResultado.toString());

            System.out.println("Operación realizada con éxito");

            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("errorcillo al leer el fichero \n" + e.toString());
        } catch (Exception e) {
            System.out.println("errorcillo " + e.toString());
        }

    }

    public void ejercicio03() {
        StringBuilder copia = new StringBuilder();
        String resultado = "";
        String linea;
        try {
            File ficheroEntrada = Entradas.pedirArchivo(true, false);
            if (ficheroEntrada.exists()) {
                //FileReader contenidoFichero = new FileReader(fichero, Charset.forName("UTF-8"));
                FileInputStream contenido = new FileInputStream(ficheroEntrada);
                int longitud = contenido.available();
                int[] arrayEntrada = new int[longitud];
                System.out.println("longitud: " + longitud);
                try {

                    for (int i = 0; i < longitud; i++) {
                        arrayEntrada[i] = contenido.read();
                        //   System.out.println(arrayEntrada[i]);
                    }
                    contenido.close();
                    File directorioSalida = Entradas.pedirArchivo(false, true);
                    File archivoSalida = new File(directorioSalida.toPath() + "\\copia_" + ficheroEntrada.getName());
                    FileOutputStream salida = new FileOutputStream(archivoSalida);

                    for (int i = 0; i < longitud; i++) {
                        salida.write(arrayEntrada[i]);
                    }
                    salida.close();
                } catch (Exception e) {
                    System.out.println("Errorrr: " + e.toString());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("errorcillo al leer el ficher \n" + e.toString());
        } catch (Exception e) {
            System.out.println("errorcillo " + e.toString());
        }
        System.out.println(resultado);

    }

    public void ejercicio04() {
        System.out.println("Indica el directorio donde guardar el archivo vehiculos.dat");
        File directorioSalida = Entradas.pedirArchivo(false, true);
        File archivoSalida = new File(directorioSalida.toPath() + "\\vehiculos.dat");

        System.out.println("Matrícula del vehículo:");
        String matricula = Entradas.pedirString(lector);
        System.out.println("Marca:");
        String marca = Entradas.pedirString(lector);
        System.out.println("Capacidad del deposito:");
        double deposito = Entradas.pedirDouble(lector);
        System.out.println("Modelo:");
        String modelo = Entradas.pedirString(lector);
        
        String resultado = "El vehículo tiene una matrícula " + matricula + ", su marca es " + marca + ", el tamaño depósito es de " + deposito + " litros y su modelo es " + modelo + ".\n";
        
        System.out.println(resultado);
        
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoSalida, true));
            dos.writeUTF(resultado);
            dos.close();
        } catch (FileNotFoundException e) {
            System.out.println("errorcillo al leer el ficher \n" + e.toString());
        } catch (Exception e) {
            System.out.println("errorcillo " + e.toString());
        }
        System.out.println("Añadido el registro: \n" + resultado);

    }

}

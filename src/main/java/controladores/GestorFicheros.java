/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import ar.csdam.pr.libreriaar.Entradas;
import ar.csdam.pr.libreriaar.EntradasGui;
import com.mycompany.ejercicioexamenrepasospa.app.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
        File fichero = new File(ruta + nombrefichero);
        FileReader contenidoFichero = null;
        try {
            if (fichero.exists()) {
                contenidoFichero = new FileReader(fichero, Charset.forName("UTF-8"));
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
        } finally {
            try {
                contenidoFichero.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            }
        }
        System.out.println(resultado);
    }

    public void ejercicio02() {
        String nombreResultado;

        StringBuilder textoResultado = new StringBuilder();
        String linea;
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            File archivo1 = EntradasGui.pedirArchivo(true, false);
            File archivo2 = EntradasGui.pedirArchivo(true, false);
            nombreResultado = archivo1.getName().replace(".txt", "") + "_" + archivo2.getName();
            br = new BufferedReader(new FileReader(archivo1, Charset.forName("UTF-8")));
            while ((linea = br.readLine()) != null) {
                textoResultado.append(linea);
            }

            br = new BufferedReader(new FileReader(archivo2, Charset.forName("UTF-8")));
            while ((linea = br.readLine()) != null) {
                textoResultado.append(linea);
            }
            File rutaDestino = EntradasGui.pedirArchivo(false, true);

            File archifoFinal = new File(rutaDestino.toPath() + "\\" + nombreResultado);
            bw = new BufferedWriter(new FileWriter(archifoFinal, Charset.forName("UTF-8")));
            bw.write(textoResultado.toString());

            System.out.println("Operación realizada con éxito");

        } catch (FileNotFoundException e) {
            System.out.println("errorcillo al leer el fichero \n" + e.toString());
        } catch (Exception e) {
            System.out.println("errorcillo " + e.toString());
        } finally {
            try {
                br.close();
                bw.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            }
        }

    }

    public void ejercicio03() {
        StringBuilder copia = new StringBuilder();
        String resultado = "";
        String linea;
        File ficheroEntrada = EntradasGui.pedirArchivo(true, false);
        FileInputStream contenido = null;
        try {
            if (ficheroEntrada.exists()) {
                contenido = new FileInputStream(ficheroEntrada);
                int longitud = contenido.available();
                int[] arrayEntrada = new int[longitud];
                System.out.println("longitud: " + longitud);
                try {

                    for (int i = 0; i < longitud; i++) {
                        arrayEntrada[i] = contenido.read();
                    }
                    contenido.close();
                    File directorioSalida = EntradasGui.pedirArchivo(false, true);
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
        } finally {
            try {
                contenido.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            }
        }
        System.out.println(resultado);

    }

    public void ejercicio04() {
        JOptionPane.showMessageDialog(null, "Indica el directorio donde guardar y leer el archivo vehiculos.dat");
        File directorioSalida = EntradasGui.pedirArchivo(false, true);
        File archivoSalida = new File(directorioSalida.toPath() + "\\vehiculos.dat");
        if (EntradasGui.pedirBoolean("¿Quieres registrar un vehiculo?")) {

            String matricula = EntradasGui.pedirString("Matrícula del vehículo:");
            String marca = EntradasGui.pedirString("Marca:");
            double deposito = EntradasGui.pedirDouble("Capacidad del deposito:");
            String modelo = EntradasGui.pedirString("Modelo:");
            String resultado = "Se ha añadido el  vehículo:\n  matrícula: " + matricula + ", marca: " + marca + ", tamaño depósito: " + deposito + " litros, modelo: " + modelo + ".";

            DataOutputStream dos = null;
            try {
                dos = new DataOutputStream(new FileOutputStream(archivoSalida, true));
                dos.writeUTF(matricula);
                dos.writeUTF(marca);
                dos.writeDouble(deposito);
                dos.writeUTF(modelo);
                //dos.writeUTF(resultado);
                dos.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "errorcillo al leer el fichero \n" + e.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            } finally {
                try {
                    dos.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
                }
            }
            JOptionPane.showMessageDialog(null, "Añadido al registro registro: \n" + resultado);
        }
        if (EntradasGui.pedirBoolean("¿Quieres mostrar los registros guardados?")) {
            FileInputStream contenido = null;
            try {
                contenido = new FileInputStream(archivoSalida);
                DataInputStream dis = new DataInputStream(contenido);
                StringBuilder salida = new StringBuilder();
                while (contenido.available() > 3) {
                    salida.append("\n*");
                    salida.append("\n  matricula: " + dis.readUTF().toString());
                    salida.append("\n  marca: " + dis.readUTF().toString());
                    salida.append("\n  deposito: " + Double.toString(dis.readDouble()));
                    salida.append("\n  modelo: " + dis.readUTF().toString());
                }
                System.out.println(salida.toString());
                JOptionPane.showMessageDialog(null, salida.toString());
                System.out.println(salida.toString());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "errorcillo al leer el fichero \n" + e.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            } finally {
                try {
                    contenido.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
                }
            }
        }

    }

    public void ejercicio05() {
        JOptionPane.showMessageDialog(null, "Indica el directorio donde guardar y leer el archivo vehiculos.dat");
        File directorioSalida = EntradasGui.pedirArchivo(false, true);
        File archivo = new File(directorioSalida.toPath() + "\\vehiculosObjetos.dat");

        if (EntradasGui.pedirBoolean("¿Quieres registrar un vehiculo?")) {

            String matricula = EntradasGui.pedirString("Matrícula del vehículo:");
            String marca = EntradasGui.pedirString("Marca:");
            double deposito = EntradasGui.pedirDouble("Capacidad del deposito:");
            String modelo = EntradasGui.pedirString("Modelo:");
            Vehiculo vehiculo = new Vehiculo(matricula, marca, deposito, modelo);

            String resultado = "Se ha añadido el OBJETO vehículo:\n  matrícula: " + matricula + ", marca: " + marca + ", tamaño depósito: " + deposito + " litros, modelo: " + modelo + ".";

            ObjectOutputStream oos = null;
            try {
                if (archivo.exists()) {
                    oos = new ObjectOutputStream(new FileOutputStream(archivo, true)) {
                        @Override
                        protected void writeStreamHeader() {
                        }
                    };
                    oos.writeObject(vehiculo);
                } else {
                    oos = new ObjectOutputStream(new FileOutputStream(archivo));
                    oos.writeObject(vehiculo);
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "errorcillo al leer el fichero \n" + e.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
            } finally {
                try {
                    oos.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
                }
            }

            JOptionPane.showMessageDialog(null, "Añadido al registro registro: \n" + resultado);
            if (EntradasGui.pedirBoolean("¿Quieres mostrar los registros guardados?")) {
                FileInputStream contenido = null;
                try {
                    contenido = new FileInputStream(archivo);
                    ObjectInputStream ois = new ObjectInputStream(contenido);
                    StringBuilder salida = new StringBuilder();
                    Vehiculo leerVehiculo;
                    while (contenido.available() != 0) {
                        leerVehiculo = (Vehiculo) ois.readObject();
                        salida.append("\n*");
                        salida.append("\n  matricula: " + leerVehiculo.getMatricula());
                        salida.append("\n  marca: " + leerVehiculo.getMarca());
                        salida.append("\n  deposito: " + Double.toString(leerVehiculo.getDeposito()));
                        salida.append("\n  modelo: " + leerVehiculo.getModelo());
                    }
                    System.out.println(salida.toString());
                    JOptionPane.showMessageDialog(null, salida.toString());
                    System.out.println(salida.toString());
                    contenido.close();
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "errorcillo al leer el fichero \n" + e.toString());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
                } finally {
                    try {
                        contenido.close();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "errorcillo " + e.toString());
                    }
                }

            }

        }
    }
}

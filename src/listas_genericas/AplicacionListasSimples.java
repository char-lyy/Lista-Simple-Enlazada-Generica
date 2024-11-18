package listas_genericas;

import entidades.Fecha;
import entidades.Persona;
import utilidades.GestorEntradaConsola;

/**
 * Ejemplo de Aplicacion de Listas Simples Enlazadas.
 *
 * @author Carlos Álvarez
 */
public class AplicacionListasSimples {

    private ListaSimple listaPersonas;

    void inicializar() {
        listaPersonas = new ListaSimple();
    }

    void cargarCasosDePrueba() {

        Persona p1 = new Persona(12345678, "Juan", "Pérez", new Fecha(15, 3, 1990));
        Persona p2 = new Persona(87654321, "María", "Gómez", new Fecha(25, 7, 1985));
        Persona p3 = new Persona(45678912, "Carlos", "López", new Fecha(5, 11, 1993));
        Persona p4 = new Persona(98765432, "Ana", "Martínez", new Fecha(30, 1, 1995));
        Persona p5 = new Persona(34567891, "Lucía", "Fernández", new Fecha(12, 6, 1998));
        Persona p6 = new Persona(65432198, "Pedro", "García", new Fecha(8, 4, 1980));
        Persona p7 = new Persona(56789123, "Sofía", "Ruiz", new Fecha(20, 10, 2000));

        listaPersonas.insertarFrente(p1);
        listaPersonas.insertarFrente(p2);
        listaPersonas.insertarFrente(p3);
        listaPersonas.insertarFrente(p4);
        listaPersonas.insertarFrente(p5);
        listaPersonas.insertarFrente(p6);
        listaPersonas.insertarFrente(p7);
    }

    void menu() {
        int opcion;

        do {
            mostrarOpciones();
            switch (opcion = GestorEntradaConsola.leerEntero()) {
                case 1:
                    break;
                case 2:
                    listaPersonas.mostrar();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        } while (opcion != 0);
    }

    void mostrarOpciones() {
        System.out.println("*** App Listas Simples ***");
        System.out.println("1. Cargar persona");
        System.out.println("2. Listar personas");
    }

    public static void main(String[] args) {

        AplicacionListasSimples app = new AplicacionListasSimples();
        app.inicializar();
        app.cargarCasosDePrueba();
        app.menu();
    }

}

package listas_genericas;

import entidades.Fecha;
import entidades.Persona;
import utilidades.GestorEntradaConsola;
import utilidades.GestorSalidaConsola;

/**
 * Ejemplo de Aplicacion de Listas Simples Enlazadas.
 *
 * @author Carlos Álvarez
 */
public class AplicacionListasSimples {

    private static final int LIMITES_CARACTER_CONSOLA = 120;
    private ListaSimple<Persona> listaPersonas;

    void inicializar() {
        listaPersonas = new ListaSimple();
    }

    void cargarCasosDePrueba() {

        Persona p1 = new Persona(12345678, "Juan Perez", "10x5 Siglo XX", new Fecha(15, 3, 2008));
        Persona p2 = new Persona(87654321, "Maria Gomez", "3x9 Siglo XIX", new Fecha(25, 7, 1985));
        Persona p3 = new Persona(45678912, "Carlos Lopez", "Ax8 Vinalar", new Fecha(5, 11, 2006));
        Persona p4 = new Persona(98765432, "Ana Martinez", "20x32 Siglo XXI", new Fecha(30, 1, 1995));
        Persona p5 = new Persona(34567891, "Lucia Fernandez", "Av. Belgrano 1654", new Fecha(12, 6, 1998));
        Persona p6 = new Persona(65432198, "Pedro Garcia", "Guemes 333", new Fecha(8, 4, 1980));
        Persona p7 = new Persona(56789123, "Sofia Ruiz", "Japon 125", new Fecha(20, 10, 2009));

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
                    cargarNuevaPersona();
                    break;
                case 2:
                    listaPersonas.mostrar();
                    break;
                case 3:
                    listarTabulado();
                    break;
                case 4:
                    actualizarPersona();
                    break;
                case 5:
                    eliminarPersonas();
                    break;
                case 6:
                    listarPersonasMayores18();
                    break;
            }
        } while (opcion != 0);
    }

    void mostrarOpciones() {
        System.out.println("*** App Listas Simples ***");
        System.out.println("1. Cargar persona");
        System.out.println("2. Listar personas");
        System.out.println("3. Listar personas (Formato tabla)");
        System.out.println("4. Actualizar persona");
        System.out.println("5. Eliminar persona");
        System.out.println("6. Listar personas con edad mayor o igual a 18");
        System.out.println("0. Salir");
    }

    void cargarNuevaPersona() {

        do {
            Persona nuevaPersona = new Persona();
            nuevaPersona.cargarDatos();
            listaPersonas.insertarFrente(nuevaPersona);
        } while (GestorEntradaConsola.confirmar());

    }

    /**
     * Lista de forma tabulada los datos de los nodos de una lista simple
     * enlazada
     */
    void listarTabulado() {
        GestorSalidaConsola.generarTitulosColumnas(Persona.class, LIMITES_CARACTER_CONSOLA);
        NodoSimple<Persona> actual = listaPersonas.getPrimero();
        while (actual != null) {

            GestorSalidaConsola.mostrarTabulado(actual.getDato().getClass(), actual.getDato(), LIMITES_CARACTER_CONSOLA);
            actual = actual.getSiguiente();
        }
    }

    /**
     * Actualiza los datos de una persona dado su DNI.
     */
    void actualizarPersona() {

        Persona personaBuscada = new Persona();
        personaBuscada.cargarDni();
        NodoSimple<Persona> nodoBuscado = listaPersonas.buscar(personaBuscada);

        if (nodoBuscado != null) {

            nodoBuscado.getDato().actualizarDatos();

        } else {

            System.out.println("La persona no se encuentra en la lista");

        }

    }

    /**
     * Elimina una persona de una lista simple enlazada hasta que el usuario
     * desee no continuar
     */
    void eliminarPersonas() {
        do {
            Persona personaBuscada = new Persona();
            personaBuscada.cargarDni();
            NodoSimple<Persona> nodoBuscado = listaPersonas.eliminar(personaBuscada);

            if (nodoBuscado != null) {

                System.out.println("La persona: ");
                GestorSalidaConsola.mostrarTabulado(Persona.class, nodoBuscado.getDato(), LIMITES_CARACTER_CONSOLA);
                System.out.println("Fue quitada de la lista");

            } else {

                System.out.println("La persona no se encuentra en la lista");

            }

        } while (GestorEntradaConsola.confirmar());
    }

    void listarPersonasMayores18() {

        NodoSimple<Persona> nodoActual = listaPersonas.getPrimero();

        while (nodoActual != null) {

            int edad = nodoActual.getDato().calcularEdad();
            boolean esMayor = edad >= 18;
            if (esMayor) {
                System.out.println(nodoActual.getDato().toString());
            }
            nodoActual = nodoActual.getSiguiente();
        }

    }

    public static void main(String[] args) {

        AplicacionListasSimples app = new AplicacionListasSimples();
        app.inicializar();
        app.cargarCasosDePrueba();
        app.menu();
    }

}

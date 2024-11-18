package listas_genericas;

/**
 *
 * @author Carlos Álvarez
 */
public class ListaSimple <T extends Comparable> {

    protected NodoSimple<T> primero;

    public ListaSimple() {
        this.primero = null;
    }

    /**
     * Inserta un elemento al frente de la lista enlazada.
     *
     * @param elemento el elemento que se almacenara en un nodo e insertará al
     * frente.
     */
    public void insertarFrente(Comparable elemento) {
        insertarFrente(new NodoSimple(elemento));
    }

    /**
     * Inserta un nodo al frente de la lista enlazada.
     *
     * @param nuevoNodo el nodo a insertar al frente de la lista.
     */
    public void insertarFrente(NodoSimple nuevoNodo) {
        if (primero != null) {
            nuevoNodo.setSiguiente(primero);
        }
        primero = nuevoNodo;
    }

    /**
     * Muestra los elementos de la lista. Los elementos deben tener implementado
     * el metodo toString.
     */
    public void mostrar() {
        NodoSimple p = primero;
        while (p != null) {
            System.out.println(p.getDato().toString());
            p = p.getSiguiente();
        }
    }

    /**
     * Busca un nodo en una lista enlazada simple comparando el dato almacenado.
     *
     * La comparación se realiza utilizando el método {@code compareTo()} del
     * elemento proporcionado y los datos almacenados en los nodos.
     *
     * @param buscado el elemento a buscar.
     * @return el nodo que contiene el elemento buscado, o {@code null} si no se
     * encuentra.
     */
    public NodoSimple buscar(Comparable buscado) {

        NodoSimple resultado = null;
        NodoSimple actual = primero;
        boolean busqueda = false;
        while (actual != null && !busqueda) {
            if (buscado.compareTo(actual.getDato()) == 0) {
                busqueda = true;
                resultado = actual;
            } else {
                actual = actual.getSiguiente();
            }
        }
        return resultado;
    }

    /**
     * Elimina un nodo de la lista enlazada que contiene un dato igual al
     * proporcionado.
     *
     * La búsqueda y comparación del dato se realizan utilizando el método
     * {@code compareTo()}. Si el nodo es encontrado, se elimina de la lista y
     * se retorna. Si no se encuentra, se muestra un mensaje indicándolo y se
     * retorna {@code null}.
     *
     * @param e el dato a buscar y comparar para determinar el nodo a eliminar.
     * @return el nodo eliminado si se encuentra, o {@code null} si no existe o
     * la lista está vacía.
     */
    public NodoSimple eliminar(Comparable e) {

        if (primero == null) {
            System.out.println("Lista vacia!");
            return null;
        }

        NodoSimple resultado = null;
        NodoSimple actual = primero;
        NodoSimple anterior = null;

        boolean busqueda = false;
        while (actual != null & !busqueda) {
            if (e.compareTo(actual.getDato()) == 0) {
                busqueda = true;
            } else {
                anterior = actual;
                actual = actual.getSiguiente();
            }
        }

        if (busqueda == true) {
            resultado = desenganchar(anterior, actual);
        } else {
            System.out.println("Elemento inexistente");
        }

        return resultado;
    }

    /**
     * Elimina un nodo de la lista desvinculándolo de la cadena de nodos.
     *
     * Si el nodo a eliminar es el primero de la lista, se actualiza el primer
     * nodo. De lo contrario, se ajusta el nodo anterior para apuntar al
     * siguiente del nodo eliminado.
     *
     * @param anterior el nodo previo al nodo a eliminar.
     * @param actual el nodo que se va a eliminar.
     * @return el nodo eliminado.
     */
    public NodoSimple desenganchar(NodoSimple anterior, NodoSimple actual) {

        NodoSimple eliminado = actual;
        if (actual == primero) {
            primero = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }
        return eliminado;
    }

    public NodoSimple getPrimero() {
        return primero;
    }

    public void setPrimero(NodoSimple primero) {
        this.primero = primero;
    }

}

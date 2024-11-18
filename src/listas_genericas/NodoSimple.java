package listas_genericas;

/**
 * Representa un nodo simple de una estructura de datos enlazada.
 *
 * @param <T> el tipo de dato que contendrá el nodo. Debe ser comparable.
 * @author Carlos Álvarez
 */
public class NodoSimple<T extends Comparable> {

    private T dato;
    private NodoSimple<T> siguiente;

    public NodoSimple(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoSimple<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoSimple{" + "dato=" + dato + ", siguiente=" + siguiente + '}';
    }

}

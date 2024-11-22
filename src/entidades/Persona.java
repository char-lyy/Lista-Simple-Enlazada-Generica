package entidades;

import java.time.LocalDate;
import utilidades.GestorEntradaConsola;

/**
 * Representa una persona con atributos básicos como DNI, nombre completo,
 * domicilio y fecha de nacimiento. Proporciona métodos para cargar datos desde
 * la consola, compararse con otras personas por su DNI y generar una
 * representación textual de sí misma.
 *
 * Esta clase implementa la interfaz {@code Comparable} para facilitar el
 * ordenamiento por DNI de objetos de tipo Persona.
 *
 * @author Carlos Álvarez
 */
public class Persona implements Comparable {

    private long dni;
    private String nombreCompleto;
    private String domicilio;
    private Fecha fechaNacimiento;

    public Persona(long dni, String nombreCompleto, String domicilio, Fecha fechaNacimiento) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona() {
    }

    /**
     * Compara esta persona con otra basada en el DNI.
     *
     * @param o Objeto con el que se compara. Debe ser una instancia de
     * {@code Persona}.
     * @return Un valor negativo si esta persona tiene un DNI menor, cero si son
     * iguales, o un valor positivo si es mayor.
     * @throws ClassCastException si el objeto no es de tipo {@code Persona}.
     */
    @Override
    public int compareTo(Object o) {

        Persona otraPersona = (Persona) o;

        return (int) (dni - otraPersona.dni);

    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nombreCompleto=" + nombreCompleto + ", domicilio=" + domicilio + ", fechaNacimiento=" + fechaNacimiento.toString() + '}';
    }

    /**
     * Carga los datos de la persona interactuando con el usuario mediante la
     * consola.
     *
     * @return La instancia actualizada de la persona.
     */
    public Persona cargarDatos() {

        cargarDni();
        cargarNombreCompleto();
        cargarDomicilio();
        cargarFechaNacimiento();

        return this;
    }

    /**
     * Solicita al usuario el ingreso del DNI de la persona.
     *
     * Un DNI es válido si es un número entero positivo mayor a 0.
     *
     * @return El DNI ingresado.
     */
    public long cargarDni() {
        boolean esValido = false;
        while (!esValido) {
            System.out.print("DNI: ");
            dni = GestorEntradaConsola.leerLong();
            esValido = dni > 0;
        }
        return dni;
    }

    /**
     * Solicita al usuario el ingreso del nombre completo de la persona.
     *
     * Un nombre completo es válido si no está vacío ni compuesto únicamente de
     * espacios en blanco.
     *
     * @return El nombre completo ingresado.
     */
    public String cargarNombreCompleto() {
        boolean esValido = false;
        while (!esValido) {
            System.out.print("Nombre Completo: ");
            nombreCompleto = GestorEntradaConsola.leerString();
            esValido = !nombreCompleto.trim().isEmpty();
        }
        return nombreCompleto;
    }

    /**
     * Solicita al usuario el ingreso del domicilio de la persona.
     *
     * Un domicilio es válido si no está vacío ni compuesto únicamente de
     * espacios en blanco.
     *
     * @return El domicilio ingresado.
     */
    public String cargarDomicilio() {
        boolean esValido = false;
        while (!esValido) {
            System.out.print("Domicilio: ");
            domicilio = GestorEntradaConsola.leerString();
            esValido = !domicilio.trim().isEmpty();
        }
        return domicilio;
    }

    /**
     * Carga y establece la fecha de nacimiento de la persona.
     *
     * Este método crea una nueva instancia de la clase {@code Fecha}, solicita
     * al usuario que ingrese los datos correspondientes a la fecha a través de
     * {@code cargarDatos()} y la asigna como fecha de nacimiento de la persona.
     *
     * @return La fecha de nacimiento cargada.
     */
    public Fecha cargarFechaNacimiento() {
        fechaNacimiento = new Fecha();
        System.out.println("Fecha de Nacimiento: ");
        fechaNacimiento.cargarDatos();
        return fechaNacimiento;
    }

    /**
     * Permite actualizar los datos de la persona interactuando con el usuario.
     *
     * Este método presenta un menú que permite al usuario seleccionar qué campo
     * desea modificar (excepto el DNI y fecha de nacimiento, que no se puede
     * cambiar). Los cambios se aplican inmediatamente al campo seleccionado, y
     * el usuario puede continuar editando otros campos hasta que confirme salir
     * del proceso.
     */
    public void actualizarDatos() {
        boolean confirmar = false;

        do {
            System.out.println("Actualizar Datos de Persona");
            System.out.println("1. Modificar Nombre Completo");
            System.out.println("2. Modificar Domicilio");
            System.out.println("0. Confirmar y Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = GestorEntradaConsola.leerEntero();

            switch (opcion) {
                case 1:
                    System.out.println("Nombre actual: " + nombreCompleto);
                    System.out.println("Actualizando Nombre Completo:");
                    cargarNombreCompleto();
                    break;
                case 2:
                    System.out.println("Domicilio actual: " + nombreCompleto);
                    System.out.println("Actualizando Domicilio:");
                    cargarDomicilio();
                    break;
                case 0:
                    System.out.println("¿Desea confirmar los cambios? (S/N): ");
                    String confirmarInput = GestorEntradaConsola.leerString().trim().toUpperCase();
                    confirmar = confirmarInput.equals("S");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (!confirmar);

        System.out.println("Datos actualizados!");
    }

    /**
     * Calcula la edad de una persona en base a su año de nacimiento
     *
     * @return la diferencia entre el año actual y el año de nacimiento de esta
     * persona
     */
    public int calcularEdad() {
        Fecha fechaActual = Fecha.fromLocalDate(LocalDate.now());
        return fechaActual.getAño() - fechaNacimiento.getAño();
    }

    public long getDni() {
        return dni;
    }

    /**
     * Establece el número de identificación personal (DNI) de la persona.
     *
     * Un DNI es válido si es un número entero positivo mayor a 0.
     *
     * @param dni El nuevo DNI de la persona.
     * @throws IllegalArgumentException Si el DNI proporcionado no es válido.
     */
    public void setDni(long dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("El DNI debe ser un número positivo mayor a 0.");
        }
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo de la persona.
     *
     * Un nombre es válido si no está vacío ni compuesto únicamente de espacios
     * en blanco.
     *
     * @param nombreCompleto El nuevo nombre completo de la persona.
     * @throws IllegalArgumentException Si el nombre proporcionado no es válido.
     */
    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacío.");
        }
        this.nombreCompleto = nombreCompleto;
    }

    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Establece el domicilio de la persona.
     *
     * Un domicilio es válido si no está vacío ni compuesto únicamente de
     * espacios en blanco.
     *
     * @param domicilio El nuevo domicilio de la persona.
     * @throws IllegalArgumentException Si el domicilio proporcionado no es
     * válido.
     */
    public void setDomicilio(String domicilio) {
        if (domicilio == null || domicilio.trim().isEmpty()) {
            throw new IllegalArgumentException("El domicilio no puede estar vacío.");
        }
        this.domicilio = domicilio;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento de la persona.
     *
     * Una fecha de nacimiento es válida si no es nula.
     *
     * @param fechaNacimiento La nueva fecha de nacimiento de la persona.
     * @throws IllegalArgumentException Si la fecha proporcionada es invalida o
     * nula.
     */
    public void setFechaNacimiento(Fecha fechaNacimiento) {
        if (fechaNacimiento == null || !fechaNacimiento.esFechaValida()) {
            throw new IllegalArgumentException("La fecha de nacimiento es invalida o nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

}

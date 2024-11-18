package entidades;

/**
 *
 * @author Carlos Álvarez
 */
public class Persona implements Comparable{
    
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
    
    
    @Override
    public int compareTo(Object o) {
        
        Persona otraPersona = (Persona) o;
        
        return (int) (dni - otraPersona.dni);
        
    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nombreCompleto=" + nombreCompleto + ", domicilio=" + domicilio + ", fechaNacimiento=" + fechaNacimiento.toString() + '}';
    }
    
    
    
}

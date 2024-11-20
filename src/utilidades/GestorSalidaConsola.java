package utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar y formatear la salida de datos en consola.
 * Proporciona métodos para mostrar información estructurada y legible en la
 * consola, como títulos, valores de atributos y formatos tabulados.
 *
 * @author Carlos Álvarez
 */
public class GestorSalidaConsola {

    /**
     * Formatea un título centrado dentro de un espacio de longitud máxima
     * especificada.
     *
     * El título se rodea con caracteres de formato en ambos lados para que su
     * longitud total coincida con la longitud máxima proporcionada. El título
     * se centra entre los caracteres de formato.
     *
     * @param titulo el título a formatear.
     * @param LIMITE_CARACTERES_LINEA_CONSOLA la longitud total máxima deseada,
     * incluyendo el título y los caracteres de formato.
     * @param c1 el carácter utilizado para formatear el lado izquierdo del
     * título.
     * @param c2 el carácter utilizado para formatear el lado derecho del
     * título.
     * @return el título formateado, con el texto centrado entre los caracteres
     * de formato.
     */
    public static String titulo(String titulo, int LIMITE_CARACTERES_LINEA_CONSOLA, char c1, char c2) {

        String cad1 = "";
        String cad2 = "";

        int cantidadCaracteres = (int) (LIMITE_CARACTERES_LINEA_CONSOLA - titulo.length()) / 2 - 2;

        for (int i = 0; i <= cantidadCaracteres; i++) {
            cad1 = cad1 + c1;
        }

        for (int i = 0; i <= cantidadCaracteres; i++) {
            cad2 = cad2 + c2;
        }
        titulo = cad1 + " " + titulo + " " + cad2;

        return titulo;
    }

    /**
     * Genera una cadena con los nombres de los atributos no constantes de una
     * clase, ajustados para ser usados como títulos de columnas en la consola.
     *
     * @param aClass la clase de la cual se obtienen los nombres de los
     * atributos.
     * @param LIMITE_CARACTERES_LINEA_CONSOLA la longitud máxima de caracteres
     * por línea en la consola.
     * @return los titulos de la clase en formato tabla por consola
     */
    public static String generarTitulosColumnas(Class<?> aClass, int LIMITE_CARACTERES_LINEA_CONSOLA) {
        // Obtener los atributos no constantes
        Field[] fields = obtenerAtributosNoConstantes(aClass);
        int cantidadAtributos = fields.length;

        // Calcular el espacio disponible para cada atributo en la línea de consola
        int espacioPorAtributo = (int) Math.floor(LIMITE_CARACTERES_LINEA_CONSOLA / (double) cantidadAtributos);

        // Construir el string con los nombres de los atributos formateados
        StringBuilder salida = new StringBuilder();

        for (Field field : fields) {
            String nombreAtributo = field.getName().toUpperCase();

            // Ajustar el nombre a la longitud adecuada
            if (nombreAtributo.length() > espacioPorAtributo) {
                nombreAtributo = nombreAtributo.substring(0, espacioPorAtributo); // Recortar si es necesario
            } else {
                // Rellenar con espacios en blanco hasta alcanzar el espacio por atributo
                while (nombreAtributo.length() < espacioPorAtributo) {
                    nombreAtributo += " ";
                }
            }

            salida.append(nombreAtributo);
        }

        System.out.println(salida.toString());
        
        return salida.toString();
    }

    /**
     * Muestra en la consola los valores de los atributos no constantes de una
     * instancia de clase, formateados para que cada atributo ocupe un espacio
     * determinado en la línea de la consola.
     *
     * @param aClass la clase de la cual se obtienen los atributos.
     * @param instancia la instancia de la cual se obtienen los valores de los
     * atributos.
     * @param LIMITE_CARACTERES_LINEA_CONSOLA la longitud máxima de caracteres
     * por línea en la consola.
     * @return los datos de la instancia en formato tabulado.
     */
    public static String mostrarTabulado(Class<?> aClass, Object instancia, int LIMITE_CARACTERES_LINEA_CONSOLA) {
        // Obtener los atributos no constantes
        Field[] fields = obtenerAtributosNoConstantes(aClass);
        int cantidadAtributos = fields.length;

        // Calcular el espacio disponible para cada atributo en la línea de consola
        int espacioPorAtributo = (int) Math.floor(LIMITE_CARACTERES_LINEA_CONSOLA / (double) cantidadAtributos);

        // Construir el string con los valores de los atributos formateados
        StringBuilder salida = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object valor = field.get(instancia);
                String valorAtributo = valor != null ? valor.toString() : "null";

                // Ajustar el valor a la longitud adecuada
                if (valorAtributo.length() > espacioPorAtributo) {
                    valorAtributo = valorAtributo.substring(0, espacioPorAtributo); // Recortar si es necesario
                } else {
                    // Rellenar con espacios en blanco hasta alcanzar el espacio por atributo
                    while (valorAtributo.length() < espacioPorAtributo) {
                        valorAtributo += " ";
                    }
                }

                salida.append(valorAtributo);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Mostrar en pantalla el string resultante
        System.out.println(salida.toString());
        
        return salida.toString();
    }

    /**
     * Obtiene los atributos no constantes (ni estáticos ni finales) de una
     * clase.
     *
     * @param aClass la clase de la cual se obtienen los atributos.
     * @return un arreglo de objetos {@link Field} representando los atributos
     * no constantes de la clase.
     */
    private static Field[] obtenerAtributosNoConstantes(Class<?> aClass) {
        List<Field> nonConstantFields = new ArrayList<>();

        while (aClass != null && aClass != Object.class) {
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                    nonConstantFields.add(field);
                }
            }
            aClass = aClass.getSuperclass();
        }

        return nonConstantFields.toArray(new Field[0]);
    }

}

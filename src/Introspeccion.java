import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Scanner;

public class Introspeccion {

    public static void main (String []args){
        Scanner sc = new Scanner(System.in);
        BigDecimal bigDecimal = new BigDecimal(1);
        Class c1 = bigDecimal.getClass();

        // Introspección clase BigDecimal
        System.out.println("DATOS DE UNA CLASE:");
        System.out.println("-------------------");
        System.out.println("Clase del objeto BigDecimal: " + c1);
        System.out.println("Nombre de la clase: " + c1.getName());
        System.out.println();
        muestraConstructores(c1);
        System.out.println();
        muestraInterfaces(c1);
        System.out.println();
        muestraMetodos(c1);
        System.out.println();
        muestraCampos(c1);
        System.out.println("\n==========================================================================================");

        // Introspección clase Ejemplo
        String rutaClase = "Ejemplo";
        try{
            Class c2 = Class.forName(rutaClase);
            System.out.println(c2.getName() + " extends " + c2.getSuperclass().getName() + " implements " + c2.getInterfaces().toString());
            System.out.println();
            muestraConstructores(c2);
            System.out.println();
            muestraInterfaces(c2);
            System.out.println();
            muestraMetodos(c2);
            System.out.println();
            muestraCampos(c2);

        } catch (ClassNotFoundException err){
            System.err.println("ERROR: No se ha encontrado la clase");
        }

    }

    private static void muestraConstructores(Class clase){
        Constructor[] constructores = clase.getDeclaredConstructors();
        System.out.println("CONSTRUCTORES:");
        int cont = 0;
        for(Constructor c : constructores){
            System.out.println("Constructor: " + c.getName());
            System.out.print("Modificadores: " + Modifier.toString(c.getModifiers()) + " (");

            Class[] tipoParametro = c.getParameterTypes();
            for(int i = 0; i < tipoParametro.length; i++){
                if(i>0)
                    System.out.print(", ");
                System.out.print(tipoParametro[i].getName());
            }
            System.out.println(");");
        }
    }

    private static void muestraInterfaces(Class clase){
        Class[] interfaces = clase.getInterfaces();

        for(Class c : interfaces){
            System.out.println("Interfaz: " + c.getName());
        }
    }

    private static void muestraMetodos(Class clase){
        Method []metodo = clase.getMethods();
        for(Method m : metodo){
            System.out.println();
            System.out.print(Modifier.toString(m.getModifiers()) + " ");
            System.out.print(m.getReturnType() + " ");
            System.out.print(m.getName() + " ");

            System.out.print("(");
            for(int i = 0; i < m.getParameterTypes().length; i++){
                if(i>0)
                    System.out.print(", ");

                System.out.print(m.getParameterTypes()[i].getName());
            }
            System.out.println(");");
        }
    }

    private static void muestraCampos(Class clase){
        Field[] campos = clase.getDeclaredFields();
        System.out.println("\nCAMPOS DE LA CLASE:");
        for(Field f:campos){
            Class tipoCampo = f.getType();
            String nombre = f.getName();
            System.out.print(" " + Modifier.toString(f.getModifiers()));
            System.out.println(" " + tipoCampo.getName() + " " + nombre + " ();");
        }
    }
}

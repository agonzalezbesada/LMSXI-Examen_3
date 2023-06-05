
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Gson gson = new Gson();
    public static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int eleccion;


        do {
            System.out.println("\nQué desea hacer?"+"\n1. Leer JSON"+"\n2. Modificar y guardar JSON");
            eleccion = entrada.nextInt();
            entrada.nextLine();

            switch (eleccion) {

                case 1:
                    leer();
                    break;

                case 2:
                    modificarGuardar();
                    break;

                default:
                    break;
            }

        } while (eleccion!=0);


    }

    public static void leer() {
        String json_recuperado = "";
        FileReader fileReader;

        try {
            fileReader = new FileReader("src\\employees.json");
            int caracter = fileReader.read();

            while (caracter!=-1) {
                json_recuperado += (char) caracter;
                caracter = fileReader.read();
            }

            fileReader.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }

        Type empleadoListType = new TypeToken<Deserializador>() {}.getType();
        Deserializador deserializador = gson.fromJson(json_recuperado, empleadoListType);

        listaEmpleados = deserializador.getEmployees();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            listaEmpleados.get(i).datos();
        }

    }

    public static void modificarGuardar() {

        listaEmpleados.remove(2);

        String jsonEmpleados = gson.toJson(listaEmpleados, ArrayList.class);

        FileWriter file;

        try {
            file = new FileWriter("src\\employees.json");
            file.write(jsonEmpleados);
            file.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

}

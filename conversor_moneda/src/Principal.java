import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();

        while (true) {
            System.out.println(
                    "***************************************\n" +
                            "Sea bienvenido/a al Conversor de Monedas =)\n\n" +
                            "1) Dólar =>> Peso argentino\n" +
                            "2) Peso argentino =>> Dólar\n" +
                            "3) Dólar =>> Real brasileño\n" +
                            "4) Real brasileño =>> Dólar\n" +
                            "5) Dólar =>> Peso colombiano\n" +
                            "6) Peso colombiano =>> Dólar\n" +
                            "7) Salir\n" +
                            "Elija una opción válida:\n" +
                            "***************************************\n"
            );

            try {
                String entrada = lectura.nextLine();
                int opcion;

                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número entre 1 y 7.");
                    continue;
                }

                if (opcion < 1 || opcion > 7) {
                    System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 7.");
                    continue;
                }

                if (opcion == 7) {
                    System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
                    break;
                }


                System.out.print("Ingrese el valor que deseas convertir: ");
                String entradaCantidad = lectura.nextLine();
                double cantidad;

                try {
                    cantidad = Double.parseDouble(entradaCantidad);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Por favor, ingrese un número para la cantidad.");
                    continue; // Volver al inicio del bucle
                }

                Moneda resultado = consulta.conversarMoneda(opcion, cantidad);

                System.out.printf("El valor %.2f [%s] corresponde al valor final de => %.2f [%s]%n",
                        cantidad,
                        resultado.base_code(),
                        resultado.conversion_result(),
                        resultado.target_code());


            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Ha ocurrido un error. Finalizando la aplicación.");
                break;
            }
        }
    }
}

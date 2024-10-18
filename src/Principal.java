import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

        public class Principal {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                String divisaAConvertir;
                String respuesta = "sí"; // Inicializa la variable respuesta

                do {
                    // Menú de selección de divisa
                    System.out.println("""
                    ********************************************
                    BIENVENIDO AL CONVERTIDOR DE DIVISAS
                    Selecciona el tipo de divisa que deseas convertir:
                    [1] USD - Dólares Americanos
                    [2] MXN - Pesos Mexicanos
                    [3] ARS - Pesos Argentinos
                    [4] BRL - Reales Brasileños
                    [5] CLP - Pesos Chilenos
                    ********************************************
                    """);

                    int opcion = 0;
                    boolean validInput = false;

                    // Validar entrada de tipo entero
                    while (!validInput) {
                        System.out.print("Por favor, ingresa tu opción: ");
                        if (scanner.hasNextInt()) {
                            opcion = scanner.nextInt();
                            validInput = true;
                        } else {
                            System.out.println("Entrada no válida. Debes ingresar un número entre 1 y 5.");
                            scanner.next(); // Limpiar el buffer del scanner
                        }
                    }

                    switch (opcion) {
                        case 1 -> divisaAConvertir = "USD";
                        case 2 -> divisaAConvertir = "MXN";
                        case 3 -> divisaAConvertir = "ARS";
                        case 4 -> divisaAConvertir = "BRL";
                        case 5 -> divisaAConvertir = "CLP";
                        default -> {
                            System.out.println("Opción no válida. Intentente nuevamente.");
                            continue; // Regresar al inicio del bucle
                        }
                    }

                    // Obtener la cantidad a convertir
                    try {
                        System.out.printf("¿Qué cantidad de %s deseas convertir?%n", divisaAConvertir);
                        double cantidad = scanner.nextDouble();

                        // Solicitar la divisa de destino
                        System.out.println("Escribe el código de la moneda a la que deseas convertir (USD, MXN, ARS, BRL, CLP):");
                        String divisaDestino = scanner.next().toUpperCase();

                        // Consultar tasas de cambio
                        ConsultaMonedas consulta = new ConsultaMonedas();
                        Monedas monedas = consulta.buscarMoneda(divisaAConvertir);
                        Double tasaConversion = monedas.obtenerTasaConversion(divisaDestino);

                        // Calcular el resultado
                        double resultado = cantidad * tasaConversion;

                        // Formatear salida
                        DecimalFormat df = new DecimalFormat("#.##");
                        System.out.printf("La cantidad de %s %s es igual a %s %s %n",
                                df.format(cantidad), divisaAConvertir, df.format(resultado), divisaDestino);
                        System.out.println("¡Conversión exitosa!");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
                    }

                    // Preguntar si desea realizar otra conversión
                    System.out.println("¿Te gustaría realizar otra conversión? (sí/no)");
                    respuesta = scanner.next().toLowerCase();

                } while (respuesta.equals("sí") || respuesta.equals("si"));

                scanner.close();
                System.out.println("Gracias por usar el convertidor de monedas. ¡Hasta pronto!");
            }
        }

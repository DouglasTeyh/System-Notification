package servico;

import model.Pedido;
import java.util.Scanner;

public class FreteCalculadoraDistancia {
    public double calcular(Pedido pedido) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe a distancia em KM: ");
        double distancia = scanner.nextDouble();
        return distancia * 0.5;

    }
}

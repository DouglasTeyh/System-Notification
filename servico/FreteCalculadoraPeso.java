package servico;

import model.Pedido;

public class FreteCalculadoraPeso {
    public double calcular(Pedido pedido){
        double pesoTotal = pedido.calcularPesoTotal();
        return pesoTotal * 5.0;
    }



}

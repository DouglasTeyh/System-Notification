package model;

import java.util.ArrayList;
import java.util.List;


public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private double frete;
    private double total;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public double getTotal() {
        return total;
    }

    // Calcula o total dos produtos (sem frete)
    public double calcularTotalProdutos() {
        double soma = 0;
        for (ItemPedido item : itens) {
            soma += item.getSubtotal();
        }
        return soma;
    }

    // Calcula o peso total do pedido
    public double calcularPesoTotal() {
        double peso = 0;
        for (ItemPedido item : itens) {
            peso += item.getPesototal();
        }
        return peso;
    }

    // Calcula o total geral (produtos + frete)
    public void calcularTotalGeral() {
        this.total = calcularTotalProdutos() + frete;
    }
}

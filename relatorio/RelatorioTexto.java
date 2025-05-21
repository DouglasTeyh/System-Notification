package relatorio;

import model.Pedido;
import model.ItemPedido;

public class RelatorioTexto {
    public void gerar(Pedido pedido){
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Produtos:");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("- " + item.getProduto().getNome() + " (" + item.getQuantidade() + "x) - R$ " + item.getSubtotal());
        }
        System.out.println("Total: R$ " + pedido.calcularTotalProdutos());
        System.out.println("Frete: R$ " + pedido.getFrete());
        System.out.println("Total com frete: R$ " + pedido.getTotal());
    }

}

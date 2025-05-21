package relatorio;

import model.Pedido;
import model.ItemPedido;

public class RelatorioJSON {
    public void gerar(Pedido pedido) {
        System.out.println("{");
        System.out.println("  \"cliente\": \"" + pedido.getCliente().getNome() + "\",");
        System.out.println("  \"produtos\": [");
        for (int i = 0; i < pedido.getItens().size(); i++) {
            ItemPedido item = pedido.getItens().get(i);
            System.out.println("    {\"nome\": \"" + item.getProduto().getNome() + "\", \"quantidade\": " + item.getQuantidade() + ", \"preco\": " + item.getSubtotal() + "}" + (i < pedido.getItens().size() - 1 ? "," : ""));
        }
        System.out.println("  ],");
        System.out.println("  \"total\": " + pedido.calcularTotalProdutos() + ",");
        System.out.println("  \"frete\": " + pedido.getFrete() + ",");
        System.out.println("  \"total_com_frete\": " + pedido.getTotal());
        System.out.println("}");
    }
}

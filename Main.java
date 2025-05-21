import model.*;
import servico.*;
import relatorio.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Cliente> clientes = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();
        List<Pedido> pedidos = new ArrayList<>();

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n-- Cadastro de Cliente --");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    clientes.add(new Cliente(nome, cpf, email));
                    System.out.println("Cliente cadastrado com sucesso!");
                }

                case 2 -> {
                    System.out.println("\n-- Cadastro de Produto --");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Peso (kg): ");
                    double peso = scanner.nextDouble();
                    scanner.nextLine();

                    produtos.add(new Produto(nome, preco, peso));
                    System.out.println("Produto cadastrado com sucesso!");
                }

                case 3 -> {
                    if (clientes.isEmpty() || produtos.isEmpty()) {
                        System.out.println("Cadastre ao menos 1 cliente e 1 produto antes.");
                        break;
                    }

                    System.out.println("\n-- Criar Pedido --");

                    // Selecionar cliente
                    System.out.println("Escolha o cliente:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println(i + " - " + clientes.get(i).getNome());
                    }
                    System.out.print("Digite o número do cliente: ");
                    int idxCliente = scanner.nextInt();
                    Cliente cliente = clientes.get(idxCliente);

                    Pedido pedido = new Pedido(cliente);

                    // Adicionar produtos
                    while (true) {
                        System.out.println("\nEscolha o produto para adicionar:");
                        for (int i = 0; i < produtos.size(); i++) {
                            System.out.println(i + " - " + produtos.get(i).getNome());
                        }
                        System.out.print("Digite o número do produto (ou -1 para finalizar): ");
                        int idxProduto = scanner.nextInt();
                        if (idxProduto == -1) break;

                        Produto produto = produtos.get(idxProduto);
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();

                        pedido.adicionarItem(new ItemPedido(produto, quantidade));
                    }

                    // Escolher tipo de frete
                    System.out.println("\nEscolha o tipo de frete:");
                    System.out.println("1 - Por peso (R$5,00/kg)");
                    System.out.println("2 - Por distância (R$0,50/km)");
                    int tipoFrete = scanner.nextInt();

                    double frete;
                    if (tipoFrete == 1) {
                        FreteCalculadoraPeso calculadora = new FreteCalculadoraPeso();
                        frete = calculadora.calcular(pedido);
                    } else {
                        FreteCalculadoraDistancia calculadora = new FreteCalculadoraDistancia();
                        frete = calculadora.calcular(pedido);
                    }

                    pedido.setFrete(frete);
                    pedido.calcularTotalGeral();

                    // Notificação
                    System.out.println("\nEscolha o tipo de notificação:");
                    System.out.println("1 - E-mail");
                    System.out.println("2 - SMS");
                    System.out.println("3 - WhatsApp");
                    int tipoNotificacao = scanner.nextInt();

                    if (tipoNotificacao == 1) {
                        new NotificadorEmail().enviar(cliente);
                    } else if (tipoNotificacao == 2) {
                        new NotificadorSMS().enviar(cliente);
                    } else {
                        new NotificadorWhatsApp().enviar(cliente);
                    }

                    // Relatório
                    System.out.println("\nGerar relatório:");
                    System.out.println("1 - Texto");
                    System.out.println("2 - JSON");
                    int tipoRelatorio = scanner.nextInt();

                    if (tipoRelatorio == 1) {
                        new RelatorioTexto().gerar(pedido);
                    } else {
                        new RelatorioJSON().gerar(pedido);
                    }

                    pedidos.add(pedido);
                    System.out.println("Pedido finalizado!");
                }

                case 4 -> {
                    System.out.println("Encerrando o sistema. Até logo!");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}

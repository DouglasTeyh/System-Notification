package servico;

import model.Cliente;

public class NotificadorSMS {
    public void enviar(Cliente cliente){
        System.out.println("Enviando SMS para " + cliente.getNome() + ": Seu pedido foi confirmado!");
    }
}

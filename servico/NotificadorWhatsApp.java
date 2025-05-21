package servico;

import model.Cliente;

public class NotificadorWhatsApp {
    public void enviar(Cliente cliente){
        System.out.println("Enviando Whatsapp para " + cliente.getNome()+": Seu pedido foi confirmado");
    }

}

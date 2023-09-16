import java.util.Scanner;


interface NotaHandler {
    void setProximoHandler(NotaHandler handler);
    void processarTroco(int valor);
}


class NotaHandlerImpl implements NotaHandler {
    private int valorNota;
    private NotaHandler proximoHandler;

    public NotaHandlerImpl(int valorNota) {
        this.valorNota = valorNota;
    }

    public void setProximoHandler(NotaHandler handler) {
        this.proximoHandler = handler;
    }

    public void processarTroco(int valor) {
        if (valor >= valorNota) {
            int quantidadeNotas = valor / valorNota;
            int trocoRestante = valor % valorNota;
            System.out.println("Notas de R$" + valorNota + ": " + quantidadeNotas);
            if (trocoRestante > 0 && proximoHandler != null) {
                proximoHandler.processarTroco(trocoRestante);
            }
        } else if (proximoHandler != null) {
            proximoHandler.processarTroco(valor);
        }
    }
}

public class SistemaTroco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        NotaHandler handler50 = new NotaHandlerImpl(50);
        NotaHandler handler10 = new NotaHandlerImpl(10);
        NotaHandler handler2 = new NotaHandlerImpl(2);
        NotaHandler handler1 = new NotaHandlerImpl(1);

        handler50.setProximoHandler(handler10);
        handler10.setProximoHandler(handler2);
        handler2.setProximoHandler(handler1);

     
        System.out.print("Digite o valor: R$");
        int valorPedido = scanner.nextInt();

     
        System.out.println("\nPedido de Troco: R$" + valorPedido);
        handler50.processarTroco(valorPedido);

        scanner.close();
    }
}

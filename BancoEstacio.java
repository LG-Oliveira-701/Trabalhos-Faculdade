import java.util.Scanner;
import java.util.ArrayList;

public class BancoEstacio {

    public static class Conta {
        String agencia;
        String numeroConta;
        String nome;

        public Conta(String agencia, String numeroConta, String nome) {
            this.agencia = agencia;
            this.numeroConta = numeroConta;
            this.nome = nome;
        }
    }

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta("001", "1234", "Luiz"));
        contas.add(new Conta("002", "5678", "Gabriel"));

        ArrayList<String> historico = new ArrayList<>();

        System.out.print("Digite a agência: ");
        String agenciaDigitada = leitor.nextLine();

        System.out.print("Digite o número da conta: ");
        String contaDigitada = leitor.nextLine();

        Conta contaLogada = null;
        for (Conta conta : contas) {
            if (conta.agencia.equals(agenciaDigitada) && conta.numeroConta.equals(contaDigitada)) {
                contaLogada = conta;
                break;
            }
        }

        if (contaLogada == null) {
            System.out.println("Agência ou conta inválida. Encerrando o sistema.");
            leitor.close();
            return;
        }

        String Nome = contaLogada.nome;
        String TipoDeConta = "Corrente";
        double Saldo = 0;
        int opc = 0;

        System.out.println("--------------------------------------------------------------");
        System.out.println("""
        
        ┓ ┏┓  ┏┓┏┓┳┳┓┏┓┏┓┳┓┓┏
        ┃ ┃┓  ┃ ┃┃┃┃┃┃┃┣┫┃┃┗┫
        ┗┛┗┛  ┗┛┗┛┛ ┗┣┛┛┗┛┗┗┛
        
        !!!SEJA BEM-VINDO!!!
        
        Nome do Cliente: %s
        Tipo De Conta: %s
        Saldo atual: %.2f 
        """.formatted(Nome, TipoDeConta, Saldo));
        System.out.println("--------------------------------------------------------------");

        String menu = """
            Qual Serviço Deseja Utilizar?

            1 - Consultar Saldo
            2 - Depositar
            3 - Transferir
            4 - Pagar Boleto
            5 - Ver Histórico
            6 - Sair
            """;

        while (opc != 6) {
            System.out.println(menu);
            System.out.print("Digite a operação Desejada: ");
            opc = leitor.nextInt();

            if (opc == 1) {
                System.out.printf("O seu saldo é: %.2f\n", Saldo);
                historico.add("Consultou o saldo: R$ %.2f".formatted(Saldo));
            } else if (opc == 2) {
                System.out.print("Digite o valor do depósito: ");
                double depositar = leitor.nextDouble();
                Saldo += depositar;
                System.out.printf("Depósito realizado com sucesso. Saldo atual: %.2f\n", Saldo);
                historico.add("Depósito: R$ %.2f".formatted(depositar));
            } else if (opc == 3) {
                System.out.print("Digite o valor da transferência: ");
                double transferir = leitor.nextDouble();
                if (Saldo < transferir) {
                    System.out.println("Transferência não autorizada.");
                    historico.add("Tentativa de transferência falhou (R$ %.2f insuficiente)".formatted(transferir));
                } else {
                    Saldo -= transferir;
                    System.out.printf("Transferência realizada com sucesso! Saldo atual: %.2f\n", Saldo);
                    historico.add("Transferência realizada: R$ %.2f".formatted(transferir));
                }
            } else if (opc == 4) {
                System.out.println("Pagamento de boleto com taxa de 2%");
                System.out.print("Digite o valor do boleto: ");
                double boleto = leitor.nextDouble();
                double boletoComTaxa = boleto * 1.02;
                if (Saldo < boletoComTaxa) {
                    System.out.println("Pagamento não autorizado.");
                    historico.add("Tentativa de pagar boleto falhou (R$ %.2f insuficiente)".formatted(boletoComTaxa));
                } else {
                    Saldo -= boletoComTaxa;
                    System.out.printf("Pagamento realizado com sucesso! Saldo atual: %.2f\n", Saldo);
                    historico.add("Boleto pago: R$ %.2f (com taxa de R$ %.2f)".formatted(boleto, boletoComTaxa - boleto));
                }
            } else if (opc == 5) {
                System.out.println("Histórico de transações:");
                if (historico.isEmpty()) {
                    System.out.println("Nenhuma transação realizada.");
                } else {
                    for (String h : historico) {
                        System.out.println("- " + h);
                    }
                }
            } else if (opc == 6) {
                System.out.println("Obrigado por utilizar nossos serviços.");
            } else {
                System.out.println("Opção inválida...");
            }
        }

        System.out.println("Fim das operações.");
        leitor.close();
    }
}

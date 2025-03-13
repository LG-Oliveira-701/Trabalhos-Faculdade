import java.util.Scanner;

public class BancoSimples {
    private static double saldo = 0;
    private static String usuarioCadastrado = "";
    private static String senhaCadastrada = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ASCII Art de boas-vindas
        System.out.println("********************************");
        System.out.println("*    BEM-VINDO AO CAIXA ELETRÔNICO    *");
        System.out.println("********************************");

        // Opção de Cadastro
        if (!realizarCadastro(scanner)) {
            System.out.println("Erro no cadastro. Encerrando...");
            return;
        }

        // Login
        if (!realizarLogin(scanner)) {
            System.out.println("Usuário ou senha incorretos. Encerrando...");
            return;
        }

        int opcao;
        do {
            System.out.println(getMenu());
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    mostrarSaldo();
                    break;
                case 2:
                    depositar(scanner);
                    break;
                case 3:
                    sacar(scanner);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);

        System.out.println("OBRIGADO POR UTILIZAR NOSSOS SERVIÇOS!");
    }

    private static boolean realizarCadastro(Scanner scanner) {
        System.out.println("Deseja cadastrar um novo usuário? (SIM/NAO)");
        String resposta = scanner.nextLine().trim().toUpperCase();

        if (resposta.equals("SIM")) {
            System.out.print("Escolha um nome de usuário: ");
            usuarioCadastrado = scanner.nextLine();
            System.out.print("Escolha uma senha: ");
            senhaCadastrada = scanner.nextLine();
            System.out.println("Usuário cadastrado com sucesso!\n");
            return true;
        } else {
            return false;
        }
    }

    private static boolean realizarLogin(Scanner scanner) {
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return usuario.equals(usuarioCadastrado) && senha.equals(senhaCadastrada);
    }

    private static String getMenu() {
        return "=============================\n" +
               "MENU PRINCIPAL\n" +
               "1 - SALDO\n" +
               "2 - DEPOSITAR\n" +
               "3 - SACAR\n" +
               "4 - SAIR\n" +
               "=============================";
    }

    private static void mostrarSaldo() {
        System.out.printf("Seu saldo atual é: R$ %.2f\n", saldo);
    }

    private static void depositar(Scanner scanner) {
        System.out.print("Informe o valor para depósito: R$ ");
        double valor = scanner.nextDouble();
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor inválido!");
        }
    }

    private static void sacar(Scanner scanner) {
        System.out.print("Informe o valor para saque: R$ ");
        double valor = scanner.nextDouble();
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido!");
        }
    }
}

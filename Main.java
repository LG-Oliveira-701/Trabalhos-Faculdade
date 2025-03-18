import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner leitura = new Scanner(System.in)) {
            Random randomizacao = new Random();
            boolean jogarNovamente = true;
            
            // O loop principal que continua enquanto o jogador quiser jogar
            while (jogarNovamente) {
                // Escolher o nível de dificuldade
                System.out.println("Escolha o nível de dificuldade:");
                System.out.println("1. Fácil (1-50, 20 tentativas)");
                System.out.println("2. Médio (1-100, 15 tentativas)");
                System.out.println("3. Difícil (1-200, 10 tentativas)");
                int nivel = leitura.nextInt();
                
                int maxNumero = 100;
                int tentativasMaximas = 15;
                
                switch (nivel) {
                    case 1:
                        maxNumero = 50;
                        tentativasMaximas = 20;
                        break;
                    case 2:
                        maxNumero = 100;
                        tentativasMaximas = 15;
                        break;
                    case 3:
                        maxNumero = 200;
                        tentativasMaximas = 10;
                        break;
                    default:
                        System.out.println("Escolha inválida! Usando nível Médio.");
                        maxNumero = 100;
                        tentativasMaximas = 15;
                }
                
                int tentativas = 0;
                int cpu = randomizacao.nextInt(maxNumero) + 1; // Número sorteado baseado no nível
                int choose = 0;
                ArrayList<Integer> historicoTentativas = new ArrayList<>(); // Histórico de tentativas
                int pontos = 0;
                
                System.out.println("Bem-vindo ao jogo de adivinhação!");
                System.out.println("Você tem " + tentativasMaximas + " tentativas para adivinhar o número.");
                System.out.println("O número sorteado está entre 1 e " + maxNumero);
                
                // ASCII Art com "Adivinhe!"
                System.out.println("""
                          AAAAA    DDDD     III   V   V    III    N   N    H   H    EEEEE
                         A     A   D   D     I     V   V     I     NN  N    H   H    E
                         AAAAAAA   D   D     I     V   V     I     N N N    HHHHH    EEEE
                         A     A   D   D     I     V   V     I     N  NN    H   H    E
                         A     A   DDDD     III    VVV    III    N   N    H   H    EEEEE
                """);

                while (tentativas < tentativasMaximas) {
                    System.out.println("Tente adivinhar o número: ");
                    choose = leitura.nextInt();
                    
                    // Guardando a tentativa no histórico
                    historicoTentativas.add(choose);
                    
                    if (choose == cpu) {
                        pontos = (tentativasMaximas - tentativas) * 10; // Pontuação baseada nas tentativas restantes
                        System.out.println("Parabéns! Você acertou!");
                        System.out.println("Você acertou em " + (tentativas + 1) + " tentativas.");
                        System.out.println("Sua pontuação é: " + pontos);
                        break;
                    } else {
                        tentativas++;
                        System.out.println("Você errou...");
                        if (choose < cpu) {
                            System.out.println("O número é maior.");
                        } else {
                            System.out.println("O número é menor.");
                        }
                        
                        // Dica especial após 3 tentativas
                        if (tentativas == 3) {
                            System.out.println("Dica: O número sorteado é " + (cpu % 2 == 0 ? "par" : "ímpar"));
                        }
                    }

                    if (tentativas == tentativasMaximas) {
                        System.out.println("Acabaram as tentativas! O número escolhido foi " + cpu);
                    }
                }
                
                // Histórico de tentativas
                System.out.println("Histórico das tentativas: " + historicoTentativas);
                
                // Perguntar se quer jogar novamente
                System.out.println("Deseja jogar novamente? (sim/nao)");
                String resposta = leitura.next();
                
                // Verificar resposta e definir se o jogador quer jogar novamente
                if (resposta.equalsIgnoreCase("nao")) {
                    jogarNovamente = false;  // Se a resposta for "não", sai do loop
                    System.out.println("Obrigado por jogar! Até a próxima!");
                } else if (resposta.equalsIgnoreCase("sim")) {
                    jogarNovamente = true;  // Se a resposta for "sim", reinicia o jogo
                    System.out.println("Reiniciando o jogo...");
                } else {
                    // Caso a resposta não seja "sim" nem "não", perguntar novamente
                    System.out.println("Resposta inválida. O jogo continuará.");
                }
            }
        } // O Scanner será fechado automaticamente ao sair do bloco try-with-resources
    }
}

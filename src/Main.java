import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree arvoreJogadores = new BinarySearchTree();
        String caminhoArquivo = "assets/players.csv";

        System.out.println("Iniciando a leitura do arquivo CSV...");

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine();

            if (linha != null && linha.startsWith("\uFEFF")) {
                linha = linha.substring(1);
            }
            if (linha != null && linha.toLowerCase().startsWith("nickname")) {
                linha = br.readLine();
            }

            while (linha != null) {
                if (linha.trim().isEmpty()) {
                    linha = br.readLine();
                    continue;
                }

                String[] dados = linha.split(",");
                if (dados.length >= 2) {
                    try {
                        String nickname = dados[0].trim();
                        int ranking = Integer.parseInt(dados[1].trim());
                        arvoreJogadores.insert(new Player(nickname, ranking));
                    } catch (NumberFormatException ignored) {
                        // Linhas com erro numérico são ignoradas silenciosamente
                    }
                }
                linha = br.readLine();
            }
            System.out.println("Leitura concluída com sucesso!\n");

        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo CSV: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n===================================");
            System.out.println("       MENU DE JOGADORES           ");
            System.out.println("===================================");
            System.out.println("1. Buscar Jogador");
            System.out.println("2. Inserir Novo Jogador");
            System.out.println("3. Remover Jogador");
            System.out.println("4. Exibir Árvore (Ordem de Ranking)");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nickname do jogador para buscar: ");
                    String nomeBusca = scanner.nextLine();
                    if (arvoreJogadores.search(nomeBusca)) {
                        System.out.println("Jogador '" + nomeBusca + "' foi encontrado no ranking!");
                    } else {
                        System.out.println("Jogador '" + nomeBusca + "' não está no ranking.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o nickname do novo jogador: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite o ranking do novo jogador (número inteiro): ");
                    try {
                        int novoRanking = Integer.parseInt(scanner.nextLine());
                        arvoreJogadores.insert(new Player(novoNome, novoRanking));
                        System.out.println("Jogador inserido com sucesso!");
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: O ranking deve ser um número inteiro.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o nickname do jogador que deseja remover: ");
                    String nomeRemover = scanner.nextLine();
                    Player removido = arvoreJogadores.remove(nomeRemover);
                    if (removido != null) {
                        System.out.println("Jogador removido: " + removido.getNickname() + " (Ranking: " + removido.getRanking() + ")");
                    } else {
                        System.out.println("Jogador não encontrado para remoção.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- Lista de Jogadores ---");
                    arvoreJogadores.inOrder();
                    System.out.println("--------------------------");
                    break;
                case 5:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um número de 1 a 5.");
            }
        }
        scanner.close();
    }
}
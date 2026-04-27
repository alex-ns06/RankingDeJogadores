import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree arvoreJogadores = new BinarySearchTree();
        String caminhoArquivo = "assets/players.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine();

            if (linha != null && linha.contains("nickname")) {
                linha = br.readLine();
            }

            while (linha != null) {
                linha = linha.replace("\uFEFF", "").trim();

                if (!linha.isEmpty()) {
                    String[] dados = linha.split(",");

                    if (dados.length == 2) {
                        String nickname = dados[0].trim();
                        try {
                            int ranking = Integer.parseInt(dados[1].trim());
                            arvoreJogadores.insert(new Player(nickname, ranking));
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao ler o ranking do jogador: " + nickname + ". Linha ignorada.");
                        }
                    }
                }
                linha = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao abrir ou ler o arquivo CSV: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            RankingGUI tela = new RankingGUI(arvoreJogadores);
            tela.setVisible(true);
        });
    }
}
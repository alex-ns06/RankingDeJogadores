import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree arvoreJogadores = new BinarySearchTree();
        String caminhoArquivo = "assets/players.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String texto = "";
            String linha = br.readLine();

            while (linha != null) {
                texto += linha + " ";
                linha = br.readLine();
            }

            texto = texto.replace("\uFEFF", "");
            texto = texto.replace("nickname,ranking", "");
            texto = texto.trim();

            String[] jogadores = texto.split("\\s+");

            for (String jogador : jogadores) {
                String[] dados = jogador.split(",");

                if (dados.length == 2) {
                    String nickname = dados[0].trim();
                    int ranking = Integer.parseInt(dados[1].trim());

                    arvoreJogadores.insert(new Player(nickname, ranking));
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo CSV.");
        } catch (NumberFormatException e) {
            System.out.println("Erro em algum ranking do arquivo CSV.");
        }

        SwingUtilities.invokeLater(() -> {
            RankingGUI tela = new RankingGUI(arvoreJogadores);
            tela.setVisible(true);
        });
    }
}
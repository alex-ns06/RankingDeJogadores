import javax.swing.*;
import java.awt.*;

public class RankingGUI extends JFrame {
    private BinarySearchTree tree;
    private TreePanel treePanel;
    private JTextField campoNickname;
    private JTextField campoRanking;

    public RankingGUI(BinarySearchTree tree) {
        this.tree = tree;

        setTitle("Ranking de Jogadores");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        treePanel = new TreePanel(tree);

        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new FlowLayout());

        campoNickname = new JTextField(12);
        campoRanking = new JTextField(6);

        JButton botaoInserir = new JButton("Inserir");
        JButton botaoBuscar = new JButton("Buscar");
        JButton botaoRemover = new JButton("Remover");

        painelTopo.add(new JLabel("Nickname:"));
        painelTopo.add(campoNickname);
        painelTopo.add(new JLabel("Ranking:"));
        painelTopo.add(campoRanking);
        painelTopo.add(botaoInserir);
        painelTopo.add(botaoBuscar);
        painelTopo.add(botaoRemover);

        add(painelTopo, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);

        botaoInserir.addActionListener(e -> inserirJogador());
        botaoBuscar.addActionListener(e -> buscarJogador());
        botaoRemover.addActionListener(e -> removerJogador());
    }

    private void inserirJogador() {
        String nickname = campoNickname.getText();

        if (nickname.isEmpty() || campoRanking.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha nickname e ranking.");
            return;
        }

        int ranking = Integer.parseInt(campoRanking.getText());

        Player player = new Player(nickname, ranking);
        tree.insert(player);

        campoNickname.setText("");
        campoRanking.setText("");

        treePanel.repaint();
    }

    private void buscarJogador() {
        String nickname = campoNickname.getText();

        if (nickname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nickname para buscar.");
            return;
        }

        boolean encontrou = tree.search(nickname);

        if (encontrou) {
            JOptionPane.showMessageDialog(this, "Jogador encontrado.");
        } else {
            JOptionPane.showMessageDialog(this, "Jogador não encontrado.");
        }
    }

    private void removerJogador() {
        String nickname = campoNickname.getText();

        if (nickname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nickname para remover.");
            return;
        }

        Player removido = tree.remove(nickname);

        if (removido != null) {
            JOptionPane.showMessageDialog(this, "Jogador removido.");
            treePanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Jogador não encontrado.");
        }
    }
}
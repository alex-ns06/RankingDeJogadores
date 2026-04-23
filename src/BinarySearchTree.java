public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(Player p) {
        this.root = insert(this.root, p);
    }

    private Node insert(Node current, Player p) {
        if (current == null) {
            return new Node(p);
        }
        if (p.getRanking() < current.getPlayer().getRanking()) {
            current.setLeft(insert(current.getLeft(), p));
        } else if (p.getRanking() > current.getPlayer().getRanking()) {
            current.setRight(insert(current.getRight(), p));
        }
        return current;
    }

    public boolean search(String name) {
        return search(this.root, name) != null;
    }

    private Node search(Node current, String name) {
        if (current == null) {
            return null;
        }
        if (current.getPlayer().getNickname().equals(name)) {
            return current;
        }
        Node leftSearch = search(current.getLeft(), name);
        if (leftSearch != null) {
            return leftSearch;
        }
        return search(current.getRight(), name);
    }

    public Player remove(String name) {
        Node targetNode = search(this.root, name);
        if (targetNode != null) {
            Player targetPlayer = targetNode.getPlayer();
            this.root = remove(this.root, targetPlayer.getRanking());
            return targetPlayer;
        }
        return null;
    }

    private Node remove(Node current, int ranking) {
        if (current == null) {
            return null;
        }

        if (ranking < current.getPlayer().getRanking()) {
            current.setLeft(remove(current.getLeft(), ranking));
        } else if (ranking > current.getPlayer().getRanking()) {
            current.setRight(remove(current.getRight(), ranking));
        } else {
            if (current.getLeft() == null) {
                return current.getRight();
            }
            if (current.getRight() == null) {
                return current.getLeft();
            }

            Player smallestValue = findSmallestValue(current.getRight());
            current.setPlayer(smallestValue);
            current.setRight(remove(current.getRight(), smallestValue.getRanking()));
        }
        return current;
    }

    private Player findSmallestValue(Node root) {
        return root.getLeft() == null ? root.getPlayer() : findSmallestValue(root.getLeft());
    }

    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(Node current) {
        if (current != null) {
            inOrder(current.getLeft());
            System.out.println(current.getPlayer().getNickname() + " " + current.getPlayer().getRanking());
            inOrder(current.getRight());
        }
    }
}
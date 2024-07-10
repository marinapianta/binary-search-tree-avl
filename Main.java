public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Adicionando elementos
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);
        tree.add(25);

        // Verificando os elementos em ordem
        System.out.println("Elementos da árvore em ordem: " + tree.inOrder());

        // Verificando se contém um elemento
        System.out.println("A árvore contém 30? " + tree.contains(30));

        // Verificando a altura da árvore
        System.out.println("Altura da árvore: " + tree.height());

        // Verificando o tamanho da árvore
        System.out.println("Tamanho da árvore: " + tree.size());

        // Verificando se a árvore está vazia
        System.out.println("A árvore está vazia? " + tree.isEmpty());

        // Limpando a árvore
        tree.clear();
        System.out.println("A árvore está vazia após limpar? " + tree.isEmpty());
    }
}

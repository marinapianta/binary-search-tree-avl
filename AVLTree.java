import java.util.ArrayList;
import java.util.List;

class AVLTree {
    private AVLNode root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void add(int key) {
        root = addRecursive(root, key, null);
    }

    private AVLNode addRecursive(AVLNode node, int key, AVLNode parent) {
        if (node == null) {
            size++;
            AVLNode newNode = new AVLNode(key);
            newNode.parent = parent;
            return newNode;
        }

        if (key < node.key) {
            node.left = addRecursive(node.left, key, node);
        } else if (key > node.key) {
            node.right = addRecursive(node.right, key, node);
        } else {
            return node; 
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    public AVLNode getParent(int key) {
        AVLNode node = getNode(root, key);
        return (node != null && node.parent != null) ? node.parent : null;
    }

    private AVLNode getNode(AVLNode node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean contains(int key) {
        return getNode(root, key) != null;
    }

    public int height() {
        return height(root);
    }

    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(AVLNode node, List<Integer> result) {
        if (node != null) {
            inOrderRecursive(node.left, result);
            result.add(node.key);
            inOrderRecursive(node.right, result);
        }
    }

    // Métodos para balanceamento
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        if (T2 != null) T2.parent = y;
        x.parent = y.parent;
        y.parent = x;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        if (T2 != null) T2.parent = x;
        y.parent = x.parent;
        x.parent = y;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode balance(AVLNode node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
}

class AVLNode {
    int key, height;
    AVLNode left, right, parent;

    AVLNode(int d) {
        key = d;
        height = 1;
        left = right = parent = null;
    }
}

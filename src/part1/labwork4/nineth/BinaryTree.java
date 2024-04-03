package part1.labwork4.nineth;

/**
 * A class representing a binary search tree.
 */
public class BinaryTree {
    /**
     * A class representing a node in the tree.
     */
    public static class Node {
        int key;
        String value;
        Node leftChild;
        Node rightChild;

        /**
         * Constructor for creating a node with a key and value.
         *
         * @param key  node key
         * @param name the value of the node
         */
        Node(int key, String name) {
            this.key = key;
            this.value = name;
        }

        @Override
        public String toString() {
            return "Key: " + key + " Value:" + value;
        }
    }

    private Node root;

    /**
     * Adds a node to the tree.
     *
     * @param key   node key
     * @param value the value of the node
     */
    public void addNode(int key, String value) {
        Node newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
        } else {
            Node currentNode = root;
            Node parent;
            while (true) {
                parent = currentNode;
                if (key < currentNode.key) {
                    currentNode = currentNode.leftChild;
                    if (currentNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    currentNode = currentNode.rightChild;
                    if (currentNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void traverseTree(Node currentNode) {
        if (currentNode != null) {
            traverseTree(currentNode.leftChild);
            System.out.println(currentNode);
            traverseTree(currentNode.rightChild);
        }
    }

    /**
     * Traverse the tree in ascending order of keys.
     */
    public void traverseTree() {
        traverseTree(root);
    }

    /**
     * Searches for a node by key.
     *
     * @param key key to find a node
     * @return found node or null if the node is not found
     */
    public Node findNode(int key) {
        Node focusNode = root;
        while (focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null) {
                return null;
            }
        }
        return focusNode;
    }

    /**
     * A method for handling the case when a node has zero children.
     *
     * @param currentNode the node to be removed
     * @param parent      parent node
     */
    private void handleZeroChildren(Node currentNode, Node parent) {
        if (parent == null) {
            root = null;
        } else if (parent.leftChild == currentNode) {
            parent.leftChild = null;
        } else {
            parent.rightChild = null;
        }
    }

    /**
     * A method for handling the case when a node has only a right child.
     *
     * @param currentNode the node to be removed
     * @param parent      parent node
     */
    private void handleRightChild(Node currentNode, Node parent) {
        if (parent == null) {
            root = currentNode.rightChild;
        } else if (parent.leftChild == currentNode) {
            parent.leftChild = currentNode.rightChild;
        } else {
            parent.rightChild = currentNode.rightChild;
        }
    }

    /**
     * A method for handling the case when a node has only a left child.
     *
     * @param currentNode the node to be removed
     * @param parent      parent node
     */
    private void handleLeftChild(Node currentNode, Node parent) {
        if (parent == null) {
            root = currentNode.leftChild;
        } else if (parent.leftChild == currentNode) {
            parent.leftChild = currentNode.leftChild;
        } else {
            parent.rightChild = currentNode.leftChild;
        }
    }

    /**
     * A method for handling the case when a node has both children.
     *
     * @param currentNode the node to be removed
     * @param parent      parent node
     */
    private void handleTwoChildren(Node currentNode, Node parent) {
        Node successorParent = currentNode;
        Node successor = currentNode.rightChild;

        while (successor.leftChild != null) {
            successorParent = successor;
            successor = successor.leftChild;
        }

        if (successorParent != currentNode) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = currentNode.rightChild;
        }
        successor.leftChild = currentNode.leftChild;

        if (parent == null) {
            root = successor;
        } else if (parent.leftChild == currentNode) {
            parent.leftChild = successor;
        } else {
            parent.rightChild = successor;
        }
    }

    /**
     * Method for performing node deletion by key.
     *
     * @param currentNode the node to be removed
     * @param parent      parent node
     */
    private void performNodeRemoval(Node currentNode, Node parent) {
        if (currentNode.leftChild == null && currentNode.rightChild == null) {
            handleZeroChildren(currentNode, parent);
        } else if (currentNode.leftChild == null) {
            handleRightChild(currentNode, parent);
        } else if (currentNode.rightChild == null) {
            handleLeftChild(currentNode, parent);
        } else {
            handleTwoChildren(currentNode, parent);
        }
    }

    /**
     * Removes a node by its key
     *
     * @param key node key
     */
    public boolean removeNode(int key) {
        Node currentNode = root;
        Node parent = null;

        while (currentNode != null) {
            if (key < currentNode.key) {
                parent = currentNode;
                currentNode = currentNode.leftChild;
            } else if (key > currentNode.key) {
                parent = currentNode;
                currentNode = currentNode.rightChild;
            } else {
                performNodeRemoval(currentNode, parent);
                return true;
            }
        }
        return false;
    }

    /**
     * Carries out testing of the functionality of the {@code BinaryTree} class.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BinaryTree continents = new BinaryTree();
        continents.addNode(1, "Європа");
        continents.addNode(3, "Африка");
        continents.addNode(5, "Австралія");
        continents.addNode(4, "Америка");
        continents.addNode(2, "Азія");
        continents.addNode(6, "Антарктида");
        continents.traverseTree();
        System.out.println("Тестування видалення вузлів за індексом:");
        System.out.println("Видалення вузла з невалідним індексом 56: " + continents.removeNode(56));
        continents.traverseTree();
        System.out.println("Видалення вузла з індексом 3(має обидва нащадки): " + continents.removeNode(3));
        continents.traverseTree();
        System.out.println("Видалення вузла з індексом 5(має одного правого нащадка): " + continents.removeNode(5));
        continents.traverseTree();
        System.out.println("Видалення вузла з індексом 6(немає нащадків): " + continents.removeNode(6));
        continents.traverseTree();
        System.out.println("Видалення вузла з індексом 4(має одного лівого нащадка): " + continents.removeNode(4));
        continents.traverseTree();
        System.out.println("\nКонтинент з ключем 4:");
        System.out.println(continents.findNode(4));
    }
}
package BinaryTree;

import java.util.ArrayList;

public class Tree {
    class Node{
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString(){
            return "Node = " + value;
        }
    }

    private Node root;

    public void insert(int value){
        var node = new Node(value);

        if(root == null){
            root = node;
            return;
        }

        Node current = root;
        while(true){
            if(value < current.value){
                if(current.leftChild == null){
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            }

            else{
                if(current.rightChild == null){
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value){
        if(root == null){
            return false;
        }

        var current = root;
        while(current != null){
            if(current.value == value){
                return true;
            }

            if(value < current.value){
                current = current.leftChild;
            }
            else{
                current = current.rightChild;
            }
        }

        return false;
    }

    public void traversePreOrder(){
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root){
        if(root == null)
            return;

        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }

    public boolean isBinarySearchTree(){
        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node node, int minRange, int maxRange){
        if(node == null)
            return true;

        if(node.value < minRange || node.value > maxRange)
            return false;

        return
            isBinarySearchTree(node.leftChild, minRange, node.value - 1)
            && isBinarySearchTree(node.rightChild, node.value + 1, maxRange);
    }

    public void traverseInOrder(){
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root){
        if(root == null)
            return;

        traverseInOrder(root.leftChild);
        System.out.println(root.value);
        traverseInOrder(root.rightChild);
    }

    public void traversePostOrder(){
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root){
        if(root == null)
            return;

        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value);
    }

    public int height(){
        return height(root);
    }

    private int height(Node root){
        if(root == null){
            return -1;
        }

        if(isLeaf(root)){
            return 0;
        }

        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    private static boolean isLeaf(Node root) {
        return root.leftChild == null && root.rightChild == null;
    }

    public int min(){
        return min(root);
    }

    private int min(Node root){
        if(isLeaf(root))
            return root.value;

        var left = min(root.leftChild);
        var right = min(root.rightChild);
        return Math.min(Math.min(left, right), root.value);
    }

    public boolean equals(Tree other){
        if(other == null)
            return false;

        return equals(root, other.root);
    }

    private boolean equals(Node first, Node second){
        if(first == null && second == null)
            return true;

        if(first != null && second != null){
            return first.value == second.value
                    && equals(first.leftChild, second.leftChild)
                    && equals(first.rightChild, second.rightChild);

        }
        return false;
    }

    public ArrayList<Integer> getNodesAtDistance(int k){
        var list = new ArrayList<Integer>();
        getNodesAtDistance(root, k, list);

        return list;
    }

    private void getNodesAtDistance(Node root, int distance, ArrayList<Integer> list){
        if(root == null)
            return;

        if(distance == 0){
            list.add(root.value);
            return;
        }

        getNodesAtDistance(root.leftChild, distance - 1, list);
        getNodesAtDistance(root.rightChild, distance - 1, list);
    }

    public void traverseLevelOrder(){
        for(var i = 0; i <= height(); i++){
            for(var value : getNodesAtDistance(i))
                System.out.println(value);
        }
    }
}

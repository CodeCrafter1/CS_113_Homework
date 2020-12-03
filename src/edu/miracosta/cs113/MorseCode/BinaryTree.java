package edu.miracosta.cs113.MorseCode;

import java.io.*;
import java.util.*;

/**
 *
 * @author
 */
public class BinaryTree<E> implements Serializable {

    protected static class Node<E> implements Serializable {

        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        public E getData() {
            return data;
        }

        public String toString() {
            return data.toString();
        }
    }

    protected Node<E> root;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        this.root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    //The input consists of a preorder traversal of a binary tree
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }

    public E getData() {
        return root.getData();
    }

}

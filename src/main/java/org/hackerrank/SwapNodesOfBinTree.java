package org.hackerrank;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;

import java.util.Scanner;

/**
 * Created by murali on 21-05-2015.
 */
public class SwapNodesOfBinTree {

    Scanner scanner = new Scanner(System.in);
    Node root = null;

    public static void main(String[] args) {

        SwapNodesOfBinTree swapNodesOfBinTree = new SwapNodesOfBinTree();
        swapNodesOfBinTree.buildTree();
        swapNodesOfBinTree.swapNodesAndPrint();
    }

    public void buildTree() {
        int nodeCount = scanner.nextInt();
        Node[] nodes = new Node[nodeCount];

        root = new Node(1, 1);
        nodes[0] = root;
        int nodeIndex = 0;

        while(nodeCount-- > 0) {
            int leftNode = scanner.nextInt();
            int rightNode = scanner.nextInt();
            Node currNode = nodes[nodeIndex];

            if (leftNode != -1) {
                Node leftChild = nodes[leftNode-1];
                if (leftChild == null) {
                    leftChild = new Node(leftNode, currNode.depth + 1);
                    nodes[leftNode-1] = leftChild;
                }
                currNode.left = leftChild;
            }

            if (rightNode != -1) {
                Node rightChild = nodes[rightNode-1];
                if (rightChild == null) {
                    rightChild = new Node(rightNode, currNode.depth + 1);
                    nodes[rightNode-1] = rightChild;
                }
                currNode.right = rightChild;
            }
            nodeIndex++;
        }
    }

    public void swapNodesAndPrint() {
        int testCaseCount = scanner.nextInt();

        while(testCaseCount-- > 0) {
            int depth = scanner.nextInt();

            swapNodes(root, depth);
            inorderTraversal(root);
            System.out.println();
        }
    }

    public void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.value + " ");
            inorderTraversal(root.right);
        }
    }

    public Node swapNodes(Node root, int depth) {
        if (root != null) {

            if (depth <= root.depth && root.depth % depth == 0) {
                Node leftTree = swapNodes(root.left, depth);
                Node rightTree = swapNodes(root.right, depth);
                root.left = rightTree;
                root.right = leftTree;
            } else {
                Node leftTree = swapNodes(root.left, depth);
                Node rightTree = swapNodes(root.right, depth);
            }
        }
        return root;
    }

    class Node {
        int value;
        int depth;
        Node left;
        Node right;

        Node(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
}

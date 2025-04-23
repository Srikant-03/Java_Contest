import java.util.*;

public class BalancedBinaryTree {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    static int index = 0;

    public static Node buildTree(String[] arr) {
        if (index >= arr.length) return null;

        int data = Integer.parseInt(arr[index++]);
        Node node = new Node(data);

        if (index < arr.length && arr[index].equals("true")) {
            index++;
            node.left = buildTree(arr);
        } else {
            index++;
        }

        if (index < arr.length && arr[index].equals("true")) {
            index++;
            node.right = buildTree(arr);
        } else {
            index++;
        }

        return node;
    }

    public static boolean isBalanced(Node root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(Node node) {
        if (node == null) return 0;

        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        String input = "10 true 20 true 40 false false true 50 false false true 30 false false";
        String[] arr = input.split(" ");

        Node root = buildTree(arr);
        System.out.println("Is the binary tree balanced? " + isBalanced(root));
    }
}
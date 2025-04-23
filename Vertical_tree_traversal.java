import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

class Pair {
    TreeNode node;
    int col;

    Pair(TreeNode node, int col) {
        this.node = node;
        this.col = col;
    }
}

public class Vertical_tree_traversal {
//-1 null hai
    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode curr = queue.poll();


            if (i < arr.length && arr[i] != -1) {
                curr.left = new TreeNode(arr[i]);
                queue.add(curr.left);
            }
            i++;

            if (i < arr.length && arr[i] != -1) {
                curr.right = new TreeNode(arr[i]);
                queue.add(curr.right);
            }
            i++;
        }

        return root;
    }

    public static void verticalOrder(TreeNode root) {
        if (root == null) return;

        TreeMap<Integer, ArrayList<Integer>> columnMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode node = pair.node;
            int col = pair.col;

            columnMap.putIfAbsent(col, new ArrayList<>());
            columnMap.get(col).add(node.val);

            if (node.left != null) queue.add(new Pair(node.left, col - 1));
            if (node.right != null) queue.add(new Pair(node.right, col + 1));
        }

        for (Map.Entry<Integer, ArrayList<Integer>> entry : columnMap.entrySet()) {
            for (int val : entry.getValue()) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {
            1, 2, 3,
            4, -1, 5, 6,
            -1, -1, -1, -1, 8, 7, -1, 9
        };

        TreeNode root = buildTree(arr);
        verticalOrder(root);    
    }
}

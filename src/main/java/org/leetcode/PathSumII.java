package org.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by murali on 08-05-2015.
 */
public class PathSumII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;

        PathSumII pathSumII = new PathSumII();
        System.out.println(pathSumII.pathSum(root, 3));
    }


    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        if (root == null) {
            return new ArrayList<>();
        }

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List elements = new LinkedList<>();
                elements.add(sum);
                List paths = new ArrayList<>();
                paths.add(elements);
                return paths;
            } else {
                List paths = new ArrayList<>();
                return paths;
            }
        }

        List<List<Integer>> leftChildpaths  = pathSum(root.left, sum - root.val);
        List<List<Integer>> rightChildpaths = pathSum(root.right, sum - root.val);
        List<List<Integer>> allPaths = new ArrayList<>();

        for (List path : leftChildpaths) {
            if (path.size() > 0) {
                path.add(0, root.val);
                allPaths.add(path);
            }
        }

        for (List path : rightChildpaths) {
            if (path.size() > 0) {
                path.add(0, root.val);
                allPaths.add(path);
            }
        }
        return allPaths;
    }
}

import java.util.Scanner

/**
 * Created by murali on 10-06-2015.
 */
object SwapNodesFunc {
  private var scanner: Scanner = new Scanner(System.in)
  private var root: Node = null

  def main(args: Array[String]) {
    buildTree
    swapNodesAndPrint
  }

  def buildTree {
    var nodeCount: Int = scanner.nextInt
    val nodes: Array[Node] = new Array[Node](nodeCount)
    root = new Node
    root.value = 1
    root.depth = 1
    nodes(0) = root
    var nodeIndex: Int = 0
    while (({
      nodeCount -= 1; nodeCount + 1
    }) > 0) {
      val leftNode: Int = scanner.nextInt
      val rightNode: Int = scanner.nextInt
      val currNode: Node = nodes(nodeIndex)
      if (leftNode != -1) {
        var leftChild: Node = nodes(leftNode - 1)
        if (leftChild == null) {
          leftChild = new Node
          leftChild.value = leftNode;
          leftChild.depth = currNode.depth + 1
          nodes(leftNode - 1) = leftChild
        }
        currNode.left = leftChild
      }
      if (rightNode != -1) {
        var rightChild: Node = nodes(rightNode - 1)
        if (rightChild == null) {
          rightChild = new Node
          rightChild.value = rightNode
          rightChild.depth = currNode.depth + 1
          nodes(rightNode - 1) = rightChild
        }
        currNode.right = rightChild
      }
      nodeIndex += 1
    }
  }

  def swapNodesAndPrint {
    var testCaseCount: Int = scanner.nextInt
    while (({
      testCaseCount -= 1; testCaseCount + 1
    }) > 0) {
      val depth: Int = scanner.nextInt
      swapNodesNameMod(root, depth)
      inorderTraversal(root)
      System.out.println
    }
  }


  def inorderTraversal(root: Node) {
    if (root != null) {
      inorderTraversal(root.left)
      System.out.print(root.value + " ")
      inorderTraversal(root.right)
    }
  }

  def swapNodesNameMod(root: Node, depth: Int): Node = {
    if (root != null) {
      if (depth <= root.depth && root.depth % depth == 0) {
        val leftTree: Node = swapNodesNameMod(root.left, depth)
        val rightTree: Node = swapNodesNameMod(root.right, depth)
        root.left = rightTree
        root.right = leftTree
      }
      else {
        val leftTree: Node = swapNodesNameMod(root.left, depth)
        val rightTree: Node = swapNodesNameMod(root.right, depth)
      }
    }
    return root
  }

   class Node {
    var value: Int = 0
    var depth: Int = 0
    var left: Node = null
    var right: Node = null

    private def this(value: Int, depth: Int) {
      this()
      this.value = value
      this.depth = depth
    }
  }
}

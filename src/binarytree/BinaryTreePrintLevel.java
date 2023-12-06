package binarytree;


import java.util.LinkedList;
import java.util.Queue;


/* Class to represent Tree Node1 */
class Node1 {
    int data;
    Node1 left, right;

    public Node1(int item)
    {
        data = item;
        left = null;
        right = null;
    }
}
/* Class to print Level Order Traversal */
public class BinaryTreePrintLevel {
 
    Node1 root;
 
    /* Given a binary tree. Print
     its Node1s in level order
     using array for implementing queue  */
    void printLevelOrder()
    {
        Queue<Node1> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
 
            /* poll() removes the present head.
            For more information on poll() visit
            http://www.tutorialspoint.com/java/
            util/linkedlist_poll.htm */
            Node1 tempNode1 = queue.poll();
            System.out.print(tempNode1.data + " ");
 
            /*Enqueue left child */
            if (tempNode1.left != null) {
                queue.add(tempNode1.left);
            }
 
            /*Enqueue right child */
            if (tempNode1.right != null) {
                queue.add(tempNode1.right);
            }
        }
    }
 
    public static void main(String args[])
    {
        /* creating a binary tree and entering
         the Node1s */
        BinaryTreePrintLevel tree_level = new BinaryTreePrintLevel();
        tree_level.root = new Node1(1);
        tree_level.root.left = new Node1(2);
        tree_level.root.right = new Node1(3);
        tree_level.root.left.left = new Node1(4);
        tree_level.root.left.right = new Node1(5);
 
        System.out.println("Level order traversal of binary tree is - ");
        tree_level.printLevelOrder();
    }
}

/*
Output
        Level Order traversal of binary tree is
        1 2 3 4 5
        Time Complexity: O(n) where n is the number of nodes in the binary tree
        Auxiliary Space: O(n) where n is the number of nodes in the binary tree

*/

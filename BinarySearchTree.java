import java.util.Scanner;

class Node{
    Node left;
    Node right;
    int data;
    boolean leftThread,rightThread;
    public Node(int data){
        this.data = data;
        this.rightThread = false;
        this.leftThread = false;
        this.left = null;
        this.right = null;
    }
}

class ThreadedBinaryTree {
    public Node root;
    public void insert(int id){
        Node newNode = new Node(id);
        Node current = root;
        Node parent = null;
        while(true){
            parent = current;
            if(id<current.data){
                if(!current.leftThread)
                {
                    current = current.left;
                    if(current==null){
                        parent.left = newNode;
                        //parent.leftThread = true;
                        newNode.right = parent;
                        newNode.rightThread = true;
                        return;
                    }
                }
              else{
                    Node temp = current.left;
                    current.left = newNode;
                    newNode.left = temp;
                    //newNode.leftThread=true;
                    return;
              }  
            }else{
                if(current.rightThread==false){
                    current = current.right;
                    if(current==null){
                        parent.right = newNode;
                        newNode.left = parent;
                        newNode.leftThread = true;
                        return;
                    }
                }else{
                    Node temp = current.right;
                    current.right = newNode;
                    newNode.right = temp;
                    newNode.rightThread=true;
                    newNode.left = current;
                    newNode.leftThread = true;
                    return;
                }
            }
        }
    }
    public void delete(int key) {
        Node dest = root.left, p = root;
        while(true) {
            if (dest.data < key) {
                if (dest.rightThread) return; // key not found
                p = dest;
                dest = dest.right;
            } else if (dest.data > key) {
                if (dest.leftThread) return; // key not found
                p = dest;
                dest = dest.left;
            } else {
                break; // found the key now we need to replace it
            }
        }
        Node target = dest;
        if (!dest.rightThread && !dest.leftThread) { // key node has two children
            p = dest;
            // find largest node at left child
            target = dest.left;
            while (!target.rightThread) {
                p = target;
                target = target.right;
            }
            dest.data = target.data; // replace key
        }
        if (p.data >= target.data) {
            if (target.rightThread && target.leftThread) {
                p.left = target.left;
                p.leftThread = true;
            } else if (target.rightThread) {
                Node largest = target.left;
                while (!largest.rightThread) {
                    largest = largest.right;
                }
                largest.right = p;
                p.left = target.left;
            } else {
                Node smallest = target.right;
                while (!smallest.leftThread) {
                    smallest = smallest.left;
                }
                smallest.left = target.left;
                p.left = target.right;
            }
        } else {
            if (target.rightThread && target.leftThread) {
                p.right = target.right;
                p.rightThread = true;
            } else if (target.rightThread) {
                Node largest = target.left;
                while (!largest.rightThread) {
                    largest = largest.right;
                }
                largest.right =  target.right;
                p.right = target.left;
            } else {
                Node smallest = target.right;
                while (!smallest.leftThread) {
                    smallest = smallest.left;
                }
                smallest.left = p;
                p.right = target.right;
            }
        }
    }
}
class BinaryTree extends ThreadedBinaryTree{
    public void inorder(Node root){
        Node current = leftMostNode(root);
        while(current!=null){
            System.out.print(" " + current.data);
            if(current.rightThread)
                current = current.right;
            else 
                {    if(current.right != null)
                    { current = current.right;
                        if(current.leftThread)
                        continue;
                    else
                        current = rightMostNode(current.right);
                    }
                else break; 
                }
            }    
        System.out.println();
    }
    public Node leftMostNode(Node node){
        if(node==null){
            return null;
        }else{
            while(node.left!=null){
                node = node.left;
            }
            return node;
        }
    }
    public void reverseinorder(Node root){
        Node current = rightMostNode(root);
        while(current!=null){
            System.out.print(" " + current.data);
            if(current.leftThread)
                current = current.left;
            else 
                {   if(current.left != null)
                       { current = current.left;
                    if(current.rightThread)
                    continue;
                    else
                    current = rightMostNode(current.left);
                       }
                    else break;   
                }
        }
        System.out.println();
    }
    public Node rightMostNode(Node node){
        if(node == null){
            return null;
        }else{
            while(node.right != null){
                node = node.right;
            }
            return node;
        }
    }
    
}

class BinarySearchTree{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTree bt = new BinaryTree();
        System.out.print("Enter the data for root node:");
        int data=sc.nextInt();
        bt.root = new Node(data);
        int ch = 4;
        do{
            System.out.println("Binary Tree Menu");
            System.out.println("1.Insert \n2.Delete \n3.Traversal\n4.Exit\nEnter your choice:");
            ch = sc.nextInt();
            switch(ch){
                case 1: System.out.print("Enter the data for node:");
                         data=sc.nextInt();
                         bt.insert(data);
                        break;
                case 2: 
                        break;
                case 3:System.out.print("Traversal menu\n1.Inorder\n2.postorder\n3.reverceinorder\nyour choice:");
                        int tc =sc.nextInt();
                        switch(tc){
                            case 1: bt.inorder(bt.root);
                                    break;
                            case 2: /*bt.postorder(bt.root);
                                    System.out.println();*/
                                    break;
                            case 3: bt.reverseinorder(bt.root);
                                    break;
                            default:
                        }
                        break;        
                case 4: break;//return;        
                default: System.out.println("Invalid operation!");
            }
        }while(ch<4);
        sc.close();
    }
}

import java.util.Scanner;
class Node{
    int data;
    Node left;
    Node right;
    boolean leftThread,rightThread;
    Node(int data){
        this.data = data;
        left = right = null;
        leftThread = rightThread = false;
    }
}
class ThreadedBinaryTree{
    public Node root;
    ThreadedBinaryTree(int data){
        root = new Node(data);
    }
    public void insert(int key,Node branch){
        Node newNode = new Node(key);
        Node current = branch;
        Node parent = null;
        while(true){
            parent = current;
            if(key<current.data){
                if(!current.leftThread){
                    current = current.left;
                    if(current==null){
                        parent.left = newNode;
                        parent.leftThread = false;
                        newNode.right = parent;
                        newNode.rightThread = true;
                        return;
                    }
                    else {
                        insert(key, current); 
                        return;
                    }
                }
                else{
                    Node temp = current.left;
                    current.left = newNode;
                    current.leftThread = false;
                    newNode.left = temp;
                    newNode.leftThread = true;
                    newNode.right = current;
                    newNode.rightThread = true;
                    return;
                }
            }      
            else{
                if(!current.rightThread){
                    current = current.right;
                    if(current==null){
                        parent.right = newNode;
                        parent.rightThread = false;
                        newNode.left = parent;
                        newNode.leftThread = true;
                        return;
                    }
                    else{
                        insert(key, current);
                        return;
                    }
                }
                else{
                    Node temp = current.right;
                    current.right = newNode;
                    current.rightThread = false;
                    newNode.right = temp;
                    newNode.rightThread=true;
                    newNode.left = current;
                    newNode.leftThread = true;
                    return;
                }   
            }
        }
    }
}
class BinaryTree extends ThreadedBinaryTree{
    BinaryTree(int data){
        super(data);
    }
    //functions to do inorder traversals
    public void inorder(Node root){
        Node current=leftMostChild(root);
        System.out.println("==============INORDER==============");
        while(current!=null){
            System.out.print(current.data + " ");
            if(current.rightThread)
                current=current.right;
            else
                current=leftMostChild(current.right);
        }
        System.out.println();
        System.out.println("====================================");
    }
    public void reverseInorder(Node root){
        Node current=rightMostChild(root);
        System.out.println("==============REVERSE_INORDER==============");
        while(current!=null){
            System.out.print(current.data + " ");
            if(current.leftThread)
                current=current.left;
            else
                current=rightMostChild(current.left);
        }
        System.out.println();
        System.out.println("=============================================");
    }
    //function to do preorder traversal
    public void preorder(Node current){
        if(current != null){
            System.out.print(current.data + " ");
            if(!current.leftThread)
                preorder(current.left);
            if(!current.rightThread)
                preorder(current.right);   
        }
    }
    //function to do postorder traversal
    public void postorder(Node current){
        if(current != null){
            if(!current.leftThread)
                postorder(current.left);
            if(!current.rightThread)
                postorder(current.right);
            System.out.print(current.data + " ");
        }
    }
    //function to find left most child
    public Node leftMostChild(Node node){
        if(node != null)
            while(node.left!=null && !node.leftThread)
               node = node.left;
        return node;
    }
    //function to find right most child   
    public Node rightMostChild(Node node){
        if(node != null)
            while(node.right!=null && !node.rightThread)
                node = node.right;
        return node;
    }
}
class Assignment{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int data=0;
        System.out.print("Enter the data for the root:");
        data = sc.nextInt();
        BinaryTree bt = new BinaryTree(data);
        
        int ch = 4;
        do{
            System.out.println("==========Binary Tree==========");
            System.out.println("1.Insert \n2.Traversal\n3.Exit\nEnter your choice:");
            ch = sc.nextInt();
            switch(ch){
                case 1: System.out.print("Enter the data for node:");
                         data=sc.nextInt();
                         bt.insert(data,bt.root);
                        break;
                case 2: System.out.print("==========Traversal==========\n1.Inorder\n2.ReverseInorder\n3.Preorder\n4.Postorder\nyour choice:");
                        int tc =sc.nextInt();
                        switch(tc){
                            case 1: bt.inorder(bt.root);
                                    break;
                            case 2: bt.reverseInorder(bt.root);
                                     break;
                            case 3: System.out.println("==============PREORDER==============");
                                    bt.preorder(bt.root);
                                    System.out.println();
                                    System.out.println("====================================");
                                    break;
                            
                            case 4: System.out.println("==============POSTORDER==============");
                                    bt.postorder(bt.root);
                                    System.out.println();
                                    System.out.println("=====================================");
                                    break;
                            default:
                        }
                        break;        
                case 3: ch =0; break;      
                default: System.out.println("Invalid operation!"); 
            }
        }while(ch>0);
        sc.close();
    }
}
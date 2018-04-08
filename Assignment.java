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
    public void insert(int id){
        Node newNode;
        Node current;
        int parentData;
        int ch;
        //to display all nodes
        inorder(root);
       
        System.out.print("\nEnter which node's child you want the new node to be : ");
        parentData = Assignment.sc.nextInt();

        //inorder traversal to find parent
        current = leftMostChild(root);
        while(current != null && current.data != parentData){
            if(current.rightThread)
                current=current.right;
            else
                current=leftMostChild(current.right);
        }
        if(current == null){
            //parent doesnt' exist
            System.out.println("The node you entered doesn't exist!\nAdding as BinarySerchTree\n");
            insert(id,root);
            return;
        }
        //now, current is parent
        //prompt for left or right child 
            System.out.print("\n Insert as \n1) Left child\n2) Right child\nEnter your choice : ");
            ch=Assignment.sc.nextInt();
            switch(ch){
                case 1 : //left child
                    if(current.leftThread || current.left==null){//thread means no child
                        //if left child doesn't exist,create new node add as left child
                        newNode=new Node(id);
                        //add threads to newNode as it has no child
                        newNode.right=current;
                        newNode.rightThread=true;
                        //thread of parent is now thread of child
                        newNode.left=current.left;
                        newNode.leftThread=current.leftThread;
                        //add as child
                        current.left=newNode;
                        //set the thread value as false since it now has child 
                        current.leftThread=false;
                    }
                    else{
                        //replace left child
                        newNode=current.left;
                        newNode.data=id;
                    }
                    break;
                case 2 : //right child
                    if(current.rightThread || current.right==null){//thread means no child
                        //if right child doesn't exist,create new node and add as right child
                        newNode=new Node(id);
                        //add threads to newNode as it has no child
                        newNode.left=current;
                        newNode.leftThread=true;
                        //thread of parent is now thread of child
                        newNode.right=current.right;
                        newNode.rightThread=current.rightThread;
                        //add as child
                        current.right=newNode;
                        //set the thread value as false since it now has child
                        current.rightThread=false;
                    }
                    else{
                        //replace right child
                        newNode=current.right;
                        newNode.data=id;
                    }
                    break;
                default : System.out.println("Invalid option!");
            }
        return;
    }
    //function to delete a node
    public void delete(int key){
        if(key == root.data)
        {
            System.out.println("Can't Delete The Root!");
            return;
        }
        Node current = leftMostChild(root);
        while(current != null && current.data != key){
            if(current.rightThread)
                current=current.right;
            else
                current=leftMostChild(current.right);
        }
        if(current == null){
            //Serch node doesnt' exist
            System.out.println("The node doesn't exist!");
            return;
        }
        //now current is the target
        if(!current.leftThread && current.rightThread)
        {   current = current.right;
            current.left = null;
        }
        else if(current.leftThread && !current.rightThread)
        {
            current = current.left;
            current.right = null;
        }
        else{
            Node temp = current.right;
            current = current.left;
            temp.left = null;
        }    
    inorder(root);        
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
    //function to find left most child
    public Node leftMostChild(Node node){
        if(node != null)
            while(node.left!=null && !node.leftThread)
               node = node.left;
        return node;
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
    
    //function to find right most child   
    public Node rightMostChild(Node node){
        if(node != null)
            while(node.right!=null && !node.rightThread)
                node = node.right;
        return node;
    }
}
class Assignment{
    public static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int data=0;
        System.out.print("Enter the data for the root:");
        data = sc.nextInt();
        BinaryTree bt = new BinaryTree(data);
        int ch = 4;
        do{
            System.out.println("==========Binary Tree==========");
            System.out.println("1.Insert \n2.Delete\n3.Traversal\n4.Exit\nEnter your choice:");
            ch = sc.nextInt();
            switch(ch){
                case 1: System.out.print("Enter the data for node:");
                         data=sc.nextInt();
                         bt.insert(data);//bt.insert(data,bt.root);
                        break;
                case 2: System.out.print("Enter the key to delete:");
                        data=sc.nextInt();
                        bt.delete(data);
                        break;
                case 3: System.out.print("==========Traversal==========\n1.Inorder\n2.ReverseInorder\n3.Preorder\n4.Postorder\nyour choice:");
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
                case 4: ch =0; break;
                default: System.out.println("Invalid operation!"); 
            }
        }while(ch>0);
        sc.close();
    }
}
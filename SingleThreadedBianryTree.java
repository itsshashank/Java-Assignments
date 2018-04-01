import java.util.Scanner;

class SingleThreadedBinaryTree {
    public static Node root;
    public void delete(int key) {
        Node dest = root.left, p = root;
        for (;;) {
            if (dest.data < key) {
                if (dest.rightThread) return; // not found
                p = dest;
                dest = dest.right;
            } else if (dest.data > key) {
                if (dest.leftThread) return; // not found
                p = dest;
                dest = dest.left;
            } else {
                break; // bingo
            }
        }
        Node target = dest;
        if (!dest.rightThread && !dest.leftThread) { // dest has two children
            p = dest;
            // find largest node at left child
            target = dest.left;
            while (!target.rightThread) {
                p = target;
                target = target.right;
            }
            dest.data = target.data; // using replace mode
        }
        if (p.data >= target.data) {
            if (target.rightThread && target.leftThread) {
                //   p
                //  /
                //  t
                p.left = target.left;
                p.leftThread = true;
            } else if (target.rightThread) {
                //      p
                //     /
                //    t
                //   /
                // t.left
                Node largest = target.left;
                while (!largest.rightThread) {
                    largest = largest.right;
                }
                largest.right = p;
                p.left = target.left;
            } else {
                //      p
                //     /
                //    t
                //     \
                //     t.right
                Node smallest = target.right;
                while (!smallest.leftThread) {
                    smallest = smallest.left;
                }
                smallest.left = target.left;
                p.left = target.right;
            }
        } else {
            if (target.rightThread && target.leftThread) {
                //   p
                //    \
                //     t
                p.right = target.right;
                p.rightThread = true;
            } else if (target.rightThread) {
                //   p
                //    \
                //    t
                //   /
                // t.left
                Node largest = target.left;
                while (!largest.rightThread) {
                    largest = largest.right;
                }
                largest.right =  target.right;
                p.right = target.left;
            } else {
                //   p
                //    \
                //    t
                //     \
                //   t.right
                Node smallest = target.right;
                while (!smallest.leftThread) {
                    smallest = smallest.left;
                }
                smallest.left = p;
                p.right = target.right;
            }
        }
    }
    public void insert(int id){
        Node newNode = new Node(id);
        newNode.rightThread = false;
        newNode.left = null;
        newNode.right = null;
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
    public void print(Node root){
        //first go to most left node
        Node current = leftMostNode(root);
        //now travel using right pointers
        while(current!=null){
            System.out.print(" " + current.data);
            //check if node has a right thread
            if(current.rightThread)
                current = current.right;
            else // else go to left most node in the right subtree
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
    /*public void Inorder(Node root){
        Node current =
    }*/
    /*Node presuc(Node root)
    {   
        /*if(root.visited)
            {//root = root.left;
                if(!root.left.visited)
                    return root.left;
                else
                    return root.right;
            }*/
        /*if(root.visited && root.rightThread)
            return root.right;    
        /*if(root.leftThread)
            return root.left;*/
        /*if(!root.rightThread){
            while(!root.rightThread)
            { root = root.right;
            }
            return root.right;
        }    
        //if(root.rightThread)
        else    {   root = root.right;
                    if(root.right != null)
                    return root.right;}
        /*while(!root.rightThread)
        { root = root.right;
              
        }
        return root.right;*/  
        /*return null;   
    }*/
    Node presuc(Node temp)
    {
        if(temp == root)
            return temp;
        if(temp.visited)
            if(temp.right != null)
                return temp.right;
            //else return temp.left;        
        if(!temp.leftThread && temp.rightThread)
        {   return temp.right;
            /*temp = temp.right;
            if(temp.right != null && !temp.rightThread)
                return temp.right;
            else return temp;  */  
        }
        if(temp.leftThread && temp.rightThread)
            return temp.left;
        if(!temp.leftThread && !temp.rightThread)
        {   temp = temp.right;
            if(temp.rightThread)
                {   temp = temp.right;
                       return temp;
                    }
                }
        return null;    
    }
    public void preorder(Node root){
        //if(root.left != null)
            Node current = leftMostNode(root);//root.left;
        //else return;    
        while(current != root)
        {   
            current.visited =true;
        System.out.print(" "+current.data);
        current=presuc(current);
        }
        System.out.print(" "+root.data);
        /*if(current.left != null)
            preorder(current.left);
        if(!current.rightThread)
            if(current.right != null)    
                preorder(current.right); */               
    }
    public void postorder(Node root){
        //first go to most left node
        Node current = rightMostNode(root);
        //now travel using right pointers
        while(current!=null){
            System.out.print(" " + current.data);
            //check if node has a right thread
            if(current.leftThread)
                current = current.left;
            else // else go to left most node in the right subtree
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the data for root node:");
        int data=sc.nextInt();
        root = new Node(data);
        SingleThreadedBinaryTree st = new SingleThreadedBinaryTree();
        int ch = 4;
        do{
            System.out.println("Binary Tree Menu");
            System.out.println("1.Insert \n2.Delete \n3.Traversal\n4.Exit\nEnter your choice:");
            ch = sc.nextInt();
            switch(ch){
                case 1: System.out.print("Enter the data for node:");
                         data=sc.nextInt();
                         st.insert(data);
                        break;
                case 2:  System.out.print("Enter the key to delete:");
                            data=sc.nextInt();
                            st.delete(data);
                            System.out.println("The Values after deletion");
                            st.print(root);
                        break;
                case 3:System.out.print("Traversal menu\n1.Inorder\n2.preorder\n3.postorder\nyour choice:");
                        int tc =sc.nextInt();
                        switch(tc){
                            case 1: st.print(root);
                                    break;
                            case 2: st.preorder(root);
                                    System.out.println();
                                    break;
                            case 3: st.postorder(root);
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
class Node{
    Node left;
    Node right;
    int data;
    boolean leftThread,rightThread,visited;
    public Node(int data){
        this.data = data;
        rightThread = false;
        leftThread = false;
        visited = false;
    }
}

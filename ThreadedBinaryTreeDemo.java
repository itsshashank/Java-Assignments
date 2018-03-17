import java.util.Scanner;
class Node
{
    int value;
    Node left, right;
    boolean leftThread, rightThread;
    public Node(int value)
    {
        this(value, null, null, true, true);
    }
    public Node(boolean leftThread, boolean rightThread)
    {
        this.value = Integer.MAX_VALUE;
        this.left = this;
        this.right = this;
        this.leftThread = leftThread;
        this.rightThread = rightThread;
    }
    public Node(int value, TBSTNode left, TBSTNode right, boolean leftThread, boolean rightThread)
    {
        this.value = value;
        this.left = left;
        this.right = right;
        this.leftThread = leftThread;
        this.rightThread = rightThread;
    }
}
class ThreadedBinaryTree
{
    private Node root;
    public ThreadedBinaryTree () 
    {
        root = new Node(true, false);      
    }
    public void clear()
    {
        root = new Node(true, false);  
    }
    public void insert(int value) 
    {
        Node ptr = findNode(root, value);
           if (ptr == null)
            return;         
        if (ptr.value < value) 
        { 
            Node nptr = new Node(value, ptr, ptr.right, true, true);            
            ptr.right = nptr;
            ptr.rightThread = false;
        }
        else
        {
            TBSTNode nptr = new TBSTNode(ele, ptr.left, ptr, true, true);         
            ptr.left = nptr;
            ptr.leftThread = false;
        }
    }
    public TBSTNode findNode(TBSTNode r, int ele)
    {
        if (r.ele < ele)
        {
            if (r.rightThread)

                return r;

            return findNode(r.right, ele);
        }
        else if (r.ele > ele)
        {

            if (r.leftThread)

                return r;
            return findNode(r.left, ele);
        }
        else
            return null;        
    }

    public boolean search(int ele) 
    {
        return findNode(root, ele) == null;
    }

    public void inOrder() 
    {
        TBSTNode temp = root;

        for (;;)

        {

            temp = insucc(temp);

            if (temp == root)

                break;

            System.out.print(temp.ele +" ");

        }

    } 

    public TBSTNode insucc(TBSTNode tree)

    {

        TBSTNode temp;

        temp = tree.right;

        if (!tree.rightThread)

            while (!temp.leftThread)

                temp = temp.left;

        return temp;

    }

}

public class ThreadedBinaryTreeDemo

{

    public static void main(String[] args)

    {                 

        Scanner scan = new Scanner(System.in);

        /** Creating object of ThreadedBinarySearchTree **/

        ThreadedBinarySearchTree tbst = new ThreadedBinarySearchTree(); 

        System.out.println("Threaded Binary Search Tree Test\n");          

        char ch;
        do    

        {

            System.out.println("\nThreaded Binary Search Tree Operations\n");

            System.out.println("1. insert ");

            System.out.println("2. search");

            System.out.println("3. clear"); 
            int choice = scan.nextInt();            
            switch (choice)
            {

            case 1 : 

                System.out.println("Enter integer element to insert");

                tbst.insert( scan.nextInt() );                     

                break;                          

            case 2 : 

                System.out.println("Enter integer element to search");

                System.out.println("Search result : "+ tbst.search( scan.nextInt() ));

                break;       

            case 3 : 

                System.out.println("\nTree Cleared\n");

                tbst.clear();

                break;           

            default : 

                System.out.println("Wrong Entry \n ");

                break;   

            }
            System.out.print("\nTree = ");
            tbst.inOrder();            
            System.out.println();
            System.out.println("\nDo you want to continue (Type y or n) \n");

            ch = scan.next().charAt(0);                        

        } while (ch == 'Y'|| ch == 'y');               
    }
}
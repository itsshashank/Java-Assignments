import java.util.Scanner;

public class Main {

	static Node root;
	static Node current;
	static boolean directionRight = false;
	static boolean directionLeft = false;

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the data for root node:");
        int data = sc.nextInt();
        root = new Node(data);

        do{
            System.out.println("Binary Tree Menu");
            System.out.println("1.Insert \n2.Delete \n3.Traversal\n4.Exit\nEnter your choice:")
            int ch = sc.nextInt();
            switch(ch){
                case 1:
                        break;
                case 2: 
                        break;
                case 3:System.out.print("Traversal menu\n1.Inorder\n2.preorder\n3.postorder\nyour choice:");
                        int tc =sc.nextInt();
                        switch(tc){
                            case 1:
                                    break;
                            case 2:
                                    break;
                            case 3:
                                    break;
                            default:
                        }
                        break;        
                case 4: return 0;        
                default: System.out.println("Invalid operation!");
            }
        }while(ch<4);

		System.out.println("Output for recursive inOrder!");
		inOrderRecursive(root.left);
		System.out.println();

		System.out.println("Output for recursive inOrder!");
		inOrder();
		System.out.println();

	}

	public static void inOrder() {

		current = root.left;

		while (current.lBit == 1) {

			current = current.left;

		}
		
		while(current != root){
			
			System.out.print(" -> " + current.data);
			current = nextInOrder(current);
			
		}
        sc.close();
	}

	public static Node nextInOrder(Node next) {
		
		if(next.rBit == 0){
			
			return next.right;
			
		}
		
		next = next.right;
		
		while(next.lBit == 1){
			
			next = next.left;
			
		}
		
		return next;
		
	}

	public static void inOrderRecursive(Node temp) {

		if (temp != root) {

			if (temp.lBit != 0)
				inOrderRecursive(temp.left);

			System.out.print(temp.data + " -> ");

			if (temp.rBit != 0)
				inOrderRecursive(temp.right);

		}

	}

	public static void createNode(int data) {

		Node node = new Node(data);

		if (root.left == root && root.right == root) {

			node.lBit = root.lBit;
			node.left = root.left;
			root.left = node;
			root.lBit = 1;
			node.rBit = 0;
			node.right = root;

		} else {

			current = root.left;

			while (true) {

				if (node.data < current.data) {

					if (current.lBit == 0) {

						directionLeft = true;
						directionRight = false;
						break;

					} else {

						current = current.left;

					}

				} else {

					if (current.rBit == 0) {

						directionLeft = false;
						directionRight = true;
						break;

					} else {

						current = current.right;

					}

				}

			}

			if (directionLeft) {

				node.lBit = current.lBit;
				node.left = current.left;
				current.left = node;
				current.lBit = 1;
				node.rBit = 0;
				node.right = current;

			} else if (directionRight) {

				node.rBit = current.rBit;
				node.right = current.right;
				current.right = node;
				current.rBit = 1;
				node.lBit = 0;
				node.left = current;

			} else {

				System.out.println("Program is a fail!");

			}

		}

		System.out.println("Nodes added!");

	}

}

class Node {

	int data;

	Node left;
	Node right;

	int lBit;
	int rBit;

	Node(int data) {

		this.data = data;

	}

}
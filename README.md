# Java-Assignments

## Threaded_Binary_Tree

A threaded binary tree is a binary tree variant that allows fast traversal: given a pointer to a node in a threaded tree, it is possible to cheaply find its in-order successor (and/or predecessor).
Binary trees have a lot of wasted space: the leaf nodes each have 2 null pointers
We can use these pointers to help us in inorder traversals
We have the pointers reference the next node in an inorder traversal; called threads
We need to know if a pointer is an actual link or a thread, so we keep a boolean for each pointer

### In-order traversal
Traversing the threaded binary tree will be quite easy, no need of any recursion or any stack for storing the node. Just go to the left most node and start traversing the tree using right pointer and whenever rightThread = false again go to the left most node in right subtree.

```java
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
```

## Reverse In-order Traversal
Its similar to that of In-order just that we need to do in reverse order

```java
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
```

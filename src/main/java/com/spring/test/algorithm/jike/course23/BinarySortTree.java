package com.spring.test.algorithm.jike.course23;

/**
 * @author hlshi3
 * @date 2021/1/26 14:52
 */
public class BinarySortTree {

    Node root; //根

    public BinarySortTree(){
        root = null;
    }

    public BinarySortTree(Node node) {
        this.root = node;
    }

    //置空
    public void makeEmpty(){
        this.root = null;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public Node findmin(Node t){//查找最小返回值是node，调用查看结果时需要.value
        //递归获取最左叶子结点
        if(t == null){
            return null;
        }
        if(t.left == null){
            return t;
        }
        return findmin(t.left);
    }

    public Node findMax(Node t){
        //递归获取最右叶子结点
        if(t == null){
            return null;
        }
        if(t.right == null){
            return t;
        }
        return findMax(t.right);
    }

//    public boolean isContains(int x){
//        Node currentNode = root;
//        if(currentNode == null){
//            return false;
//        }
//        while(currentNode.value != x && currentNode != null){
//            if(x < currentNode.value){
//                currentNode = currentNode.left;
//            }else if(x > currentNode.value){
//                currentNode = currentNode.right;
//            }
//            if(currentNode == null){
//                return false;
//            }
//        }
//        if(currentNode.value == x){
//            return true;
//        }
//        return false;
//    }

    public Node find(int data) {
        Node p = root;
        while (p != null) {
            if (data < p.value) p = p.left;
            else if (data > p.value) p = p.right;
            else return p;
        }
        return null;
    }


    public Node insert(int x){
        Node currentNode = root;
        if(root == null){
            root = new Node(x);
            return root;
        }
        while(currentNode != null){
            if(x < currentNode.value){
                if(currentNode == null){
                    currentNode.left = new Node(x) ;
                    return currentNode.left;
                }else{
                    currentNode = currentNode.left;
                }
            }else if(x > currentNode.value){
                if(currentNode == null){
                    currentNode.right = new Node(x); ;
                    return currentNode.right;
                }else{
                    currentNode = currentNode.right;
                }
            }
        }
        return currentNode;
    }

    /**
     * 二叉查找树要求，在树中的任意一个节点，其左子树中的每个节点的值，都要小于这个节点的值，而右子树节点的值都大于这个节点的值。
     *
     * 第一种情况 要删除的节点没有子节点 只需要找到父节点，将指向要删除的节点设置为null
     * 第二种情况 要删除的节点只有一个子节点（左节点or右节点），找到父节点，指向要删除节点的指针指向删除节点的子节点
     * 第三种情况 要删除的节点有左右节点 我们需要找到这个节点的右子树的最小节点，把它替换到要删除的节点上，然后再删除这个最小节点
     * @param data
     */
    public  void delete(int data){
        Node p = root; //p指向要删除的节点，初始化指向根节点
        Node pp = null; //pp为p的父节点
        while (p != null && p.value != data) {
            pp = p;
            if(data > p.value){
                p = p.right;
            }else{
                p = p.left;
            }
        }
        if(p == null){
           return;  //没找到
        }
        //存在左右节点  则查找右子树中的最小节点
        if(p.left != null && p.right != null){
            Node minP = p.right;
            Node minPP = p; // minPP标记为minP的父节点
            while(minP.left != null){
                minPP = minP;
                minP = minP.left;  //通过while循环获取右子树的最小节点
            }
            p.value =minP.value; //将最小节点的值赋值到被删除节点上  这时候只需要删除最左节点就可以了
            p = minP;     //p标记在minp上，后续代码一起删除
            pp = minPP;
        }

        //删除的节点为叶子结点或者只有一个子节点
        Node child; //p的子节点
        if(p.left != null){
            child = p.left;
        }else if(p.right != null){
            child = p.right;
        }else{
            child = null;
        }
        
        if(pp == null){
            root = child; //删除了根节点
        }else if(pp.left == p){
            pp.left = child;
        }else {
            pp.right =child;
        }

    }

}

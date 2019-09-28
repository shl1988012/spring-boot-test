package com.spring.test.algorithm;

import java.util.Stack;

//7：用两个栈实现队列
public class Test7 {


    public class CQueue<T> {
        private Stack<T> stack1;
        private Stack<T> stack2;

        public CQueue(){
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }
        //模拟尾部插入数据
        public void appendTail(T node){
            stack1.push(node);
        }

        //模拟删除队列的头数据
        public T delHead(){
            if(stack2.isEmpty()){
                if(stack1.isEmpty()){
                    try {
                        throw new Exception("queue is null ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    while(!stack1.isEmpty()){
                        stack2.push(stack1.pop());
                    }
                }
            }
             return  stack2.pop();
        }
    }


    public static void main(String[] args) {

        CQueue<Integer> queue = new Test7().new CQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        System.out.println(queue.delHead());
        queue.appendTail(4);
        System.out.println(queue.delHead());
        queue.appendTail(5);
        System.out.println(queue.delHead());
        System.out.println(queue.delHead());
        System.out.println(queue.delHead());

    }
}

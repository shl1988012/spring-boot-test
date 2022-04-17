package com.spring.test.algorithm.niuke.堆栈队列;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min 函数，输入操作时保证 pop、top 和 min 函数操作时，栈中一定有元素。
 * 43512入栈
 * 43311 辅助栈
 * @author hlshi3
 * @date 2022/4/17 23:23
 */
public class BM43 {

    // 存放数据的栈
    Stack<Integer> stack = new Stack<Integer> ();
    // 辅助栈，用于存当前栈最小值
    Stack<Integer>  minStack = new Stack<Integer> ();

    public void push(int node) {
        stack.push(node);
        if(minStack.isEmpty()){
            minStack.push(node);
        }else{
            //毕竟minstack的栈顶元素和node的大小，如果栈顶元素<node ,则继续往minstack中保存一次栈顶元素； 如果栈顶元素>node ,则保存node
            if(minStack.peek()> node){
                minStack.push(node);
            }else{
                minStack.push(minStack.peek());
            }
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}

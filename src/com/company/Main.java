package com.company;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(7);
        stack.push(4);
        stack.push(8);
        stack.push(1);
        stack.push(8);

        Stack<Integer> result = new Handler(stack).sortStack();
        while (result!=null && !result.empty()){
            System.out.println(result.pop());
        }
    }
}

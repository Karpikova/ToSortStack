package com.company;

import java.util.*;

public class Handler {

    //Я буду называть в комментариях стэки, коих три, башнями. Мне просто так нравится.

    Stack<Integer> stackMain = new Stack<>();              //number 0
    Stack<Integer> st1;    //number 1
    Stack<Integer> st2 = new Stack<>();    //number 2
    HashMap<Integer, ArrayList<Integer>> order = new HashMap<>(); //ключ - номер блока, но они кстати упорядочены, ArrayList[0] - номер башни, ArrayList[1] - кол-во элементов в блоке
    Integer countOfBlocks = 0;
    Stack<Integer> result;
    Integer countOfAllElements = 0;

    public Handler(Stack<Integer> st1) {
        //поскольку перед первой операцией нам придется посчитать количество элементов в главной башне, поэтому в самом начале я помещаю ее
        //в st1, а потом перекладываю в stackMain и в процессе считаю.
        this.st1 = st1;
    }

    public Stack<Integer> sortStack(){
        if (st1.empty()){
            System.out.println("Stack is empty.");
            return null;
        }
        else {
            countMainStack();
            sortStackRecursive();
            return result;
        }
    }

    private void countMainStack() {
        while (!st1.empty()){
            stackMain.push(st1.pop());
            countOfAllElements++;
        }
    }

    public void sortStackRecursive(){

        if (countOfBlocks == 0 && stackMain.empty()){
            return;
        }
        Integer pivot;
        Stack<Integer> workStack = null;
        Stack<Integer> workStackForBigElements = null;
        Stack<Integer> workStackForSmallElements = null;
        Integer countOfElements;
        Integer numberOfSmallStack = 0;
        Integer numberOfBigStack = 0;
        if (countOfBlocks == 0) {
            workStack = stackMain;
            workStackForBigElements = st1;
            numberOfBigStack = 1;
            workStackForSmallElements = st2;
            numberOfSmallStack = 2;
            countOfElements = countOfAllElements;
        } else {
            switch (order.get(countOfBlocks).get(0)){
                case 0:
                    workStack = stackMain;
                    workStackForBigElements = st1;
                    numberOfBigStack = 1;
                    workStackForSmallElements = st2;
                    numberOfSmallStack = 2;
                case 1:
                    workStack = st1;
                    workStackForBigElements = stackMain;
                    numberOfBigStack = 0;
                    workStackForSmallElements = st2;
                    numberOfSmallStack = 2;
                case 2:
                    workStack = st1;
                    workStackForBigElements = st2;
                    numberOfBigStack = 2;
                    workStackForSmallElements = stackMain;
                    numberOfSmallStack = 0;
            }
            countOfElements = order.get(countOfBlocks).get(1);
        }

        pivot = workStack.peek();
        int countSmallBlock = 0;
        int countBigBlock = 0;

        if (countOfElements==1){
            result.push(workStack.pop());
            countOfBlocks--;
            return;
        }

        for (int i = 0; i < countOfElements; i++) {
            Integer pop = workStack.pop();
            if (pop>pivot){
                workStackForBigElements.push(pop);
                countBigBlock++;
            }
            else {
                workStackForSmallElements.push(pop);
                countSmallBlock++;
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(numberOfBigStack);
        list.add(countBigBlock);
        order.put(++countOfBlocks, list);

        list.clear();
        list.add(numberOfSmallStack);
        list.add(countSmallBlock);
        order.put(++countOfBlocks, list);
    }
}

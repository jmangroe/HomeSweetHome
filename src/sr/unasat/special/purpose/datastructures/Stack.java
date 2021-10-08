package sr.unasat.special.purpose.datastructures;

    public class Stack {
       private final int SIZE = 20;//size vermelden
       private int[] stackArray;
       private int top;

    public Stack() {
        stackArray = new int[SIZE];
        top = -1;
    }

    public void push(int item) {
        stackArray[++top] = item;
    }

    public int pop() {
        return stackArray[top--];
    }
    public int peek() { // peek at top of stack

        return stackArray[top];
    }
    public int peekAtIndex(int index) {

        return stackArray[index];
    }
    public boolean isEmpty(){
        return (top == -1);
    }

    public int size() {
        return stackArray.length;
    }

    public void printStack() {
        for (int values : stackArray) {
            if (values != 0) {
                System.out.println(values);
            }
        }
    }
}


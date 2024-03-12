package com.company;

public class ArrayStack<T>{

    T [] stack;
    int size;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        stack=(T[])(new Object[capacity]);
        size=0;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public boolean isFull() {
        return size==stack.length;
    }

    public T top()
    {
        return stack[size -1];
    }

    public T nextToTop()
    {
        return stack[size-2];
    }

    public T pop()
    {
        return stack[--size];
    }

    public void push(T value)
    {
        stack[size++]=value;
    }

    public int size()
    {
        return size;
    }
}

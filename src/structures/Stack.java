package structures;

import java.util.List;

/*
Pilha de elementos não repetidos.
*/

public class Stack {

    private int[] stack;
    private int top, size, base;

    public Stack(int size){
        stack = new int[size];
        top = -1;
        base = 0;
        this.size = size;
    }

    public int search(int x){
        if(top == -1) return -1;
        for(int i = 0; i < (top+1); i++)if(stack[i] == x) return i;
        return -1;
    }

    public void add(int x){
        remove(x);
        top++;
        if(top == size) {
            top = 0;
            stack[top] = x;
            base++;
            if(base == size)
                base = 0;
            return;
        }
        stack[top] = x;
    }

    public void addAll(List<Integer> list){ for(int i : list) add(i); }

    public void remove(int x){
        int loc = search(x);
        if(loc == -1) return;
        while(loc < top){
            stack[loc] = stack[loc+1];
            loc++;
        }
        top--;
    }

    public void removeAll(List<Integer> list){ for(int i : list) remove(i); }

    public int getBase(){ return base; }

    public void incrementBase(){ base++; }

    public int[] getStack(){
        return stack;
    }
}


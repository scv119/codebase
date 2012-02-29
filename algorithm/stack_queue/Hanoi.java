import java.util.*;

public class Hanoi{
    private List<Integer> rod;
    
    public Hanoi(){
        rod = new LinkedList<Integer>();
    }

    public Hanoi(int top, int bottom){
        rod = new LinkedList<Integer>();
        for(int i = bottom; i >= top ; i --){
            push(i);
        }
    }


    public boolean push(int i){
        if(peek() != null && peek() < i)
            return false;
        rod.add(i);        
        return true;
    }

    public Integer pop(){
        if(rod.size() == 0)
            return null;
        return rod.remove(rod.size() - 1);
    }

    public Integer peek(){
        if(rod.size() == 0)
            return null;
        return rod.get(rod.size() - 1);
    }

    public boolean moveTopTo(Hanoi des){
        Integer top = null;
        if(des == null || (top = pop()) == null)
            return false;
        
        if(!des.push(top)){
            push(top);
            return false;
        }
        return true;
    }
    
    public void print(){
        System.out.println("Start"); 
        for(Integer i:rod){
            System.out.print(i+",");
        }
        System.out.println("End");
    }

    public static void move(Hanoi from, Hanoi to, Hanoi tmp, int size){
        if(size > 0){
            move(from, tmp, to, size - 1);
            from.moveTopTo(to);
            move(tmp,to,from, size - 1);
        }
    }

    public static void main(String args[]){
        Hanoi h1 = new Hanoi(1,10);
        Hanoi h2 = new Hanoi();
        Hanoi h3 = new Hanoi();

        h1.print();
        h2.print();
        h3.print();

        Hanoi.move(h1,h2,h3,10);

        h1.print();
        h2.print();
        h3.print();
    }
}

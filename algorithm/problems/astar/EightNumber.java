import java.util.*;
public class EightNumber{
    public class Node{
        int state;
        int depth;
        int g;
        Node pre;
        public Node(int state){
            this.state = state;
        }
        
        int weight(){
            return depth + g;
        }
        String sState(){
            String s= state + "";
            if(s.length() == 8)
                s = "0"+s;
            return s;
        }
        List<Integer> otherState(){
            List<Integer> result = new LinkedList<Integer>();
            String sstate = sState();
            int pos = 0;
            for(;pos < sstate.length(); pos ++){
                if(sstate.charAt(pos) == '0')
                    break;
            }
            int x = pos / 3;
            int y = pos % 3;
            int new_x;
            int new_y;
            char[] new_array = new char[9];
            for(int j = -1; j <=1 ;j +=2){
                new_x = j + x;
                new_y = y;
                if(new_x >= 0 && new_x <= 2){
                    for(int k = 0 ; k < 9; k ++){
                        new_array[k] = sstate.charAt(k);
                     }
                    int new_pos = new_x * 3 + new_y; 
                    char tmp = new_array[pos];
                    new_array[pos] = new_array[new_pos];
                    new_array[new_pos] = tmp;
                    result.add(Integer.parseInt(new String(new_array)));
                }
                new_x = x;
                new_y = j + y;
                if(new_y >= 0 && new_y <= 2){
                    for(int k = 0 ; k < 9; k ++){
                        new_array[k] = sstate.charAt(k);
                     }
                    int new_pos = new_x * 3 + new_y; 
                    char tmp = new_array[pos];
                    new_array[pos] = new_array[new_pos];
                    new_array[new_pos] = tmp;
                    result.add(Integer.parseInt(new String(new_array)));
                }
            }
            return result;
        }
    }

    int get_g ( Node cur, Node des){
        int diff = 0;
        String cstate = cur.sState() ;
        String dstate = des.sState() ;
        for(int i = 0 ; i < 9 ; i ++){
           if(cstate.charAt(i) != dstate.charAt(i))
            diff ++ ;
        }
        return diff;
    }
    public void solve(int init_state, int des_state){
        Node des  = new Node(des_state);
        Node init = new Node(init_state);
        init.depth = 0;
        init.g    = get_g(init, des);
        

        Set<Integer> queued = new HashSet<Integer>();
        
        PriorityQueue<Node> q = new PriorityQueue<Node>(10, new Comparator<Node>(){
            public int compare(Node a, Node b){
                return a.weight() - b.weight();
            }
        });

        queued.add(init.state);
        q.add(init);
        Node find = null;
        while(q.size() > 0){
            Node node = q.poll();
            System.out.println("q's statu" + node.sState());
            for(Integer state:node.otherState()){
                if(queued.contains(state))
                    continue;
                Node child = new Node(state);
                child.depth = node.depth + 1;
                child.g = get_g(child, des);
                child.pre = node;
                if(child.g == 0)
                {
                    find = child;
                    break;
                }
                q.add(child);
            }
            if(find != null)
                break;
        }
        if(find == null)
            System.out.println("No answer");
        else{
            while(find != null)
            {
                System.out.println(find.sState());
                find = find.pre;
            }
        }
    }
    public static void main(String args[]){
        EightNumber en = new EightNumber();
        en.solve(412583706, 123456780);
    }
}

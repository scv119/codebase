public class EightNumber{
    public class Node{
        int state;
        int depth;
        int g;
        Node pre;
        public Node(state){
            this.state = state;
        }
        
        int weight(){
            return depth + g;
        }
        String sState(){
            String s= state + "";
            if(s.length == 8)
                s = "0"+s;
            return s;
        }
        int[] otherState(){
            
        }
    }

    int get_g ( Node cur, Node des){
        int diff = 0;
        String cstate = cur.sState ;
        String dstate = des.sState ;
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
        while(q.size() > 0){
            Node node = q.poll();
            for(
        }
    }
}

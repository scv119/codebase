package srm495;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CarrotBoxes
{
	int g[][];
	int len;
	int sccg[][];
	Set<Integer> d;
	List<Integer> f;
	List<Set<Integer>> sccs;
	Set<Integer> scc;
	public double theProbability(String[] in)
	{	
		len = in.length;
		g = new int[len][len];
		for(int i = 0 ; i < len; i++)
			for(int j = 0 ; j < len; j ++){
				if(i != j && in[i].charAt(j) == 'Y')
					g[i][j] = 1;
			}
		
		scc();
		sccg = new int[sccs.size()][sccs.size()];
		int size[] = new int[sccs.size()];
		int indegree[] = new int[sccs.size()];
		int[] map = new int[len];
		for(int i = 0 ; i < sccs.size() ; i ++){
			Set<Integer> s = sccs.get(i);
			
			for(int j:s){
				map[j] = i;
			}
		}
		
		for(int i = 0 ; i < sccs.size() ; i ++){
			Set<Integer> s = sccs.get(i);
			size[i] = s.size();
			for(int j:s){
				for(int k = 0 ; k < len ; k ++){
					if(k != j && g[j][k] == 1 && i != map[k])
						sccg[i][map[k]] =1;
				}
			}
		}
		
		for(int i = 0 ; i < sccg.length ; i ++){
			for(int j = 0 ; j < sccg.length ; j++){
				indegree[i] += sccg[j][i];
			}
		}
		
		
		int count  = 0 ;
		int nlen = sccg.length;
		Set<Integer> possibleSet = new HashSet<Integer>();
		Set<Integer> startSet    = new HashSet<Integer>();
		for(int i = 0 ; i < sccg.length ; i ++){
			if(indegree[i] == 0)
				startSet.add(i);
			if(indegree[i] == 0 && size[i] == 1){
				possibleSet.add(i);
			}
		}
		
		boolean hasOneLeft = false;
		if(possibleSet.size() > 0){
			for(Integer guess:possibleSet){
				d = new HashSet<Integer>();
				for(int i:startSet){
					if( i == guess)
						continue;
					dfs1(i, nlen, sccg);
				}
				boolean isExample = true;
				for(int j = 0 ; j < nlen; j ++){
					if(guess != j && sccg[guess][j] == 1 && !d.contains(j)){
						isExample = false;
						break;
					}
				}
				if(isExample){
					hasOneLeft = true;
					break;
				}
			}
		}
		count = startSet.size();
		if(hasOneLeft)
			count --;
		return 1.0 - (double)(count)/len;
	}
	
	void dfs1(int node, int nlen, int[][] graph){
		if(d.contains(node))
			return;
		d.add(node);
		for(int i = 0 ; i< nlen; i ++){
			if(i != node && graph[node][i] == 1)
				dfs1(i, nlen, graph);
		}
	}
	
	void scc(){
		d = new HashSet<Integer>();
		f = new ArrayList<Integer>();
		sccs = new ArrayList<Set<Integer>>();
		for(int i = 0 ; i < len; i ++ ){
			dfs(i, false);
		}

		d = new HashSet<Integer>();
		for(int i = len- 1; i >= 0 ; i --){
			scc = new HashSet<Integer>();
			dfs(f.get(i), true);
			if(scc.size() > 0)
				sccs.add(scc);
		}
	}
	

	
	void dfs(int node, boolean trans){
		if(d.contains(node))
			return;
		d.add(node);
		if(trans)
			scc.add(node);
		for(int i = 0 ; i < len ; i++){
			if(!trans){
				if(i != node && g[node][i] == 1)
					dfs(i, trans);
			}
			else{
				if(i != node && g[i][node] == 1)
					dfs(i, trans);
			}
		}
		if(!trans)
			f.add(node);
	}
	
	
	private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, double p1) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print("\"" + p0[i] + "\"");
		}
		System.out.print("}");
		System.out.println("]");
		CarrotBoxes obj;
		double answer;
		obj = new CarrotBoxes();
		long startTime = System.currentTimeMillis();
		answer = obj.theProbability(p0);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + p1);
		}
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		if (hasAnswer) {
			res = Math.abs(p1 - answer) <= 1e-9 * Math.max(1.0, Math.abs(p1));
		}
		if (!res) {
			System.out.println("DOESN'T MATCH!!!!");
		} else if ((endTime - startTime) / 1000.0 >= 2) {
			System.out.println("FAIL the timeout");
			res = false;
		} else if (hasAnswer) {
			System.out.println("Match :-)");
		} else {
			System.out.println("OK, but is it right?");
		}
		System.out.println("");
		return res;
	}
	public static void main(String[] args) {
		boolean all_right;
		all_right = true;
		
		String[] p0;
		double p1;
		
//		// ----- test 0 -----
//		p0 = new String[]{"YYYYY","NYNNN","NNYNN","NNNYN","NNNNY"};
//		p1 = 0.8D;
//		all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
//		// ------------------
//		
//		// ----- test 1 -----
//		p0 = new String[]{"YNNNN","NYNNN","NNYNN","NNNYN","NNNNY"};
//		p1 = 0.2D;
//		all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
//		// ------------------
//		
//		// ----- test 2 -----
//		p0 = new String[]{"Y"};
//		p1 = 1.0D;
//		all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = new String[]{"YNNNN","YYNNN","YNYNN","NNNYY","NNNYY"};
		p1 = 0.6D;
		all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = new String[]{"YYYNNNYN","NYNNNNYN","NNYNNNNN","NYNYNNNN","YNNNYNNY","NNYNNYNN","NNNNYNYN","NNYNNNNY"};
		p1 = 0.875D;
		all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 5 -----
		p0 = new String[]{"YNNNNNNNNYNNNNNNNNNN","NYNNNNNNNNNNNNNNNNNN","NNYNNNNNNNYNNNNNYNNN","NNNYNYNNNNNNNNYNNNNN","NNNNYNNNNNNNNNYNNNNY","NNNNNYNNNNNNNNNNNNNY","NNNNYNYNYNNNNNNNNNNN","NNNNNNNYNNNYYNNNNNNN","NNNNNNNNYNNNNNNNNNNN","YNNNNNNNNYNNNNNYNNNN","NNNNNNNNNNYNNNNNNNNN","NYNNNNNNNNNYNNNNNNNN","NNNNNNNYNNNNYNNNNNNN","NNNNNNNNNNNNNYNNNYNN","NNNNNNNNNNNYNNYNNNYN","NYNNNNNNNNNNNNNYNNNN","NNYNNNNNNNNNNNNNYNNN","NNNNNNNNNNNNNYNYNYNN","NNNNNNNNYNYNNNNNNNYY","NNNYNNNNNNNNNNNNNNNY"};
		p1 = 0.75D;
		all_right = KawigiEdit_RunTest(5, p0, true, p1) && all_right;
		// ------------------
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
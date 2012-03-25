package srm497;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class CssRules
{
	Pattern tagStart = Pattern.compile("<([a-z]+)\\sid='([a-z]+)'\\sstyle='color:([a-z]+)'");
	Pattern tagEnd   = Pattern.compile("</([a-z]+)");
	
	Map<String,Integer> tagMap;
	Map<String,Integer> colorMap;
	Map<String,Integer> cache;
	int nodeCount = 0;
	public int getMinimalCssRuleCount(String[] xthml)
	{
		StringBuffer sb = new StringBuffer();
		for(String s:xthml){
			sb.append(s);
		}
		xthml = sb.toString().split(">");
		tagMap = new HashMap<String,Integer>();
		colorMap = new HashMap<String,Integer>();
		cache = new HashMap<String,Integer>();
		nodeCount = 0;
		Node root = new Node();
		parse(xthml, root);
		int rules = 0;
		for(Node node:root.children){
			rules += minRules(node, null);
		}
		return rules;
	}
	
	private int minRules(Node node, int[] ptagColor){
		if(ptagColor == null)
			ptagColor = new int[tagMap.size()+1];
		StringBuffer sb = new StringBuffer();
		sb.append(node.node_id).append(";");
		for(Integer i:ptagColor){
			sb.append(i).append(";");
		}
		String key = sb.toString();
		if(cache.containsKey(key))
			return cache.get(key);
		int min = Integer.MAX_VALUE;
		int[] tagColor = new int[tagMap.size()+1];

		for(int i =0; i<= colorMap.size(); i++)
		for(int j =1; j<= tagMap.size(); j ++){
			tagColor[j] = i;
			int count = 0;
			for(int k = 0; k < tagColor.length; k++){
				if(tagColor[k] > 0 && ptagColor[k] != tagColor[k])
					count ++;
			}
			for(Node child:node.children){
				count += minRules(child, tagColor);
			}
			if(count < min)
				min = count;
		}
		if(ptagColor[node.tag] != node.color)
			min ++;
		cache.put(key, min);
		return min;
	}
	
	
	public class Node{
		public Node(){
			children = new ArrayList<Node>();
		}
		List<Node> children;
		Node parent;
		int color;
		int tag;
		String id;
		int node_id;
	}
	
	private void parse(String[] xhtml, Node node){
		Node cur = node;
		for(String s :xhtml){
			if(s.length()==0)
				continue;
			List<String> start= matchStart(s);
			int ends     = matchEnd(s);
			
			if(start.size() > 0){
				Node tmp = new Node();
				
				String tag = start.get(0);
				if(!tagMap.containsKey(tag))
					tagMap.put(tag,tagMap.size()+1);
				tmp.tag = tagMap.get(tag);
				tmp.id  = start.get(1);
				String color = start.get(2);
				if(!colorMap.containsKey(color))
					colorMap.put(color, colorMap.size()+1);
				tmp.color = colorMap.get(color);
				cur.children.add(tmp);
				tmp.parent = cur;
				tmp.node_id = ++nodeCount;
				cur = tmp;
			}
			while(ends > 0){
				cur = cur.parent;
				ends --;
			}
		}
	}
	
	private List<String> matchStart(String s){
		List<String> result = new LinkedList<String>();
		Matcher m = tagStart.matcher(s);
		while(m.find()){
			result.add(m.group(1));
			result.add(m.group(2));
			result.add(m.group(3));
		}
		return result;
	}
	
	private int matchEnd(String s){
		Matcher m = tagEnd.matcher(s);
		int matches = 0;
		while(m.find()){
			matches ++;
		}
		return matches;
	}
	
	private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, int p1) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print("\"" + p0[i] + "\"");
		}
		System.out.print("}");
		System.out.println("]");
		CssRules obj;
		int answer;
		obj = new CssRules();
		long startTime = System.currentTimeMillis();
		answer = obj.getMinimalCssRuleCount(p0);
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
			res = answer == p1;
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
		int p1;
		
		// ----- test 0 -----
		p0 = new String[]{"<b id='x' style='color:red'></b>"};
		p1 = 1;
		all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 1 -----
		p0 = new String[]{"<b id='x' style='color:red'>","<b id='y' style='color:red'>","<b id='z' style='color:red'>","</b></b></b>"};
		p1 = 2;
		all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 2 -----
		p0 = new String[]{"<b id='x' style='color:red'>","<b id='y' style='color:red'>","<b id='w' style='color:red'>","</b>","</b>","<u id='z' style='color:red'>","</u>","</b>"};
		p1 = 3;
		all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 3 -----
		p0 = new String[]{"<b id='x' style='color:red'>","<i id='y' style='color:black'>","<u id='w' style='color:white'>","</u>","</i>","<u id='z' style='color:yellow'>","</u>","</b>"};
		p1 = 4;
		all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
		// ------------------
		
		// ----- test 4 -----
		p0 = new String[]{"<b id='x' style='col","or:red'></b>","<b id=","'xx' style='color",":red'></b>"};
		p1 = 2;
		all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
		// ------------------
		
		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
}
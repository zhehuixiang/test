package SCC;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import util.ReadLineByLine;


public class DCSCC {
	class Edge {
		int next;
		int to;
	}
	ArrayList<Edge>decendant=new ArrayList<Edge>();
	ArrayList<Edge>predecessor=new ArrayList<Edge>();
	int[]decenH;
	int[]preH;
	int cnt;
	ReadLineByLine lbl=new ReadLineByLine();
	void add(int x, int y) {
		cnt++;
		Edge de = new Edge();
		Edge pre=new Edge();
		decendant.add(de);
		predecessor.add(pre);
		de.next = decenH[x];
		de.to = y;
		pre.next=preH[y];
		pre.to=x;
		decenH[x] = cnt;
		preH[y]=cnt;
		return;
	}
	ArrayList<ArrayList<Integer>> trim(Set<Integer>vertex){
		ArrayList<ArrayList<Integer>>trimed=new ArrayList<ArrayList<Integer>>();
		return trimed;
	}
	void reachable(ArrayList<Edge>e,int[]heads,int i,Set<Integer>reach,Set<Integer>vertex){
		for(int j=heads[i];j!=0;j=e.get(j).next){
			int v=e.get(j).to;
			if(vertex.contains(v)&&!reach.contains(v)){
				reach.add(v);
				reachable(e,heads,v,reach,vertex);
			}
		}
	}
	ArrayList<ArrayList<Integer>>SCC(Set<Integer>vertex){
		ArrayList<ArrayList<Integer>>scc=new ArrayList<ArrayList<Integer>>();
		if(vertex.size()==0)return scc;
		ArrayList<Integer>component=new ArrayList<Integer>();
		Set<Integer>pre=new HashSet<Integer>();
		Set<Integer>de=new HashSet<Integer>();
		for(int i:vertex){
			pre.add(i);
			de.add(i);
			reachable(decendant,decenH,i,de,vertex);
			reachable(predecessor,preH,i,pre,vertex);
			break;
		}
		pre.retainAll(vertex);
		de.retainAll(vertex);
		Set<Integer>inter=new HashSet<Integer>();
		inter.addAll(pre);
		inter.retainAll(de);
		component.addAll(inter);
		scc.add(component);
		vertex.removeAll(pre);
		vertex.removeAll(de);
		pre.removeAll(inter);
		de.removeAll(inter);
		scc.addAll(SCC(pre));
		scc.addAll(SCC(de));
		scc.addAll(SCC(vertex));
		return scc;
	}
	public ArrayList<ArrayList<Integer>> run(String filename) {
		int n = Integer.parseInt(lbl.readLBL(filename));
		decenH=new int[n+1];
		preH=new int[n+1];
		predecessor.add(new Edge());
		decendant.add(new Edge());
		while (true) {
			String a = lbl.readLBL(filename);
			if (a == null)
				break;
			String[] b = a.split(",");
			add(Integer.parseInt(b[0]), Integer.parseInt(b[1]));
		}
		Set<Integer> vertex=new HashSet<Integer>();
		for(int i=1;i<=n;i++){
			vertex.add(i);
		}
		return SCC(vertex);
	}
	public static void main(String[]args){
		DCSCC scc=new DCSCC();
		ArrayList<ArrayList<Integer>>sc=scc.run("graph");
		for (ArrayList<Integer> component : sc) {
			System.out.print("{");
			for (int i : component)
				System.out.print(i + ",");
			System.out.print("}");
		}
	}
}

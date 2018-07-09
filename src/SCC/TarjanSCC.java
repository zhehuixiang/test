package SCC;

import java.util.ArrayList;

import util.ReadLineByLine;

public class TarjanSCC {
	class Edge {
		int next;
		int to;
	}

	ReadLineByLine lbl = new ReadLineByLine();

	private int[] dfn;
	private int[] low;
	private boolean[] vis;
	private int dfs_num;
	private int top;
	private int[] stack;
	private int[] head;
	private ArrayList<Edge> e;
	private int cnt;

	void add(int x, int y) {
		cnt++;
		Edge ee = new Edge();
		e.add(ee);
		ee.next = head[x];
		ee.to = y;
		head[x] = cnt;
		return;
	}

	ArrayList<ArrayList<Integer>> SCC(int x) {
		ArrayList<ArrayList<Integer>> scc = new ArrayList<ArrayList<Integer>>();
		dfn[x] = ++dfs_num;
		low[x] = dfs_num;
		vis[x] = true;// 是否在栈中
		stack[++top] = x;
		for (int i = head[x]; i != 0; i = e.get(i).next) {
			int temp = e.get(i).to;
			if (dfn[temp] == 0) {
				scc.addAll(SCC(temp));
				low[x] = Math.min(low[x], low[temp]);
			} else if (vis[temp])
				low[x] = Math.min(low[x], dfn[temp]);
		}
		if (dfn[x] == low[x]) {// 构成强连通分量
			ArrayList<Integer> component = new ArrayList<Integer>();
			vis[x] = false;
			while (stack[top] != x) {// 清空
				component.add(stack[top]);
				vis[stack[top--]] = false;
			}
			component.add(stack[top]);
			scc.add(component);
			top--;
		}
		return scc;
	}

	public ArrayList<ArrayList<Integer>> run(String filename) {
		int n = Integer.parseInt(lbl.readLBL(filename));
		dfn = new int[n + 1];
		low = new int[n + 1];
		vis = new boolean[n + 1];
		head = new int[n + 1];
		stack = new int[n + 1];
		e = new ArrayList<Edge>();
		e.add(new Edge());
		while (true) {
			String a = lbl.readLBL(filename);
			if (a == null)
				break;
			String[] b = a.split(",");
			add(Integer.parseInt(b[0]), Integer.parseInt(b[1]));
		}
		ArrayList<ArrayList<Integer>> scc = new ArrayList<ArrayList<Integer>>();
		for (int i = 1; i <= n; i++)
			if (dfn[i] == 0)
				scc.addAll(SCC(i));// 当这个点没有访问过，就从此点开始。防止图没走完
		return scc;
	}

	public ArrayList<ArrayList<Integer>> run(boolean[][] graph) {
		int n=0;
		for(int i=0;i<graph.length;i++){
			for(int j=0;j<graph[0].length;j++){
				if(graph[i][j])n++;
			}
		}
		dfn = new int[n + 1];
		low = new int[n + 1];
		vis = new boolean[n + 1];
		head = new int[n + 1];
		stack = new int[n + 1];
		e = new ArrayList<Edge>();
		e.add(new Edge());
		for(int i=0;i<graph.length;i++){
			for(int j=0;j<graph[0].length;j++)
				if(graph[i][j])add(i,j);
		}
		ArrayList<ArrayList<Integer>> scc = new ArrayList<ArrayList<Integer>>();
		for (int i = 1; i <= n; i++)
			if (dfn[i] == 0)
				scc.addAll(SCC(i));// 当这个点没有访问过，就从此点开始。防止图没走完
		return scc;
	}

	public static void main(String[] args) {
		TarjanSCC scc = new TarjanSCC();
		ArrayList<ArrayList<Integer>> sc = scc.run("graph");
		for (ArrayList<Integer> component : sc) {
			System.out.print("{");
			for (int i : component)
				System.out.print(i + ",");
			System.out.print("}");
		}
	}
}

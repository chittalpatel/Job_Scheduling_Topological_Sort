package dag;

import java.util.*;

public class UGraph {

	private int vertices;
	private int edges;
	LinkedList<Integer> adjlist[];

///////////Constructor/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	UGraph(int vertices) {
		adjlist = new LinkedList[vertices + 1];
		for (int i = 0; i <= vertices; i++) {
			adjlist[i] = new LinkedList<>();
		}
		this.vertices = vertices;

	}
/////////////Applications///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	// Breadth First Search.........

	public void bfs(int i) {
		boolean visited[] = new boolean[vertices + 1];
		Deque<Integer> q = new ArrayDeque<>();
		q.add(i);
		visited[i] = true;
		while (!q.isEmpty()) {
			int j = q.removeFirst();
			System.out.print(j + " ");
			Iterator itr = adjlist[j].iterator();
			while (itr.hasNext()) {
				int k = (int) itr.next();
				if (visited[k] == false) {
					q.add(k);
					visited[k] = true;
				}
			}
		}
	}

	// Depth First Search.............

	public void dfs(int i) {
		boolean visited[] = new boolean[vertices + 1];
		dfsmain(i, visited);
	}

	private void dfsmain(int i, boolean[] visited) {
		if (visited[i] == false) {
			System.out.print(i + " ");
			visited[i] = true;
			Iterator itr = adjlist[i].iterator();
			while (itr.hasNext()) {
				dfsmain((int) itr.next(), visited);
			}
		}
	}

	// Print shortest path between two vertices if exists.........

	public void printShortestPath(int i, int d) {

		int[] path = new int[vertices];
		boolean visited[] = new boolean[vertices + 1];
		int[] parent = new int[vertices + 1];

		parent[i] = -1;
		Deque<Integer> q = new ArrayDeque();
		q.add(i);
		visited[i] = true;
		abc: while (!q.isEmpty()) {
			int j = q.removeFirst();
			Iterator itr = adjlist[j].iterator();
			while (itr.hasNext()) {
				int k = (int) itr.next();
				if (visited[k] == false) {
					q.add(k);
					parent[k] = j;
					visited[k] = true;
					if (k == d)
						break abc;
				}
			}
		}
		int cur = d;
		int count = -1;
		try {
			while (cur != -1) {
				path[++count] = cur;
				cur = parent[cur];
			}
			for (; count >= 0; count--)
				System.out.print(path[count] + " ");
		} catch (Exception e) {
			System.out.println("No Path Exists");
		}
	}

	// Longest path in the tree...........

	public void longestPath(int i) {
		LinkedList<Integer> ll = new LinkedList<>();
		bfsmod(i, ll);
		int x = ll.getFirst();
		ll.clear();
		int[] parent = bfsmod(x, ll);
		int ver = ll.getFirst(), ans = 0;
		while (ver != -1) {
			ans++;
			ver = parent[ver];
		}
		System.out.println(ans - 1);
	}

	private int[] bfsmod(int i, LinkedList<Integer> ll) {
		boolean visited[] = new boolean[vertices + 1];
		int[] parent = new int[vertices + 1];
		Deque<Integer> q = new ArrayDeque<>();
		q.add(i);
		parent[i] = -1;
		visited[i] = true;
		while (!q.isEmpty()) {
			int j = q.removeFirst();
			ll.addFirst(j);
			Iterator<Integer> itr = adjlist[j].iterator();
			while (itr.hasNext()) {
				int k = (int) itr.next();
				if (visited[k] == false) {
					q.add(k);
					parent[k] = j;
					visited[k] = true;
				}
			}
		}
		return parent;
	}

	/*
	 * //Check if graph is Bipartite or not......... public boolean isBipartite(int
	 * v) { Set<Integer> U=new HashSet<>(); Set<Integer> V=new HashSet<>();
	 * 
	 * boolean visited[]=new boolean[vertices+1]; boolean flag=true; Deque<Integer>
	 * q=new ArrayDeque(); q.add(v); visited[v]=true; while(!q.isEmpty()) { int
	 * j=q.removeFirst(); //System.out.print(j+" "); Iterator
	 * itr=adjlist[j].iterator(); while(itr.hasNext()) { int k=(int)itr.next();
	 * if(visited[k]==false) { q.add(k); visited[k]=true; } } } }
	 */

	// Job Scheduling using Topological Sorting
	public void topologicalSort() {

		// Check if the Graph is Cyclic or Not.
		if(isCycle()) {
			return;
		}

		// Stores the Topologically sorted order
		Stack<Integer> sorted = new Stack<>();
		boolean visited[] = new boolean[vertices + 1];

		for (int i = 1; i <= vertices; i++) {
			// Do a dfs if the vertex is not visited
			if (!visited[i])
				dfsTopo(i, visited, sorted);
		}
		System.out.print(sorted.pop());
		while (!sorted.empty()) {
			System.out.print("->" + sorted.pop());
		}
	}

	private void dfsTopo(int i, boolean[] visited, Stack<Integer> sorted) {
		if (!visited[i]) {
			visited[i] = true;
			Iterator<Integer> itr = adjlist[i].iterator();
			while (itr.hasNext()) {
				dfsTopo((int) itr.next(), visited,sorted);
			}
			sorted.push(i);
		}
	}
	
	//Check if there is a Cycle in a ***Directed Graph***(Use for Directed Graph Only!)
	public boolean isCycle() {
		boolean visited[]=new boolean[vertices+1];
		
		for(int i=1;i<=vertices;i++) {
			if(!visited[i]) {
				boolean recStack[]=new boolean[vertices+1];
				if(checkCycle(i,visited,recStack)) {
					System.out.println("There is a Cycle");
					return true;
				}
			}
		}
		return false;
	}
	private boolean checkCycle(int i,boolean visited[],boolean recStack[]) {
		
		
		if(recStack[i]) {
			return true;
		}
		if (!visited[i]) {
			visited[i] = true;
			recStack[i]=true;
			Iterator<Integer> itr = adjlist[i].iterator();
			while (itr.hasNext()) {
				return checkCycle(itr.next(),visited,recStack);
			}	
		}
		return false;
	}

/////////////Class Methods/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public int vertices() {
		// TODO Auto-generated method stub
		return vertices;
	}

	public int edges() {
		// TODO Auto-generated method stub
		return edges;
	}

	public boolean existsEdge(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addEdge(int i, int j) {
		adjlist[i].add(j);
		// for un-directional graph uncomment below line
		// adjlist[j].addFirst(i);
		edges++;
	}

	public void removeEdge(int i, int j) {
		adjlist[i].remove(j);
		// for un-directional graph uncomment below line
		// adjlist[j].remove(i);
		edges--;
	}

	public int degree(int i) {
		// TODO Auto-generated method stub
		return adjlist[i].size();
	}

	public Iterator iterator(int i) {
		// TODO Auto-generated method stub
		return adjlist[i].iterator();
	}

	public void printGraph() {
		for (int i = 1; i <= vertices; i++) {
			Iterator itr = iterator(i);
			System.out.print("\nVertex " + i);
			while (itr.hasNext()) {
				System.out.print(" -> " + itr.next());
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

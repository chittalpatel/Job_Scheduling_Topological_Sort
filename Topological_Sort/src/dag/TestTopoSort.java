package dag;

public class TestTopoSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UGraph g=new UGraph(12);
		g.addEdge(1, 8);
		g.addEdge(2, 1);
		g.addEdge(3, 1);
		g.addEdge(3, 2);
		g.addEdge(4, 3);
		g.addEdge(5, 4);
		g.addEdge(5, 7);
		g.addEdge(6, 4);
		g.addEdge(6, 5);
		g.addEdge(6, 8);
		g.addEdge(9, 10);
		g.topologicalSort();
	}

}

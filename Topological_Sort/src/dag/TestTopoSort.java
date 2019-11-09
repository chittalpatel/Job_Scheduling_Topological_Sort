package dag;

import java.io.*;

public class TestTopoSort {

	public static void main(String[] args) throws IOException{
		
		//Enter the testcase file name(format:"testcase1.txt") here......
		String testcasefilename="ExampleTestCase.txt";
		
		File f=new File("..\\TestCases\\"+testcasefilename);
		BufferedReader br;
		try {
		br=new BufferedReader(new FileReader(f));
		}catch(FileNotFoundException e) {	
			System.out.println("File you Enter was Not found Please Enter Valid Filename in \"Filename.txt\" format");	
			return;
		}
		int noOfVertices;
		try {
		noOfVertices=Integer.parseInt(br.readLine());
		}catch(IOException e) {
			System.out.println("An IOException Occured in noOfVertices Refer the README File");
			return;
		}
		UGraph g=new UGraph(noOfVertices);
		
		a:	while(true) {
			int u,v;
			String input[];
			try {
			input=br.readLine().split(" ");
			}catch(IOException e) {
				System.out.println("An IOException Occured in inputting vertex u v (u->v)... Refer the README File");
				return;
			}catch(NullPointerException e) { 
	//When this Exception Occurs it means that there are No further Lines to read and we have taken all input edge pairs u->v 
				break a;
			}
			u=Integer.parseInt(input[0]);
			v=Integer.parseInt(input[1]);
			g.addEdge(u, v);
		}
		
		g.topologicalSort();
		
		try {
			br.close();
			}catch(IOException e) {
				System.out.println("An Exception Occured while closing the file");
			}
	}

}

/*
 * ICSI 521 Discrete Mathematics and Applications
 * Programming Assignment Group 4
 * 1 Assginment Description:
 * Given the distances between pairs of television stations and the minimum allowable 
 * distance between stations, assign frequencies to these stations.
 * 2 Solution:
 * Based on our understanding, this is an undirected graph coloring question with a 
 * minimum distance additional condition.
 * This means that:
 * 1)For two stations that have the distance shorter than minimum allowable distance 
    definitely have different frequencies. Moreover, we can set an edge between these two stations.
 * 2)For two stations that have longer distance than minimum distance, we don¡¯t add edge between these stations.
 * 3)After 1) and 2) we can get a graph then it becomes a graph coloring questions for the frequencies assignment.
 * The test example is the same to the one in the submitted document. 
 * 
 * 
 * */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TelevisionFrequency {
	private int min_distance;
	private int vertices;
	private ArrayList<Vertex> vertexes; //List of vertexes 
	private int total_color;//counter for total color/frequencies in the graph
	//Add inner class Vertex
	public class Vertex
	{
		private LinkedList<Vertex> adj; //Adjacency List of this vertex
		private int vertex_id;
		private int color;
		//use the color counter to check if this vertex is colored correctly, only when color_counter == adj.size()
		private int color_counter; 
		
		public Vertex(int id)
		{
			this.vertex_id = id;
			//Init the color to 0
			this.color = 0;
			//Init adj list for this vertex
			adj = new LinkedList<Vertex>();
			// set color counter to 0
			this.color_counter = 0;
		}
		private void setColor(int color)
		{
			this.color = color;
		}
		private void increaseColor()
		{
			this.color += 1;
		}
		private int getColor()
		{
			return this.color;
		}
		//Print the adj list 
		private void printiAdjList()
		{
			Iterator<Vertex> it = adj.iterator();
			System.out.print("Station " + this.vertex_id + "'s adjacencies are { ");
			while(it.hasNext())
			{
				System.out.print(it.next().vertex_id + " ");
			}
			System.out.println("}");
			
		}
	}//end of inner class Vertex
	//Initialize the vertex based on adjacency matrix
	public void vertexInit(int vertexNum,int adjMatrix[][])
	{
		vertexes = new ArrayList<Vertex>();//should this put in class constructor?
		//create vertexes based on vertex num
		for(int i = 0; i< vertexNum; i++)
		{
			Vertex v = new Vertex(i);
			vertexes.add(v);
		}
		System.out.println("\n1: Create station vertex in graph: ");
		System.out.println(vertexNum + " stations are created and frequency for each is 0 now.");
		//After create vertexes then add the adjacencies to each one using the adjacency matrix
		for(int vertex = 0; vertex<vertexNum; vertex++)
		{
			Vertex v = vertexes.get(vertex);
			for(int adj = 0; adj< vertexNum; adj++)
			{
				//check if the distance for v <= min_distance = 4 and not itself, then add to its adj list
				if(adjMatrix[vertex][adj] < min_distance && adj != vertex)
				{
					Vertex temp = vertexes.get(adj);
					//add to list of v
					v.adj.add(temp);					
				}
			}
		}
		
	}	
	//Print the frequency assignment result for each station
	private void printFrequencies()
	{
		System.out.print("Frequency result: ");
		for(int i = 0; i<vertexes.size(); i++)
		{
			System.out.print("{Station " + vertexes.get(i).vertex_id + ", " + vertexes.get(i).color + "} ");
		}
		System.out.println();
	}
	//Update the whole graph when one Vertex v has been colored
	//Because it maybe in other vertex's adj list
	private void graphColorUpdate(Vertex vertexChanged)
	{
		for(int i = 0;i< this.vertices;i++)
		{
			//update the other vertexes' adj list
			if(vertexes.get(i).vertex_id != vertexChanged.vertex_id)
			{
				int adj_size = vertexes.get(i).adj.size();
				for(int j = 0;j < adj_size;j++)
				{
					//update the color of vertex with vertexId in adj list
					if(vertexes.get(i).adj.get(j).vertex_id == vertexChanged.vertex_id)
					{
						vertexes.get(i).adj.get(j).color = vertexChanged.color;
					}
				}
			}
		}
	}
	//Checking and solving color conflict
	//If there is a conflict then changing color of vertex until no conflicts		
	private void solvingColorConflicts()
	{				
		for(int i = 0;i < vertexes.size();i++)
		{
			int adj_size = vertexes.get(i).adj.size();
			//while loop for coloring one vertex in graph
			while(vertexes.get(i).color_counter != vertexes.get(i).adj.size())
			{
				// if this vertex is not correctly colored then just increase the color
				// Then check conflicts until the color_counter equals to adj size
				// This means the color for this vertex is finished
				for(int j = 0; j<adj_size;j++)
				{
					if(vertexes.get(i).color == vertexes.get(i).adj.get(j).color)
					{
						vertexes.get(i).increaseColor();
						
					}
				}
				vertexes.get(i).color_counter += 1;//add the color counter
			}//end of while
			//After finishing color one vertex need to update the whole graph
			this.graphColorUpdate(vertexes.get(i));
			//Print the frequency for each station
			System.out.println("Step " + (i+1) + ": Assign frequency for station " + i);
			this.printFrequencies();
		}
		//print the color of each vertex
		System.out.println("\n" + "4: Finish frequency assignment!");
		this.printFrequencies();
	}
	//Counting the total color/frequency numbers
	private void colorCounting()
	{
		
	}
	
	//Constructor
	public TelevisionFrequency(int v,int min) 
    {
        this.vertices = v;
        this.min_distance = min;
        this.total_color = 0;
    }
   
	public static void main(String[] args) {
		
		//Station distance matrix
		
		int[][] stations1 = 
		{
				
				{0,2,5,8,7,6,5,9},//the distance of each station to station 0
				{2,0,9,7,8,4,3,2},//the distance of each station to station 1
				{5,9,0,5,6,8,9,5},//the distance of each station to station 2
				{8,7,5,0,4,2,3,9},//the distance of each station to station 3
				{7,8,6,4,0,8,7,5},//the distance of each station to station 4
				{6,4,8,2,8,0,2,5},//the distance of each station to station 5
				{5,3,9,3,7,2,0,4},//the distance of each station to station 6
				{9,2,5,9,5,5,4,0} //the distance of each station to station 7
		};
		int min_station1 = 4;
		
		TelevisionFrequency graph = new TelevisionFrequency(stations1.length,min_station1);
		
		System.out.println("The distances between stations matrix: ");
		for (int i = 0; i < stations1.length; i++) {
		    for (int j = 0; j < stations1[i].length; j++) {
		        System.out.print(stations1[i][j] + " ");
		    }
		    System.out.println();
		}		
		graph.vertexInit(graph.vertices,stations1);
		Iterator<Vertex> it = graph.vertexes.iterator();
		//print the adjacencies of each vertex
		System.out.println("\n2: The adjacencies list for each station is ");
		while(it.hasNext())
		{
			it.next().printiAdjList();
		}
		System.out.println("\n3: Begin frequency assignment ");
		graph.solvingColorConflicts();
	}
}

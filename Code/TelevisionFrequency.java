import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class TelevisionFrequency {
	
	private int min_distance;
	static int stations[][];
	static int adjacency_matrix[][];
	static int colors[];
	static int colors_used[];
	static int num_colors = 3;
	//static int num_vertices = 8;
	private int vertices;
	//Modified by Yunwei
	private ArrayList<Vertex> vertexes; //List of vertexes 

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
			System.out.print("Vertex " + this.vertex_id + "'s adjacencies are { ");
			while(it.hasNext())
			{
				System.out.print(it.next().vertex_id + " ");
			}
			System.out.println("}");
			
		}
		//Checking color conflict
		//If there is a conflict then changing color of vertex until no conflicts		
		private void checkingColorConflicts()
		{
			
			
			
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
		System.out.println("The size of vertexes list is "+vertexes.size());
		Iterator<Vertex> it = vertexes.iterator();
		
		while(it.hasNext())
		{
			Vertex temp = it.next();
			System.out.println("Vertex " + temp.vertex_id + " created!" + " The color is " + temp.color);
		}
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
		Iterator<Vertex> v = vertexes.iterator();
		while(v.hasNext())
		{
			
			v.next();
		}
		
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
		}
		//print the color of each vertex
		System.out.println("\n" + "Coloring finishs!");
		for(int i = 0;i < vertexes.size();i++)
		{
			System.out.println("The color of Vertex " + vertexes.get(i).vertex_id + " is " + vertexes.get(i).color);
		}
	}
	
	//End of Yunwei Modification
	public TelevisionFrequency(int v,int min) 
    {
        this.vertices = v;
        this.min_distance = min;
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
		
		//test by Yunwei
		graph.vertexInit(graph.vertices,stations1);
		Iterator<Vertex> it = graph.vertexes.iterator();
		//print the adjacencies of each vertex
		while(it.hasNext())
		{
			it.next().printiAdjList();
		}
		graph.solvingColorConflicts();
	}
}

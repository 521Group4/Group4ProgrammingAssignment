import java.util.*;


public class TelevisionFrequency {
	
	private boolean checkDistance = false;
	private int min = 6;
             
	  public TelevisionFrequency(){
	  }
	  
	  
	  public boolean checkDistance(int d){
		  
		  if (d < min) checkDistance = true;
		  else checkDistance = false;
		  
		  return checkDistance;
	  }
	  
	  public void addColor(){
	  }
	  
	  
	  
	  
	public static void main(String[] args) {
		
		int num_vertices = 0; // number of vertices in graph depending on distance check increment this value as you check
		int edge1 = 0;
		int edge2 = 0;
		
		int[ ][ ] stations = {
				// intersection of the matrix is the distance between stations
				/*
				 *   A B C D E
				 * A 0 3 10 4 6
				 * B
				 * C
				 * D
				 * D
				 * 
				 */
				{0,3,10,4,6},
				{3,0,4,5,3},
				{10,4,0,4,5},
				{4,5,4,0,2},
				{6,3,5,2,0}
		};
		
		// Loop through array and pass each element to checkDistance
		// if the value is less than the minimum distance then return true 
		// if the distance is true then we need an edge between those stations therefore add an edge
		
		Graph graph = new Graph(num_vertices);
		graph.addEdge(edge1, edge2);

	}

}

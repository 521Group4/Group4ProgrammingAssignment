import java.util.Arrays;

public class TelevisionFrequency {
	
	private static int min_distance = 4;
	static int stations[][];
	static int adjacency_matrix[][];
	static int colors[];
	static int colors_used[];
	static int num_colors = 3;
	static int num_vertices = 8;
	private final int vertices;

	
	public TelevisionFrequency(int v) 
    {
        vertices = v;
        adjacency_matrix = new int[vertices + 1][vertices + 1];
    }
 
    public void addEdge(int station1, int station2, int edge) 
    {
        try 
        {
            adjacency_matrix[station1][station2] = edge;

        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The vertices does not exists");
        }
    }
 
    public int getEdge(int station1, int station2) 
    {
        try 
        {
            return adjacency_matrix[station1][station2];
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The vertices does not exists");
        }
        return -1;
    }     
	  static void color(int k){
		   
	            colors[k] = getVertexColor(k); 
	            if(colors[k] == 0)return;
	            if(k==num_vertices){
	            System.out.println("Stations: [0, 1, 2, 3, 4, 5, 6, 7]");
	            System.out.println("Color Assignment: "+Arrays.toString(colors));
	            }
	            else color(k+1);
	
	  }

	  	static int getVertexColor(int k){
	  		
	  		 do{ 
	             int j;
	             colors[k] = colors[k]+1; 
	             if(colors[k]==num_colors+1)return 0;
	             for(j=1;j<=num_vertices;++j){ 
	                 if(adjacency_matrix[k][j] == 1 && colors[k] == colors[j] && k!=j){ 
	                     break;
	                 }
	             }  
	             if(j==num_vertices+1)return colors[k];
	         }while(true);
	  	}
	  
	  
	  	
	  
	public static void main(String[] args) {
		
		colors = new int[num_vertices+1];
		TelevisionFrequency graph = new TelevisionFrequency(num_vertices);
		int[][] stations = {
				
				{0,2,5,8,7,6,5,9},
				{2,0,9,7,8,4,3,2},
				{5,9,0,5,6,8,9,5},
				{8,7,5,0,4,2,3,9},
				{7,8,6,4,0,8,7,5},
				{6,4,8,2,8,0,2,5},
				{5,3,9,3,7,2,0,4},
				{9,2,5,9,5,5,4,0}
		};
		
		System.out.println("The distances between stations matrix: ");
		for (int i = 0; i < stations.length; i++) {
		    for (int j = 0; j < stations[i].length; j++) {
		        System.out.print(stations[i][j] + " ");
		    }
		    System.out.println();
		}
		
		
		for (int i = 0; i < stations.length; ++i) {
	        for(int j = 0; j < stations[i].length; ++j) {
	        	if(stations[i][j] < min_distance && stations[i][j]!=0){
	        		graph.addEdge(i, j, 1);
	        		System.out.println("Edges: " + i + " to " + j);
	        	}
	        }
	     }
		
		
		System.out.println("The adjacency matrix is: ");
		for (int i = 0; i < stations.length; ++i) {
	        for(int j = 0; j < stations[i].length; ++j) {
	        	
	        	System.out.print(graph.getEdge(i, j) + " ");
	        }
	        	System.out.println();
	        	
	        
	     }
		

		color(1);
		 

	}
	

}

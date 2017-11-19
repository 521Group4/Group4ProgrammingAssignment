import java.util.Arrays;

public class TelevisionFrequency {
	
	private static int min_distance = 5;
	static int stations[][];
	static int adjacency_matrix[][];
	static int colors[];
	static int num_colors = 4;
	static int num_vertices = 5;
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
		TelevisionFrequency graph = new TelevisionFrequency(5);
		int[][] stations = {
				{0,0,0,0,0,0},
				{0,0,3,10,4,6},
				{0,3,0,4,5,3},
				{0,10,4,0,4,5},
				{0,4,5,4,0,2},
				{0,6,3,5,2,0}
		};
		
		System.out.println("The distances between stations matrix: ");
		for (int i = 0; i < stations.length; i++) {
		    for (int j = 0; j < stations[i].length; j++) {
		        System.out.print(stations[i][j] + " ");
		    }
		    System.out.println();
		}
		
		
		for (int i = 1; i < stations.length; ++i) {
	        for(int j = 1; j < stations[i].length; ++j) {
	        	if(stations[i][j] < min_distance && stations[i][j]!=0){
	        		graph.addEdge(i, j, 1);
	        		System.out.println("Edges: " + i + " to " + j);
	        	}
	        }
	     }
		
		System.out.println("The adjacency matrix is: ");
        System.out.print("  ");
        for (int i = 1; i <= 5; i++)
            System.out.print(i + " ");
        System.out.println();

        for (int i = 1; i <= 5; i++) 
        {
            System.out.print(i + " ");
            for (int j = 1; j <= 5; j++) 
                System.out.print(graph.getEdge(i, j) + " ");
            System.out.println();
        }

		color(1);
		 

	}
	

}

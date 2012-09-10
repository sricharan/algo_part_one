public class Percolation {

   private int open_flag;
   private int N;
   private int[][] sites;
   private WeightedQuickUnionUF uf;
   
   private int xy_to_id(int i, int j)
   {  
      int id;
      id = i*N + j ;
      return id;
   }

   public Percolation(int n)                 // create N-by-N grid, with all sites blocked
   {  
      N = n+1;
      sites = new int[N][N];
      uf = new WeightedQuickUnionUF((n+1)*(n+1));
      for (int i=0; i<n+1; i++)
        for (int j = 0; j<n+1;j++)
          sites[i][j] = 0;
   }
  
   public void open(int i, int j)                        // open site (row i, column j) if it is not already
   {  
      if ((i>0 && i<N) && (j>0 && j<N))
      {
         sites[i][j] = 1;

         if ( i == 1 ) 
            uf.union(0,xy_to_id(i,j));
 
         if ( i == N-1)
           {uf.union(2,xy_to_id(i,j));
           }

         if (i != 1) 
           {if (sites[i-1][j] == 1)
               uf.union(xy_to_id(i,j), xy_to_id(i-1,j));}

         if (i != N-1 ) 
           {if (sites[i+1][j] == 1)
               uf.union(xy_to_id(i,j), xy_to_id(i+1,j));}

         if (j != 1)
           {if (sites[i][j-1] == 1)
               uf.union(xy_to_id(i,j), xy_to_id(i,j-1));}

         if (j != N-1) 
           {if (sites[i][j+1] == 1)
               uf.union(xy_to_id(i,j), xy_to_id(i,j+1));}
         
         
            
 
      }
      else
         throw new IndexOutOfBoundsException("index i or j out of bounds");
   }  
      
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
      if (sites[i][j] == 1)
         return true;
      else
         return false;
   }

   public boolean isFull(int i, int j)    // is site (row i, column j) full?
   {  
      if (uf.connected(xy_to_id(i,j), 0))
         return true;
      else
         return false;
   }

   public boolean percolates()            // does the system percolate?
   {
      if (uf.connected(0,2))
         return true;
      else
         return false;
   }

   public static void main(String[] args)
   {   int x,y;
      /* double count;
       double p_star;
       int n ;
       n = Integer.parseInt(args[0]);    // for accepting command line args
       Percolation new_matrix = new Percolation(n);
       count = 0;
       while (!new_matrix.percolates())
       { 
         x = StdRandom.uniform(1,new_matrix.N);
         y = StdRandom.uniform(1,new_matrix.N);
         if (!new_matrix.isOpen(x,y))
            {new_matrix.open(x,y); count++;}
       }
       p_star = count/((new_matrix.N - 1)*(new_matrix.N - 1)); 
       StdOut.println("p_star = " + p_star);*/
       
   }

}

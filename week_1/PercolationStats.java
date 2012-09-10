public class PercolationStats {
   private double[] experiments;

   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   {   int x,y;
       double count;
       double p_star;
       experiments = new double[T];
       for (int i = 0; i<T; i++) 
       {  count = 0;
          Percolation new_matrix = new Percolation(N);
          while (!new_matrix.percolates())
          { 
            x = StdRandom.uniform(1,N+1);
            y = StdRandom.uniform(1,N+1);
            if (!new_matrix.isOpen(x,y))
               {new_matrix.open(x,y); count++;}
          }
          p_star = count/((N)*(N));
          experiments[i] =  p_star; 
       }
   }
   public double mean(){return StdStats.mean(experiments);}                     // sample mean of percolation threshold
   public double stddev(){return StdStats.stddev(experiments);}                   // sample standard deviation of percolation threshold
   public static void main(String[] args)   // test client, described below
   { 
      double mean, stddev;
      int n,t;
      n = Integer.parseInt(args[0]);
      t = Integer.parseInt(args[1]);
      if ( n > 1 )
      {  PercolationStats perc_stats = new PercolationStats(n,t);
         mean = perc_stats.mean();
         stddev = perc_stats.stddev();
         StdOut.println("mean                    =" + mean);
         StdOut.println("stddev                  =" + stddev);
         StdOut.println("95% confidence interval =" + (mean - (1.96*stddev/Math.sqrt(t))) + ", " + (mean + (1.96*stddev/Math.sqrt(t))) );
      }
      else
         throw new IndexOutOfBoundsException("N must be greater than 1");   
   }
}

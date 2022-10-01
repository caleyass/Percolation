import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class PercolationStats {
    private Percolation p;
    double[] res;
    double avrg ;
    public PercolationStats(int N, int T){
        res = new double[T];
        for(int i=0; i < T; i++){
            p = new Percolation(N);
            do {
                Random rnd = new Random();
                int a, b;
                //generate coordinates until find the blocked site
                do {
                    a = rnd.nextInt(N);
                    b = rnd.nextInt(N);
                } while (p.isOpened(a, b));
                //open it
                p.open(a, b);
            } while (!p.percolates());
            res[i] = (double)p.getOpenedCount()/(N*N);
        }
    }
    // sample mean of percolation threshold
    public double mean(){
        avrg = Arrays.stream(res).sum()/res.length;
        return avrg;
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        double numerator = 0;
        for(int i = 0; i < res.length; i++){
            numerator += Math.pow(res[i] - avrg,2);
        }
        return Math.sqrt(numerator/(res.length-1));
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        PercolationStats percolationStats = new PercolationStats(scanner.nextInt(), scanner.nextInt());
        System.out.println("mean = "+percolationStats.mean());
        System.out.println("stddev = "+percolationStats.stddev());
        System.out.println("95% confidence interval = [" + (percolationStats.mean() - 1.96* percolationStats.stddev()/Math.sqrt(percolationStats.res.length)) + " , "+
                (percolationStats.mean() + 1.96* percolationStats.stddev()/Math.sqrt(percolationStats.res.length)) + "]");
    }
}

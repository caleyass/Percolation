public class Percolation {
    private boolean[][] matrix;
    private int openedCells; // number of opened cells
    QuickFindUF quickUnionUF;
    private int size;
    private boolean percolates;
    /**Creates n-by-n grid, with all sites initially blocked
     * @param N - size of grid*/
    public Percolation(int N){
        matrix = new boolean[N][N];
        size = N;
        quickUnionUF = new QuickFindUF(N*N+2);
        openedCells = 0;
    }
    /**@return number of opened cells*/
    public int getOpenedCount(){
        return openedCells;
    }
    /**Opens the site (row, col) if it is not open already
     * @param i row
     * @param j column*/
    public void open(int i, int j){
        if(!isOpened(i,j)) {
            matrix[i][j] = true;
            int arrayIndex = size * i + j + 1;
            if (i == 0) //if site is in the first row
                quickUnionUF.union(0, arrayIndex);
            if (i == size - 1) //if site is in the last row
                quickUnionUF.union(size * size + 1, arrayIndex);
            if (j < size - 1 && isOpened(i, j + 1)) // if site is not in the right column
                quickUnionUF.union(arrayIndex, arrayIndex + 1); // connect with adjacent right
            if (j > 0 && isOpened(i, j - 1)) // if site is not in the left column
                quickUnionUF.union(arrayIndex, arrayIndex - 1); // connect with adjacent left
            if (i < size - 1 && isOpened(i + 1, j)) // if site is not in the bottom row
                quickUnionUF.union(arrayIndex, arrayIndex + size); // connect with adjacent top
            if (i > 0 && isOpened(i - 1, j)) // if site is not in the top row
                quickUnionUF.union(arrayIndex, arrayIndex - size); // connect with adjacent bottom
            if (quickUnionUF.connected(0, size * size + 1))
                percolates = true;
            openedCells++;
        }
    }
    //is the site (row, col) open?
    public boolean isOpened(int i, int j){
        return matrix[i][j];
    }
    /**
     * @return whether the system percolates*/
    public boolean percolates(){
        return percolates;
    }
}


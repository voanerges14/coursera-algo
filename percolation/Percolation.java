import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private int numberOfOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        numberOfOpenSites = 0;

        if (n > 0) {
            for (int i = 1; i <= grid.length; i++) {
                uf.union(0, coordinateToNumber(1, i));
                uf.union(n * n + 1, coordinateToNumber(grid.length, i));
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        numberOfOpenSites++;
        grid[row - 1][col - 1] = true;

        for (int neighbor : getNeighbors(row, col)) {
            if (neighbor != -1) {
                uf.union(coordinateToNumber(row, col), neighbor);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1] && uf.find(0) == uf.find(coordinateToNumber(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.length == 1) return isOpen(0, 0);
        return uf.find(0) == uf.find(grid.length * grid.length + 1);
    }

    private int[] getNeighbors(int row, int col) {
        int n = grid.length;
        return new int[] {
                row + 1 <= n && isOpen(row + 1, col) ? coordinateToNumber(row + 1, col) : -1,
                row - 1 > 0 && isOpen(row - 1, col) ? coordinateToNumber(row - 1, col) : -1,
                col + 1 <= n && isOpen(row, col + 1) ? coordinateToNumber(row, col + 1) : -1,
                col - 1 > 0 && isOpen(row, col - 1) ? coordinateToNumber(row, col - 1) : -1
        };
    }

    private int coordinateToNumber(int row, int col) {
        return grid.length * (row - 1) + col;
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1 || row > grid.length || col > grid.length) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
    }
}

import java.util.Random;

public class Majority extends AutomateCellulaire{
    public int[][] grille;
    public int dimension;
    public double blackRatio;
    public Majority(int dimension, double blackRatio) {
        this.dimension = dimension;
        this.blackRatio = blackRatio;
        this.grille = new int[dimension][dimension];
    }
    @Override
    public void initialiserGrille() {
        Random random = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.grille[i][j] = (random.nextDouble() < blackRatio) ? 1 : 0;
            }
        }
    }
    @Override
    public void evoluer() {
        int rows = this.grille.length;
        int cols = this.grille[0].length;
        int[][] nextGeneration = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nextGeneration[i][j] = calculateMajority(i, j);
            }
        }
        this.grille = nextGeneration;
    }
    @Override
    public int[][] getGrille() {
        return grille;
    }

    private int calculateMajority(int row, int col) {
        int neighborsCount = 0;
        int rows = this.grille.length;
        int cols = this.grille[0].length;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                int neighborRow = (i + rows) % rows;
                int neighborCol = (j + cols) % cols;
                neighborsCount += this.grille[neighborRow][neighborCol];
            }
        }
        return (neighborsCount > 4) ? 1 : 0;
    }
}

package automates;

import java.util.Random;

public class JeuVie {
	
    public static void Affichr(int row, int col, int repeticoes) {
        int[][] ground = initialiser(row, col);

        for (int g = 0; g < repeticoes; g++) {
            System.out.println("Génération " + g);
            createGame(ground);
            ground = logique(ground);
        }
    }

    private static void createGame(int[][] ground) {
        for (int[] row : ground) {
            for (int cell : row) {
                System.out.print((cell == 1) ? "O " : "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] initialiser(int row, int col) {
        int[][] Ground = new int[row][col];
        Random random = new Random();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Ground[i][j] = random.nextInt(2);
            }
        }

        return Ground;
    }

    private static int[][] logique(int[][] ground) {
        int row = ground.length;
        int col = ground[0].length;
        int[][] newGround = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int voisinsAlive = countVoisinsAlives(ground, i, j);

                if (ground[i][j] == 1) {
                    if (voisinsAlive < 2 || voisinsAlive > 3) {
                        newGround[i][j] = 0;
                    } else {
                        newGround[i][j] = 1;
                    }
                } else {
                    if (voisinsAlive == 3) {
                        newGround[i][j] = 1;
                    }
                }
            }
        }

        return newGround;
    }

    private static int countVoisinsAlives(int[][] ground, int row, int col) {
        int voisinsAlive = 0;
        int rows = ground.length;
        int cols = ground[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < rows && newCol >= 0
                        && newCol < cols) {
                    voisinsAlive += ground[newRow][newCol];
                }
            }
        }

        voisinsAlive -= ground[row][col];

        return voisinsAlive;
    }

}

import java.util.Random;
public class JeuVie extends AutomateCellulaire{
    int[][] grille;
    int dimension;

    /**
     * Constructeur de la classe JeuVie.
     * @param dimension La dimension du tableau du jeu de la vie.
     */
	public JeuVie(int dimension){
        this.dimension=dimension;
        this.grille = new int[dimension][dimension];
    }

    /**
     * Initialise la grille du jeu de la vie avec des valeurs aléatoires (0 ou 1).
     */
    @Override
    public void initialiserGrille() {
        Random random = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.grille[i][j] = random.nextInt(2);
            }
        }
    }

    /**
     * Fait évoluer le jeu de la vie d'une génération.
     */
    @Override
    public void evoluer(){
        int row = this.grille.length;
        int col = this.grille[0].length;
        int[][] newGround = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int voisinsAlive = countVoisinsAlives(i, j);

                if (this.grille[i][j] == 1) {
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
        this.grille=newGround;
    }

    /**
     * Récupère la grille actuelle du jeu de la vie.
     *
     * @return La grille actuelle du jeu de la vie.
     */
    @Override
    public int[][] getGrille(){
        return this.grille;
    }

    /**
    * Compte le nombre de voisins vivants autour d'une cellule donnée.
    * @param row L'indice de ligne de la cellule.
    * @param col L'indice de colonne de la cellule.
    * @return Le nombre de voisins vivants autour de la cellule.
    */
    private int countVoisinsAlives(int row, int col) {
        int voisinsAlive = 0;
        int rows = this.grille.length;
        int cols = this.grille[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < rows && newCol >= 0
                        && newCol < cols) {
                    voisinsAlive += this.grille[newRow][newCol];
                }
            }
        }
        voisinsAlive -= this.grille[row][col];
        return voisinsAlive;
    }

}

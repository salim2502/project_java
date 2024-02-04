import java.util.Random;
/**
 * La classe FeuDeForet représente un automate cellulaire modélisant la propagation du feu dans une forêt.
 * Elle hérite de la classe AutomateCellulaire.
 */
public class FeuDeForet extends AutomateCellulaire {
    /**
     * Constantes pour les états des cellules dans la forêt.
     */
    public static final int VIDE = 0;
    public static final int FORET = 2;
    public static final int EN_FEU = 3;
    public static final int BRULEE = 4;

    /**
     * La dimension de la grille représentant la forêt.
     */
    private int dimension;

    /**
     * La grille représentant l'état de chaque cellule dans la forêt.
     */
    private int[][] grille;

    /**
     * Les voisins possibles d'une cellule dans la forêt.
     */
    private int[][] voisins;

    /**
     * La probabilité de propagation du feu d'une cellule à une cellule adjacente.
     */
    private double ProbProg;

    /**
     * La probabilité de brûler une cellule de forêt.
     */
    private double ProbBrul;

    /**
     * La force du vent horizontale, influençant la propagation du feu dans la direction horizontale.
     */
    private double forceVentHorizontale;

    /**
     * La force du vent verticale, influençant la propagation du feu dans la direction verticale.
     */
    private double forceVentVerticale;
    /**
     * Constructeur de la classe FeuDeForet.
     *
     * @param dimension          La dimension de la forêt (nombre de cellules en largeur et hauteur).
     * @param nbredeVoisins      Le nombre de voisins à considérer pour chaque cellule (4 ou 8).
     * @param ProbProg           La probabilité de propagation du feu d'une cellule à une cellule adjacente.
     * @param ProbBrul           La probabilité de brûler une cellule de forêt.
     * @param forceVentHorizontale   La force du vent horizontale.
     * @param forceVentVerticale     La force du vent verticale.
     */
    public FeuDeForet(int dimension,int nbredeVoisins, double ProbProg, double ProbBrul, double forceVentHorizontale, double forceVentVerticale) {
        this.dimension = dimension;
        this.ProbProg = ProbProg;
        this.ProbBrul = ProbBrul;
        this.forceVentHorizontale = forceVentHorizontale;
        this.forceVentVerticale = forceVentVerticale;
        this.grille = new int[dimension][dimension];
        if (nbredeVoisins ==4){
            this.voisins = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        } else if (nbredeVoisins ==8) {
            this.voisins = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1},{-1, -1}, {1,1}, {1, -1}, {-1, 1}};
        }
    }
    /**
     * Initialise la grille de la forêt avec des cellules de forêt et une cellule en feu de manière aléatoire.
     */
    @Override
    public void initialiserGrille() {
        Random random = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grille[i][j] = random.nextBoolean() ? FORET : VIDE;
            }
        }
        int feuX = random.nextInt(dimension);
        int feuY = random.nextInt(dimension);
        grille[feuX][feuY] = EN_FEU;
    }
    /**
     * Évolue l'état de la forêt selon les règles définies.
     */
    @Override
    public void evoluer() {
        int[][] nouvelleGrille = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int etatCourant = grille[i][j];
                int prochainEtat = etatCourant;

                if (etatCourant == FORET) {
                    if (voisinEnFeu(i, j)) {
                        double probPropagationHorizontale = ProbProg + (forceVentHorizontale * (Math.random() - 0.5));
                        double probPropagationVerticale = ProbProg + (forceVentVerticale * (Math.random() - 0.5));

                        if (Math.random() < probPropagationHorizontale) {
                            prochainEtat = EN_FEU;
                        } else if (Math.random() < probPropagationVerticale) {
                            prochainEtat = EN_FEU;
                        }
                    }
                } else if (etatCourant == EN_FEU) {
                    prochainEtat = BRULEE;
                }

                if (etatCourant == FORET && Math.random() < ProbBrul) {
                    prochainEtat = EN_FEU;
                }

                nouvelleGrille[i][j] = prochainEtat;
            }
        }
        grille = nouvelleGrille;
    }

    public boolean voisinEnFeu(int x, int y) {
        for (int[] voisin : voisins) {
            int voisinX = (x + voisin[0] + dimension) % dimension;
            int voisinY = (y + voisin[1] + dimension) % dimension;

            if (grille[voisinX][voisinY] == EN_FEU) {
                return true;
            }
        }
        return false;
    }
    /**
     * Obtient la grille représentant l'état actuel de la forêt.
     *
     * @return La grille représentant la forêt.
     */
    @Override
    public int[][] getGrille() {
        return grille;
    }
}

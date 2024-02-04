import java.util.Random;

public class FeuDeForet extends AutomateCellulaire {

    public static final int VIDE = 0;
    public static final int FORET = 2;
    public static final int EN_FEU = 3;
    public static final int BRULEE = 4;

    private int dimension;
    private int[][] grille;
    private int[][] voisins;
    private double ProbProg;
    private double ProbBrul;
    private double forceVentHorizontale;
    private double forceVentVerticale;
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
    @Override
    public int[][] getGrille() {
        return grille;
    }
}

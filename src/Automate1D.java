public class Automate1D extends AutomateCellulaire{
    private int regleLocale;
    private int dimension;
    private int[][] grille;
    private int t=0;
    public Automate1D(int dimension, int regleLocale) {
        this.dimension = dimension;
        this.regleLocale = regleLocale;
        this.grille = new int[dimension][];
    }
    @Override
    public void initialiserGrille(){
        int[] configurationActuelle = new int[dimension];
        configurationActuelle[dimension / 2] = 1;
        this.grille[t] = configurationActuelle;

        //Mets des 0s sur le reste de lignes pour l'instant
        for (int i = t + 1; i < this.grille.length; i++) {
            this.grille[i] = new int[dimension];
        }
    }
    @Override
    public void evoluer() {
        int[] configurationActuelle = this.grille[t];
        int[] nouvelleConfiguration = new int[dimension];

        for (int i = 0; i < dimension; i++) {
            int gauche = (i - 1 + dimension) % dimension;
            int droite = (i + 1) % dimension;

            nouvelleConfiguration[i] = getBit(regleLocale, new int[]{configurationActuelle[gauche], configurationActuelle[i], configurationActuelle[droite]});
        }
        t++;
        if (t >= this.grille.length) {
            // If t exceeds the array length, shift values up and leave space at the end
            for (int i = 0; i < this.grille.length - 1; i++) {
                this.grille[i] = this.grille[i + 1];
            }
            this.grille[this.grille.length - 1] = nouvelleConfiguration;
            t--;
        } else {
            this.grille[t] = nouvelleConfiguration;
        }
    }

    public int[][] getGrille(){
        return this.grille;
    }

    private int getBit(int value, int[] voisinage) {
        int position = voisinage[2] + 2 * voisinage[1] + 4 * voisinage[0];
        return (value >> position) & 1;
    }
}

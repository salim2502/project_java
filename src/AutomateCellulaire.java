public class AutomateCellulaire {
    public int tailleGrille;
    public int[] alphabet;
    public int[] voisinage;

    public AutomateCellulaire(int tailleGrille, int[] alphabet, int[] voisinage) {
        this.tailleGrille = tailleGrille;
        this.alphabet = alphabet;
        this.voisinage = voisinage;
    }

    public int[] evoluer(int[] configurationActuelle) {
        return configurationActuelle;
    }
}

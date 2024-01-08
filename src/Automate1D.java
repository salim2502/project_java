public class Automate1D extends AutomateCellulaire{
    private int regleLocale;

    public Automate1D(int tailleGrille, int[] alphabet, int[] voisinage, int regleLocale) {
        super(tailleGrille, alphabet, voisinage);
        this.regleLocale = regleLocale;
    }

    public  void afficherTableauBits(int[] tableauBits) {
        for (int bit : tableauBits) {
            System.out.print(bit);
        }
        System.out.println();
    }


    public void afficherLignediag(int[] configuration) {
        for (int i = 0; i < tailleGrille; i++) {
            System.out.print((configuration[i] == 1) ? 'X' : ' ');
        }
        System.out.println();
    }

    public void afficherLigne(int[] configuration){
        int milieu = tailleGrille / 2;
        for (int i = 0; i < tailleGrille; i++){
            int index = (i + milieu) % tailleGrille; // Ajuster pour centrer le sommet

            System.out.print((configuration[index] == 1) ? 'X' : ' ');
        }
        System.out.println();
    }

    public boolean estDiagonale(int[] regleLocale) {
        int nbUn = 0;
        for (int i : regleLocale) {
            if (i == 1) {
                nbUn++;
                if (nbUn > 2) {
                    return false;
                }
            } else {
                nbUn = 0;
            }
        }

        return true;
    }

    @Override
    public int[] evoluer(int[] configurationActuelle) {
        int[] nouvelleConfiguration = new int[tailleGrille];

        for (int i = 0; i < tailleGrille; i++) {
            // Construire le voisinage actuel pour chaque cellule
            int gauche = (i - 1 + tailleGrille) % tailleGrille;
            int droite = (i + 1) % tailleGrille;

            // Application de la règle locale
            nouvelleConfiguration[i] = getBit(regleLocale, new int[]{configurationActuelle[gauche], configurationActuelle[i], configurationActuelle[droite]});
        }

        return nouvelleConfiguration;
    }

    private int getBit(int value, int[] voisinage) {
        int position = voisinage[2] + 2 * voisinage[1] + 4 * voisinage[0];
        return (value >> position) & 1;
    }
}

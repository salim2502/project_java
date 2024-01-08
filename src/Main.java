// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int tailleGrille = 50;
        int[] alphabet = {0, 1};
        int[] voisinage = {0, 1, 0};
        int regleLocale =17 ;

        Automate1D automate1D = new Automate1D(tailleGrille, alphabet, voisinage, regleLocale);

        int[] configurationActuelle = new int[tailleGrille];
        configurationActuelle[tailleGrille-1] = 1;
        boolean diag =automate1D.estDiagonale(automate1D.evoluer(configurationActuelle));

        for (int i = 0; i < tailleGrille; i++) {
            //automate1D.afficherTableauBits(configurationActuelle);
            if (diag){
                automate1D.afficherLignediag(configurationActuelle);
            }
            else{
                automate1D.afficherLigne(configurationActuelle);
            }

            configurationActuelle = automate1D.evoluer(configurationActuelle);
        }
    }
}
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AutomateCellulaire automate = null;
        // Affichage du menu
        System.out.println("Choisissez un automate:");
        System.out.println("1. 1D");
        System.out.println("2. Regle de Majorite");
        System.out.println("3. Jeu de la Vie");
        System.out.println("4. FeuDeForet");

        int dimension=10;
        System.out.print("Votre choix: ");
        int choixAutomate = scanner.nextInt();

        switch (choixAutomate){
            case 1:
                System.out.print("Dimension (par défaut 10): ");
                dimension = scanner.nextInt();
                System.out.print("Règle locale: ");
                int regle = scanner.nextInt();
                automate = new Automate1D(dimension,regle);
                break;
            case 2:
                System.out.print("Dimension (par défaut 10): ");
                dimension = scanner.nextInt();
                double blackRatio;
                while (true) {
                    System.out.print("Ratio des 1 au début (entre 0 et 1): ");
                    blackRatio = scanner.nextDouble();
                    if (blackRatio >= 0 && blackRatio <= 1) {
                        break;
                    } else {
                        System.out.println("Veuillez entrer une valeur entre 0 et 1.");
                    }
                }
                automate = new Majority(dimension,blackRatio);
                break;
            case 3:
                System.out.print("Dimension (par défaut 30): ");
                dimension = scanner.nextInt();
                automate = new JeuVie(dimension);
                break;
            case 4:
                System.out.print("Dimension (par défaut 30): ");
                dimension = scanner.nextInt();
                int NbredeVoisin;
                while (true) {
                    System.out.print("Nombre de voisins (4, 6 ou 8): ");
                    NbredeVoisin = scanner.nextInt();
                    if (NbredeVoisin == 4 || NbredeVoisin == 6 || NbredeVoisin == 8) {
                        break;
                    } else {
                        System.out.println("Choix invalide. Veuillez choisir entre 4, 6 ou 8.");
                    }
                }

                System.out.print("Probabilité de propagation (par exemple 0,5): ");
                double probabilitePropagation = scanner.nextDouble();

                System.out.print("Probabilité de brûlure (par exemple 0,001): ");
                double probabiliteBrulure = scanner.nextDouble();

                System.out.print("Force du vent horizontale (par défaut 5): ");
                double forceVentHorizontale = scanner.nextDouble();

                System.out.print("Force du vent verticale (par défaut 0): ");
                double forceVentVerticale = scanner.nextDouble();

                System.out.print("Affichage...");
                automate = new FeuDeForet(dimension, NbredeVoisin, probabilitePropagation, probabiliteBrulure, forceVentHorizontale, forceVentVerticale);
                break;
            default:
                System.out.println("Choix d'automate non pris en charge pour l'instant.");
        }
        Affichage affichage = new Affichage(automate);
        scanner.close();
        }
    }
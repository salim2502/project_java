import javax.swing.*;
import java.awt.*;
public class Graphique extends JFrame{
    private final Automate1D automate1D;
    private  int[] configurationActuelle;

    private final int taille_grille;

    public Graphique(Automate1D automate1D, int[] configurationActuelle,int taille_grille) {
        this.automate1D = automate1D;
        this.taille_grille = taille_grille;
        this.configurationActuelle = configurationActuelle;

        setTitle("Automate 1D GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerAutomate(g);
            }
        };

        add(panel);
        setVisible(true);
        //afficherAutomateFinal();
    }

    public void dessinerAutomate(Graphics g){
        int cellSize = getWidth() / (this.taille_grille);
        int ligneHeight = getHeight() / (this.taille_grille);
        int x;
        int y;
        int milieu = (this.taille_grille/2)+1;
        int index;
        boolean estActive = false;
        boolean diag =this.automate1D.estDiagonale(automate1D.evoluer(configurationActuelle));
        for (int ligne = 0; ligne < this.taille_grille; ligne++) {
            //this.automate1D.afficherTableauBits(configurationActuelle);
            x=0;
            y =ligneHeight*ligne;
            for (int i = 0; i<configurationActuelle.length;i++){
                index = (i + milieu) % (this.taille_grille);
                x =i*cellSize;
                estActive = configurationActuelle[index] == 1;
                //if (estActive){System.out.println(index);}

                dessinerCellule(g, x, y, cellSize, ligneHeight, estActive);
            }


            configurationActuelle = automate1D.evoluer(configurationActuelle);
        }
    }

    private void dessinerCellule(Graphics g, int x, int y, int largeur, int hauteur, boolean estActive) {
        if (estActive) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(x, y, largeur, hauteur);
    }

}

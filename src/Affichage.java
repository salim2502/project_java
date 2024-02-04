import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe Affichage étend JFrame et représente l'interface graphique pour afficher un automate cellulaire.
 */
public class Affichage extends JFrame {

    private AutomateCellulaire automate;
    private JPanel grillePanel;
    private JButton evoluerButton;
    private JLabel tempsLabel;
    private int temps = -1;
    private int valeurDeuxInitiale;
    private boolean premierDeuxDetecte = false;

    /**
     * Constructeur de la classe Affichage.
     *
     * @param automate L'automate cellulaire à afficher.
     */
    public Affichage(AutomateCellulaire automate) {
        this.automate = automate;
        this.automate.initialiserGrille();
        initialize();
    }

    /**
     * Initialise l'interface graphique en configurant les composants Swing.
     */
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Automate Frame");

        grillePanel = new JPanel();
        updateGrillePanel();
        add(grillePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        evoluerButton = new JButton("Évoluer");
        evoluerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automate.evoluer();
                updateGrillePanel();
                updateTempsLabel();
            }
        });
        bottomPanel.add(evoluerButton, BorderLayout.SOUTH);

        tempsLabel = new JLabel("Temps: " + temps);
        tempsLabel.setHorizontalAlignment(JLabel.RIGHT);
        bottomPanel.add(tempsLabel, BorderLayout.NORTH);

        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setSize(700, 700);
        setVisible(true);
    }

    /**
     * Met à jour le panneau d'affichage de la grille dans l'interface graphique.
     */
    private void updateGrillePanel() {
        temps++;
        int[][] grille = automate.getGrille();
        grillePanel.removeAll();
        grillePanel.setLayout(new GridLayout(grille.length, grille[0].length));

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (grille[i] != null) {
                    setColor(label, grille[i][j]);
                    grillePanel.add(label);

                    // Détecter le premier 2
                    if (!premierDeuxDetecte && grille[i][j] == 2) {
                        valeurDeuxInitiale = compterValeur(2);
                        premierDeuxDetecte = true;
                    }
                }
            }
        }

        grillePanel.revalidate();
        grillePanel.repaint();
    }

    /**
     * Met à jour l'affichage du label de temps dans l'interface graphique.
     */
    private void updateTempsLabel() {
        if (premierDeuxDetecte) {
            int valeurDeuxActuelle = compterValeur(2);
            int valeurQuatreActuelle = compterValeur(4);

            double pourcentage = (valeurQuatreActuelle / (double) valeurDeuxInitiale) * 100;

            tempsLabel.setText("Temps: " + temps + " - Pourcentage de bruleur par rapport à la densité de foret initial: " + pourcentage + "%");
        } else {
            tempsLabel.setText("Temps: " + temps);
        }
    }

    /**
     * Compte le nombre d'occurrences d'une valeur donnée dans la grille de l'automate.
     *
     * @param valeur La valeur à compter dans la grille.
     * @return Le nombre d'occurrences de la valeur dans la grille.
     */
    private int compterValeur(int valeur) {
        int[][] grille = automate.getGrille();
        int count = 0;

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (grille[i] != null && grille[i][j] == valeur) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Définit la couleur d'un label en fonction de l'état spécifié.
     *
     * @param label Le label dont la couleur doit être définie.
     * @param etat  L'état qui détermine la couleur.
     */
    private void setColor(JLabel label, int etat) {
        label.setOpaque(true);
        switch (etat) {
            case 0:
                label.setBackground(Color.WHITE);
                break;
            case 1:
                label.setBackground(Color.BLACK);
                break;
            case 2:
                label.setBackground(new Color(5, 152, 0));
                break;
            case 3:
                label.setBackground(new Color(255, 206, 0));
                break;
            case 4:
                label.setBackground(new Color(160, 19, 0));
                break;
            default:
                label.setBackground(Color.WHITE);
                break;
        }
    }
}

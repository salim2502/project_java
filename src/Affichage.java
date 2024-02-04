import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Affichage extends JFrame {

    private AutomateCellulaire automate;
    private JPanel grillePanel;
    private JButton evoluerButton;

    public Affichage(AutomateCellulaire automate) {
        this.automate = automate;
        this.automate.initialiserGrille();
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Automate Frame");

        grillePanel = new JPanel();
        updateGrillePanel();
        add(grillePanel, BorderLayout.CENTER);

        evoluerButton = new JButton("Évoluer");
        evoluerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automate.evoluer();
                updateGrillePanel();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(evoluerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setSize(700, 700);
        setVisible(true);
    }

    private void updateGrillePanel() {
        int[][] grille = automate.getGrille();
        grillePanel.removeAll();
        grillePanel.setLayout(new GridLayout(grille.length, grille[0].length));

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(grille[i]!=null){
                setColor(label, grille[i][j]);
                grillePanel.add(label);}
            }
        }

        grillePanel.revalidate();
        grillePanel.repaint();
    }

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
                label.setBackground(new Color(255,206,0));
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

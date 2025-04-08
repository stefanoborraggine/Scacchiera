import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Scacchiera extends JFrame {
  private static final long serialVersionUID = 1L;
  
  private static final int ROW = 4;
  private final JButton[][] caselle = new JButton[ROW][ROW];
  private final int[] colonne = new int[ROW];
  private final ImageIcon imgRegina;

  public Scacchiera() {
    imgRegina = new ImageIcon("/home/john/4SA-2024/queen-64.png");
    for (int i = 0; i < ROW; i++) {
      colonne[i] = -1;
    }
    this.initScacchiera();
  }

  private void initScacchiera() {
    setSize(800, 800);
    setLayout(new GridLayout(ROW, ROW));

    for (int r = 0; r < ROW; r++) {
      for (int c = 0; c < ROW; c++) {
        JButton tempButton = new JButton();
        int resto = (r + c) % 2;
        tempButton.setBackground(1 == resto ? Color.black : Color.white);
        caselle[r][c] = tempButton;
        add(tempButton);
      }
    }

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void cercaSoluzione() {
    int numRegina = 0;
    while (numRegina < ROW) {
      this.colonne[numRegina] = this.colonne[numRegina] + 1;
      if (this.colonne[numRegina] < ROW) {
        if (this.reginaOk(numRegina)) {
          numRegina++;
        }
      } else {
        // non ho trovato una posizione per la regina numRegina, quindi
        // torno a cambiare la posizione della regina precedente
        this.colonne[numRegina] = -1;
        
        numRegina--;
      }
      this.disegnaRegine();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException exc) {
        // ignoro l'eccezione
      }
    }
  }

  
  private boolean reginaOk(int numRegina) {
    // due regine non possono occupare la stessa colonna
    final int colonnaReginaAttuale = this.colonne[numRegina];
    for (int r = 0; r < numRegina; r++) {
      if (colonnaReginaAttuale == this.colonne[r]) {
        return false;
      }
    }
    
    // due regine non possono stare sulla stessa diagonale principale
    final int diagonalePrincipaleReginaAttuale = numRegina - this.colonne[numRegina];
    for (int r = 0; r < numRegina; r++) {
      if (diagonalePrincipaleReginaAttuale == r - this.colonne[r]) {
        return false;
      }
    }
    
    // due regine non possono stare sulla stessa diagonale secondaria
    final int diagonaleSecondariaReginaAttuale = numRegina + this.colonne[numRegina];
    for (int r = 0; r < numRegina; r++) {
      if (diagonaleSecondariaReginaAttuale == r + this.colonne[r]) {
        return false;
      }
    }
    
    return true;
  }
  
  
  private void disegnaRegine() {
    for (int r = 0; r < ROW; r++) {
      for (int c= 0; c < ROW; c++) {
        this.caselle[r][c].setIcon(null);
      }
      if (-1 != colonne[r]) {
        this.caselle[r][colonne[r]].setIcon(this.imgRegina);
      }
    }
  }
}

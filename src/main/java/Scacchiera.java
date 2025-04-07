import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Scacchiera extends JFrame {
  private static final long serialVersionUID = 1L;
  
  private static final int ROW = 8;
  private final JButton[][] caselle = new JButton[ROW][ROW];
  private final int[] posizioni = new int[ROW];
  private final ImageIcon imgRegina;

  public Scacchiera() {
    imgRegina = new ImageIcon("/home/john/4SA-2024/queen-64.png");
    for (int i = 0; i < ROW; i++) {
      posizioni[i] = -1;
    }
    this.initScacchiera();

    posizioni[0] = 0;
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
    int tentativo = 0;
    while (tentativo < 100) {
      this.posizioni[0] = (this.posizioni[0] + 1) % ROW;
      this.disegnaRegine();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException exc) {
        // ignoro l'eccezione
      }
      tentativo++;
    }
  }

  private void disegnaRegine() {
    for (int r = 0; r < ROW; r++) {
      for (int c= 0; c < ROW; c++) {
        this.caselle[r][c].setIcon(null);
      }
      if (-1 != posizioni[r]) {
        this.caselle[r][posizioni[r]].setIcon(this.imgRegina);
      }
    }
  }
}

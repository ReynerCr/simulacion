package UI;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.AlphaComposite;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ayuntamiento extends JPanel {

    private Image[] frames;
    private int frameIndex = 0;

    public Ayuntamiento(int x,int y){
        setBounds(x,y,96,96);
        setOpaque(false);

        frames = new Image[3];
        frames[0] = new ImageIcon("src/assets/Ayuntamiento/1.png").getImage();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
             
                showHola();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
    
      
        // Dibujar la imagen actual en la posición de la Aldea
        g2d.drawImage(frames[frameIndex], 0, 0, 96, 96, this);
    
        g2d.dispose();
    }

    public void updateImage() {
     
        repaint(); // Repintar el componente para mostrar la nueva imagen    
        
        
    }

    public void showHola() {
        System.out.println("H"); // Método para mostrar "Hola" en la consola
    
    }
}

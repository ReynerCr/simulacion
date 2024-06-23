import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.AlphaComposite;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Aldea extends JPanel {

    private int frameIndex = 0;
    private Image[] frames;
    private int cantidad = 0;
    private int max = 10;

    public Aldea(int x, int y) {
        setBounds(x, y, 96, 96);
        setOpaque(false);
        // Cargar las imágenes en el arreglo frames
        frames = new Image[3]; // Assuming you have 3 frames
        frames[0] = new ImageIcon("assets/Mineria/1.png").getImage();
        frames[1] = new ImageIcon("assets/Mineria/2.png").getImage();
        frames[2] = new ImageIcon("assets/Mineria/1.png").getImage();
    
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
        int entrada = cantidad + 1;
        if(max > entrada){
            frameIndex = (frameIndex + 1) % 3; // Ciclo entre las imágenes
            repaint(); // Repintar el componente para mostrar la nueva imagen    
        } 
        
        }

        public void showHola() {
            System.out.println("Hola"); // Método para mostrar "Hola" en la consola
            this.cantidad=0;
        }
}

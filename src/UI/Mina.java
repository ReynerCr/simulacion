package UI;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.AlphaComposite;

import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class Mina extends JPanel {

    private Image[] frames;
    private int frameIndex = 0;
    private int cantidad;
    private int nivel;

    public Mina(int x,int y,int oro){
        setBounds(x,y,96,96);
        setOpaque(false);
        this.cantidad=oro;
        this.nivel=0;
        frames = new Image[3];
        frames[0] = new ImageIcon("src/assets/Mineria/1.png").getImage();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
             
                showHola();
            }
        });
    }

    public void SetOro(int cantidad){
        this.cantidad=cantidad;
    }

    public void SetNivel(int nivel){
        this.nivel=nivel;
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
        JDialog dialog = new JDialog();
        dialog.setTitle("Mensaje");
        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);

        
        JLabel label = new JLabel("Nivel de la mina: " + nivel + ".  Cantidad oro:" + cantidad);
        label.setBounds(10, 10, 200, 10);
        dialog.add(label);


        JButton closeButton = new JButton("Cerrar");
        closeButton.setBounds(30, 30, 100, 30);
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton);

        dialog.setVisible(true);
    }
}

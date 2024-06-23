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

public class AlmacenElixir extends JPanel {

    private Image[] frames;
    private int frameIndex = 0;
    private int nivel = 0;
    private int cantidad = 0;

    public AlmacenElixir(int x,int y){
        setBounds(x,y,96,96);
        setOpaque(false);

        frames = new Image[3];
        frames[0] = new ImageIcon("src/assets/AlmacenElixir/1.png").getImage();
        
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

    public void setNivel(int nivel){
        this.nivel=nivel;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public void showHola() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Mensaje");
        dialog.setSize(700, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(null);

    

        JLabel label = new JLabel("Nivel del almacén: " + nivel + ".  Cantidad elixir:" + cantidad);
        label.setBounds(10, 10, 500, 10);
        dialog.add(label);


        JButton closeButton = new JButton("Cerrar");
        closeButton.setBounds(30, 30, 100, 30);
        closeButton.addActionListener(e -> dialog.dispose());
        dialog.add(closeButton);

        dialog.setVisible(true);
    }
}

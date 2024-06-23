import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import java.awt.event.MouseEvent;

import UI.AlmacenElixir;
import UI.AlmacenOro;
import UI.Ayuntamiento;
import UI.Extractor;
import UI.Mina;
import Aldea.Aldea;
import UI.Campamento;
import UI.Cuartel;

public class Main extends JPanel {

    private JMenuBar menuBar;
    private JMenuItem newGameItem;
    private JMenuItem aboutGameItem;
    private JMenuItem exitItem;
    private JFrame frame;
    private JLabel fondo;
    private Mina mina;
    private AlmacenElixir almacenElixir;
    private Ayuntamiento ayuntamiento;
    private Extractor extractor;
    private AlmacenOro almacenOro;
    private char value;
    private Aldea aldea;
    private Campamento campamento;
    private Cuartel cuartel;

    public Main(Simulation sim) {
        this.aldea = sim.GetAldea();
        initializeMenu();
        initializeFrame();
        setLayout(new BorderLayout());

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                value = 'v';
                System.out.println("Hola");
            }
        });
    }

    public char getValue(){
        return this.value;
    }

    private void initializeMenu() {
        menuBar = new JMenuBar();
        
        JMenu optionsMenu = new JMenu("Opciones");
        newGameItem = new JMenuItem("Partida Nueva");
        aboutGameItem = new JMenuItem("Acerca del Juego");
        exitItem = new JMenuItem("Salir");

        optionsMenu.add(newGameItem);
        optionsMenu.add(aboutGameItem);
        optionsMenu.add(exitItem);
        
        menuBar.add(optionsMenu);
    }

    public void Update(){
        System.out.println(aldea.getMina().getAcumulado());
        mina.SetOro(aldea.getMina().getAcumulado());
        mina.SetNivel(aldea.getMina().getNivel());
        almacenOro.setCantidad(aldea.getAlmacenOro().getAcumulado());
        almacenOro.setNivel(aldea.getAlmacenOro().getNivel());
        almacenElixir.setCantidad(aldea.getAlmacenElixir().getAcumulado());
        almacenElixir.setNivel(aldea.getAlmacenElixir().getNivel());
        extractor.setNivel(aldea.getExtractor().getNivel());
        extractor.setCantidad(aldea.getExtractor().getAcumulado());
        campamento.setNivel(aldea.getCampamento().getNivel());
        campamento.setCantidad(aldea.getCampamento().getCantidadActualCampamento());
        cuartel.setNivel(aldea.getCuartel().getNivel());
        cuartel.setCantidad(aldea.getCuartel().getColaEntrenamiento());
    }

    private void initializeFrame() {
        frame = new JFrame("Mini Tennis");
        frame.setJMenuBar(menuBar);

        // Load the background image
        ImageIcon fondoIcon = new ImageIcon("src/assets/fondo.JPG");
        fondo = new JLabel(fondoIcon);
        fondo.setLayout(null); // Use absolute positioning

        // Get image dimensions and set frame size accordingly
        int imgWidth = fondoIcon.getIconWidth();
        int imgHeight = fondoIcon.getIconHeight();
        
        frame.setSize(imgWidth, imgHeight + 50); // Additional space for menu bar
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mina = new Mina(200, 200,aldea.getAlmacenOro().getAcumulado());
        campamento = new Campamento(200,296);
        almacenElixir = new AlmacenElixir(296,200);
        ayuntamiento = new Ayuntamiento(392,200);
        extractor = new Extractor(488,200);
        almacenOro = new AlmacenOro(584,200); 
        cuartel = new Cuartel(296, 296);

        fondo.add(cuartel);
        fondo.add(campamento);
        fondo.add(mina);
        fondo.add(almacenOro);
        fondo.add(almacenElixir);
        fondo.add(ayuntamiento);
        fondo.add(extractor);

        frame.add(fondo);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.RED);
        g2d.fillOval(0, 0, 120, 120);
        g2d.drawOval(0, 50, 30, 30);        
        g2d.fillRect(50, 0, 30, 30);
        g2d.drawRect(50, 50, 30, 30);
        g2d.draw(new Ellipse2D.Double(0, 100, 30, 30));
    }

    public static void main(String[] args) {
      
    }
}

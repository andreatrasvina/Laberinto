import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Ventana extends JFrame implements KeyListener{

    public int px=5;
    public int py=5;
    int anteriorPx, anteriorPy;

    ArrayList <Rect> paredes = new ArrayList<>();

    private int[][] laberinto = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}
    };

    public Ventana() {
        //PROPIEDADES VENTANA
        this.setTitle("LABERINTO");
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        addKeyListener(this);

        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel marco = new JPanel(new BorderLayout());
        marco.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
        marco.setBackground(Color.decode("#c6dee9"));
        marco.add(panel, BorderLayout.CENTER);

        marco.add(new MiPanel());


        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setFont(new Font("Arial", Font.BOLD, 15));
        btnReiniciar.setPreferredSize(new Dimension(120, 40));
        btnReiniciar.setBackground(Color.WHITE);
        btnReiniciar.setForeground(Color.BLACK);
        btnReiniciar.setFocusable(false);

        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                px=5;
                py=5;

                repaint();
                revalidate();

            }
        });

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(Color.decode("#c6dee9"));
        panelInferior.add(btnReiniciar);

        marco.add(panelInferior, BorderLayout.SOUTH);

        this.add(marco, BorderLayout.CENTER);

        this.repaint();
        this.revalidate();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub



    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        System.out.println(e.getKeyCode());

        anteriorPx = px;
        anteriorPy = py;

        if(e.getKeyCode() == 87 && py > 0) {
            py = py - 1;
            colision();
        }
        if(e.getKeyCode() == 83 && py < 430) {
            py = py + 1;
            colision();
        }
        if(e.getKeyCode() == 65 && px > 0) {
            px = px - 1;
            colision();
        }
        if(e.getKeyCode() == 68 && px < 460) {
            px = px + 1;
            colision();
        }



        this.repaint();
        this.revalidate();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void colision(){

        Rect r = new Rect(px, py, 5, 5, Color.black);

        for (Rect pared : paredes) {
            if (r.colision(pared)) {

                px = anteriorPx;
                py = anteriorPy;
                break;
            }
        }
    }

    class MiPanel extends JPanel {

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            //JUGADOR
            Rect r = new Rect(px, py, 5, 5, Color.red);
            g.setColor(r.c);
            g.fillRect(r.x, r.y, r.w, r.h);

            //PAREDES
            for(int i = 0; i < laberinto.length; i++) {
                for(int j = 0; j < laberinto[i].length; j++) {
                    if(laberinto[i][j] == 1) {
                        //CREA OBJETO RECT Y LO AGREGA AL ARRAY
                        Rect pared = new Rect(j * 5, i * 5, 5, 5, Color.black);
                        paredes.add(pared);
                    }
                }
            }

            //SE PINTAN PAREDES
            for (Rect pared : paredes) {
                g.setColor(pared.c);
                g.fillRect(pared.x, pared.y, pared.w, pared.h);

            }

        }
    }

    public class Rect{
        int x, y, w, h;
        Color c = Color.black;

        Rect(int x, int y, int w, int h, Color c) {

            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
            this.c=c;

        }

        public Boolean colision(Rect target) {

            if(this.x < target.x + target.w &&
                    this.x + this.w > target.x      &&

                    this.y < target.y + target.h &&
                    this.y + this.h > target.y) {
                return true;
            }

            return false;
        }
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ventana extends JFrame implements KeyListener{

    public int px=230;
    public int py=230;
    int anteriorPx, anteriorPy;

    public Ventana() {
        //PROPIEDADES VENTANA
        this.setTitle("ROMPECABEZAS NUMERICO");
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

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
            py = py - 10;
            colision();
        }
        if(e.getKeyCode() == 83 && py < 430) {
            py = py + 10;
            colision();
        }
        if(e.getKeyCode() == 65 && px > 0) {
            px = px - 10;
            colision();
        }
        if(e.getKeyCode() == 68 && px < 460) {
            px = px + 10;
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

        Rect r = new Rect(px, py, 20, 20, Color.black);
        Rect p = new Rect(0,0,10,600, Color.blue);

        if (r.colision(p)) {

            px = anteriorPx;
            py = anteriorPy;
        }
    }

    class MiPanel extends JPanel {

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            //JUGADOR
            Rect r = new Rect(px, py, 10, 10, Color.red);
            g.setColor(r.c);
            g.fillRect(r.x, r.y, r.w, r.h);

            //PAREDES
            Rect p1 = new Rect(0, 0, 10, 500, Color.black);
            g.setColor(p1.c);
            g.fillRect(p1.x, p1.y, p1.w, p1.h);

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

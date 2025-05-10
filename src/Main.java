import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main extends JFrame {
    private ControlWindow cw = new ControlWindow();

    public Main() {
        this.add(cw);
        this.pack();
        this.setTitle("PONG");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
class ControlWindow extends JPanel implements ActionListener,KeyListener{
    private Ball ball =new Ball(Consts.WIDTH/2,Consts.HEIGHT/2,30);
    private Timer timer = new Timer(30,this);
    private Player lp=new Player(0,Consts.HEIGHT/2);
    private Player rp=new Player(Consts.WIDTH-lp.width-lp.width/2,Consts.HEIGHT/2);
    private Font gameFont=new Font("Consolas",Font.PLAIN,30);
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval(ball.x, ball.y, ball.diameter,ball.diameter);

        g.setColor(Color.white);
        g.fillRect(lp.x, lp.y, lp.width,lp.height);
        g.fillRect(rp.x, rp.y, rp.width,rp.height);

        g.setColor(Color.CYAN);
        g.setFont(gameFont);
        g.drawString("Left: "+ lp.score,0,40);
        g.drawString("right: "+rp.score,Consts.WIDTH-150,40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.x+=ball.speedX;
        ball.y+= ball.speedY;

        Rectangle rectBall=new Rectangle(ball.x, ball.y, ball.diameter,ball.diameter);
        Rectangle rectlp=new Rectangle(lp.x, lp.y, lp.width,lp.height);
        Rectangle rectrp=new Rectangle(rp.x, rp.y, rp.width,rp.height);
        if(rectBall.intersects(rectrp)){
            ball.speedX=-ball.speedX;
        }
        if(rectBall.intersects(rectlp)){
            ball.speedX=-ball.speedX;
        }
        if(ball.y>=Consts.HEIGHT-ball.diameter*2||ball.y<=0){
            ball.speedY*=-1;
        }
        if(ball.x>=Consts.WIDTH-ball.diameter){
            lp.score++;
            ball.x=Consts.WIDTH/2;
            ball.y=Consts.HEIGHT/2;
        }
        if(ball.x<=0){
            rp.score++;
            ball.x=Consts.WIDTH/2;
            ball.y=Consts.HEIGHT/2;
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            lp.y-=lp.speedY;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            lp.y+=lp.speedY;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            rp.y-=rp.speedY;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            rp.y+=rp.speedY;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public ControlWindow(){
        timer.start();
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
    }
}
class Ball{
    public int x,y,diameter;
    public int speedX=8,speedY=8;

    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }
}
class Player{
    public int x,y;
    public int width=30,height=100;
    public int speedY=38;
    public int score=0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Consts {
    public static final int WIDTH=1000;
    public static final int HEIGHT=500;
}
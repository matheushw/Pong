package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
	

	private static final long serialVersionUID = 1L;
	//declaração das constantes do tamanho de tela
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	public static int enemy_putacao = 0; // O MATHEUS ESTEVE AQUI.
	public static int player_putacao = 0; // O MATHEUS ESTEVE AQUI.
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	//metodo construtor Game
	public Game(){
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		//estanciando o jogador quando o jogo comeca
		player = new Player(100, HEIGHT-5);
		enemy = new Enemy(100,0);
		ball = new Ball(100,HEIGHT/2 - 1);
	}
	
	public static void main(String[]args){
		Game game = new Game();
		JFrame frame = new JFrame("PONG!!!");
		//setando a janela para o jogador
		frame.setResizable(false);
		
		//parêmetro do JFrame para fechar o programa quando fechar a janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//para que ajanela apereça no jogo, por setup precisa definir como TRUE
		frame.add(game);
		frame.pack();
		
		//setando o centro da tela para aparecer a janela
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
		
	}
	
	//Estanciar o jogador e as logicas, mecanicas do jogo
	public void tick (){
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render(){
		//BufferStrategy é o que renderiza tudo do jogo
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.GREEN); // O MATHEUS ESTEVE AQUI.
		g.fillRect((WIDTH/2)-8, (HEIGHT/2)-15, 20, 20); // O MATHEUS ESTEVE AQUI.
		
		player.render(g);
		enemy.render(g);
		ball.render(g);


		g.setColor(Color.WHITE); // O MATHEUS ESTEVE AQUI.
		g.drawString(Integer.toString(enemy_putacao), 10, 50); // O MATHEUS ESTEVE AQUI.
		
		g.setColor(Color.WHITE); // O MATHEUS ESTEVE AQUI.
		g.drawString(Integer.toString(player_putacao), 10, 70); // O MATHEUS ESTEVE AQUI.

		g=bs.getDrawGraphics();
		g.drawImage(layer,0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
		
		//bs.shhow é o que ativa o jogadora aparecer na tela
		bs.show();
	}

	@Override
	public void run() {
		while(true){
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	//enquanto o teclado for pressionado
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT){
			player.right = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
		}
		
	}

	@Override
	//após o teclado deixar de ser pressionado
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT){
			player.right = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

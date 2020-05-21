package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	public double x, y;
	public int width, height;
	
	//parametros de dinamica e velocidade da bola
	public double dx,dy;
	public double speed = 1.7;
	
	//this. faz referencia a classe 
	public Ball(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		//forma mais fluida da bola rebater
		int angle = new Random().nextInt(120-45)+45+1;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		
		/*outra forma de fazer nextGaussian oferece valores entre 0 e 10
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
		 * */
		
	}
	
	//representa a lógica da bola
	public void tick(){
		
		//para fazer a bola rebater nas paredes
		if(x+(dx*speed) + width >= Game.WIDTH){
			dx*= -1;
		}else if(x+(dx*speed) < 0){
			dx*= -1;
		}

		if(x >= (Game.WIDTH/2)-8 && x<= (Game.WIDTH/2)+12 && y >= (Game.HEIGHT/2)-15 && y <= (Game.HEIGHT/2)+5 ){ // O MATHEUS ESTEVE AQUI.
			speed *= 1.1; // O MATHEUS ESTEVE AQUI.
		}
		
		//para fazer a bola rebater no inimigo
		if(y >= Game.HEIGHT){
			//Ponto do inimigo
			System.out.println("Ponto do INIMIGO!!");
			Game.enemy_putacao = Game.enemy_putacao + 1; // O MATHEUS ESTEVE AQUI.
			new Game();
			return;
			
		}else if(y<0){
			//Ponto do jogador
			System.out.println("Ponto do JOGADOR!!");
			Game.player_putacao = Game.player_putacao + 1; // O MATHEUS ESTEVE AQUI.
			new Game();
			return;
		}
		
		//tratando as colisões da bola com Player e Inimigo
		Rectangle bounds = new Rectangle ((int)(x+(dx*speed)), (int)(y+(dy*speed)), width,height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if (bounds.intersects(boundsPlayer)){
			int angle = new Random().nextInt(120-45)+45+1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0)
			   dy*= -1;
		}else if(bounds.intersects(boundsEnemy)){
			
			int angle = new Random().nextInt(120-45)+45+1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0)
			dy*= -1;
		}
		
		x+=dx*speed;
		y+=dy*speed;
		
	}
	
	
	//representa a parte grafica da bola
	public void render(Graphics g){
		
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int)y, width, height);
		
	}

}

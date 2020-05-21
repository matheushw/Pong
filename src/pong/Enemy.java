package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x, y;
	public int width, height;
	
	
	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	
	//representa a lógica do inimigo
	public void tick(){
		//logica para o inimigo perseguir a bola (-6) é um desconto para a maquina sempre vender, o ajuste esta no *0.4
		x+= (Game.ball.x - x - 6) * 0.07;
		
		
	}
	
	
	//representa a parte grafica do inimigo
	public void render(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y, width, height);
		
	}

}

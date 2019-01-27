package crtanje;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape{

	private int x;
	private int y;
	private Color edge;
	
	public Point(){
		
	}
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Point(int x, int y, boolean selected){
		this.x=x;
		setY(y);
		setSelected(selected);
	}
	
	public double distance(int x1,int y1){
		return Math.sqrt((this.x-x1)*(this.x-x1)+(this.y-y1)*(this.y-y1));
		
	}
	
	public boolean contains(int x,int y){
		return (this.distance(x, y)<=3);
	}
	
	public boolean equals(Object o){
		if (o instanceof Point)
		{
			Point p=(Point)o;
			return (this.x==p.getX() && this.y ==p.getY() );
		}
		else
			return false;
		}
	
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
		
	}
	
	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point start = new Point(0, 0);
			return (int) (this.distance(start.getX(), start.getY()) - ((Point) o).distance(start.getX(), start.getY()));
		}
		return 0;
	}
	
	public void draw(Graphics g){
		g.setColor(edge);
		g.drawLine(this.x-2, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		
		if (isSelected()){
			g.setColor(Color.RED);
			g.drawRect(this.x-3, this.y-3, 6, 6);
			g.setColor(Color.BLACK);
		}
	}
	
	
	public Color getEdge(){
		return this.edge;
	}
	
	public void setEdge(Color edge){
		this.edge=edge;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}
}

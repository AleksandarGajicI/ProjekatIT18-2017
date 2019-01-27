package crtanje;

import java.awt.Color;
import java.awt.Graphics;


public class Donut extends Circle{
private int innerRadius;
private Color innerEdge;
private Color innerFill;
	
	
	public Donut(){
		
	}
	
	public Donut(Point center,int radius, int innerRadius){
		super(radius, center);
		this.innerRadius=innerRadius;
		
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected){
		this(center,radius,innerRadius);
		super.setSelected(selected);
	}
	
	
	public double area(){
		return (super.area()-(innerRadius*innerRadius*Math.PI));
	}
	
	public boolean equals(Object o){
		if (o instanceof Donut)
		{
			Donut d=(Donut)o;
			return (this.getCenter().equals(d.getCenter()) && this.innerRadius==d.getInnerRadius() && this.getRadius()==d.getRadius());
		}
		else
			return false;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	
	
	public boolean contains(int x, int y){
		double d=this.getCenter().distance(x, y);
		return (d>this.innerRadius && super.contains(x, y));
		
	}
	
	public boolean contains(Point p){
		return (this.getCenter().distance(p.getX(), p.getY()) >this.innerRadius && super.contains(p.getX(),p.getY()));
	}
	
	
	public void draw(Graphics g){
		super.draw(g);
		g.setColor(innerFill);
		g.fillOval(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius, this.innerRadius*2, this.innerRadius*2);
		g.setColor(innerEdge);
		g.drawOval(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius, this.innerRadius*2, this.innerRadius*2);
		
		if(isSelected())
		{
			g.setColor(Color.RED);
			g.drawRect(this.getCenter().getX() + getInnerRadius() - 3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX() - getInnerRadius() - 3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getInnerRadius() -3, 6, 6);
			g.drawRect(this.getCenter().getX()  - 3, this.getCenter().getY() - getInnerRadius() -3, 6, 6);
			g.setColor(Color.BLACK);
		}
	}
	
	
	
	public Color getInnerEdge(){
		return this.innerEdge;
	}
	
	public void setInnerEdge(Color innerEdge){
		this.innerEdge=innerEdge;
	}
	
	public Color getInnerFill(){
		return this.innerFill;
	}
	
	public void setInnerFill(Color innerFill){
		this.innerFill=innerFill;
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return super.toString() + ", inner radius=" + innerRadius;
	}
}

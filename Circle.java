package crtanje;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape{

	private int radius;
	private Point center;
	private Color fill;
	private Color edge;
	
	public Circle(){
		
	}
	
	public Circle(int radius, Point center){
		this.radius=radius;
		this.center=center;
		
	}
	
	public Circle( Point center,int radius,boolean selected){
		this(radius,center);
		setSelected(selected);
	}
	
	public double area(){
		return Math.PI*radius*radius;
	}
	
	public boolean contains(int x,int y){
		return (this.getCenter().distance(x, y)<= this.radius);
		
	}
	
	public boolean contains(Point p) {
		if (p.distance(getCenter().getX(), getCenter().getY()) <= radius) {
			return true;
		} else {
			return false;
		}
	}
	
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
	
	
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
		
	}
	
	public boolean equals(Object o){
		if (o instanceof Circle)
		{
			Circle c=(Circle)o;
			return (this.center.equals(c.getCenter()) && this.radius==c.getRadius());
		}
		else
			return false;
	}
	
	public void draw(Graphics g){
		
		g.setColor(this.fill);
		g.fillOval(this.center.getX()-this.radius,this.center.getY()-this.radius, this.radius*2,this.radius*2);
		g.setColor(edge);
		g.drawOval(this.center.getX()-this.radius,this.center.getY()-this.radius, this.radius*2,this.radius*2);
		
		
		if (isSelected())
		{
			g.setColor(Color.RED);
			g.drawRect(this.center.getX()-3,this.center.getY()-3,6, 6);
			g.drawRect(this.center.getX()-this.radius-3,this.center.getY()-3,6, 6);
			g.drawRect(this.center.getX()-3,this.center.getY()-this.radius-3,6, 6);
			g.drawRect(this.center.getX()+this.radius-3,this.center.getY()-3,6, 6);
			g.drawRect(this.center.getX()-3,this.center.getY()+this.radius-3,6, 6);
			g.setColor(Color.BLACK);
		}
	}
	
	public Color getEdge(){
		return this.edge;
	}
	
	public void setEdge(Color edge){
		this.edge=edge;
	}
	
	public Color getFill(){
		return this.fill;
	}
	
	public void setFill(Color c){
		this.fill=c;
	}
	
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0");
		}
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public String toString(){
		return "Center = "+center+", Radius = "+radius;
	}
}

package crtanje;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends Shape{
	private Point upperLeftPoint;
	private int width;
	private int height;
	private Color edge;
	private Color fill;
	
	public Rectangle(){
		
	}
	
	public Rectangle(Point upperLeftPoint,int width,int height){
		this.upperLeftPoint=upperLeftPoint;
		this.width=width;
		this.height=height;
		
	}
	
	public Rectangle (Point upperLeftPoint,int width,int height,boolean selected){
		this(upperLeftPoint,width,height);
		setSelected(selected);
	}
	
	public double area(){
		return width*height;
	}
	
	public boolean equals(Object o){
		if (o instanceof Rectangle)
		{
			Rectangle r=(Rectangle)o;
			return (this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height==r.getHeight() && this.width==r.getHeight());
		}
		else
			return false;
	}
	
	public boolean contains(int x, int y) {
		if (this.getUpperLeftPoint().getX() <= x
				&& x <= (this.getUpperLeftPoint().getX() + width)
				&& this.getUpperLeftPoint().getY() <= y
				&& y <= (this.getUpperLeftPoint().getY() + height)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}
	
	public void moveBy(int byX, int byY) {
		upperLeftPoint.moveBy(byX, byY);
		
	}
	
	
	public boolean contains(Point p) {
		if (this.getUpperLeftPoint().getX() <= p.getX()
				&& p.getX() <= (this.getUpperLeftPoint().getX() + width)
				&& this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getY() <= (this.getUpperLeftPoint().getY() + height)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void draw(Graphics g){
		g.setColor(fill);
		g.fillRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(),this.width,this.height);
		g.setColor(edge);
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(),this.width,this.height);
		
		if(isSelected())
		{
			g.setColor(Color.RED);
			g.drawRect(this.upperLeftPoint.getX()-3, this.upperLeftPoint.getY()-3, 6,6);
			g.drawRect(this.upperLeftPoint.getX()-3, this.upperLeftPoint.getY()+this.height-3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX()+this.width-3, this.upperLeftPoint.getY()+this.height-3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX()+this.width-3, this.upperLeftPoint.getY()-3, 6, 6);
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
	
	public void setFill(Color fill){
		this.fill=fill;
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String toString(){
		return "Upper left point = "+upperLeftPoint+", width = "+width+", height = "+height;
	}
}

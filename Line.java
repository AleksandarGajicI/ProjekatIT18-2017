package crtanje;

import java.awt.Color;
import java.awt.Graphics;


public class Line extends Shape{

	private Point startPoint;
	private Point endPoint;
	private Color edge;

	
	public Line(){
		
	}

	public Line(Point startPoint,Point endPoint){
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	
	public Line (Point startPoint,Point endPoint,boolean selected){
		this.startPoint=startPoint;
		this.endPoint= endPoint;
		setSelected(selected);
	}
	
	public double Length(){
		return this.startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	
	public void moveBy(int byX, int byY) {
		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
		
	}
	
	
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.Length() - ((Line) o).Length());
		}
		return 0;
	}
	
	public boolean contains(int x, int y){
		return ((startPoint.distance(x,y)+endPoint.distance(x, y)-Length())<=0.05);
		
	}
	
	public boolean equals(Object o){
		if(o instanceof Line)
		{
			Line l=(Line)o;
			return (this.startPoint.equals(l.startPoint) && this.endPoint.equals(l.endPoint));
		}
		else
			return false;
	}
	
	public Point middleOfLine(){
		int middleX = (this.startPoint.getX()+this.endPoint.getX())/2;
		int middleY=(this.startPoint.getY()+this.endPoint.getY())/2;
		return new Point(middleX,middleY);
	}
	
	
	public void draw(Graphics g){
		
		g.setColor(edge);
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if (isSelected())
		{
			g.setColor(Color.RED);
			g.drawRect(this.startPoint.getX()-3, this.startPoint.getY()-3, 6, 6);
			g.drawRect(this.endPoint.getX()-3, this.endPoint.getY()-3, 6, 6);
			g.drawRect(this.middleOfLine().getX()-3, this.middleOfLine().getY()-3, 6, 6);
			g.setColor(Color.BLACK);
		}
	}
	
	public Color getEdge(){
		return this.edge;
	}
	
	public void setEdge(Color edge){
		this.edge=edge;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public String toString(){
		return startPoint+" ----> "+endPoint;
	}
}

package crtanje;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDrawing extends JPanel {

	private MainWindow frame;
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	private Shape sh;
	private Point startPoint;
	private Shape testShape;
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Shape getTestShape() {
		return testShape;
	}

	public void setTestShape(Shape testShape) {
		this.testShape = testShape;
	}
	
	/**
	 * Create the panel.
	 */
	public PnlDrawing() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisClicked(e);
			}
		});
	}
	
	public PnlDrawing(MainWindow main){
		frame=main;
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				thisClicked(e);
			}
		});
	}
	
	
	
	
		protected void thisClicked(MouseEvent e)
		{
			if(frame.getTglbtnSelektuj())
			{
				testShape=null;
				Point p=new Point(e.getX(),e.getY());
				Iterator<Shape> it=shapes.iterator();
				while(it.hasNext())
				{
					Shape geo=it.next();
					geo.setSelected(false);
					if((geo.contains(p.getX(),p.getY())) && geo.isSelected()==false)
					{
						testShape=geo;
						
					}
					
				}
			}
			if(frame.getTglbtnTacka())
			{
				Point p = new Point(e.getX(),e.getY());
				DlgPoint dlgT= new DlgPoint();
				dlgT.setTxtX(Integer.toString(e.getX()));
				dlgT.setTxtY(Integer.toString(e.getY()));
				dlgT.setVisible(true);
				if(dlgT.isOk())
				{
					int x = Integer.parseInt(dlgT.getTxtX());
					int y = Integer.parseInt(dlgT.getTxtY());
					if(dlgT.getEdge()==null)
						dlgT.setEdge(Color.BLACK);
					p.setX(x);
					p.setY(y);
					p.setEdge(dlgT.getEdge());
					shapes.add(p);
					
					
				}
			}
			if(frame.getTglbtnLinija())
			{
				if(startPoint==null)
				{
					startPoint= new Point(e.getX(),e.getY());
				}
				else
				{
					DlgLine dlgL = new DlgLine();
					dlgL.setTxtStartX(Integer.toString(startPoint.getX()));
					dlgL.setTxtStartY(Integer.toString(startPoint.getY()));
					dlgL.setTxtEndX(Integer.toString(e.getX()));
					dlgL.setTxtEndY(Integer.toString(e.getY()));
					dlgL.setVisible(true);
					if(dlgL.isOk())
					{
						int startX = Integer.parseInt(dlgL.getTxtStartX());
						int startY = Integer.parseInt(dlgL.getTxtStartY());
						int endX = Integer.parseInt(dlgL.getTxtEndX());
						int endY = Integer.parseInt(dlgL.getTxtEndY());
						Line l = new Line(new Point(startX,startY),new Point (endX,endY));
						
						if(dlgL.getEdge()==null)
							dlgL.setEdge(Color.BLACK);
						
						l.setEdge(dlgL.getEdge());
						shapes.add(l);
						startPoint=null;
					}
				}
			}
			if(frame.getTglbtnKrug())
			{
				DlgCircle dlgC = new DlgCircle();
				dlgC.setTxtX(Integer.toString(e.getX()));
				dlgC.setTxtY(Integer.toString(e.getY()));
				dlgC.setVisible(true);
				if(dlgC.isOk())
				{
					int x = Integer.parseInt(dlgC.getTxtX());
					int y = Integer.parseInt(dlgC.getTxtY());
					int radius = Integer.parseInt(dlgC.getTxtRadius());
					
					if(dlgC.getFill()==null)
						dlgC.setFill(Color.WHITE);
					if(dlgC.getEdge()==null)
						dlgC.setEdge(Color.BLACK);
					
					Circle c = new Circle(radius,new Point(x,y));
					c.setFill(dlgC.getFill());
					c.setEdge(dlgC.getEdge());
					shapes.add(c);
					
				}
			}
			if(frame.getTglbtnKrugSaRupom())
			{
				DlgDonut dlgD= new DlgDonut();
				dlgD.setTxtX(Integer.toString(e.getX()));
				dlgD.setTxtY(Integer.toString(e.getY()));
				dlgD.setVisible(true);
				if(dlgD.isOk())
				{
					int x = Integer.parseInt(dlgD.getTxtX());
					int y = Integer.parseInt(dlgD.getTxtY());
					int outerRadius = Integer.parseInt(dlgD.getTxtOuterRadius());
					int innerRadius = Integer.parseInt(dlgD.getTxtInnerRadius());
					shapes.add(new Donut(new Point(x,y),outerRadius,innerRadius));
				}
			}
			if(frame.getTglbtnPravougaonik())
			{
				DlgRectangle dlgR = new DlgRectangle();
				dlgR.setTxtX(Integer.toString(e.getX()));
				dlgR.setTxtY(Integer.toString(e.getY()));
				dlgR.setVisible(true);
				if(dlgR.isOk())
				{
					if(dlgR.getEdge()==null)
						dlgR.setEdge(Color.BLACK);
					if(dlgR.getFill()==null)
						dlgR.setFill(Color.WHITE);
					
					int x = Integer.parseInt(dlgR.getTxtX());
					int y = Integer.parseInt(dlgR.getTxtY());
					int width = Integer.parseInt(dlgR.getTxtWidth());
					int heigth = Integer.parseInt(dlgR.getTxtHeigth());
					Rectangle r = new Rectangle(new Point(x,y),width, heigth);
					r.setEdge(dlgR.getEdge());
					r.setFill(dlgR.getFill());
					shapes.add(r);
				}
				
			}
			else if(!(testShape==null))
			{
				testShape.setSelected(true);
			}
			if(!shapes.isEmpty())
				repaint();
			
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			Iterator it=shapes.iterator();
			while(it.hasNext())
				((Shape) it.next()).draw(g);
	        }
		
		
	}



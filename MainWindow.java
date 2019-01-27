package crtanje;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JToggleButton tglbtnTacka = new JToggleButton("ta\u010Dka");
	JToggleButton tglbtnLinija = new JToggleButton("linija");
	JToggleButton tglbtnKrug = new JToggleButton("krug");
	JToggleButton tglbtnKrugSaRupom = new JToggleButton("Krug sa rupom");
	JToggleButton tglbtnModifikuj = new JToggleButton("Modifikuj");
	JToggleButton tglbtnSelect = new JToggleButton("Selektuj");
	JToggleButton tglbtnObrisi = new JToggleButton("Obri\u0161i");
	JToggleButton tglbtnPravougaonik = new JToggleButton("Pravougaonik");

	public boolean getTglbtnTacka() {
		return tglbtnTacka.isSelected();
	}

	public boolean getTglbtnLinija() {
		return tglbtnLinija.isSelected();
	}

	public boolean getTglbtnPravougaonik() {
		return tglbtnPravougaonik.isSelected();
	}

	public boolean getTglbtnKrug() {
		return tglbtnKrug.isSelected();
	}

	public boolean getTglbtnKrugSaRupom() {
		return tglbtnKrugSaRupom.isSelected();
	}

	public boolean getTglbtnSelektuj() {
		return tglbtnSelect.isSelected();
	}

	public boolean getTglbtnModifikuj() {
		return tglbtnModifikuj.isSelected();
	}

	public boolean getTglbtnObrisi() {
		return tglbtnObrisi.isSelected();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setExtendedState(MAXIMIZED_BOTH);

		PnlDrawing panel = new PnlDrawing(this);
		panel.setBounds(10, 40, 660, 410);
		contentPane.add(panel);

		JToolBar toolBar = new JToolBar();

		buttonGroup.add(tglbtnTacka);
		toolBar.add(tglbtnTacka);

		buttonGroup.add(tglbtnLinija);
		toolBar.add(tglbtnLinija);
		tglbtnLinija.setHorizontalAlignment(SwingConstants.LEFT);

		buttonGroup.add(tglbtnKrug);
		toolBar.add(tglbtnKrug);

		buttonGroup.add(tglbtnKrugSaRupom);
		toolBar.add(tglbtnKrugSaRupom);

		buttonGroup.add(tglbtnPravougaonik);
		toolBar.add(tglbtnPravougaonik);

		buttonGroup.add(tglbtnSelect);
		toolBar.add(tglbtnSelect);

		buttonGroup.add(tglbtnModifikuj);
		toolBar.add(tglbtnModifikuj);

		buttonGroup.add(tglbtnObrisi);
		toolBar.add(tglbtnObrisi);

		tglbtnModifikuj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!(panel.getTestShape() == null)) {
					Shape pomObj = panel.getTestShape();
					ArrayList<Shape> list = panel.getShapes();
					int index = list.indexOf(pomObj);
					if (pomObj instanceof Point) {
						DlgPoint dlgP = new DlgPoint();
						dlgP.setTxtX(Integer.toString(((Point) pomObj).getX()));
						dlgP.setTxtY(Integer.toString(((Point) pomObj).getY()));
						dlgP.setEdge(((Point) pomObj).getEdge());
						dlgP.setVisible(true);
						if (dlgP.isOk()) {
							((Point) pomObj).setX(Integer.parseInt(dlgP.getTxtX()));
							((Point) pomObj).setY(Integer.parseInt(dlgP.getTxtY()));
							((Point) pomObj).setEdge(dlgP.getEdge());
							pomObj.setSelected(true);
							panel.setTestShape(pomObj);
							list.add(index, pomObj);
							panel.setShapes(list);
							panel.repaint();
						} else {
							
						}
					} else if (pomObj instanceof Line) {
						DlgLine dlgL = new DlgLine();
						dlgL.setTxtStartX(Integer.toString(((Line) pomObj).getStartPoint().getX()));
						dlgL.setTxtStartY(Integer.toString(((Line) pomObj).getStartPoint().getY()));
						dlgL.setTxtEndX(Integer.toString(((Line) pomObj).getEndPoint().getX()));
						dlgL.setTxtEndY(Integer.toString(((Line) pomObj).getEndPoint().getY()));
						dlgL.setEdge(((Line) pomObj).getEdge());
						dlgL.setVisible(true);
						if (dlgL.isOk()) {
							((Line) pomObj).setStartPoint(new Point(Integer.parseInt(dlgL.getTxtStartX()),
									Integer.parseInt(dlgL.getTxtStartY())));
							((Line) pomObj).setEndPoint(new Point(Integer.parseInt(dlgL.getTxtEndX()),
									Integer.parseInt(dlgL.getTxtEndY())));
							((Line) pomObj).setEdge(dlgL.getEdge());
							list.add(index, pomObj);
							panel.setShapes(list);
							pomObj.setSelected(true);
							panel.setTestShape(pomObj);
							panel.repaint();
						}
					} else if (pomObj instanceof Donut) {
						DlgDonut dlgD = new DlgDonut();
						dlgD.setTxtX(Integer.toString(((Donut) pomObj).getCenter().getX()));
						dlgD.setTxtY(Integer.toString(((Donut) pomObj).getCenter().getY()));
						dlgD.setTxtOuterRadius(Integer.toString(((Donut) pomObj).getRadius()));
						dlgD.setTxtInnerRadius(Integer.toString(((Donut) pomObj).getInnerRadius()));
						dlgD.setVisible(true);
						if (dlgD.isOk()) {
							((Donut) pomObj).setCenter(
									new Point(Integer.parseInt(dlgD.getTxtX()), Integer.parseInt(dlgD.getTxtY())));
							((Donut) pomObj).setRadius(Integer.parseInt(dlgD.getTxtOuterRadius()));
							((Donut) pomObj).setInnerRadius(Integer.parseInt(dlgD.getTxtInnerRadius()));
							list.add(index, pomObj);
							panel.setShapes(list);
							pomObj.setSelected(true);
							panel.setTestShape(pomObj);
							panel.repaint();

						}
					} else if (pomObj instanceof Circle) {
						DlgCircle dlgC = new DlgCircle();
						dlgC.setTxtX(Integer.toString(((Circle) pomObj).getCenter().getX()));
						dlgC.setTxtY(Integer.toString(((Circle) pomObj).getCenter().getY()));
						dlgC.setTxtRadius(Integer.toString(((Circle) pomObj).getRadius()));
						dlgC.setFill(((Circle) pomObj).getFill());
						dlgC.setEdge(((Circle) pomObj).getEdge());
						dlgC.setVisible(true);
						if (dlgC.isOk()) {
							((Circle) pomObj).setCenter(
									new Point(Integer.parseInt(dlgC.getTxtX()), Integer.parseInt(dlgC.getTxtY())));
							((Circle) pomObj).setRadius(Integer.parseInt(dlgC.getTxtRadius()));
							((Circle) pomObj).setFill(dlgC.getFill());
							((Circle) pomObj).setEdge(dlgC.getEdge());
							list.add(index, pomObj);
							panel.setShapes(list);
							pomObj.setSelected(true);
							panel.setTestShape(pomObj);
							panel.repaint();
						}
					}

					else if (pomObj instanceof Rectangle) {
						DlgRectangle dlgR = new DlgRectangle();
						dlgR.setTxtX(Integer.toString(((Rectangle) pomObj).getUpperLeftPoint().getX()));
						dlgR.setTxtY(Integer.toString(((Rectangle) pomObj).getUpperLeftPoint().getY()));
						dlgR.setTxtWidth(Integer.toString(((Rectangle) pomObj).getWidth()));
						dlgR.setTxtHeigth(Integer.toString(((Rectangle) pomObj).getHeight()));
						dlgR.setEdge(((Rectangle) pomObj).getEdge());
						dlgR.setFill(((Rectangle) pomObj).getFill());
						dlgR.setVisible(true);
						if (dlgR.isOk()) {
							((Rectangle) pomObj).setUpperLeftPoint(
									new Point(Integer.parseInt(dlgR.getTxtX()), Integer.parseInt(dlgR.getTxtY())));
							((Rectangle) pomObj).setWidth(Integer.parseInt(dlgR.getTxtWidth()));
							((Rectangle) pomObj).setHeight(Integer.parseInt(dlgR.getTxtHeigth()));
							((Rectangle) pomObj).setEdge(dlgR.getEdge());
							((Rectangle) pomObj).setFill(dlgR.getFill());
							list.add(index, pomObj);
							panel.setShapes(list);
							pomObj.setSelected(true);
							panel.setTestShape(pomObj);
							panel.repaint();
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Morate prvo selektovati oblik!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		tglbtnObrisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (panel.getTestShape() != null) {
					Shape pomObj = panel.getTestShape();
					ArrayList<Shape> list = panel.getShapes();
					int index = list.indexOf(pomObj);
					if (JOptionPane.showConfirmDialog(contentPane, "Da li ste sigurni?", "Upozorenje",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						list.remove(index);
						
						panel.setTestShape(null);
						panel.setShapes(list);
					}
					panel.repaint();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Morate selektovati oblik!", "Greška",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(toolBar,
				GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(412, Short.MAX_VALUE)));

		contentPane.setLayout(gl_contentPane);

	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 *
 * @author nivit
 */
public class PreviewFrame extends JFrame implements MouseListener, MouseMotionListener{
    JPanel p;
    JLabel preFrame;
    JButton CropFrame;
    BufferedImage buffImg;
    ImageIcon imgIcon;
    BufferedImage OribuffImg;
    ImageIcon OriimgIcon;
    BufferedImage preImg;
    Point startDrag,endDrag;
    File file;
    int w,h=0;
    Shape r;
    int x1,y1,x2,y2=0;
    int drag_status=0,c1,c2,c3,c4;
    
    public PreviewFrame(BufferedImage buffImg,ImageIcon imgIcon){
        super("First Frame Preview");
        p = new JPanel();
        preFrame = new JLabel("",SwingConstants.CENTER);
        
        this.buffImg=buffImg;
        this.imgIcon=imgIcon;
        OribuffImg=buffImg;
        OriimgIcon=imgIcon;
        //CropFrame button
        CropFrame = new JButton("Crop Frame");
        CropFrame.addActionListener(new ButtonListener());
        r=makeRectangle(0,0,0,0);
        p.add(CropFrame);
        preFrame.setIcon(imgIcon);
        p.add(preFrame);
        addMouseListener(this);
	addMouseMotionListener(this); 
        add(p);
        this.setResizable(false);
        
    }
  
    @Override
    public void mouseClicked(MouseEvent e) {
        preFrame.setIcon(OriimgIcon);
    }

    @Override
    public void mousePressed(MouseEvent e) {
          startDrag = new Point(e.getX(), e.getY());
          endDrag = startDrag;
          
          repaint();
          
    }

    @Override
    public void mouseReleased(MouseEvent e) {
          r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
          x1=startDrag.x;
          y1=startDrag.y-60;
          x2=e.getX();
          y2=e.getY()-60;
          w=Math.abs(x2-x1);
          h=Math.abs(y2-y1);
          startDrag = null;
          endDrag = null;  
          repaint();
          preImg=buffImg.getSubimage(x1, y1,w,h);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endDrag = new Point(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        g2.setPaint(Color.BLUE);
        g2.draw(r);
        if (startDrag != null && endDrag != null) {
        g2.setPaint(Color.red);
        Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
        g2.draw(r);
        }
      
       
    }
    
    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
      return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e1) {
        if(e1.getSource()==CropFrame){
            CheckPreview chkp = new CheckPreview(preImg,x1,y1,w,h);
            chkp.setSize(chkp.getFrameWidth()+100,chkp.getFrameHeight()+100);
            chkp.setVisible(true);
            
        }
        }
    
}
 
    
}

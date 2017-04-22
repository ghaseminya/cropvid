/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author nivit
 */
public class CheckPreview extends JFrame{
    private JPanel p;
    JLabel a;
    JButton CropVideo;
    BufferedImage buff0;
    String path;
    File file;
    int x1,y1,w,h=0;
    
    
    public CheckPreview(BufferedImage buff,int x1,int y1,int w,int h){
        super("");
        p=new JPanel();
        a=new JLabel("",SwingConstants.CENTER);
        CropVideo=new JButton("Crop Video");
        buff0=buff;
        path=null;
        
        this.x1=x1;
        this.y1=y1;
        this.w=w;
        this.h=h;
        a.setIcon(new ImageIcon(buff));
        CropVideo.addActionListener(new ButtonListener());
        p.add(CropVideo);
        p.add(a);
        
        add(p);
    }
    public int getFrameWidth(){
        return buff0.getWidth();
    }
    
    public int getFrameHeight(){
        return buff0.getHeight();
    }
    
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==CropVideo){
               JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Output Directory");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                       path=chooser.getSelectedFile().getAbsolutePath();
                       CapturingProcess cpp = new CapturingProcess(file,x1,y1,w,h,path);
                       cpp.setSize(w+50,h+50);
                       cpp.setVisible(true);
                } 
                else {
                       JOptionPane.showMessageDialog(null, "No selection.");
                }
            }
        }
        
    }
}

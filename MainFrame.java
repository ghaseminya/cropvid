/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import javax.swing.JFrame;
import java.io.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author nivit
 */
public class MainFrame extends JFrame{
    private JPanel p;
    public File file;
    JButton Choose;
    JButton Preview;
    JLabel Status;
    JLabel howto;
    Process pcss;
    
    public MainFrame(){
        super("--Video Extraction--");
        p = new JPanel();
        Choose = new JButton("Select File");
        Preview = new JButton("Preview");
        Status = new JLabel("status: File is not ready.",SwingConstants.CENTER);
        howto = new JLabel("<html><p>1.Choose your video file(.mp4 format only)<br>2.Click preview then select the part you want to crop by dragging your mouse<br>3.Click crop video</p></html>",SwingConstants.CENTER);
        Choose.addActionListener(new ButtonListener());
        Preview.addActionListener(new ButtonListener());
        p.add(Choose);
        p.add(Status);
        p.add(howto);
        add(p);
    }
    
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Choose){
               FileFilter filter = new FileNameExtensionFilter(".mp4 file only","mp4"); 
               JFileChooser fileChooser = new JFileChooser ( );
               fileChooser.setFileFilter(filter);
               fileChooser.addChoosableFileFilter(filter);
               int returnVal = fileChooser.showDialog ( null, "OPEN" );
               fileChooser.setDialogTitle("Select File");
               fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               if(returnVal == JFileChooser.CANCEL_OPTION){
                   file=null;
                   JOptionPane.showMessageDialog(null, "Pls select mp4 file.");
               }
               else{
                   file=fileChooser.getSelectedFile();
                   Status.setText("status: File is ready.");
                   p.add(Preview);
               }
            }
            
            if(e.getSource()==Preview){
                pcss = new Process(file);
                PreviewFrame prevF = new PreviewFrame(pcss.getBuffImg(),pcss.getImgIcon());
                prevF.setSize(pcss.getVidWidth(),pcss.getVidHeight()+100);
                prevF.setVisible(true);
            }
        }
        
    
    }
    
}

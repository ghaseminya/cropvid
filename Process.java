/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

/**
 *
 * @author nivit
 */
public class Process {
    private File file;
    Frame frame;
    FFmpegFrameGrabber grabber;
    BufferedImage img;
    Java2DFrameConverter op = new Java2DFrameConverter();
    
    public Process(File inpfile){
        this.file = inpfile;
        grabber = new FFmpegFrameGrabber(file);
        try{
            grabber.start();
            
        }
        catch(FrameGrabber.Exception e){
            System.out.println("Error on start");
        }
    }
    
    public ImageIcon getImgIcon(){
         try{
           
            grabber.setFrameNumber(1);
            frame = grabber.grabFrame();
            img = op.convert(frame);
            
        }
        catch(FrameGrabber.Exception e){
            System.out.println("Error on start");
        }
        return new ImageIcon(img);
    }
    
    public BufferedImage getBuffImg(){
         try{
           
            grabber.setFrameNumber(1);
            frame = grabber.grabFrame();
            img = op.convert(frame);
            
        }
        catch(FrameGrabber.Exception e){
            System.out.println("Error on start");
        }
        return img;
    }
    
    
    
    public int getVidWidth(){
        return img.getWidth();
    }
    
    public int getVidHeight(){
        return img.getHeight();
    }
    
}

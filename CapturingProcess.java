/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.Java2DFrameConverter;

/**
 *
 * @author nivit
 */
public class CapturingProcess extends JFrame{
    private JPanel p;
    JLabel realtimeFrame;
    
    private File file;
    Frame frame;
    FFmpegFrameGrabber grabber;
    FFmpegFrameRecorder recorder;
    BufferedImage img;
    BufferedImage imgr;
    String path;
    Java2DFrameConverter op = new Java2DFrameConverter();
    int x1,y1,w,h;
    
    public CapturingProcess(File file,int x1,int y1,int w,int h,String path){
        super("");
        p=new JPanel();
        realtimeFrame=new JLabel("");
        this.file=file;
        this.x1=x1;
        this.y1=y1;
        this.w=w;
        this.h=h;
        this.path=path;
        
        p.add(realtimeFrame);
        add(p);
        this.start();
    }
    
    public void start(){
        
        grabber = new FFmpegFrameGrabber(file);
        recorder = new FFmpegFrameRecorder(path+"/test.mp4",w,h,0);
        try{
            grabber.start();
            recorder.setVideoCodec(13);
            recorder.setFormat("mp4");
            recorder.setFrameRate(grabber.getFrameRate());
            recorder.start();
        }
        catch(FrameGrabber.Exception e){
            System.out.println("Error on start at grabber");
            e.printStackTrace();
        } catch (FrameRecorder.Exception ex) {
            System.out.println("Error on start at recorder");
            ex.printStackTrace();
        }
        try{
           for(int i=1; i<=grabber.getLengthInFrames(); i++){
            grabber.setFrameNumber(i);
            frame = grabber.grabFrame();
            
            img = op.convert(frame);
            imgr = img.getSubimage(x1, y1, w, h);
            realtimeFrame.setIcon(new ImageIcon(imgr));
            p.add(realtimeFrame);
            frame = op.convert(imgr);
            recorder.record(frame);
           }
           recorder.stop();
           grabber.stop();
           super.setVisible(false);
        }
        catch(FrameGrabber.Exception e){
            System.out.println("Error on start");
            e.printStackTrace();
        } catch (FrameRecorder.Exception ex) {
            System.out.println("Error on record frame");
            ex.printStackTrace();
        }
        
    }
    
    
}

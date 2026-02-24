package kr.go.mhc.common.util;

import javax.imageio.*;

import java.awt.image.*;
import java.awt.*;
import java.io.*;
class DrawImage
{
	private GradientPaint gp;
	private int width,height;
	
	    public DrawImage(int width,int height,GradientPaint gp)
	    {
	        this.width=width;
	        this.height=height;
	        this.gp=gp;
	        drawImage();
	    }
	    
	    private void drawImage()
	    {
	        // Create a BufferedImage object specifying width,height of the image
	        // and also the type (here alpha-red-green-blue)
	        BufferedImage bim=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
	       
	        // Create Graphics object
	        // Graphics2D is a sub class of the Graphics class,
	        // so this statement is correct
	        Graphics2D g2=bim.createGraphics();
	       
	        // Set the paint
	        g2.setPaint(gp);
	       
	        // You can also use rendering hints
	        // to smoothen the edges or the rounded rectangle
	        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        qualityHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	        g2.setRenderingHints(qualityHints);
	       
	        // Fill the round rectangle from (0,0) and the paint
	        // should spread over the entire width and height
	        // and the corner radius width,height is 1/4th of
	        // the corresponding dimension
	        g2.fillRoundRect(0,0,width,height,width/4,height/4);
	       
	        // Dispose the Graphics2D object
	        g2.dispose();
	       
	        // Throws IOException
	        try
	        {
	            // Write the BufferedImage object to a file
	            // The type of the image here is made PNG for
	            // transparent edges as the image contains a 
	            // rounded rectangle
	            ImageIO.write(bim,"PNG",new File("output_image.png"));
	            
	            
	            
	            BufferedImage img = null;
	            //img = ImageIO.read(new File("output_image.png"));
	            img = ImageIO.read(new File("graph.png"));
	            
			//	File fileNode = new File("graph.png");
	            File fileNode = new File("output_image.png");
				
				Image imageNode = ImageIO.read(fileNode);	            
	            
				//Image image = ImageIO.read(file);
				//BufferedImage cpimg = bufferImage(image,BufferedImage.TYPE_INT_RGB);
				Graphics g = img.createGraphics();
				//g.setColor(background);
				//g.fillRect(0,0,width,height);
				Font fnt=new Font("Impact",1,14);
				Color fntC = new Color(0,100,0);
				g.setColor(fntC);
				g.setFont(fnt);
				g.drawString("now Status",153,160);
				g.drawLine(96, 127, 122, 127);
				g.drawImage(imageNode, 90, 190,null);
				
				g.drawImage(imageNode, 180, 390,null);
				
				File f1 = new File("overWrite_image.png");
				ImageIO.write(img, "png", f1);	            
	            
	        }catch(Exception e){
	        	System.out.println(e.getMessage());
	        }
	    }
	    
	    public static void main(String args[])
	    {
	        // Invoke the constructor sending the width,height
	        // and the GradientPaint object
	        // The GradientPaint object defines a gradient
	        // consisting of two colors with their starting and ending points
	        new DrawImage(30,30,new GradientPaint(0,0,Color.BLUE,0,30,Color.CYAN));
	    }
}
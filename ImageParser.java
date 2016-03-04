package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageParser {
	private float[][] grayScaleArray;
	int width;
	int height;
	public BufferedImage image;
	
	public ImageParser(String imgPath){
		try{
			
			File input = new File(imgPath);
			image = ImageIO.read(input);
			height = image.getHeight();
			width = image.getWidth();
			grayScaleArray = get2DArray(image);
			
			
		}
		catch (Exception e) {}
		
		
		
	}
	public float[][] get2DArray(BufferedImage image)
	{
		int mwidth = image.getWidth();
		int mheight = image.getHeight();
		float[][] Array = new float[height][width];			
		for(int i=0; i<mheight; i++){
			for(int j=0; j<mwidth; j++){
				
				Color color = new Color(image.getRGB(j, i));
				float red = (float)(color.getRed() * 0.299)/255;
				float blue = (float)(color.getBlue() * 0.114)/255;
				float green = (float)(color.getGreen() * 0.587)/255;					
				Array[i][j] = red + blue + green;
									
			}
		}
		return Array;
	}
	public void makeWhiteBlackImg(){
		float average = 0;		
		for(float[] row : grayScaleArray){
			for(float pixValue : row){
				average += pixValue;
			}
		}
		average = average/(width*height);
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				if(grayScaleArray[i][j] <= 0.5){
					grayScaleArray[i][j] = (float) 0.2;
				}
				else{
					grayScaleArray[i][j] = 1;
				}
				
			}
		}
		setImage();
		
	}
	public float[] getGrayScaleArray()
	{
		List<Float> tempArr = new ArrayList<Float>();
		for(int i=0; i < grayScaleArray.length; i++){
			for(int j = 0; j < grayScaleArray[i].length; j++){
				float x = grayScaleArray[i][j];				
				tempArr.add(2*x - 1);
			}
		}
		
		
		float[] grayList = new float[tempArr.size()];
		for(int i=0; i < grayList.length; i++){
			grayList[i] = tempArr.get(i);
		}
		//System.out.println(Arrays.toString(grayList));
		
		return grayList;
	}
	public void createGrayScaleImage(String path)
	{
		BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				int value = (int) (grayScaleArray[i][j]*255);
				Color color = new Color(value, value, value);
				img.setRGB(j, i, color.getRGB());
			}
		}
		try{
			File file = new File(path);
			ImageIO.write(img, "jpg", file);
			System.out.println("Done");
		}
		catch (Exception e){
			
		}
		
	}
	public void createNegative()
	{
		for (int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				grayScaleArray[i][j] = 1 - grayScaleArray[i][j];
			}
		}
		setImage();
		
	}
	private void setImage()
	{
		for(int i=0; i<grayScaleArray.length; i++){
			for(int j=0; j<grayScaleArray[i].length; j++){
				int value = (int) (grayScaleArray[i][j]*255);
				Color color = new Color(value, value, value);
				image.setRGB(j, i, color.getRGB());
				
			}
		}
	}
	public void setArray(float[][] array)
	{
		grayScaleArray = array;
	}
	
	public void resizeImage(int w, int h) throws IOException
	{
		  BufferedImage tThumbImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB );
		  Graphics2D tGraphics2D = tThumbImage.createGraphics(); //create a graphics object to paint to
		  tGraphics2D.setBackground( Color.WHITE );
		  tGraphics2D.setPaint( Color.WHITE );
		  tGraphics2D.fillRect( 0, 0, w, h );
		  tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		  //tGraphics2D.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		  //tGraphics2D.setRenderingHint( RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY );
		  //tGraphics2D.setRenderingHint( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
		  //tGraphics2D.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
		  tGraphics2D.drawImage(image, 0, 0, w, h, null ); //draw the image scaled
		  tGraphics2D.dispose();
		  
		  //set new class instance arguments		  
		  setArray(get2DArray(tThumbImage));
		  setImage();
		  width = w;
		  height = h;
		  
		  
	}
	
	public static void main(String args[]) throws Exception
	{
		
		
	}
	

}

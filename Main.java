package test;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Main
{
	public static BufferedReader input_data;
	
	
    public static void main(String[] args) throws IOException {
    	
    	Main main = new Main();
    	
    	ImageParser grayScale = new ImageParser("src/test/img3.jpg");
    	//grayScale.resizeImage(20, 20);
    	//grayScale.createNegative();
    	grayScale.makeWhiteBlackImg();
    	//grayScale.resizeImage(20, 20);
    	
    	
    	
    	grayScale.createGrayScaleImage("src/test/gray.jpg");
    	
    	
		
		
		float[] x = grayScale.getGrayScaleArray();
		//System.out.println(Arrays.toString(x));
		
		//theta creation
		float[][] all_theta = new float[10][400];
		input_data = new BufferedReader(new FileReader("src/test/matrix.txt"));
    	for(int i=0; i<10; i++)
    	{    
    		float[] theta = main.convertToInt(input_data.readLine());
    		theta = Arrays.copyOf(theta, theta.length - 1);
    		all_theta[i] = theta;
    	}
    	for(float[] theta : all_theta){
    		float result = main.vectorMultiplication(theta, x);
    		System.out.println(Float.toString(result));
    	}
    };
    
    public void predict(float[] example, float[][] all_theta)
    {
    	for(int i=0; i<10; i++){
    		float tmp = (float) vectorMultiplication(all_theta[i], example);
        	System.out.println(Float.toString(tmp));
    		
    	}
    }
    
    public float[] convertToInt(String input)
    {
    	String[] splited = input.split(",");
    	float[] floatArray = new float[splited.length];
    	for(int i = 0; i < splited.length; i++){
    		float tmp = Float.parseFloat(splited[i]);    		   		
    		floatArray[i] = tmp;
    	}
    	
    	return floatArray;
    }
    
    public float vectorMultiplication(float[] array1, float[] array2)
    {
    	float result =0;
    	for(int i=0; i<array1.length; i++){
    		result = result + array1[i]*array2[i];
    	}
    	return result;
    	
    }   
   
    
    public void drawImg(Float[] data){
    	
    }

}
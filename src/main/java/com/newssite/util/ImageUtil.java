package com.newssite.util;


import org.apache.commons.codec.binary.Base64;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 *	Utility for manipulating images.  
 *  Contains methods for saving,loading,
 *  converting and compressing images 
 *
 */
public class ImageUtil {

	
	private final String defaultDestPath;
	private final long  maxUncompressedSize;
	private final boolean convertToJpg;
	
	public ImageUtil() {
		super();
		this.defaultDestPath = null;
		this.convertToJpg = false;
		this.maxUncompressedSize = 0;
	}

	public ImageUtil(String defaultDestPath,boolean convertToJpg,long maxUncompressedSize) {
		super();
		this.defaultDestPath = defaultDestPath;
		this.convertToJpg = convertToJpg;
		this.maxUncompressedSize = maxUncompressedSize;
	}
	
	/**
	 * Uses Apache's common.codec library
	 * to convert a file on the file system to a b64 string
	 * @param fileName the name of the file to load.The actual file location is determined by
	 * the defaultDestPath set in the constructor.
	 * @return a base 64 representation of an image
	 */
	public String encodeToB64String(String fileName) throws IOException{
		return Base64.encodeBase64String(getByteArray(fileName));
	}
	
	/**
	 * Loads an image into a from a preset directory
	 * @param fileName the file to load
	 * @return
	 */
	public File loadImage(String path) throws IOException{
        File f = new File(defaultDestPath,path);
        if(!f.exists()){
            throw new IOException("Missing image file");
        }
        return f;
    }
	/**
	 *  Saves an image to a preset folder.
	 *  Converts the image to a jpg if the convertToJpg flag 
	 *  was set in the constructor.
	 *  The final image will be compressed if it is bigger 
	 *  than maxUncompressedSize and
	 *  the javax.imageIO.ImageWriter implementation for the imageType
	 *  can write compressed images(e.g. yes for jpg no for png).
	 *  
	 * @param input The image file to be saved
	 * @param contentType the content type of the file 
	 * @param fileName the file name the file will be saved with
	 */
	public String saveImage(File input,String contentType,String fileName) {
		OutputStream out = null;
		ImageOutputStream imageOut = null;
		ImageWriter writer = null;
		BufferedImage image = null;
		File dest = null;			

		try{
			//Do we need conversion
			if(convertToJpg && contentType != "image/jpeg"){
				image = convertToJpg(ImageIO.read(input));
				fileName = fileName.substring(0, fileName.lastIndexOf('.'))
						           .concat(".jpg");
			}else{
				image = ImageIO.read(input);
			}
			
			dest = new File(defaultDestPath,fileName);
			//Set up writer
			out = new FileOutputStream(dest);
			imageOut = ImageIO.createImageOutputStream(out);
			writer = (ImageWriter) ImageIO.getImageWritersByFormatName("jpg")
										  .next();// getImageWritersByFormatName actually just returns an iterator for all image writers that work with the chosen format		
			writer.setOutput(imageOut);
			
			
			ImageWriteParam param = writer.getDefaultWriteParam();
			//Do we want compression 
		    if(input.length() > maxUncompressedSize && param.canWriteCompressed()){  
		    	param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		    	param.setCompressionQuality(0.7f);	
		    }
		    
		    writer.write(null, new IIOImage(image,null,null), param);
		    
		}catch(Exception e){
			dest.delete();
			throw new RuntimeException(e);
		}finally{
			try {			
				writer.dispose(); // Looking at the sources for ImageWriter and its various implementations it's hard to 
				imageOut.close(); // discern whether it actually frees resources e.g in the abstract ImageWriter class dispose() is just 
				out.close();      // an empty method. Some subclasses have their own implementations of dispose() and some don't
				       			  // so all resources will be freed manually.
			}catch(Exception ignore){}
		}
		return fileName;
	}	
	

    
	/**
	 * Uses java.nio.file.Files
	 * to load images from the filesystem into a <code>byte[]</code> 
	 * @param path the final path to the file resource
	 * @throws IOException
	 */
	private  byte [] getByteArray(String path) throws IOException{
	    return Files.readAllBytes((Paths.get(defaultDestPath, path)));

	}
	/**
	 * Converts a Buffered image to a jpg format.
	 * Sets image backgrounds to white by default.
	 * @param bufferedImage the image to convert
	 * @return a converted to jpg version of the supplied image
	 */
	private  BufferedImage convertToJpg(BufferedImage bufferedImage){
		BufferedImage converted = new BufferedImage(bufferedImage.getWidth(),
				  									  bufferedImage.getHeight(), 
				  									  BufferedImage.TYPE_INT_RGB);
		  
		converted.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);// Set background to white as jpg does not support transparent backgrounds
		return converted;
	}

}
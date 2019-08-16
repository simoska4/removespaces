import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * This class implements methods to remove
 * spaces from an image.
 *
 * @author Simone Sapienza <simoska4@gmail.com>
 * @version 1.0
 */
public class RemoveSpaces {

    private static final int threshold = 250;

    /**
     * Main method. Given in input a BufferedImage, it removes
     * the spaces and returns the compressed image.
     * @param image input BufferedImage
     * @param onlyExternally if it is true it means that we want to remove
     *                       spaces only outside the image
     * @return compressed image (without spaces)
     */
    public static BufferedImage compress(BufferedImage image,
                                         boolean onlyExternally){
        int[] rgbDataThreshold = EditImage.getRGBData(EditImage.thresholdImage(image));
        int[] rgbData= EditImage.getRGBData(image);
        //We get the X coordinates of the columns to keep:
        ArrayList<Integer> columns = compress_Columns(image, onlyExternally, rgbDataThreshold);
        //We get the Y coordinates of the rows to keep:
        ArrayList<Integer> rows = compress_Rows(image, onlyExternally, rgbDataThreshold);
        //Compress the BufferedImage:
        return compressBuffered(image, rows, columns, rgbData);
    }


    /*
     * Method to compress (remove spaces) the BufferedImage
     * image: input BufferedImage
     * rows: ArrayList containing the valid rows (which must be kept)
     * columns: ArrayList containing the valid columns (which must be kept)
     * rgbData: Array containing all the pixels of the original BufferedImage
     * return: compressed BufferedImage
     */
    private static BufferedImage compressBuffered(BufferedImage image,
                                                  ArrayList<Integer> rows,
                                                  ArrayList<Integer> columns,
                                                  int[] rgbData){
        if(columns.size()==0 || rows.size()==0)
            return image;
        BufferedImage img = new BufferedImage(columns.size(), rows.size(), BufferedImage.TYPE_INT_ARGB);
        int sottrai_x = 0;
        int sottrai_y;
        for(int x = 0; x < image.getWidth(); x++){
            if(columns.contains(x)) {
                sottrai_y=0;
                for (int y = 0; y < image.getHeight(); y++) {
                    if(rows.contains(y)) {
                        int p = (new Color((rgbData[(y*image.getWidth())+x] >> 16) & 0xFF, (rgbData[(y*image.getWidth())+x] >> 8) & 0xFF, (rgbData[(y*image.getWidth())+x]) & 0xFF)).getRGB();
                        img.setRGB(x - sottrai_x, y - sottrai_y, p);
                    }
                    else sottrai_y++;
                }
            }
            else sottrai_x++;
        }
        return img;
    }


    /*
    * Method to get the list of valid columns of the BufferedImage
    * image: input BufferedImage
    * onlyExternally: flag that indicates if we want to remove spaces only outside the BufferedImage
    * rgbData: Array containing all the pixels of the edited BufferedImage
     */
    private static ArrayList<Integer> compress_Columns(BufferedImage image,
                                                               boolean onlyExternally,
                                                               int[] rgbData){
        ArrayList<Integer> colonne = new ArrayList<>(); //X coordinates of the columns to keep
        boolean find = false; //true if we found at least one colored pixel
        int x;
        for(x = 0; x < image.getWidth() && !(onlyExternally&&find); x++)
            find = verify(image, x, image.getHeight(), true, colonne, find, rgbData, false);
        if(onlyExternally){
            verify(image, x, image.getWidth()-1, image.getHeight()-1, true, colonne, rgbData, true);
        }
        return colonne;
    }


    /*
     * Method to get the list of valid rows of the BufferedImage
     * image: input BufferedImage
     * onlyExternally: flag that indicates if we want to remove spaces only outside the BufferedImage
     * rgbData: Array containing all the pixels of the edited BufferedImage
     */
    private static ArrayList<Integer> compress_Rows(BufferedImage image,
                                                            boolean onlyExternally,
                                                            int[] rgbData){
        ArrayList<Integer> righe = new ArrayList<>(); //Y coordinates of the rows to keep
        boolean find = false; //true if we found at least one colored pixel
        int y;
        for(y = 0; y < image.getHeight() && !(onlyExternally&&find); y++) {
            find = verify(image, y, image.getWidth(), false, righe, find, rgbData, false);
        }
        if(onlyExternally)
            verify(image, y, image.getHeight()-1, image.getWidth()-1, false, righe, rgbData, true);
        return righe;
    }


    /*
     * Method by which a row/columns is checked (based on the firstValue flag).
     * image: input BufferedImage
     * main_value:  is the current value of the reference parameter (X in the analysis from the left - Y in the analysis from above)
     * main_max: is the maximum value that the main_value can take (for X it will be the width, for Y it will be the height)
     * second_max: is the maximum value that the second parameter can take (if the first is X, then the second will refer to Y)
     * firstValue: if true indicates that the main_value corresponds to the x-coordinate (X)
     * array: ArrayList that refers to the list of rows or columns
     * rgbData: Array containing all the pixels of the edited BufferedImage
     * onlyExternally: flag that indicates if we want to remove spaces only outside the BufferedImage
     */
    private static void verify(BufferedImage image,
                               int main_value,
                               int main_max,
                               int second_max,
                               boolean firstValue,
                               ArrayList<Integer> array,
                               int[] rgbData,
                               boolean onlyExternally){
        boolean find=false;
        for(int value1 = main_max; value1>main_value; value1--) {
            find=verify(image, value1, second_max, firstValue, array, find, rgbData, onlyExternally);
        }
    }


    /*
     * Method by which a row/columns is checked (based on the firstValue flag).
     * image: input BufferedImage
     * value1: is the value of the reference parameter (X or Y)
     * max: is the maximum value that the second parameter can take
     * firstValue: if true indicates that the main_value corresponds to the x-coordinate (X)
     * array: ArrayList that refers to the list of rows or columns
     * find: if true means that we have found at least one occurrence of a pixel that does not belong to the background
     * onlyExternally: flag that indicates if we want to remove spaces only outside the image
     * return: a boolean is returned indicating whether we have found at least one pixel that does not belong to the background
     */
    private static boolean verify(BufferedImage image,
                                  int value1,
                                  int max,
                                  boolean firstValue,
                                  ArrayList<Integer> array,
                                  boolean find,
                                  int[]rgbData,
                                  boolean onlyExternally){
        boolean valueOk = false;
        for(int value2 = 0; value2 < max; value2++){
            if(notBackground(image, firstValue, rgbData, value1, value2)||(find&&onlyExternally)){
                valueOk = true;
                find = true;
            }
        }
        if(valueOk)
            array.add(value1);
        return find;
    }


    /*
     * Method by which we verify if a given pixel (calculated by coordinates) is not part of the background.
     * image: input BufferedImage
     * firstValue: if true indicates that the value1 corresponds to the coordinate of the abscissas (X)
     * rgbData: Array containing all the pixels of the original image
     * return: true if the pixel is not part of the background
     */
    private static boolean notBackground(BufferedImage image,
                                      boolean firstValue,
                                      int[] rgbData,
                                      int value1,
                                      int value2){
        int red, green, blue;
        if(!firstValue) {
            red = ((rgbData[(value1 * image.getWidth()) + value2] >> 16) & 0xFF);
            green = ((rgbData[(value1 * image.getWidth()) + value2] >> 8) & 0xFF);
            blue = ((rgbData[(value1 * image.getWidth()) + value2]) & 0xFF);
        }
        else {
            red = ((rgbData[(value2 * image.getWidth()) + value1] >> 16) & 0xFF);
            green = ((rgbData[(value2 * image.getWidth()) + value1] >> 8) & 0xFF);
            blue = ((rgbData[(value2 * image.getWidth()) + value1]) & 0xFF);
        }
        return red < threshold && green < threshold && blue < threshold;
    }

}

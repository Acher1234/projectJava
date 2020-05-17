package unittests;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

public class ImageWriterTest {

    @Test
    public void testImageWritter()
    {
        ImageWriter test = new ImageWriter("TestRender",800,500,1600,1000);
        for(int i=0;i<1000;i++)
        {
            for(int j=0;j<1600;j++)
            {
                if(i % 100 == 0)
                {
                    test.writePixel(j,i,new Color(255,0,0));
                }
                else if(j % 100 == 0)
                {
                    test.writePixel(j,i,new Color(255,0,0));
                }
                else
                {
                    test.writePixel(j,i,new Color(120,150,180));
                }
            }
        }
        test.writeToImage();
    }
}

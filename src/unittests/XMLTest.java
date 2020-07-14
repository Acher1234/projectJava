package unittests;

import org.junit.Test;
import renderer.Render;

public class XMLTest
{
    @Test
    public void TestRender()
    {
        Render render = Render.getRenderFromXML("test.xml");
        render.renderImage();
        render.writeToImage();
    }
}

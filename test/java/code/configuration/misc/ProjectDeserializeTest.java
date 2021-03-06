/*
 * The MIT License
 *
 * Copyright 2017 Micha� Szyma�ski, kontakt: michal.szymanski.aajar@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package code.configuration.misc;

import code.model.Image;
import code.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Micha� Szyma�ski, kontakt: michal.szymanski.aajar@gmail.com
 */
public class ProjectDeserializeTest {

    public ProjectDeserializeTest() {
    }

    ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void testDeserialize() throws Exception {
        String[] techs = new String[]{"java", " sql", "javascript"};
        Image[] expectedImages = new Image[]{
            new Image(new URL("http://original"), new URL("http://miniature")),
            new Image(new URL("http://original" + "2"), new URL("http://miniature2"))
        };
        Project p = new Project();
        p.setTechs(techs);
        p.setImages(expectedImages);
        Map map = mapper.convertValue(p, Map.class);

        Project result = mapper.convertValue(map, Project.class);
        String[] resultTechs = result.getTechs();
        assertTrue(Arrays.equals(techs, resultTechs));
        assertEquals(expectedImages[0], result.getImages()[0]);

    }

}

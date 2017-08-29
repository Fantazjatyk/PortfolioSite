/*
 * The MIT License
 *
 * Copyright 2017 Micha³ Szymañski, kontakt: michal.szymanski.aajar@gmail.com.
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
package code.dao;

import code.Conf;
import code.model.Project;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author Micha³ Szymañski, kontakt: michal.szymanski.aajar@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Conf.class})
@WebAppConfiguration
@Transactional
public class ProjectsDaoTest {

    public ProjectsDaoTest() {
    }

    @Autowired
    ProjectsDao dao;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of updateProject method, of class ProjectsDao.
     */
    @Test
    public void testUpdateProject() {
        Project p = new Project();
        p.setName("Test");
        p.setShortname("test");
        p.setDesc("this is ain't real project");
        p.setSite("project_site");
        p.setSource("project_source");
        dao.insert(p);

        Project updated = dao.getProject("test");
        updated.setDesc("blah blah");
        updated.setName("Test2");
        dao.updateProject(updated);

        Project result = dao.getProject("test");
        assertEquals(updated, result);
        assertNotEquals(p, updated);
        assertNotEquals(p, result);
    }

    /**
     * Test of isExists method, of class ProjectsDao.
     */
    @Test
    public void testIsExists() {
        Project p = new Project();
        p.setName("Test");
        p.setShortname("test");
        p.setDesc("this is ain't real project");
        p.setSite("project_site");
        p.setSource("project_source");
        dao.insert(p);
        assertTrue(dao.isExists(p.getShortname()));
        assertFalse(dao.isExists(Long.toString(System.currentTimeMillis())));
    }

    /**
     * Test of getProject method, of class ProjectsDao.
     */
    @Test
    public void testInsertAndGetProject() {
        Project p = new Project();
        p.setName("Test");
        p.setShortname("test");
        p.setDesc("this is ain't real project");
        p.setSite("project_site");
        p.setSource("project_source");

        dao.insert(p);

        Project project = dao.getProject(p.getShortname());
        assertEquals(p.getName(), project.getName());
        assertEquals(p.getShortname(), project.getShortname());
        assertEquals(p.getDesc(), project.getDesc());
        assertEquals(p.getSite(), project.getSite());
        assertEquals(p.getSource(), project.getSource());

    }

    /**
     * Test of getAll method, of class ProjectsDao.
     */
    @Test
    public void testGetAll() {
        List<Project> projects = dao.getAll();
        assertFalse(projects.isEmpty());
    }

    /**
     * Test of insert method, of class ProjectsDao.
     */
}

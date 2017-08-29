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
import code.model.Personal;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
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
public class PersonalDaoTest {

    public PersonalDaoTest() {
    }

    @Autowired
    PersonalDao dao;

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of getAboutMe method, of class PersonalDao.
     */
    @Test
    public void testGetAboutMe() {
        String result = dao.getAboutMe();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getEmail method, of class PersonalDao.
     */
    @Test
    public void testGetEmail() {
        String result = dao.getEmail();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getSocialMedias method, of class PersonalDao.
     */
    @Test
    public void testGetSocialMedias() throws Exception {
        Map result = dao.getSocialMedias();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of update method, of class PersonalDao.
     */
    @Test
    public void testUpdate() {
        // Can't do it due to using pure JDBC in that class.
    }

    /**
     * Test of getPersonal method, of class PersonalDao.
     */
    @Test
    public void testGetPersonal() {

        Personal p = dao.getPersonal();
        assertNotNull(p);
        assertNotNull(p.getAboutme());
        assertNotNull(p.getEmail());
        assertNotNull(p.getSocialMedias());
    }

}

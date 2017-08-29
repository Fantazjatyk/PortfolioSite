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
package code.controllers;

import code.Conf;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

/**
 *
 * @author Micha³ Szymañski, kontakt: michal.szymanski.aajar@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Conf.class})
@Transactional
@WebAppConfiguration
@WithMockUser(roles = "ADMIN")
public class EditorTest {

    MockMvc mvc;

    @Autowired
    WebApplicationContext ctx;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(ctx).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    /**
     * Test of editPersonal method, of class Editor.
     */
    @Test
    public void testEditPersonal() throws Exception {
        mvc.perform(get("/edit/personal"))
                .andExpect(status().isOk())
                .andExpect(view().name("personal_editor"))
                .andExpect(model().attributeExists("personal", "social_media"));

    }

    /**
     * Test of updatePersonal method, of class Editor.
     */
    @Test
    public void testUpdatePersonal() throws Exception {

    }

    /**
     * Test of editProject method, of class Editor.
     */
    @Test
    public void testEditProject() throws Exception {
        mvc.perform(get("/edit/projects/crawler"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("project"))
                .andExpect(view().name("project_editor"));
    }

    /**
     * Test of listProjects method, of class Editor.
     */
    @Test
    public void testListProjects() throws Exception {
        mvc.perform(get("/edit/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }

    /**
     * Test of updateProject method, of class Editor.
     */
    @Test
    public void testUpdateProject() {
    }

}

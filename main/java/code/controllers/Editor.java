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

import code.dao.PersonalDao;
import code.dao.ProjectsDao;
import code.model.Personal;
import code.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Micha³ Szymañski, kontakt: michal.szymanski.aajar@gmail.com
 */
@Controller
@RequestMapping(value = "/edit")
public class Editor {

    @Autowired
    PersonalDao pe;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ProjectsDao pd;

    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String editPersonal(Model model) {
        Personal p = pe.getPersonal();
        Map pMap = mapper.convertValue(p, HashMap.class);
        model.addAttribute("personal", pMap);
        model.addAttribute("social_media", p.getSocialMedias());
        return "personal_editor";
    }

    @RequestMapping(value = "/personal", method = RequestMethod.POST)
    public String updatePersonal(Personal p) {
        pe.update(p);
        return "redirect:personal";
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public String editProject(Model model, @PathVariable("id") String shortname) {
        Project p = pd.getProject(shortname);
        Map serialized = mapper.convertValue(p, Map.class);
        model.addAttribute("project", serialized);
        return "project_editor";
    }

    @RequestMapping(value = "/projects")
    @ResponseBody
    public String listProjects(Model model) {

        List<Project> projects = pd.getAll();
        StringBuilder b = new StringBuilder();

        projects.stream().forEach(el -> b.append(generateHTMLLine(el)));
        return b.toString();
    }

    private String generateHTMLLine(Project p) {
        String html = "<a style=\"display:block; font-size: xx-large; color: ${color};\" href=\"projects/${shortname}\">${name}</a>";
        Map map = new HashMap();
        map.put("color", p.getLeadingColor());
        map.put("shortname", p.getShortname());
        map.put("name", p.getName());
        return new StrSubstitutor(map).replace(html);
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.POST)
    public String updateProject(Project p, @PathVariable("id") String shortname) {
        pd.updateProject(p);
        return "redirect:" + shortname;
    }

}

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

import code.dao.mapping.ProjectMapper;
import code.misc.ProjectNotFoundException;
import code.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Micha³ Szymañski, kontakt: michal.szymanski.aajar@gmail.com
 */
@Repository
public class ProjectsDao {

    @Autowired
    NamedParameterJdbcTemplate t;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ProjectMapper pm;

    public void updateProject(Project p) {
        HashMap map = mapper.convertValue(p, HashMap.class);
        String st0 = "update projects set name = :name, `desc` = :desc where short_name = :shortname";
        String st1 = "update projects_details set used_techs = :techs, leading_color = :leadingColor, leading_text_color = :leadingTextColor, images = :images, project_site_link = :site, source_code_link = :source"
                + " where short_name = :shortname";

        int u1 = t.update(st0, map);
        int u2 = t.update(st1, map);
        if (u1 == 0 || u2 == 0) {
            throw new ProjectNotFoundException();
        }
    }

    public boolean isExists(String shortname) {
        String s = "select count(*) from projects where short_name = :shortname";
        HashMap map = new HashMap();
        map.put("shortname", shortname);
        Long count = t.queryForObject(s, map, Long.class);
        return count > 0;
    }

    public Project getProject(String shortName) {
        String statement = "select * from projects natural join projects_details where short_name = :short_name";
        Project result = null;
        try {
            result = t.queryForObject(statement, new HashMap() {
                {
                    put("short_name", shortName);
                }
            }, pm);

        } catch (EmptyResultDataAccessException e) {
        }
        return result;
    }

    public List<Project> getAll() {
        String st = "select * from projects natural join projects_details";
        return t.query(st, pm);
    }

    public void insert(Project p) {
        HashMap<String, Object> map = mapper.convertValue(p, HashMap.class);

        String st1 = "insert into projects (short_name, name, `desc`) values (:shortname, :name, :desc)";
        String st2 = "insert into projects_details (short_name, used_techs, images, project_site_link, source_code_link) "
                + "values (:shortname, :techs, :images, :site, :source)";
        t.update(st1, map);
        t.update(st2, map);
    }
}

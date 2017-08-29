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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import code.model.Personal;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Micha³ Szymañski, kontakt: michal.szymanski.aajar@gmail.com
 */
@Repository
public class PersonalDao {

    @Autowired
    @Qualifier("dataSource")
    javax.sql.DataSource ds;

    @Autowired
    ObjectMapper mapper;

    public String getAboutMe() {

        try {
            return getField("aboutme");
        } catch (SQLException ex) {
            Logger.getGlobal().warning(ex.getMessage());
            return p.getProperty("emergency.aboutme");
        }
    }
    @Autowired
    @Qualifier("application.properties")
    Properties p;

    public String getEmail() {
        try {
            return getField("email");
        } catch (SQLException ex) {
            Logger.getGlobal().warning(ex.getMessage());
            return p.getProperty("emergency.email");
        }
    }

    public Map<String, String> getSocialMedias() throws SQLException, IOException {
        String json = getField("media_profiles");
        return mapper.readValue(json, Map.class);
    }

    private <T extends Object> T getField(String field) throws SQLException {

        T result = null;

        try (Connection conn = ds.getConnection()) {
            try (Statement stt = conn.createStatement()) {
                try (ResultSet rs = stt.executeQuery("select " + field + " from personal")) {
                    if (rs.next()) {
                        result = (T) rs.getObject(field);
                    }
                }
            }
        }

        return (T) result;
    }

    public void update(Personal p) {
        try (Connection conn = ds.getConnection()) {

            try (Statement st = conn.createStatement()) {
                Map values = new HashMap();
                values.put("aboutme", p.getAboutme());
                values.put("email", p.getEmail());
                String mediaJson = null;
                try {
                    mediaJson = mapper.writeValueAsString(p.getSocialMedias());
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(PersonalDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                values.put("media", mediaJson);
                String sql = "update personal set aboutme = '${aboutme}', email = '${email}', media_profiles = '${media}'";
                StrSubstitutor ss = new StrSubstitutor(values);
                String ssFormated = ss.replace(sql);
                st.executeUpdate(ssFormated);

            } catch (SQLException ex) {
                Logger.getLogger(PersonalDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonalDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Personal getPersonal() {

        Personal p = null;
        try {
            try (Connection conn = ds.getConnection()) {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery("select * from personal")) {
                        if (rs.next()) {
                            p = new Personal();
                            p.setAboutme(rs.getString("aboutme"));
                            p.setEmail(rs.getString("email"));
                            Map mediaProfiles = null;
                            mediaProfiles = mapper.readValue(rs.getString("media_profiles"), HashMap.class);
                            p.setSocialMedias(mediaProfiles);
                        }
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        }
        return p;
    }
}

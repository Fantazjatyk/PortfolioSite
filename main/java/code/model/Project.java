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
package code.model;

import code.misc.StringArrayDeserializer;
import code.misc.StringArraySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Micha³ Szymañski, kontakt: michal.szymanski.aajar@gmail.com
 */
public class Project {

    private String name;
    private String desc;
    @JsonSerialize(using = StringArraySerializer.class)
    @JsonDeserialize(using = StringArrayDeserializer.class)
    private String[] images;
    @JsonSerialize(using = StringArraySerializer.class)
    @JsonDeserialize(using = StringArrayDeserializer.class)
    private String[] animations;
    @JsonSerialize(using = StringArraySerializer.class)
    @JsonDeserialize(using = StringArrayDeserializer.class)
    private String[] videos;
    @JsonSerialize(using = StringArraySerializer.class)
    @JsonDeserialize(using = StringArrayDeserializer.class)
    private String[] techs;
    private String source;
    private String site;
    private String shortname;
    private String leadingColor;
    private String leadingTextColor;

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public String getLeadingColor() {
        return leadingColor;
    }

    public void setLeadingColor(String leadingColor) {
        this.leadingColor = leadingColor;
    }

    public String getLeadingTextColor() {
        return leadingTextColor;
    }

    public void setLeadingTextColor(String leadingTextColor) {
        this.leadingTextColor = leadingTextColor;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String[] getAnimations() {
        return animations;
    }

    public void setAnimations(String[] animations) {
        this.animations = animations;
    }

    public String[] getVideos() {
        return videos;
    }

    public void setVideos(String[] videos) {
        this.videos = videos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getTechs() {
        return techs;
    }

    public void setTechs(String[] techs) {
        this.techs = techs;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.desc);
        hash = 23 * hash + Arrays.deepHashCode(this.images);
        hash = 23 * hash + Arrays.deepHashCode(this.animations);
        hash = 23 * hash + Arrays.deepHashCode(this.videos);
        hash = 23 * hash + Arrays.deepHashCode(this.techs);
        hash = 23 * hash + Objects.hashCode(this.source);
        hash = 23 * hash + Objects.hashCode(this.site);
        hash = 23 * hash + Objects.hashCode(this.shortname);
        hash = 23 * hash + Objects.hashCode(this.leadingColor);
        hash = 23 * hash + Objects.hashCode(this.leadingTextColor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.site, other.site)) {
            return false;
        }
        if (!Objects.equals(this.shortname, other.shortname)) {
            return false;
        }
        if (!Objects.equals(this.leadingColor, other.leadingColor)) {
            return false;
        }
        if (!Objects.equals(this.leadingTextColor, other.leadingTextColor)) {
            return false;
        }
        if (!Arrays.deepEquals(this.images, other.images)) {
            return false;
        }
        if (!Arrays.deepEquals(this.animations, other.animations)) {
            return false;
        }
        if (!Arrays.deepEquals(this.videos, other.videos)) {
            return false;
        }
        if (!Arrays.deepEquals(this.techs, other.techs)) {
            return false;
        }
        return true;
    }

}

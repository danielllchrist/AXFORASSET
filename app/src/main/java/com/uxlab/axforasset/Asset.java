package com.uxlab.axforasset;

import java.io.Serializable;

public class Asset implements Serializable {
    private String name, shortDesc, longDesc;
    int image;

    public Asset(String name, String shortDesc, String longDesc, int image) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public int getImage() {
        return image;
    }
}

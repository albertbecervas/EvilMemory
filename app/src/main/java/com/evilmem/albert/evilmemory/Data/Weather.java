package com.evilmem.albert.evilmemory.Data;

import java.lang.ref.SoftReference;

/**
 * Created by Albert on 03/02/2017.
 */
public class Weather {
    String main;
    String description;

    @Override
    public String toString() {
        return main + "\n" + description;
    }
}

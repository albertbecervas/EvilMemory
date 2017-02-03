/*
Created by Helm  31/01/2017.
*/


package com.evilmem.albert.evilmemory.Data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private String name;
    private String pass;
    private String completename;

    public String getCompletename() {
        return completename;
    }

    public void setCompletename(String completename) {
        this.completename = completename;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getName() {
        return name;
    }
}

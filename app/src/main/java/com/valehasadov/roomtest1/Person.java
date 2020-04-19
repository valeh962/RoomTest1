package com.valehasadov.roomtest1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

@Entity(tableName = "_person")
public class Person {


    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "person_name")
    private String name;

    @NonNull
    @ColumnInfo(name = "person_age")
    private String age;

    public Person(@NonNull String id, @NonNull String name, @NonNull String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getAge() {
        return age;
    }

    public void setAge(@NonNull String age) {
        this.age = age;
    }
}

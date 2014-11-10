package com.willyout.BasicDemo.service;


import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{

    private int id;
    private String name, pass;

    public Person(){}

    public Person(int id, String name, String pass){
        super();
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result + ((name == null)?0 : name.hashCode());
        result = prime*result + ((pass == null)?0 : pass.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if((o == null) || (this.getClass() != o.getClass())){
            return false;
        }
        Person p = (Person)o;

        if(name == null || pass == null || p.getName() == null || p.getPass() == null){
            return false;
        }

        if(!name.equals(p.getName())){
            return false;
        }
        else if(!pass.equals(p.getPass())){
            return false;
        }
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(pass);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source.readInt(),source.readString(), source.readString());
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}

package com.goeuro.model;

public class CSVPosition {

    private int _id;
    private String name;
    private String  type;
    private double latitude;
    private double longitude;

    public CSVPosition(int _id, String name, String type, double latitude, double longitude) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static String[] toHeader(){
        return new String[]{ "_id" , "name", "type", "longitude", "latitude" };
    }
    public String[] toArray(){
        return new String[]{ Integer.toString(_id) ,name,type, Double.toString(longitude), Double.toString(latitude) };
    }
}

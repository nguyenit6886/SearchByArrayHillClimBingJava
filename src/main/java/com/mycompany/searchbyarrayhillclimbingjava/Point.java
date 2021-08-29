/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.searchbyarrayhillclimbingjava;

import java.util.ArrayList;
/**
 *
 * @author Admin
 */

public class Point {

    private String name;
    private int value;
    ArrayList<Point> child;

    public Point() {
    }

    public Point(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Point(String name, int value, ArrayList<Point> child) {
        this.name = name;
        this.value = value;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<String> getNameChild() {
        ArrayList<String> arrayNameChild = new ArrayList();
        for (Point point : child) {
            arrayNameChild.add(point.getName());
        }
        return arrayNameChild;
    }

    public ArrayList<Point> getChild() {
        return child;
    }

    public void setChild(ArrayList<Point> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Point{" + "name=" + name + ", value=" + value + ", child=" + getNameChild() + '}';
    }

}

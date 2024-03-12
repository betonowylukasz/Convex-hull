package com.company;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;

public class CosinusComparator implements Comparator<Point> {

    HashMap<Point, Double> cosinus;

    public CosinusComparator (HashMap<Point, Double> cosinus)
    {
        this.cosinus=cosinus;
    }

    public int compare (Point p1, Point p2)
    {
        return cosinus.get(p2).compareTo(cosinus.get(p1));
    }
}

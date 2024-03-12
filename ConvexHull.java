package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ConvexHull {
    public static List<Point> solve(List<Point> points) throws IllegalArgumentException {
        List<Point> copyPoints=new ArrayList<>(points);
        Point lowest=lowestPoint(copyPoints);
        copyPoints.sort(new CosinusComparator(cosinusMap(copyPoints, lowest)));
        deleteSameRadius(copyPoints, lowest);
        if (copyPoints.size()<3) throw new IllegalArgumentException();
        ArrayStack<Point> stack=new ArrayStack<>(copyPoints.size()+1);
        Iterator<Point> iter=copyPoints.iterator();
        Point p;
        stack.push(iter.next());
        stack.push(iter.next());
        stack.push(iter.next());
        while (iter.hasNext())
        {
            p=iter.next();
            while (sarus(stack.nextToTop(), stack.top(), p)<=0) stack.pop();
            stack.push(p);
        }
        stack.push(lowest);
        return stackToList(stack);
    }

    private static Point lowestPoint (List<Point> points)
    {
        Iterator<Point> iter=points.iterator();
        Point min=iter.next();
        Point p;
        while (iter.hasNext())
        {
            p=iter.next();
            if (p.getY()<min.getY()) min=p;
            else if (p.getY()==min.getY())
            {
                if (p.getX()<min.getX()) min=p;
            }
        }
        return min;
    }

    private static HashMap<Point, Double> cosinusMap(List<Point> points, Point lowest)
    {
        Iterator<Point> iter=points.iterator();
        HashMap<Point, Double> cosinus=new HashMap<>();
        Point p;
        while (iter.hasNext())
        {
            p=iter.next();
            if (p.equals(lowest)) cosinus.put(p, 2.0);
            else
            {
                cosinus.put(p, (p.getX()-lowest.getX())/p.distance(lowest));
            }
        }
        return cosinus;
    }

    private static void deleteSameRadius (List<Point> points, Point lowest)
    {
        for (int i=2;i<points.size();i++)
        {
            if (sarus(lowest, points.get(i-1), points.get(i))==0)
            {
                if (lowest.distance(points.get(i-1))<lowest.distance(points.get(i))) points.remove(i-1);
                else points.remove(i);
                i--;
            }
        }
    }

    private static double sarus(Point p1, Point p2, Point p3)
    {
        return p1.getX()*p2.getY()+p2.getX()*p3.getY()+p3.getX()*p1.getY()-p1.getY()*p2.getX()-p2.getY()*p3.getX()-p3.getY()*p1.getX();
    }

    private static List<Point> stackToList (ArrayStack<Point> stack)
    {
        List<Point> points=new LinkedList<>();
        while (!stack.isEmpty())
        {
            points.add(0, stack.pop());
        }
        return points;
    }
}

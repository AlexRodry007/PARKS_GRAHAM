import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;


public class ConvexHull
{
    static final int INF = 10000;
   
    static int orientation(Point p, Point q, Point r)
{
    int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

    if (val == 0) return 0;

    return (val > 0)? 1: 2;
}

static Point[] convexHull(Point points[], int n)
{
    if (n < 3) return;

    Vector<Point> hull = new Vector<Point>();

    int l = 0;
    for (int i = 1; i < n; i++)
        if (points[i].x < points[l].x)
            l = i;

    int p = l, q;
    do
    {
        hull.add(points[p]);

        q = (p + 1) % n;

        for (int i = 0; i < n; i++)
        {
           if (orientation(points[p], points[i], points[q]) == 2)
               q = i;
        }

        p = q;

    } while (p != l);

    Point[] hullArray = new Point[hull.size()];
    for (int i = 0; i < hull.size(); i++)
        hullArray[i] = hull.get(i);

    return hullArray;
}
}

public static void main(String[] args) 
{
    Point points[] = new Point[8];
    points[0] = new Point(0, 3);
    points[1] = new Point(1, 1);
    points[2] = new Point(2, 2);
    points[3] = new Point(4, 4);
    points[4] = new Point(0, 0);
    points[5] = new Point(1, 2);
    points[6] = new Point(3, 1);
    points[7] = new Point(3, 3);

    int n = points.length;
    convexHull(points, n);
}
 
public class Point 
{
    int x, y;
    Point()
    {
        x = 0;
        y = 0;
    }
    Point(int a, int b)
    {
        x = a;
        y = b;
    }
}

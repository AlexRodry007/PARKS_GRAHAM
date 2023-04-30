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
    if (n < 3) return points;

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
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import parcs.*;

public class Bluck {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("DFS.jar");
        Point[] n = fromFile(curtask.findFile("input"));
        Point[][] dividedArray = divideArray(n, 4);

        AMInfo info = new AMInfo(curtask, null);

        List<point> points = new ArrayList<>();
        List<channel> chans = new ArrayList<>();
        for (Point[] subarray : dividedArray) 
        {
            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("DFS");
            c.write(subarray);
            points.add(p);
            chans.add(c);
        }

        Point[] concatenatedArray = new Point[0];
        for (channel c: chans) {
            try {
                Point[] subpoints = (Point[]) c.readObject();
                Point[] tempArray = new Point[concatenatedArray.length + subpoints.length];
                System.arraycopy(concatenatedArray, 0, tempArray, 0, concatenatedArray.length);
                System.arraycopy(subpoints, 0, tempArray, concatenatedArray.length, subpoints.length);
                concatenatedArray = tempArray;
            
            } catch (Exception e) {
                e.printStackTrace();
            }        
        }
        System.out.println("Waiting for result...");
        Point[] res = convexHull(concatenatedArray);
        System.out.println("Result: " + pointsToString(res));
        curtask.end();
    }

    public static Point[] fromFile(String filename) throws Exception {
        Scanner scanner = new Scanner(new File(filename));
            int numPoints = 0;
            while (scanner.hasNextLine()) {
                numPoints++;
                scanner.nextLine();
            }
            scanner.close();
            scanner = new Scanner(new File(filename));
            Point[] points = new Point[numPoints];
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                points[i] = new Point(x, y);
                i++;
            }
            scanner.close();
            return points;
    }

    public static Point[][] divideArray(Point[] array, int x) {
        int subarraySize = array.length / x;
        int remainingElements = array.length % x;
        Point[][] subarrays = new Point[x][];
        for (int i = 0; i < x; i++) {
            int size = subarraySize;
            if (i < remainingElements) {
                size++;
            }
            Point[] subarray = new Point[size];
            System.arraycopy(array, i * subarraySize + Math.min(i, remainingElements), subarray, 0, size);
            subarrays[i] = subarray;
        }
        return subarrays;
    }

    public static String pointsToString(Point[] points) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < points.length; i++) {
        sb.append(points[i].x);
        sb.append(",");
        sb.append(points[i].y);
        if (i < points.length - 1) {
            sb.append(" ");
        }
    }
    return sb.toString();
    }
}

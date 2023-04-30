import java.util.List;
import java.util.ArrayList;
import parcs.*;

public class DFS implements AM {
    public void run(AMInfo info) {
        Point[] n = (Point[])info.parent.readObject();
        Point[] convex = convexHull(n);
        info.parent.write(convex);
    }
}

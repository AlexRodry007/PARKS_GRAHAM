import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.*;

public class Point implements Serializable {
private static final long serialVersionUID = 1L;

int x, y;

Point() {
    x = 0;
    y = 0;
}

Point(int a, int b) {
    x = a;
    y = b;
}
}

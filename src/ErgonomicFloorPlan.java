import java.util.List;

public class ErgonomicFloorPlan {

    // Function to check if two line segments (switch to fixture, and wall) intersect
    public static boolean doLineSegmentsIntersect(int[] start1, int[] end1, int[] start2, int[] end2) {
        int orientation1 = getOrientation(start1, end1, start2);
        int orientation2 = getOrientation(start1, end1, end2);
        int orientation3 = getOrientation(start2, end2, start1);
        int orientation4 = getOrientation(start2, end2, end1);
        return (orientation1 != orientation2 && orientation3 != orientation4);
    }

    // Helper function to find the orientation of three points
    public static int getOrientation(int[] point1, int[] point2, int[] point3) {
        int value = (point2[1] - point1[1]) * (point3[0] - point2[0]) - (point2[0] - point1[0]) * (point3[1] - point2[1]);
        if (value == 0) return 0; // Collinear points
        return (value > 0) ? 1 : 2; // Clockwise or Counter-clockwise
    }

    // Function to check if a line of sight exists between a switch and fixture
    public static boolean hasLineOfSight(int[] switchPosition, int[] fixturePosition, List<int[][]> walls) {
        for (int[][] wall : walls) {
            if (doLineSegmentsIntersect(switchPosition, fixturePosition, wall[0], wall[1])) {
                return false; // Line of sight blocked by wall
            }
        }
        return true; // Line of sight is clear
    }
}
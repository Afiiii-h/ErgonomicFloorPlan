import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ErgonomicFloorPlan ergonomicFloorPlan = new ErgonomicFloorPlan();

        // Get the number of coordinates for the floor plan (walls)
        System.out.print("Enter number of coordinates for floor plan: ");
        int numCoordinates = scanner.nextInt();

        List<int[][]> walls = new ArrayList<>();
        int[] previousPoint = new int[2];

        System.out.println("Please enter each coordinate in clockwise order:");
        for (int i = 0; i < numCoordinates; i++) {
            System.out.print("Enter x" + (i + 1) + ": ");
            int x = scanner.nextInt();
            System.out.print("Enter y" + (i + 1) + ": ");
            int y = scanner.nextInt();
            int[] currentPoint = {x, y};

            if (i > 0) {
                walls.add(new int[][]{previousPoint, currentPoint});
            }
            previousPoint = currentPoint;
        }

        // Closing the polygon for the last wall
        walls.add(new int[][]{previousPoint, walls.get(0)[0]});

        // Get the number of switches and fixtures
        System.out.print("Enter number of light fixtures and switches: ");
        int n = scanner.nextInt();

        int[][] switches = new int[n][2];
        int[][] fixtures = new int[n][2];

        System.out.println("Please enter coordinates of each switch:");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter x-coordinate of switch " + (i + 1) + ": ");
            switches[i][0] = scanner.nextInt();
            System.out.print("Enter y-coordinate of switch " + (i + 1) + ": ");
            switches[i][1] = scanner.nextInt();
        }

        System.out.println("Please enter coordinates of each light fixture:");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter x-coordinate of fixture " + (i + 1) + ": ");
            fixtures[i][0] = scanner.nextInt();
            System.out.print("Enter y-coordinate of fixture " + (i + 1) + ": ");
            fixtures[i][1] = scanner.nextInt();
        }

        // Checking if each switch has a line of sight to at least one fixture
        int countVisible = 0;
        for (int i = 0; i < n; i++) {
            boolean visible = false;
            for (int j = 0; j < n; j++) {
                if (ergonomicFloorPlan.hasLineOfSight(switches[i], fixtures[j], walls)) {
                    visible = true;
                    break;
                }
            }
            if (visible) {
                countVisible++;
            }
        }

        // Output the result
        if (countVisible == n) {
            System.out.println("Given floor plan is ergonomic.");
        } else {
            System.out.println("Given floor plan is not ergonomic. Only " + countVisible + " out of " + n + " light fixtures can have line of sight to switches.");
        }

        scanner.close();
    }
}
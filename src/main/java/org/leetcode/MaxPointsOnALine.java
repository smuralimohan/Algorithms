package org.leetcode;

import java.util.*;

/**
 * Created by murali on 04-05-2015.
 */
public class MaxPointsOnALine {

    public static void main(String[] args) {
        MaxPointsOnALine maxPointsOnALine = new MaxPointsOnALine();
        //System.out.println(maxPointsOnALine.maxPoints(new Point[] {new Point(2,2), new Point(0,0),new Point(2,2),new Point(1,1),new Point(0,0),new Point(1,1)}));
        System.out.println(maxPointsOnALine.maxPoints(new Point[] {new Point(84,250), new Point(0,0), new Point(1,0), new Point(0,-70), new Point(0,-70), new Point(1,-1), new Point(21,10), new Point(42,90), new Point(-42,-230)}));
    }

    public int maxPoints(Point[] points) {

        if (points.length <= 0) return 0;
        if (points.length == 1) return 1;

        List<Line> lines = new ArrayList<>();

        List<Point> pointList = new ArrayList<>();
        Map<String, Integer> replicateCount = new HashMap<>();

        Arrays.sort(points, new ComparePoints());

        int currX = points[0].x, currY = points[0].y;
        pointList.add(points[0]);

        for (int i = 1; i < points.length; i++) {
            if (points[i].x == currX && points[i].y == currY) {
                if (!replicateCount.containsKey(currX + ":" + currY)) {
                    replicateCount.put(currX + ":" + currY, 1);
                } else {
                    replicateCount.put(currX + ":" + currY, replicateCount.get(currX + ":" + currY) + 1);
                }
            } else {
                currX = points[i].x;
                currY = points[i].y;
                pointList.add(points[i]);
            }
        }

        for (int i = 0; i < pointList.size() - 1; i++) {
            for (int j = i + 1; j < pointList.size(); j++) {
                Line line = new Line();
                if (pointList.get(j).x - pointList.get(i).x == 0) {
                    line.slope = Float.MAX_VALUE;
                } else {
                    line.slope =  (pointList.get(j).y - pointList.get(i).y * 1.0) / (pointList.get(j).x - pointList.get(i).x);
                }
                if (pointList.get(j).x - pointList.get(i).x == 0) {
                    line.intercept = pointList.get(j).x;
                } else {
                    line.intercept = pointList.get(j).y - line.slope * pointList.get(j).x;
                }

                line.p1 = pointList.get(i);
                line.p2 = pointList.get(j);
                lines.add(line);
            }
        }

        if (lines.size() == 0) {
            return replicateCount.get(pointList.get(0).x + ":" + pointList.get(0).x) + 1;
        } else if (lines.size() == 1){
            Integer firstPointReplicateCount = replicateCount.get(lines.get(0).p1.x + ":" + lines.get(0).p1.y);
            Integer secondPointReplicateCount = replicateCount.get(lines.get(0).p2.x + ":" + lines.get(0).p2.y);
            return 2 + (firstPointReplicateCount == null ? 0 : firstPointReplicateCount.intValue()) + (secondPointReplicateCount == null ? 0 : secondPointReplicateCount.intValue());
        }

        Collections.sort(lines, new CompareLines());
        int optimum = 0;
        double currentSlope = lines.get(0).slope, currentIntercept = lines.get(0).intercept;
        Set<Point> pointSet = new HashSet<>();
        pointSet.add(lines.get(0).p1);
        pointSet.add(lines.get(0).p2);

        for (int i = 1; i < lines.size(); i++) {

            if (lines.get(i).slope == currentSlope && lines.get(i).intercept == currentIntercept) {
                pointSet.add(lines.get(i).p1);
                pointSet.add(lines.get(i).p2);
            } else {
                int pointCount = 0;
                for (Point point : pointSet) {
                    pointCount++;
                    Integer rCount = replicateCount.get(point.x + ":" + point.y);
                    pointCount = pointCount + (rCount == null ? 0 : rCount.intValue());
                }

                if (optimum < pointCount) {
                    optimum = pointCount;
                }
                pointSet.clear();
                pointSet.add(lines.get(i).p1);
                pointSet.add(lines.get(i).p2);
                currentIntercept = lines.get(i).intercept;
                currentSlope = lines.get(i).slope;
            }
        }
        int pointCount = 0;
        for (Point point : pointSet) {
            pointCount++;
            Integer rCount = replicateCount.get(point.x + ":" + point.y);
            pointCount = pointCount + (rCount == null ? 0 : rCount.intValue());
        }
        return Math.max(optimum,pointCount);
    }

    class CompareLines implements Comparator<Line> {
        public int compare(Line l1, Line l2) {
            int ret = ((Double) l1.slope).compareTo(l2.slope);
            if (ret == 0) {
                return ((Double) l1.intercept).compareTo(l2.intercept);
            } else {
                return ret;
            }
        }
    }

    class ComparePoints implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if (p1.x == p2.x) {
                return ((Integer) p1.y).compareTo(p2.y);
            }
            return ((Integer) p1.x).compareTo(p2.x);
        }
    }

    class Line {
        double slope;
        double intercept;
        Point p1;
        Point p2;
    }
}


class Point {
       int x;
        int y;
         Point() { x = 0; y = 0; }
         Point(int a, int b) { x = a; y = b; }
     }
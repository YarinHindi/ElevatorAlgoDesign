package ex0.algo;

import ex0.*;
import ex0.simulator.ElevetorCallList;

import java.util.ArrayList;
import java.util.Collections;


public class OurElevatorAlgo implements ElevatorAlgo {
    public static final int UP = 1, DOWN = -1;
    public CallListOnline Lists;
    private Building _building;


    public OurElevatorAlgo(Building b) {
        _building = b;
        Lists = new CallListOnline(_building.numberOfElevetors());

    }


    @Override
    public Building getBuilding() {
        return this._building;
    }

    @Override
    public String algoName() {
        return "Ex0_OOP_OPTIMAL_ELEVATOR_ALGO";
    }

    public int dest(int i, int j) {
        return Math.abs(i - j);
    }

    @Override

    public int allocateAnElevator(CallForElevator c) {
        int elevNum = this._building.numberOfElevetors();
        int ans = 0;
        int src = c.getSrc();
        int dest = c.getDest();
        if (elevNum > 1) {
            for (int i = 0; i < elevNum; i++) {
                Elevator curr = this.getBuilding().getElevetor(i);
                boolean isLevel = curr.getState() == 0;
                boolean isUp = curr.getState() == 1;
                boolean isDown = curr.getState() == -1;
                boolean onTheWayUp = curr.getPos() <= src;
                boolean onTheWayDown = curr.getPos() >= src;
                if (src < dest) { // UP
                    if(destination(curr.getPos() , c.getSrc()) < 3 && isUp && elevNum >= 10){
                        addSrcDest(Lists.elevRoute, i, src, dest);
                      //  Collections.sort(Lists.elevRoute[i]);
                        return i;
                    }
                    if (curr.getPos() == src && isLevel) {
                        addSrcDest(Lists.elevRoute, i, src, dest);
                       Collections.sort(Lists.elevRoute[i]);
                        return i;
                    }
                    if (dist(src, i,c.getDest()) < dist(src, ans,c.getDest())) {
                        if (isUp) {
                            if (onTheWayUp) {
                                ans = i;
                            }
                        } else if (isLevel) {
                            ans = i;
                        }
                    }
                } else {
                    if(destination(curr.getPos() , c.getSrc()) < 3 && isDown && elevNum >= 10){
                        addSrcDest(Lists.elevRoute, i, src, dest);
                        Collections.sort(Lists.elevRoute[i]);
                        return i;
                    }
                    if (curr.getPos() == src && isLevel) {
                        addSrcDest(Lists.elevRoute, i, src, dest);
                        //Collections.sort(Lists.elevRoute[i], Collections.reverseOrder());
                        return i;
                    }
                    if (dist(src, i,c.getDest()) < dist(src, ans,c.getDest())) {
                        if (isDown) {
                            if (onTheWayDown) {
                                ans = i;
                            }
                        } else if (isLevel) {
                            ans = i;
                        }
                    }
                }
            }
        }
        addSrcDest(Lists.elevRoute, ans, src, dest);
        return ans;
    }

    public void addSrcDest(ArrayList<Integer>[] call, int i, int src, int dest) {
        if (!call[i].contains(src)) {
            call[i].add(src);
        }
        if (!call[i].contains(dest)) {
            call[i].add(dest);
        }
    }

    public int destination(int a, int b) {
        int res = Math.abs(a - b);
        return res;
    }

    private double dist(int src, int elev, int dest) {
        double ans = -1;
        Elevator curr = this.getBuilding().getElevetor(elev);
        double speed = curr.getSpeed();
        double floorTime = speed + curr.getStopTime() + curr.getStartTime()
                + curr.getTimeForOpen() + curr.getTimeForClose();
        int floors = 0;
        if (Lists.elevRoute[elev].size() > 1) {
            floors = Math.abs(curr.getPos() - Lists.elevRoute[elev].get(0)) + Math.abs(src - dest);
        }
        for (int i = 0; i < Lists.elevRoute[elev].size() - 1; i++) {
            floors += Math.abs(Lists.elevRoute[elev].get(i) - Lists.elevRoute[elev].get(i + 1));
        }
        ans = floors / speed + floorTime * Lists.elevRoute[elev].size();
        return ans;
    }

    public void cmdElevator(int elev) {

        Elevator curr = this.getBuilding().getElevetor(elev);
        if (Lists.elevRoute[elev].size() > 0) {
            if (Lists.elevRoute[elev].get(0) == curr.getPos()) {
                Lists.elevRoute[elev].remove(0);
            }

        }
        if (Lists.elevRoute[elev].size() > 0) {
            curr.goTo(Lists.elevRoute[elev].get(0));
        }


    }

}





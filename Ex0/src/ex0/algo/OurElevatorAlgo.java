package ex0.algo;

import ex0.*;
import ex0.simulator.ElevetorCallList;
import java.util.ArrayList;


public class OurElevatorAlgo implements ElevatorAlgo {
    public static final int UP = 1, DOWN = -1;
    public CallListOnline Lists;
    private int _direction;
    private boolean[] _firstTime;
     public ArrayList<Integer> PlacesOf_Elevator;
    private Building _building;


    public OurElevatorAlgo(Building b) {
        _building = b;
        _direction = UP;
        PlacesOf_Elevator = new ArrayList<Integer>();
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

    @Override
    public int allocateAnElevator(CallForElevator c) {
        int ans = 0, elevNum = _building.numberOfElevetors();
        if (elevNum > 1) {
            for (int i = 1; i < elevNum; i++) {
                if (dist(c.getSrc(), i, c.getDest()) < dist(c.getSrc(), ans, c.getDest())) {
                    ans = i;
                }
            }
        }
        Lists.add(c,ans);
        return ans;
    }

    private double dist(int src, int elev, int dest) {
        Elevator thisElev = this._building.getElevetor(elev);
        int pos = thisElev.getPos();
        double speed = thisElev.getSpeed();
        double totalTime = 0;
        int floors = 0;
        if (Lists.ElevatorsGoTo[elev].size() > 0) {
            floors = Math.abs(Lists.ElevatorsGoTo[elev].get(0) - thisElev.getPos());
        }
        double floorTime = thisElev.getStopTime() + thisElev.getStartTime() + thisElev.getTimeForOpen() + thisElev.getTimeForClose();
        for (int i = 0; i < Lists.ElevatorsGoTo[elev].size(); i++) {
            if (i != Lists.ElevatorsGoTo[elev].size()-1) {
                floors += Math.abs(Lists.ElevatorsGoTo[elev].get(i+1) - Lists.ElevatorsGoTo[elev].get(i));
            }
            for (int j = 0; j < Lists.ElevatorsOnTheWay[elev].size(); j++) {
                totalTime += floorTime;
            }
            totalTime += floorTime;
        }
        totalTime += floors*speed;
        return totalTime;
    }


    public int getDirection() {
        return this._direction;
    }


    public void cmdElevator(int elev) {
       Elevator curr = this.getBuilding().getElevetor(elev);
        for (int i = 0; i < Lists.ElevatorsList[elev].size(); i++) {
            Lists.remove(Lists.ElevatorsList[elev].get(i), elev);
        }
        if (Lists.ElevatorsGoTo[elev].size() > 0) {
            if (curr.getPos() == Lists.ElevatorsGoTo[elev].get(0)) {
                Lists.ElevatorsGoTo[elev].remove(0);
            }
            if (Lists.ElevatorsOnTheWay[elev].size() > 0) {
                if (curr.getPos() == Lists.ElevatorsOnTheWay[elev].get(0)) {
                    Lists.ElevatorsOnTheWay[elev].remove(0);
                }
            }
        }
        for (int i = 0; i < Lists.ElevatorsGoTo[elev].size(); i++) {
            curr.goTo(Lists.ElevatorsGoTo[elev].get(i));
            for (int j = 0; j < Lists.ElevatorsOnTheWay[elev].size(); j++) {
                curr.stop(Lists.ElevatorsOnTheWay[elev].get(j));
            }
        }
        curr.goTo(0);
    }

        private static int rand ( int min, int max){
        if(max<min){
        throw new RuntimeException("ERR: wrong values for range max should be >= min");
        }
        int ans=min;
        double dx=max-min;
        double r=Math.random()*dx;
        ans=ans+(int)(r);
        return ans;
        }
    }





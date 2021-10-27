package ex0.algo;

import ex0.*;
import ex0.simulator.ElevetorCallList;
import java.util.ArrayList;


public class OurElevatorAlgo implements ElevatorAlgo {
    public static final int UP = 1, DOWN = -1;
    public CallListOnline Lists;
    private int _direction;
    private Building _building;


    public OurElevatorAlgo(Building b) {
        _building = b;
        _direction = UP;
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
                if (dist(i, c) < dist(i, c)) {
                    ans = i;
                }
            }
        }
        Lists.add(c, ans);
        return ans;
    }

    private double dist(int elev, CallForElevator call) {
        Lists.add(call, elev);
        Elevator thisElev = this._building.getElevetor(elev);
        int pos = thisElev.getPos();
        double speed = thisElev.getSpeed();
        double totalTime = 0;
        int floors = 0;
        double floorTime = thisElev.getStopTime() + thisElev.getStartTime() + thisElev.getTimeForOpen() + thisElev.getTimeForClose();
        //gonna chech all the cases for the call and the elevator state
        if(thisElev.getState()==1&&call.getType()==1&&thisElev.getPos()<call.getSrc()){
        for (int i = 0; i < Lists.Upcalls[elev].size(); i++) {
            if(thisElev.getPos()<=Lists.Upcalls[elev].get(i)){
                floors++;
            }
            }
            totalTime = (call.getDest()-thisElev.getPos())/speed + floorTime * floors;
        }
        else if(thisElev.getState()==-1&&call.getType()==-1&&thisElev.getPos()>call.getSrc()){
            for (int i = 0 ;i < Lists.Downcalls[elev].size(); i++) {
                if(thisElev.getPos()>=Lists.Downcalls[elev].get(i)){
                  floors++;
                }
            }
            totalTime = (call.getDest()-thisElev.getPos())/speed + floorTime * floors;
        }else if(thisElev.getState()==1&&call.getType()==1&&thisElev.getPos()>call.getSrc()){
            for (int i = 0; i <Lists.Upcalls[elev].size() ; i++) {
                if(thisElev.getPos()<=Lists.Upcalls[elev].get(i)){
                    floors++;
            }
        }
            for (int i = 0; i < ; i++) {

            }
        Lists.ElevatorsList[elev].remove(call);
        if (call.getType() == 1) {
            Lists.Upcalls[elev].remove(call.getSrc());
            Lists.Upcalls[elev].remove(call.getDest());
        } else {
            if (call.getType() == 1) {
                Lists.Downcalls[elev].remove(call.getSrc());
                Lists.Downcalls[elev].remove(call.getDest());
            } else {

            }

//
        }
        return totalTime;
    }



    public int getDirection() {
        return this._direction;
    }


    public void cmdElevator(int elev) {
        Elevator curr = this.getBuilding().getElevetor(elev);
        if (Lists.elevRoute[elev].size() > 0 && curr.getPos() == Lists.elevRoute[elev].get(0)) {
            Lists.elevRoute[elev].remove(0);
            for (int i = 0; i < Lists.ElevatorsList[elev].size(); i++) {
                Lists.remove(Lists.ElevatorsList[elev].get(i), elev);

            }

        }
        if (Lists.elevRoute[elev].size() > 0) {
            curr.goTo(Lists.elevRoute[elev].get(0));
            for (int i = 0; i <Lists.ElevatorsList[elev].size() ; i++) {
                if(curr.getState()==Elevator.UP&&Lists.ElevatorsList[elev].get(i).getState()==Elevator.UP&&curr.getPos()<Lists.ElevatorsList[elev].get(i).getSrc()){
                    curr.stop(Lists.ElevatorsList[elev].get(i).getSrc());
                    curr.goTo(Math.min(Lists.ElevatorsList[elev].get(i).getDest(),Lists.elevRoute[elev].get(0)));
                    curr.goTo(Math.max(Lists.ElevatorsList[elev].get(i).getDest(),Lists.elevRoute[elev].get(0)));


                }

        }

    }



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





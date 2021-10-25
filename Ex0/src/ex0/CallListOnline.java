package ex0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class  CallListOnline {
    public ArrayList<Integer>[] ElevatorsGoTo;
    public ArrayList<Integer>[] ElevatorsOnTheWay;
    public ArrayList<CallForElevator>[] ElevatorsList;

    public CallListOnline(int NumberOfElevators) {
        this.ElevatorsList = new ArrayList[NumberOfElevators];
        for (int i = 0; i < NumberOfElevators; i++) {
            this.ElevatorsList[i] = new ArrayList<CallForElevator>();
        }
        this.ElevatorsGoTo = new ArrayList[NumberOfElevators];
        for (int i = 0; i < NumberOfElevators; i++) {
            this.ElevatorsGoTo[i] = new ArrayList<Integer>();
        }
        this.ElevatorsOnTheWay = new ArrayList[NumberOfElevators];
        for (int i = 0; i < NumberOfElevators; i++) {
            this.ElevatorsOnTheWay[i] = new ArrayList<Integer>();
        }
    }

    public  void add(CallForElevator callForElevator,int elev) {
        this.ElevatorsList[elev].add(callForElevator);
        int curr = ElevatorsList[elev].size()-1;
        //for (int i = 1; i < this.ElevatorsList[elev].size(); i++) {
            if (this.ElevatorsList[elev].get(curr).getType() == this.ElevatorsList[elev].get(0).getType()) {
                if (this.ElevatorsList[elev].get(curr).getSrc() < this.ElevatorsList[elev].get(0).getDest()) {
                    this.ElevatorsOnTheWay[elev].add(this.ElevatorsList[elev].get(curr).getSrc());
                    if (this.ElevatorsList[elev].get(curr).getDest() < this.ElevatorsList[elev].get(0).getDest()) {
                        this.ElevatorsOnTheWay[elev].add(this.ElevatorsList[elev].get(curr).getDest());
                    }
                    else {
                        this.ElevatorsGoTo[elev].add(this.ElevatorsList[elev].get(curr).getDest());
                    }
                }
                else {
                    this.ElevatorsGoTo[elev].add(this.ElevatorsList[elev].get(curr).getSrc());
                    this.ElevatorsGoTo[elev].add(this.ElevatorsList[elev].get(curr).getDest());
                }
            }
            else {
                this.ElevatorsGoTo[elev].add(this.ElevatorsList[elev].get(curr).getSrc());
                this.ElevatorsGoTo[elev].add(this.ElevatorsList[elev].get(curr).getDest());
            }
        //}
    }
    public void remove (CallForElevator callForElevator,int elev){
        if(callForElevator.getState()==3){
            this.ElevatorsList[elev].remove(callForElevator);
        }
    }
}





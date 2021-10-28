package ex0;

import java.util.*;

public class  CallListOnline {

    public ArrayList<Integer>[] elevRoute;
    public ArrayList<Integer>[] Upcalls;
    public ArrayList<Integer>[] Downcalls;
    public ArrayList<CallForElevator>[] ElevatorsList;

    public CallListOnline(int NumberOfElevators) {
        this.ElevatorsList = new ArrayList[NumberOfElevators];
        for (int i = 0; i < NumberOfElevators; i++) {
            this.ElevatorsList[i] = new ArrayList<CallForElevator>();
        }

        this.elevRoute = new ArrayList[NumberOfElevators];
        for (int i = 0; i < NumberOfElevators; i++) {
            this.elevRoute[i] = new ArrayList<Integer>();

        }
    }

        public void add (CallForElevator callForElevator,int elev) {


            this.ElevatorsList[elev].add(callForElevator);
            if (!elevRoute[elev].contains(callForElevator.getSrc())) {
                this.elevRoute[elev].add(callForElevator.getSrc());
            }
            if (!elevRoute[elev].contains(callForElevator.getDest())) {
                this.elevRoute[elev].add(callForElevator.getDest());


            }
        }

        public void remove (CallForElevator callForElevator,int elev){
            if (callForElevator.getState() == 3) {
                this.ElevatorsList[elev].remove(callForElevator);
            }

        }
    }






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
        this.Upcalls = new ArrayList[NumberOfElevators];
        for (int i = 0; i < NumberOfElevators; i++) {
            this.Upcalls[i] = new ArrayList<Integer>();
        }
        this.Downcalls = new ArrayList[NumberOfElevators];
        for (int i = 0; i < NumberOfElevators; i++) {
            this.Downcalls[i] = new ArrayList<Integer>();
        }


    }


    public  void add(CallForElevator callForElevator,int elev) {
        if(callForElevator.getType()==1) {
            this.Upcalls[elev].add(callForElevator.getSrc());
            this.Upcalls[elev].add(callForElevator.getDest());
            Collections.sort(Upcalls[elev]);
        }else{
            this.Downcalls[elev].add(callForElevator.getSrc());
            this.Downcalls[elev].add(callForElevator.getDest());
            Collections.sort(this.Downcalls[elev], Collections.reverseOrder());
        }
        this.ElevatorsList[elev].add(callForElevator);

    }

    public void remove (CallForElevator callForElevator,int elev){
        if(callForElevator.getState()==3){
            this.ElevatorsList[elev].remove(callForElevator);
        }
    }
}






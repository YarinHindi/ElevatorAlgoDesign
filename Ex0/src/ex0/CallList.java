package ex0;

import java.util.PriorityQueue;

public class CallList {
    public PriorityQueue<Integer> Upcalls;
    public PriorityQueue<Integer> Downcalls;

    public CallList (){
        this.Upcalls = new PriorityQueue<Integer>();
        this.Downcalls = new PriorityQueue<Integer>();
    }
    public void add(CallForElevator call){
        if(call.getState()==3){
            return ;
        }else{
            if(call.getType()==1){
                Upcalls.add(call.getSrc());
            }else if(call.getType()==-1){
                Downcalls.add(call.getSrc());
            }
        }
    }
    public void remove (CallForElevator call){
        if(call.getType()==1){
            Upcalls.remove(call.getSrc());
        }else if(call.getType()==-1){
            Downcalls.remove(call.getSrc());

        }
    }

    }


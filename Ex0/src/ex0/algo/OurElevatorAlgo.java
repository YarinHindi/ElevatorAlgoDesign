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
                if (dist(c, i) < dist(c, ans)) {
                    ans = i;
                }
            }
        }
        Lists.add(c, ans);
        return ans;
    }





private double dist(CallForElevator c, int elev) {
    Elevator thisElev = this._building.getElevetor(elev);
    //Lists.add(c, thisElev, elev);
    int pos = thisElev.getPos();
    double speed = thisElev.getSpeed();
    double stopTime = 0;
    int floors = 0, upStops = Lists.Upcalls[elev].size(), downStops = Lists.Downcalls[elev].size();
    boolean srcReachedCheck = false, destReachedCheck = false, finished = false;
    if (Lists.ElevatorsList[elev].size() > 0) {
        if (Lists.ElevatorsList[elev].get(0).getType() == 1) {
            if (Lists.Upcalls[elev].size() > 0) {
                floors += Math.abs(Lists.Upcalls[elev].get(0) - thisElev.getPos());
                for (int i = 0; i < Lists.Upcalls[elev].size(); i++) {
                    if (i != Lists.Upcalls[elev].size()-1) {
                        floors += Lists.Upcalls[elev].get(i+1) - Lists.Upcalls[elev].get(i);
                    }
                    stopTime += thisElev.getStopTime() + thisElev.getStartTime() + thisElev.getTimeForOpen() + thisElev.getTimeForClose();
                    if (c.getType() == 1) {
                        if (c.getSrc() < Lists.Upcalls[elev].get(i)) {
                            srcReachedCheck = true;
                        }
                        if (c.getDest() < Lists.Upcalls[elev].get(i)) {
                            destReachedCheck = true;
                        }
                        if (srcReachedCheck && destReachedCheck) {
                            i = Lists.Upcalls[elev].size();
                            finished = true;
                        }
                    }
                }
                if (!finished) {
                    if (Lists.Downcalls[elev].size() > 0) {
                        floors += Math.abs(Lists.Downcalls[elev].get(0) - Lists.Upcalls[elev].get(Lists.Upcalls[elev].size()-1));
                        for (int i = 0; i < Lists.Downcalls[elev].size(); i++) {
                            if (i != Lists.Downcalls[elev].size()-1) {
                                floors += Lists.Downcalls[elev].get(i+1) - Lists.Downcalls[elev].get(i);
                            }
                            stopTime += thisElev.getStopTime() + thisElev.getStartTime() + thisElev.getTimeForOpen() + thisElev.getTimeForClose();
                            if (c.getType() == -1) {
                                if (c.getSrc() > Lists.Downcalls[elev].get(i)) {
                                    srcReachedCheck = true;
                                }
                                if (c.getDest() > Lists.Downcalls[elev].get(i)) {
                                    destReachedCheck = true;
                                }
                                if (srcReachedCheck && destReachedCheck) {
                                    i = Lists.Downcalls[elev].size();
                                    finished = true;
                                }
                            }
                        }
                    }

                }
            }
        }
        else {
            if (Lists.Downcalls[elev].size() > 0) {
                floors += Math.abs(Lists.Downcalls[elev].get(0) - thisElev.getPos());
                for (int i = 0; i < Lists.Downcalls[elev].size(); i++) {
                    if (i != Lists.Downcalls[elev].size()-1) {
                        floors += Lists.Downcalls[elev].get(i+1) - Lists.Downcalls[elev].get(i);
                    }
                    stopTime += thisElev.getStopTime() + thisElev.getStartTime() + thisElev.getTimeForOpen() + thisElev.getTimeForClose();
                    if (c.getType() == -1) {
                        if (c.getSrc() > Lists.Downcalls[elev].get(i)) {
                            srcReachedCheck = true;
                        }
                        if (c.getDest() > Lists.Downcalls[elev].get(i)) {
                            destReachedCheck = true;
                        }
                        if (srcReachedCheck && destReachedCheck) {
                            i = Lists.Downcalls[elev].size();
                            finished = true;
                        }
                    }
                }
                if (!finished) {
                    if (Lists.Upcalls[elev].size() > 0) {
                        floors += Math.abs(Lists.Downcalls[elev].get(0) - Lists.Upcalls[elev].get(Lists.Upcalls[elev].size()-1));
                        for (int i = 0; i < Lists.Upcalls[elev].size(); i++) {
                            if (i != Lists.Upcalls[elev].size()-1) {
                                floors += Lists.Upcalls[elev].get(i+1) - Lists.Upcalls[elev].get(i);
                            }
                            stopTime += thisElev.getStopTime() + thisElev.getStartTime() + thisElev.getTimeForOpen() + thisElev.getTimeForClose();
                            if (c.getType() == 1) {
                                if (c.getSrc() < Lists.Upcalls[elev].get(i)) {
                                    srcReachedCheck = true;
                                }
                                if (c.getDest() < Lists.Upcalls[elev].get(i)) {
                                    destReachedCheck = true;
                                }
                                if (srcReachedCheck && destReachedCheck) {
                                    i = Lists.Upcalls[elev].size();
                                    finished = true;
                                }
                            }
                        }
                    }

                }
            }
        }
    }
    double totalTime = floors/speed + stopTime;
    return totalTime;
}
        public int getDirection () {
            return this._direction;
        }


public void cmdElevator(int elev) {
    Elevator curr = this.getBuilding().getElevetor(elev);
         if(Lists.Downcalls[elev].size()>0) {
            if (curr.getPos() == Lists.Downcalls[elev].get(0)) {
                Lists.Downcalls[elev].remove(0);
            }
        }
        if(Lists.Upcalls[elev].size()>0) {
            if (curr.getPos() == Lists.Upcalls[elev].get(0)) {
                Lists.Upcalls[elev].remove(0);
            }
        }
        if(Lists.Downcalls[elev].size()>0){
            curr.goTo(Lists.Downcalls[elev].get(0));
        }else{
            if(Lists.Upcalls[elev].size()>0) {
                curr.goTo(Lists.Upcalls[elev].get(0));
            }
        }
}

        private static int rand ( int min, int max){
            if (max < min) {
                throw new RuntimeException("ERR: wrong values for range max should be >= min");
            }
            int ans = min;
            double dx = max - min;
            double r = Math.random() * dx;
            ans = ans + (int) (r);
            return ans;
        }
    }





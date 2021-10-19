package ex0.algo;

import ex0.Building;
import ex0.CallForElevator;
import ex0.Elevator;

import java.util.ArrayList;

public class OurElevatorAlgo implements ElevatorAlgo {
        public static final int UP=1, DOWN=-1;
        public ArrayList<CallForElevator> requsts;
        private int _direction;
        public  int[] PlacesOf_Elevator;
        private Building _building;
        public OurElevatorAlgo(Building b) {
            _building = b;
            this.requsts=new ArrayList<CallForElevator>();
            _direction = UP;

            PlacesOf_Elevator = new int[_building.maxFloor() - _building.minFloor() + 1];
            int FloorArea = (_building.maxFloor() - _building.minFloor()) / _building.numberOfElevetors();
            int restdiv = _building.maxFloor() - _building.minFloor() % _building.numberOfElevetors();
            int i = _building.minFloor() + FloorArea;
            int counter = 0;
            int helper = 0;
            for (int k = 0; k < _building.maxFloor() - _building.minFloor(); k++) {
                if (restdiv == 0) {
                    if (helper > FloorArea) {
                        counter++;
                        PlacesOf_Elevator[k] = counter;
                        helper = 0;

                    } else {
                        PlacesOf_Elevator[k] = counter;
                        helper++;
                    }
                } else {
                    if (helper > FloorArea + 1) {
                        counter++;
                        PlacesOf_Elevator[k] = counter;
                        helper = 0;
                        restdiv--;

                    } else {
                        PlacesOf_Elevator[k] = counter;
                        helper++;
                    }

                }


            }

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
        if(_building.minFloor()<0){
            int ans = c.getSrc()-_building.minFloor();
            return PlacesOf_Elevator[ans];
        }
        else if(_building.minFloor()==0) {
            return PlacesOf_Elevator[c.getSrc()];
        }else{
            int ans = c.getSrc()+_building.minFloor();
            return PlacesOf_Elevator[ans];
        }
    }

    @Override
    public void cmdElevator(int elev) {
        Elevator curr = this.getBuilding().getElevetor(elev);


    }
}

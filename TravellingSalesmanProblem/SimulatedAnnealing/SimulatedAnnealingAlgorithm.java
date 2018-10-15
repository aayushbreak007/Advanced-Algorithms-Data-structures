package TravellingSalesmanProblem.SimulatedAnnealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulatedAnnealingAlgorithm {
    //this is NP-HARD problem
    private SingleTour best;
    public void simulation(){
        double temperature=10000;
        double coolingRate=0.003;

        SingleTour currentSolution=new SingleTour();
        currentSolution.generateIndividual();

        System.out.println("Initial solution distance: "+currentSolution.getDistance());

        best=new SingleTour(currentSolution.getTour());
        while(temperature>1){
            SingleTour newSolution=new SingleTour(currentSolution.getTour());
            //simulated annealing
            //pick 2 cities at random and swap them for different solution---could be a better solution as well
            int randomIndex1=(int)(newSolution.getTourSize()*Math.random());
            City city1=newSolution.getCity(randomIndex1);

            int randomIndex2=(int)(newSolution.getTourSize()*Math.random());
            City city2=newSolution.getCity(randomIndex2);

            //swap 2 cities
            newSolution.setCity(randomIndex2,city1);
            newSolution.setCity(randomIndex1,city2);

            double currentEnergy=currentSolution.getDistance();
            double neighbourEnergy=newSolution.getDistance();

            if(acceptanceProbability(currentEnergy,neighbourEnergy,temperature)>Math.random()){
                currentSolution=new SingleTour(newSolution.getTour());
            }
            if(currentSolution.getDistance()<best.getDistance()){
                best=new SingleTour(currentSolution.getTour());
            }
            temperature*=1-coolingRate;
        }


    }

    private double acceptanceProbability(double currentEnergy, double neighbourEnergy, double temperature) {
        if(neighbourEnergy<currentEnergy){
            return 1;//accept it
        }
        return Math.exp((currentEnergy-neighbourEnergy)/temperature);
    }

    public SingleTour getBest() {
        return best;
    }

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            City city = new City();
            Repository.addCity(city);
        }

        SimulatedAnnealingAlgorithm simulatedAnnealing = new SimulatedAnnealingAlgorithm();
        simulatedAnnealing.simulation();

        System.out.println("Final solution distance: " + simulatedAnnealing.getBest().getDistance());
        System.out.println("Tour: " + simulatedAnnealing.getBest()  );

    }


}
class SingleTour{

    //will generate a single tour at rondom

    private List<City> tour=new ArrayList<>();
    private double distance=0;

    public SingleTour() {
        for(int i=0;i<Repository.numOfCities();i++){
            tour.add(null);//to avoid null pointer exception
        }
    }

    public SingleTour(List<City> tour) {
        List<City> currentTour=new ArrayList<>();

        for(int i=0;i<tour.size();i++){
            currentTour.add(null);
        }

        for (int i=0;i<tour.size();i++){
            currentTour.set(i,tour.get(i));
        }
        this.tour=currentTour;
    }

    public List<City> getTour() {
        return tour;
    }

    //helper
    public void generateIndividual(){
        for(int cityIndex=0;cityIndex<Repository.numOfCities();cityIndex++){
            setCity(cityIndex,Repository.getCity(cityIndex));

        }
        //shuffle tours at random
        Collections.shuffle(tour);
    }

    public void setCity(int cityIndex, City city) {
        this.tour.set(cityIndex,city);
        distance=0;
    }

    //helper 2
    public City getCity(int tourPosition){
        return tour.get(tourPosition);
    }

    public int getTourSize(){
        return this.tour.size();
    }

    //helper 3
    public double getDistance(){
        if(distance==0){

            int tourDistance=0;
            for(int cityIndex=0;cityIndex<getTourSize();cityIndex++){
                City fromCity=getCity(cityIndex);
                City destinationCity;

                if(cityIndex+1<getTourSize()){
                    destinationCity=getCity(cityIndex+1);
                }else{
                    destinationCity=getCity(0);
                }
                tourDistance+=fromCity.distanceTo(destinationCity);
            }
            this.distance=tourDistance;
        }
        return  this.distance;
    }

    @Override
    public String toString() {

        //use string builder
        String s="";
        for(int i=0;i<getTourSize();i++){
            s+=getCity(i)+" -> ";
        }
        return s;
    }
}



class City{
    private int x;
    private int y;

    public City() {
        this.x=(int)(Math.random()*100);//generate random number in range 0 to 100
        this.y=(int)(Math.random()*100);

    }

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //helper
    //we do not always need the minimum distance
    public double distanceTo(City otherCity){
        //apply pythagoras theorem
        int xDistance=Math.abs(getX()-otherCity.getX());
        int yDistance=Math.abs(getY()-otherCity.getY());

        //euclidian distance
        double distance=Math.sqrt((xDistance*xDistance)+(yDistance*yDistance));
        return distance;
    }

    @Override
    public String toString() {
        return this.x+"-"+this.y;
    }
}

class Repository{
    //store list of cities
    private static List<City> cities=new ArrayList<>();
    public static void addCity(City city){
        cities.add(city);
    }
    public static City getCity(int index){
        return cities.get(index);
    }
    public static int numOfCities(){
        return cities.size();
    }
}
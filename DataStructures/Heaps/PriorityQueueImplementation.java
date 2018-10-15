package DataStructures.Heaps;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueImplementation {

    public static void main(String[] args){

        //IMPLEMENTING MIN HEAP
        Queue<Person> queue=new PriorityQueue<>();
        queue.add(new Person("Kevin",22));
        queue.add(new Person("Joe",37));
        queue.add(new Person("Adam",12));
        queue.add(new Person("Anna",45));

        while(queue.peek()!=null){
            System.out.println(queue.poll());
        }
    }
}
class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(){

    }

    public Person(String name,int age){
        this.age=age;
        this.name=name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person: "+this.name+"-"+this.age;
    }

    @Override
    public int compareTo(Person otherPerson) {

        //MIN HEAP
        return -1*Integer.compare(this.age,otherPerson.getAge());
    }
}

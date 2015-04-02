/**
 * Created by danielseetoh on 3/31/2015.
 */
public class CourseComponent {

    private String componentName;
    private double weight;

    public CourseComponent(String componentName){
        this.componentName = componentName;
    }

    public String getComponentName(){
        return componentName;
    }

    public double getWeight(){
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

}

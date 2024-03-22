import java.util.Arrays;
import java.util.Objects;

/**
 * description
 *
 * @author Alexander Isai on 20.03.2024.
 */
public class Family {

    static {
        System.out.println("Загрузился новый класс Family");
    }

    {
        System.out.println("Новая Family создана");
    }

    private Human mother;
    private Human father;
    private Human[] children;
    private Pet pet;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new Human[0];
        this.mother.setFamily(this);
        this.father.setFamily(this);
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
        this.children = children;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void addChild(Human child){
        children = Arrays.copyOf(children, children.length + 1);
        children[children.length - 1] = child;
        child.setFamily(this);
    }

    public boolean deleteChild(int index){
        if (index < 0 || index >= children.length){
            return false;
        }
        Human[] newChildren = new Human[children.length - 1];
        int j = 0;

        for (int i = 0; i < children.length; i++) {
            if (i == index){
                children[i].setFamily(null);
            }
            else {
                newChildren[j] = children[i];
                j++;
            }
        }
        children = newChildren;
        return true;
    }

    public boolean deleteChild(Human child){
        boolean isChildDelete = false;
        if (children.length < 1){
            return false;
        }
        for (int i = 0; i < children.length; i++) {
            //if (human.hashCode() == child.hashCode()) {
            if (children[i].equals(child)) {
                deleteChild(i);
                isChildDelete = true;
            }
        }
        return isChildDelete;
    }


    public int countFamily(){
        return children.length + 2;
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + Arrays.toString(children) +
                ", pet=" + pet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && Arrays.equals(children, family.children) && Objects.equals(pet, family.pet);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(mother, father, pet);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }
}

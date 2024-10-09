/*this program was made to shgowcase complient code Rule Code:  msc01-j    Name:  do not use empty infinite loops   */

public class NoEmptyLoops {

    public static void main(String[] args) {
        int current = 1;
        int target = 5;
        while (target > current) { // this loop will not be infinte since it is doing a comparison every iteration
            current += 1; // Something is being done in this program every loop iteration
            System.out.println("currently :" + current);
        }
    }
}

/*this program was made to shgowcase complient code Rule Code:  msc01-j    Name:  do not use empty infinite loops   */

public class NoEmptyLoops {

    public static void main(String[] args) {
        int current = 1;
        int target = 5;
        while (target > current) {
            current += 1;
            System.out.println("currently :" + current);
        }
    }
}

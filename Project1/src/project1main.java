import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class project1main {
    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<House>> houseSemesters = new ArrayList<>();
        ArrayList<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(args[0]));
        PrintStream output = new PrintStream(args[1]);
        /**
         *creates current moments and future semesters
         */
        for (int i = 0; i < 9; i++) {
            houseSemesters.add(new ArrayList<>());
        }
        ArrayList<String> inputLines = new ArrayList<>();
        /**
         * takes input
         */
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            inputLines.add(line);
        }
        for (String string : inputLines) {
            String[] returnedArray = string.split(" ");
            if (returnedArray[0].equals("h")) {
                int id = Integer.parseInt(returnedArray[1]);
                int duration = Integer.parseInt(returnedArray[2]);
                double rating = Double.parseDouble(returnedArray[3]);
                if (duration <= 8) {
                    houseSemesters.get(duration).add(new House(id, duration, rating));
                }
            } else if (returnedArray[0].equals("s")) {
                int id = Integer.parseInt(returnedArray[1]);
                String name = returnedArray[2];
                int duration = Integer.parseInt(returnedArray[3]);
                double rating = Double.parseDouble(returnedArray[4]);
                studentList.add(new Student(id, name, duration, rating));
            }
        }
        /**
         * sorts semesters
         */
        for(ArrayList<House> semester:houseSemesters){
            Collections.sort(semester);
        }
        Collections.sort(studentList);
        /**
         * checks whether any student can locate at a house at the first moment
         */
        for(House house: houseSemesters.get(0)){
            int sameDuration = house.getDuration();
            for(Student student :studentList){
                if(student.getRating()<=house.getRating() && house.getDuration()==0 && student.getDuration()>0){

                    house.setDuration(student.getDuration());
                    if((student.getDuration())<=8){
                        houseSemesters.get(student.getDuration()).add(house);
                    }
                    studentList.remove(student);
                    break;
                }
            }
            if(sameDuration == house.getDuration()){
                houseSemesters.get(1).add(house);
            }
        }
        /**
         * checks whether a student can locate at a house each semester
         */
        for(int i =1; i<9;i++){
            for(Student student:studentList){
                student.setDuration(student.getDuration()-1);
            }
            for(ArrayList<House> semester:houseSemesters){
                Collections.sort(semester);
                for(House house:semester){
                    if(house.getDuration()>=1) {
                        house.setDuration(house.getDuration() - 1);
                    }
                }
            }
            for(House house:houseSemesters.get(i)){
                int sameSemester = house.getDuration();
                for(Student student :studentList){
                    if(student.getRating()<=house.getRating() && house.getDuration()==0 && student.getDuration()>0){
                        System.out.println("Semester "+i+" "+house.getID()+" "+house.getRating()+" "+
                                house.getDuration()+"-->"+student.getName()+" "+ student.getID()+" "+student.getRating()+" "+student.getDuration());

                        house.setDuration(student.getDuration());
                        if((i+student.getDuration())<=8){
                            houseSemesters.get(i+student.getDuration()).add(house);
                        }
                        studentList.remove(student);
                        break;
                    }
                }
                if(sameSemester ==house.getDuration()&& i!=8){
                    houseSemesters.get(i+1).add(house);
                }
            }
        }
        for(Student student:studentList){
            output.println(student.getName());
        }
        output.close();
    }
}
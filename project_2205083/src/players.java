import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class players {

    List<player> list = new ArrayList<>();

    public void add(player p) {
        list.add(p);
    }

    public void add_to_file(int new_palyers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\\\Users\\\\soura\\\\OneDrive\\\\Desktop\\\\project\\\\project_2205083\\\\src\\\\players.txt", true))) {
            for (int i = list.size() - 1; i >= list.size() - new_palyers; i--) {
                String line = list.get(i).getName() + "," + list.get(i).getCountry() + ","
                        + String.valueOf(list.get(i).getAge()) + "," + String.valueOf(list.get(i).getHeight()) + ","
                        + list.get(i).getClub() + "," + list.get(i).getPosition()+ "," + String.valueOf(list.get(i).getNumber()) + ","
                        + String.valueOf(list.get(i).getSalary());
                writer.newLine();
                writer.write(line);
            }
            System.out.println("Strings have been added to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeall() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\\\Users\\\\soura\\\\OneDrive\\\\Desktop\\\\project\\\\project_2205083\\\\src\\\\players.txt"))) {
            for (int i = 0; i < list.size(); i++) {
                String line = list.get(i).getName() + "," + list.get(i).getCountry() + ","
                        + String.valueOf(list.get(i).getAge()) + "," + String.valueOf(list.get(i).getHeight()) + ","
                        + list.get(i).getClub() + "," + list.get(i).getPosition() + "," + String.valueOf(list.get(i).getNumber()) + ","
                        + String.valueOf(list.get(i).getSalary());
        
                writer.write(line); // Write the content of the line first
                if (i < list.size() - 1) { // Add a new line only if it's not the last line
                    writer.newLine();
                }
            }
            System.out.println("Strings have been added to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }


    public void setClub (String name, String club)
    {
        for (player p: list)
        {
            if (p.getName().equals(name))
            {
                p.setClub(club);
                
            }
        }
    }








    // option-1

    public ArrayList<player> search_name(String name) {
        name = name.toLowerCase();
        ArrayList<player> toreturn = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String candidate = list.get(i).getName().toLowerCase();
            if (name.equals(candidate)) {
                toreturn.add(list.get(i));    
            }

        }
        
    //    System.out.println("No such player with this name!");
    return toreturn;
    }

    public ArrayList<player> search_club_name(String name) {
        name = name.toLowerCase();
        ArrayList<player> toreturn = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String candidate = list.get(i).getClub().toLowerCase();
            
            if (name.equals(candidate)) {
                toreturn.add(list.get(i));    
            }

        }
        
    //    System.out.println("No such player with this name!");
    
    return toreturn;
    }

    public String club_name(String name)
    {
        for (player p: list)
        {
            if (p.getName().equals(name)) return p.getClub();
        }
        return "N/A";
    }

    public int search_name_number(String name) {
        name = name.toLowerCase();
        int n = 0;
        for (int i = 0; i < list.size(); i++) {
            String candidate = list.get(i).getName().toLowerCase();
            if (name.equals(candidate)) {
                n++;
            }
        }
        return n;
        
    }

    public ArrayList<player> search_club_country(String country, String club) {
        country = country.toLowerCase();
        club = club.toLowerCase();
        ArrayList<player> toreturn = new ArrayList<>();
        if (club.equals("any")) {
            // int found = 0;
            for (int i = 0; i < list.size(); i++) {
                String candidate = list.get(i).getCountry().toLowerCase();
                if (country.equals(candidate)) {
                    toreturn.add(list.get(i));
                //    found++;
                }

            }
            // if (found == 0)  System.out.println("No such player with this country!");
        } else {
            // int found = 0;
            for (int i = 0; i < list.size(); i++) {
                String candidate = list.get(i).getCountry().toLowerCase();
                String candidate2 = list.get(i).getClub().toLowerCase();
                if (country.equals(candidate) && club.equals(candidate2)) {
                    toreturn.add(list.get(i));
                //    found++;
                }

            }
            // if (found == 0)  System.out.println("No such player with this country and club!");

        }
        return toreturn;

    }

    public ArrayList<player> search_position(String position) {
        position = position.toLowerCase();
        // int found = 0;
        ArrayList<player> toreturn = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String candidate = list.get(i).getPosition().toLowerCase();
            if (position.equals(candidate)) {
                toreturn.add(list.get(i));
            //    found++;
            }

        }
        return toreturn;
        // if (found == 0)  System.out.println("No such player with this position!");
    }

    public ArrayList<player>search_salary(int low, int high) {

        // int found = 0;
        ArrayList<player> toreturn = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int candidate = list.get(i).getSalary();
            if (candidate >= low && candidate <= high) {
                toreturn.add(list.get(i));
            //    found++;
            }

        }
        // if (found == 0) System.out.println("No such player with this weekly salary range!");
        return toreturn;
    }

    public ArrayList<country> search_country() {
        HashMap<String, Integer> m = new HashMap<>();
        for (player p : list) {
            String country = p.getCountry();
            /* m.putIfAbsent(country, 0);
            m.get(country)++; */
            m.put(country, m.getOrDefault(country, 0) + 1);
        }
        ArrayList<country> toreturn = new ArrayList<>();


         for (var entry : m.entrySet()) {

            country c = new country(entry.getKey(),entry.getValue());
            toreturn.add(c);

        } 

        return toreturn;

    }

    // option-2

    public ArrayList<player> search_maximum_salary_club(String club) {
        club = club.toLowerCase();
        int highest = 0;
        // int found = 0;
        ArrayList<player>toreturn = new ArrayList<>();
        for (player p : list) {
            if (club.equals(p.getClub().toLowerCase())) {
                highest = Math.max(highest, p.getSalary());
            //    found++;
            }
        }
        for (player p : list) {
            if (club.equals(p.getClub().toLowerCase())) {
                if (highest == p.getSalary())
                    toreturn.add(p);
            }
        }
        //if (found == 0)   System.out.println("No such club with this name!");
        return toreturn;

    }

    public ArrayList<player> search_maximum_age_club(String club) {
        club = club.toLowerCase();
        int highest = 0;
        //int found = 0;
        ArrayList<player> toreturn = new ArrayList<>();
        for (player p : list) {
            if (club.equals(p.getClub().toLowerCase())) {
                highest = Math.max(highest, p.getAge());
            //    found++;
            }
        }
        for (player p : list) {
            if (club.equals(p.getClub().toLowerCase())) {
                if (highest == p.getAge())
                    toreturn.add(p);
            }
        }
        // if (found == 0)   System.out.println("No such club with this name!");
        return toreturn;
    }

    public ArrayList<player> search_maximum_height_club(String club) {
        club = club.toLowerCase();
        double highest = 0;
        //int found = 0;
        ArrayList<player> toreturn = new ArrayList<>();
        for (player p : list) {
            if (club.equals(p.getClub().toLowerCase())) {
                highest = Math.max(highest, p.getHeight());
            //    found++;
            }
        }
        for (player p : list) {
            if (club.equals(p.getClub().toLowerCase())) {
                if (highest == p.getHeight())
                    toreturn.add(p);
            }
        }
        // if (found == 0)   System.out.println("No such club with this name!");
        return toreturn;

    }

    public int search_salary_club(String club) {
        club = club.toLowerCase();
        int total = 0;
        int found = 0;

        for (player p : list) {
            if (club.equals(p.getClub().toLowerCase())) {
                total += p.getSalary();
                found++;
            }
        }
        if (found != 0) return total;
        //    System.out.println("The total salary of this club is " + total);
        return -1;
        //    System.out.println("No such club with this name!");

    }

}

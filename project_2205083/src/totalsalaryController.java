
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.BufferedReader; 
import java.io.FileReader;     
import java.io.IOException;

public class totalsalaryController {

    
     @FXML
    private TextField playerNameField;

    @FXML
    private TextField result;

    @FXML
    private Button searchButton;
    

    @FXML
    public void initialize() {
        // Set up the table columns with the Player class's properties
        
        // Attach the observable list to the table view
        
    }

    @FXML
    private void handleSearch() {
        String playerName = playerNameField.getText();
        if (!playerName.isEmpty()) {
            
            // Call the search_name function from the Players class
          
        
        String filename = "C:\\\\Users\\\\soura\\\\OneDrive\\\\Desktop\\\\project\\\\project_2205083\\\\src\\\\players.txt";
        players database = new players();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");  
                String name = data[0];
                String country = data[1];
                int age = Integer.parseInt(data[2]);
                double height = Double.parseDouble(data[3]);
                String club = data[4];
                String position = data[5];
                int number = data[6].isEmpty() ? -1 : Integer.parseInt(data[6]);
                int weeklySalary = Integer.parseInt(data[7]);

                player p = new player(name, country, age, height, club, position, number, weeklySalary);
                database.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

            int ans = database.search_salary_club(playerName);

            // Clear the existing data in the table
            result.clear();

            // Add the new data to the observable list
            String output;
            if (ans==-1) {output = "There is no club with the name '" + playerName + "'";}
            else {output = "The total yearly salary of the club '" + playerName + "' is "+ String.valueOf(ans);}
            
            result.setText(output);
        }
    }
}

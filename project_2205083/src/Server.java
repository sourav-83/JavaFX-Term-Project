import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;



public class Server implements Runnable {
    private ServerSocket server;
    private HashMap<String, String> map;
    private players dataBase;
    private ArrayList<player> tosell;

    Server() {
        map = new HashMap<>();
        tosell = new ArrayList<player>();
        dataBase = new players();
        String filename = "C:\\Users\\soura\\OneDrive\\Desktop\\project\\project_2205083\\src\\players.txt";

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
                dataBase.add(p);
            }
        } catch (IOException e) {
            System.out.println("aaa");
            System.out.println("Error reading from file: " + e.getMessage());
        }

        try {
            server = new ServerSocket(2083);
            System.out.println("Server waiting...");
        } catch (Exception e) {
            System.out.println("bbb");
            e.printStackTrace();
        }
        loadCredentials();
        Thread t1 = new Thread(this);
        t1.start();

    }

    private void loadCredentials() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("C:\\Users\\soura\\OneDrive\\Desktop\\project\\project_2205083\\src\\secret.txt"))) {
            String line = br.readLine();
            while (line != null && !line.isEmpty()) {
                String[] credentials = line.split(",");
                map.put(credentials[0], credentials[1]);
                line = br.readLine();

            }
        } catch (Exception e) {
            System.out.println("ccc");
            e.printStackTrace();
        }
    }

    

    @Override
    public void run() {
        while (true) {
            try {
                Socket s1 = server.accept();
                System.out.println("Server connected...");
                new ReadClient(s1);
            } catch (IOException e) {
                System.out.println("ddd");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    class ReadClient implements Runnable {
        private Socket sc1;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private String club;

        public ReadClient(Socket s1) {
            System.out.println("Reading client message by Server");
            sc1 = s1;
            try {
                oos = new ObjectOutputStream(sc1.getOutputStream());
                ois = new ObjectInputStream(sc1.getInputStream());
            } catch (IOException e) {
                System.out.println("eee");
                e.printStackTrace();
            }
            Thread t2 = new Thread(this);
            t2.start();
        }

        @Override
        public void run() {
            try {
                String username = (String) ois.readObject();
                String password = (String) ois.readObject();

                System.out.println(username);
                System.out.println(password);

                if (map.containsKey(username)) {
                    if (map.get(username).equals(password)) {
                        oos.writeObject("Correct"+","+username);
                        // play music here

                        ServerApp.playMusic("C:\\Users\\soura\\OneDrive\\Desktop\\project\\project_2205083\\src\\ipl.mp3");



                        // play music here
                        listenToClientRequest(username);
                    } else {
                        oos.writeObject("Incorrect");
                    }
                } else {
                    oos.writeObject("Incorrect");
                }

            } catch (Exception e) {
                System.out.println("fff");
                e.printStackTrace();
            }
        }

        private void listenToClientRequest(String name) {
            club = name;

            while (true) {
                try {
                    String request = (String) ois.readObject();
                    System.out.println("requesttttttttttttt: " + request + " from " + club);
                    if (request.equals("showPlayer")) {
                        System.out.println(club + " wants the server to show it's players");
                        // ----------------------------------------------------------------------------
                        dataBase = new players();
                        String filename = "C:\\\\Users\\\\soura\\\\OneDrive\\\\Desktop\\\\project\\\\project_2205083\\\\src\\\\players.txt";

                        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                String[] data = line.split(",");
                                String name2 = data[0];
                                String country = data[1];
                                int age = Integer.parseInt(data[2]);
                                double height = Double.parseDouble(data[3]);
                                String club = data[4];
                                String position = data[5];
                                int number = data[6].isEmpty() ? -1 : Integer.parseInt(data[6]);
                                int weeklySalary = Integer.parseInt(data[7]);

                                player p = new player(name2, country, age, height, club, position, number, weeklySalary);
                                dataBase.add(p);
                            }
                        } catch (IOException e) {
                            System.out.println("aaa");
                            System.out.println("Error reading from file: " + e.getMessage());
                        }

                        // ---------------------------------------------------------------------------
                        ArrayList<player> list = dataBase.search_club_name(club);

                        player[] arr = list.toArray(new player[0]);
                        oos.writeObject("playerList");
                        oos.writeObject(arr);

                        // for (player p: list) p.details();

                    } else if (request.equals("buyPlayer")) {
                        System.out.println(club + " wants the server to show purchasable players");
                        // ArrayList<player> list = tosell;
                        player[] arr2 = tosell.toArray(new player[0]);
                        oos.writeObject("sellList");
                        oos.writeObject(arr2);

                        // for (player p: list) p.details();
                    } else {
                        String[] arr = request.split(",");
                        int check = 0;
                        if (arr[0].equals("sellPlayer")) {

                            System.out.println(club + " wants the server to add " + "'" + arr[1] + "'"
                                    + " to purchasable players list");
                            System.out.println("Here club = " + club + " name = " + arr[1]);
                            if (name.toLowerCase().equals(dataBase.club_name(arr[1].trim()).toLowerCase())) {
                                player p = dataBase.search_name(arr[1].trim()).get(0);
                                tosell.add(p);
                                System.out.println(p.getName() + " have been added to the purchasable players.");
                                check = 1;
                                oos.writeObject("purchase request received");

                            }
                            if(check==0) {oos.writeObject("player sell request unsuccessful");}

                        } else if (arr[0].equals("confirmBuyPlayer")) {

                            System.out
                                    .println(club + " wants the server to add " + arr[1] + " to it's own players list");
                                     check = 0;
                                    for (player p : tosell) {
                                if (p.getName().toLowerCase().equals(arr[1].trim().toLowerCase())) {
                                    p.setClub(name);
                                    dataBase.setClub(p.getName(), name);
                                    System.out.println("Changing the club name of " + p.getName());
                                    dataBase.writeall();
                                    tosell.remove(p);
                                    check = 1;
                                    oos.writeObject("player successfully bought");

                                }
                            }
                            if (check==0) {oos.writeObject("player purchase unsuccessful");}

                        }

                    }
                } catch (Exception e) {
                    System.out.println("ggg");
                    System.out.println("no request");
                    e.printStackTrace();
                }

            }

        }

    }
}

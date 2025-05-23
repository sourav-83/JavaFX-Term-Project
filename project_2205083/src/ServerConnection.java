

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private static ServerConnection instance;

    private ServerConnection() {
    }

    public static ServerConnection getInstance() {
        if (instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }


    public void setStreams(ObjectOutputStream output,ObjectInputStream input){
        this.output=output;
        this.input=input;
    }

    public  boolean isStreamSet(){
        if(output!=null&&input!=null)return  true;
        return false;
    }

    public  void writeObject(Object... objects){
        for(Object obj:objects){
            try {
                output.writeObject(obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   public  ObjectOutputStream getOutput(){
        return  output;
   }

   public  ObjectInputStream getInput(){
        return input;
   }

    public void close() throws IOException {
        if (input != null) input.close();
        if (output != null) output.close();

    }
}
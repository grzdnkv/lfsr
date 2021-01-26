import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Geffe {
    //String seq;
    public  String readGeffeInput(String filename){
        try {
            File file = new File("genOut/" + filename);
            if(!file.exists()){
                throw new IOException("File not found");
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            //seq.clear();
            String seq = "";
            while ((line = br.readLine()) != null) {
                seq += line;
            }
            return seq;
        }catch (IOException e){
            e.getMessage();
        }
        return "FileNotFound";
    }

    public String GeffeGenerator(String In1, String In2, String ContIn3){
        String Out = "";
        for (int i = 0; i < ContIn3.length(); i++){
            if (ContIn3.charAt(i) == '0'){
                Out += In1.charAt(i);
            } else if(ContIn3.charAt(i) == '1'){
                Out += In2.charAt(i);
            }
        }
        return Out;
    }

    public void writeGeffeOutput(String Out){

        try{
            //int counter = 1;
            File file = new File("genOut/output.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            int counter = 0;
            for(int i = 0; i < Out.length(); i++){
                if (counter == 17){
                    bw.write('\n');
                    counter = 0;
                }
                counter++;
                bw.write(Out.charAt(i));

            }
            bw.close();
            fw.close();

        }catch (IOException e) {

        }
    }
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
// This code is for Loading CSV File data into X(features) and Y(label) data.
//The parameters are filename, number of elements, ypos(i.e) Last column
class Data{
    int[] yarray;
    int[][] xarray;
    Data(int[][] x,int[] y){
        xarray=x;
        yarray=y;
    }
}
public class Multi {
    
    static Data LoadCSV(String Filename,int NumberofElements,int ypos){
        String csvFile = Filename;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int[][] x=new int[NumberofElements][ypos-1];
        int[] y=new int[NumberofElements];
        int[] datas=null;
        try{
            br = new BufferedReader(new FileReader(csvFile));
            for(int i=0;i<NumberofElements;i++){
                line = br.readLine();
                String[] data = line.split(cvsSplitBy);
                y[i]=Integer.parseInt(data[ypos-1]);
                for(int j=0;j<ypos-1;j++){
                    x[i][j]=Integer.parseInt(data[j]);
                }

            }
            

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new Data(x,y);
    }

    
        
        
    }


    public static void main(String[] args) {
        Data data= LoadCSV("student.csv",1000,3);
        int[] y=data.yarray;
        int[][] x=data.xarray;
        
    }
}

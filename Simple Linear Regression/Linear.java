import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Linear {
    static double Mean(int[] array) {
        int sum=0;
        for(int i=0;i<array.length;i++){
            sum = sum + array[i];
        }
        double mean=(double)sum/(double)array.length;
        return mean;
      }

    static double[] Parameters(double meanx,double meany, int[] arrayx, int[] arrayy){
        double[] ans = new double[2]; 
        double num=0;
        double denum=0;
        int m=arrayx.length;
        for(int i=0;i<m;i++){
            num += (arrayx[i]-meanx)*(arrayy[i]-meany);
            denum += Math.pow((arrayx[i]-meanx), 2);
        }
        double b1=num/denum;
        double b0=meany-(b1*meanx);
        ans[0]=b1;
        ans[1]=b0;
        return ans;
    }

    static double[] SimpleLinearRegression(int[] x,int[] y){
        double mean_x=Mean(x);
        double mean_y=Mean(y);
        double[] params=Parameters(mean_x,mean_y,x,y);
        double b1=params[0];
        double b0=params[1];
        double rmse=0;
        int m=x.length;
        for(int i=0;i<m;i++){
            double y_pred=b0+b1*x[i];
            rmse += Math.pow((y[i]-y_pred),2);
        }
        rmse=Math.pow((rmse/m),0.5);
        double ss_t = 0;
        double ss_r = 0;
        for(int i=0;i<m;i++){
            double y_pred=b0+b1*x[i];
            ss_t += Math.pow((y[i]-mean_y),2);
            ss_r += Math.pow((y[i]-y_pred),2);
        }
        double r2 =1 - (ss_r/ss_t);
        double[] scores = {rmse,r2};
        return scores;
    }

    static int[][] LoadCSV(String Filename,int NumberofElements,int xpos,int ypos){
        String csvFile = Filename;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int[] x=new int[NumberofElements];
        int[] y=new int[NumberofElements];
        int[][] datas=null;
        try{
            br = new BufferedReader(new FileReader(csvFile));
            for(int i=0;i<NumberofElements;i++){
                line = br.readLine();
                String[] data = line.split(cvsSplitBy);
                x[i]=Integer.parseInt(data[xpos]);
                y[i]=Integer.parseInt(data[ypos]);
            }
            int[][] array={x,y};
            datas=array;

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
        return datas;
    }

    

    public static void main(String[] args) {
        int[][] data = LoadCSV("hd1.csv",237,0,1);
        int[] x=data[0];
        int[] y=data[1];
        double[] model=SimpleLinearRegression(x,y);
        System.out.println(model[0]);
        System.out.println(model[1]);
    }
}
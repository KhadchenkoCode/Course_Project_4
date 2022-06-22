package com.company;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static Random random = new Random();
    static int[] buffer = new int[8];
    static int used = 0;
    public static void main(String[] args) {

        System.out.println("Hello World");


        for(int i = 0; i<100; i++){
            int arg = random.nextInt();


            arg = Math.abs(arg%10);

            System.out.print(fifo(arg));
            System.out.println("        Passsed argument:  "+arg);
        }

    }

    static String fifo(int arg){
        String ret = "[";

        boolean flagged = false;
        for(int i = 0; i<=used&&i<8; i++){
            if(i==used){
                buffer[i] = arg;
                used++;
                flagged = true;
                break;
            }
        }
        if(!flagged){

            for(int i = 1; i<8; i++){
                buffer[i-1] = buffer[i];
            }
            buffer[7] = arg;
        }

        int[] sorted_buffer = new int[8];
        for(int i = 0; i<8; i++){
            sorted_buffer[i] = buffer[i];
        }
        Arrays.sort(sorted_buffer);
        String sort_ret = "[";

        float index_median1 = 7-used/2.0f;
        float median;
        int med1 = 0;
        int med0 = 0;
        if(index_median1%1==0){
            med1 =(int)Math.ceil(index_median1)+1;
            med0 = med1-1;
        } else {
            med1 = (int)index_median1+1;
            med0= med1-1;
        }


        for(int i = 0; i<7; i++){   // forms strings
            ret = ret + buffer[i]+",";

            sort_ret = sort_ret + sorted_buffer[i]+",";
            if(i == med0){
                sort_ret = sort_ret +" ";
            }
        }

        ret = ret + buffer[7]+"]";
        sort_ret = sort_ret+sorted_buffer[7]+"]";


        if(used%2== 1){
            med0=med1=(8-used/2)-1;
        }
            median = sorted_buffer[med0]+sorted_buffer[med1];
            median = median/2;

        ret = ret+" -> " + sort_ret+" -> ["+median+"]";

        return ret;
    }
}

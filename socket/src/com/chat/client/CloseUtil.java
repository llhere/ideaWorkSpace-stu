package com.chat.client;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {

    public static void closeAll(boolean isRunning,Closeable... able){

        isRunning = false;

        for(Closeable a : able){
            try {
                if(null != a){
                    a.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

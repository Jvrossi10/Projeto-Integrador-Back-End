package com.dh.Projeto.Integrador.logger;

import org.springframework.stereotype.Component;

@Component
public class Logger{

//    private static Logger instance = null;

    public Logger() {
    }

//    public static Logger getLogger(){
//        if (instance){
//            return instance;
//        }else {
//            instance = new Logger();
//            return instance;
//        }
//
//    }

    public void info(String msg){

        System.out.println("[INFO] "+msg);
    }
}





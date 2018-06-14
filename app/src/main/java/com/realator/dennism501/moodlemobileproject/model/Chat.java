package com.realator.dennism501.moodlemobileproject.model;

import com.realator.dennism501.moodlemobileproject.POJO.MoodleMessage;
import com.realator.dennism501.moodlemobileproject.modelRest.MoodleServices;

/**
 * Created by dennism501 on 5/18/17.
 */

public class Chat {

    private String format = MoodleServices.FORMAT;
    private String sendFunction = MoodleServices.SEND_MESSAGE;
    private String getFunction = MoodleServices.GET_MESSAGES;


    public boolean sendMessage(MoodleMessage message){




        return true;
    }

    public MoodleMessage getMessages(String useridto, String useridfrom, String read){


        return null;


    }
}

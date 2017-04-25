package com.avseredyuk.activemq.pubsub;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JMSProducerHelper helper = new JMSProducerHelper();
        if (helper.create()) {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String messageText = scanner.nextLine();
                helper.send(messageText);
            }
            helper.close();
        }

    }

}

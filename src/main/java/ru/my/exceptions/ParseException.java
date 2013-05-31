package ru.my.exceptions;

public class ParseException  extends Exception{

    private static String MESSAGES="Ошибка при разборе ответа от удаленного сервиса";

    public ParseException(Throwable throwable){
        super(MESSAGES, throwable);
    }
}

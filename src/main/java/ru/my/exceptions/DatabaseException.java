package ru.my.exceptions;

public class DatabaseException  extends Exception{

    private static String MESSAGES="Ошибка при работе с базой данных";

    public DatabaseException(Throwable throwable){
        super(MESSAGES, throwable);
    }
}

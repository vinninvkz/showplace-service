package ru.vinninvkz.homework.aspect;

public class TownNotFoundException extends EntityNotFoundException{
    public TownNotFoundException(String message) {
        super(message);
    }
}

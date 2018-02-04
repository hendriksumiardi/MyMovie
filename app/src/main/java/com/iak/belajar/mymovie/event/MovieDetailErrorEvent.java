package com.iak.belajar.mymovie.event;

public class MovieDetailErrorEvent extends BaseEvent {
    public MovieDetailErrorEvent(String message) {
        super(message);
    }
}
package ru.my.service.remote.services.openweathermap.jsons;


public class Wind {
    private Number speed;
    private Number gust;
    private Number deg;

    public Number getSpeed() {
        return speed;
    }

    public void setSpeed(Number speed) {
        this.speed = speed;
    }

    public Number getGust() {
        return gust;
    }

    public void setGust(Number gust) {
        this.gust = gust;
    }

    public Number getDeg() {
        return deg;
    }

    public void setDeg(Number deg) {
        this.deg = deg;
    }
}

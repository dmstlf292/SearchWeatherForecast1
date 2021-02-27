package com.info.searchweather.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Arrays;

@JsonIgnoreProperties({"coord", "base", "clouds", "dt", "id", "name", "cod", "timezone","message"})
public class CurrentWeather {

    private Weather[] weather;
    private Main main;
    private String visibility;
    private Wind wind;
    private Sys sys;

    @JsonCreator
    public CurrentWeather(@JsonProperty("weather") Weather[] weather,
                          @JsonProperty("main") Main main,
                          @JsonProperty("visibility") String visibility,
                          @JsonProperty("wind") Wind wind,
                          @JsonProperty("sys") Sys sys) {

        this.weather = weather;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.sys = sys;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @JsonIgnoreProperties({"id", "description", "icon"})
    public static class Weather {
        private String main;

        @JsonCreator
        public Weather(@JsonProperty("main") String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "main='" + main + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties({"temp_min", "temp_max","feels_like"})
    public static class Main {
        private String temp;
        private String pressure;
        private String humidity;

        @JsonCreator
        public Main(@JsonProperty("temp") String temp,
                    @JsonProperty("pressure") String pressure,
                    @JsonProperty("humidity") String humidity) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "temp='" + temp + '\'' +
                    ", pressure='" + pressure + '\'' +
                    ", humidity='" + humidity + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties({"gust"})
    public static class Wind {
        private String speed;
        private String deg;

        @JsonCreator
        public Wind(@JsonProperty("speed") String speed,
                    @JsonProperty("deg") String deg) {
            this.speed = speed;
            this.deg = deg;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "Wind{" +
                    "deg='" + deg + '\'' +
                    ", speed='" + speed + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties({"type", "id", "message", "country"})
    public static class Sys {
        private String sunrise;
        private String sunset;

        @JsonCreator
        public Sys(@JsonProperty("sunrise") String sunrise,
                   @JsonProperty("sunset") String sunset) {
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Sys{" +
                    "sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "weather=" + Arrays.toString(weather) +
                ", main=" + main +
                ", visibility='" + visibility + '\'' +
                ", wind=" + wind +
                ", sys=" + sys +
                '}';
    }
}

package com.info.searchweather.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties({"cod", "message", "cnt", "city"})
public class Forecast {
    private List[] list;

    @JsonCreator
    public Forecast(@JsonProperty("list") List[] list){
        this.list=list;
    }

    public List[] getList() {
        return list;
    }

    public void setList(List[] list) {
        this.list = list;
    }

    @Override
    public String toString(){
        return "Forecast{"+
                "list=" + Arrays.toString(list)+
                '}';
    }

    public ArrayList<String> getGroupData(){
        ArrayList<String> groupData = new ArrayList<>();
        for(int i=0; i<list.length; i++){
            groupData.add(list[i].dt_txt);
        }
        return groupData;
    }

    public ArrayList<ArrayList<List>> getChildData(){
        ArrayList<ArrayList<List>> childData = new ArrayList<>();
        for (int i =0; i<list.length;i++){
            ArrayList<List> childContents = new ArrayList<>();
            childContents.add(list[i]);
            childData.add(childContents);
        }
        return childData;
    }

    @JsonIgnoreProperties({"dt", "clouds", "visibility", "pop", "sys", "rain", "snow"})
    public static class List{
        private Main main;
        private Weather[] weather;
        private Wind wind;
        private String dt_txt;

        @JsonCreator
        public List(@JsonProperty("main") Main main,
                    @JsonProperty("weather") Weather[] weather,
                    @JsonProperty("wind") Wind wind,
                    @JsonProperty("dt_txt") String dt_txt) {
            this.main = main;
            this.weather = weather;
            this.wind = wind;
            this.dt_txt = dt_txt;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public Weather[] getWeather() {
            return weather;
        }

        public void setWeather(Weather[] weather) {
            this.weather = weather;
        }

        public Wind getWind() {
            return wind;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        @Override
        public String toString(){
            return "List{"+
                    "main=" + main +
                    ", weather=" + Arrays.toString(weather)+
                    ", wind=" + wind +
                    ", dt_txt='" + dt_txt + '\'' +
                    '}';
        }
    }


    @JsonIgnoreProperties({"feels_like","temp_min", "temp_max", "sea_level", "grnd_level", "temp_kf"})
    public static class Main{
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

    @JsonIgnoreProperties({"id", "description", "icon"})
    public static class Weather{
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
                    "speed='" + speed + '\'' +
                    ", deg='" + deg + '\'' +
                    '}';
        }
    }


}

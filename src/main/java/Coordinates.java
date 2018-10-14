public class Coordinates {
        private String latitude;
        private String longtitude;

        public Coordinates(String latitude, String longtitude){
            this.latitude = latitude;
            this.longtitude = longtitude;
        }

    @Override
    public String toString() {
        return this.latitude+";"+this.longtitude;
    }


    public String getLongtitude() {
        return this.longtitude;
    }

    public String getLatitude() {
        return this.latitude;
    }
}

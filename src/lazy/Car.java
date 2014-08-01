package lazy;


public class Car {

        private String model;
        private int year, price;
        private String id;
        private String brand;
        private String manufacturer;
        private String color;
        private boolean soldState;
        
        public Car(String model, int year, String manufacturer, String color) {
                this.model = model;
                this.year = year;
                this.manufacturer = manufacturer;
                this.color = color;
        }

        public Car(String randomId, String randomBrand, int randomYear,
				String randomColor, int randomPrice, boolean randomSoldState) {
        	this.id = randomId;
        	this.brand= randomBrand;
        	this.year = randomYear;
        	this.color = randomColor;
        	this.price = randomPrice;
        	
        }

		public String getModel() {
                return model;
        }

        public void setModel(String model) {
                this.model = model;
        }

        public int getYear() {
                return year;
        }

        public void setYear(int year) {
                this.year = year;
        }

        public String getManufacturer() {
                return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
        }

        public String getColor() {
                return color;
        }

        public void setColor(String color) {
                this.color = color;
        }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public boolean isSoldState() {
			return soldState;
		}

		public void setSoldState(boolean soldState) {
			this.soldState = soldState;
		}
}
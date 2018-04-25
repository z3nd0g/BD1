public class Picture {
    private int ID;
    private int PGRating;
    private String Language;
    private String Title;
    private int ProductionYear;
    private int WorldwideGross;
    private int Budget;
    private boolean Color;
    private Person Director;

    public int getID() {return ID;}
    public void setID(int ID) {this.ID = ID;}

    public int getPGRating() {return PGRating;}
    public void setPGRating(int PGRating) {this.PGRating = PGRating;}

    public String getLanguage() {return Language;}
    public void setLanguage(String l) {Language = l;}

    public String getTitle() {return Title;}
    public void setTitle(String title) {Title = title;}

    public int getProductionYear() {return ProductionYear;}
    public void setProductionYear(int productionYear) {ProductionYear = productionYear;}

    public int getWorldwideGross() {
        return WorldwideGross;
    }
    public void setWorldwideGross(int worldwideGross) {
        WorldwideGross = worldwideGross;
    }

    public int getBudget() {
        return Budget;
    }
    public void setBudget(int budget) {
        Budget = budget;
    }

    public boolean isColor() {
        return Color;
    }
    public void setColor(boolean color) {
        Color = color;
    }

    public Person getDirector() {
        return Director;
    }
    public void setDirector(Person director) {
        Director = director;
    }
}

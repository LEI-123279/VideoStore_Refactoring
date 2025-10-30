package v0;

public class NewReleasePrice {
    public NewReleasePrice()
    {
    }

    public double getRentalAmount(int duration)
    {
        return duration * 3;
    }

    public int getFrequentRentalPoints(int duration)
    {
        return duration > 1 ? 2 : 1;
    }
}

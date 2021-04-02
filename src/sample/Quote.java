package sample;

public class Quote {
    private String date;
    private String number;
    private String text;

    public Quote(String date, String number, String text) {
        this.date = date;
        this.number = number;
        this.text = text;
    }

    public String getDate()
    {
        return date;
    }

    public String getNumber()
    {
        return number;
    }

    public String getText()
    {
        return text;
    }
}

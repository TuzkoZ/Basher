package sample;

import sample.Quote;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public List<Quote> quotes = new ArrayList<>();
    public int maxPageNumber;

    public Storage() throws IOException {
    }

    public int getMaxPageNumber() throws IOException {
        Document doc = Jsoup.connect("http://www.bash.im/").get();
        return Integer.parseInt(doc.select("input.pager__input").attr("max"));
    }

    private static String get(Element element, String cssQuery) {
        final Element result = element.select(cssQuery).first();
        if (result != null) {
            return result.html()
                    // способен обрабатывать несколько или более переносов строки
                    .replaceAll("(<br>)\n+(\\1)*", "\n")
                    .replaceAll("<br>", "");
        }

        return null;
    }

    public void getData(int pageNumber) throws IOException {
        maxPageNumber = getMaxPageNumber();
        quotes.clear();
        String url = "https://bash.im/index/" + Integer.toString(pageNumber);
        System.out.println(url);

        Document doc = Jsoup.connect(url).get();

        Elements elements = doc.select(".quote");

        elements.stream().forEach((elem) -> {
            final String date = get(elem, "div[class=quote__header_date]");
            final String number = elem.text().substring(0, elem.text().indexOf(" "));
            final String text = get(elem, "div[class=quote__body]");

            if (text != null) {
                quotes.add(new Quote(date, number, text));
            }
        });
    }

}

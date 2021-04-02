package sample;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;
import sample.Storage;

import java.io.IOException;

public class Controller {

    int MAX_QUOTE_COUNT;
    final int MIN_QUOTE_COUNT = 0;
    Storage quoteStorage;
    Boolean isInit = false;
    int quoteCount = 0;
    int currentPage = 0;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelNumber;

    @FXML
    private TextArea textQuote;

    @FXML
    private Button btnPrevList;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrev;

    public Controller() throws IOException {
        quoteStorage = new Storage();
        MAX_QUOTE_COUNT = 25;
    }

    @FXML
    private javafx.scene.text.Text maxListNumber;

    @FXML
    private javafx.scene.text.Text currentListNumber;

    public void btnNext_onClickMethod() throws IOException {
            if(quoteCount + 1 < MAX_QUOTE_COUNT)
            {
                quoteCount = quoteCount + 1;
                showData(quoteCount);
            }
    }

    public void btnPrev_onClickMethod() throws IOException {
        if(quoteCount - 1 > MIN_QUOTE_COUNT)
        {
            quoteCount = quoteCount - 1;
            showData(quoteCount);
        }
    }

    public void btnPrevList_onClickMethod() throws IOException {
        if(currentPage + 1 <= quoteStorage.getMaxPageNumber())
        {
            textQuote.clear();
            currentPage = currentPage + 1;
            System.out.println(currentPage);
            quoteCount = 0;
            quoteStorage.getData(currentPage);
            MAX_QUOTE_COUNT = quoteStorage.quotes.size();
            btnNext_onClickMethod();
        }
    }

    public void btnClose_onClickMethod(){
        Platform.exit();
    }

    public void showData(int quoteNum) throws IOException {
        labelDate.setText(quoteStorage.quotes.get(quoteNum).getDate());
        labelNumber.setText(quoteStorage.quotes.get(quoteNum).getNumber() + " | " + quoteNum + "/" + (MAX_QUOTE_COUNT - 1));
        textQuote.setText(quoteStorage.quotes.get(quoteNum).getText());
        maxListNumber.setText(Integer.toString(quoteStorage.maxPageNumber));
        currentListNumber.setText(Integer.toString(currentPage));
    }

    public void btnNextList_onClickMethod() throws IOException {
            if(currentPage == 0)
            {
                currentPage = quoteStorage.getMaxPageNumber() + 1;
                btnPrev.setDisable(false);
                btnNext.setDisable(false);
                btnPrevList.setDisable(false);
            }

            if(currentPage - 1 > 0)
            {
                quoteCount = 0;
                textQuote.clear();
                currentPage = currentPage - 1;
                System.out.println(currentPage);
                quoteStorage.getData(currentPage);
                MAX_QUOTE_COUNT = quoteStorage.quotes.size();
                btnNext_onClickMethod();
            }

    }
}

package domain.com.homework_5.lesson;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParsingTests {

    private ClassLoader cl = getClass().getClassLoader();

    @Test
    void parsePdfTest() throws Exception {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/");
        File pdfDownload = $(byText("PDF download")).download();
        PDF parsed = new PDF(pdfDownload);

        // We can extract different info from PDF file:
        String author = parsed.author;
        byte[] content = parsed.content;
        Calendar creationDate = parsed.creationDate;
        String text = parsed.text;
        String title = parsed.title;
        int numberOfPages = parsed.numberOfPages;

        assertThat(author).contains("Juliette de Rancourt");
        assertThat(title).contains("JUnit 5 User Guide");
    }

    @Test
    void parseXlsTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("files_lesson/file_example_XLS_50.xls")) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(3).getCell(4).getStringCellValue())
                    .isEqualTo("France");
        }
    }

    @Test
    void parseCsvFileTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("files_lesson/example.csv")) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(3)
                    .contains(
                            new String[]{"Author", "Book"},
                            new String[]{"Block", "Apteka"},
                            new String[]{"Esenin", "Cherniy Chelovek"}
                    );
        }
    }

    @Test
    void zipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("files_lesson/simple1.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                assertThat(zipEntry.getName()).isEqualTo("simple1.txt");
            }
        }
    }

    // Part of Homework: using ZipFile class we need to read and assert the content of file inside zip archive.
    // ZipFile zipFile = new ZipFile(new File(cl.getResource("files/simple1.zip").toURI()));
}

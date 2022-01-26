package domain.com.homework_5.homework;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ParsingFilesInsideZip {

    private ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void verifyFilesInsideZipArchive() throws Exception {
        ZipFile zipFile = new ZipFile(new File(Objects.requireNonNull(classLoader
                .getResource("files_hw/Archive.zip")).toURI()));

        //CSV
        ZipEntry csvEntry = zipFile.getEntry("hw_csv_file_1.csv");
        try (InputStream stream = zipFile.getInputStream(csvEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(6)
                    .contains(
                            new String[]{"Username", "Identifier", "First name", "Last name"},
                            new String[]{"booker12", "9012", "Rachel", "Booker"},
                            new String[]{"grey07", "2070", "Laura", "Grey"},
                            new String[]{"johnson81", "4081", "Craig", "Johnson"},
                            new String[]{"jenkins46", "9346", "Mary", "Jenkins"},
                            new String[]{"smith79", "5079", "Jamie", "Smith"}
                    );
        }

        //XLS
        ZipEntry xlsEntry = zipFile.getEntry("hw_xls_file.xls");
        try (InputStream stream = zipFile.getInputStream(xlsEntry)) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(3).getCell(4).getStringCellValue())
                    .isEqualTo("France");
            assertThat(parsed.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue())
                    .isEqualTo("Gent");
        }

        //PDF
        ZipEntry pdfEntry = zipFile.getEntry("hw_pdf_file.pdf");
        try (InputStream stream = zipFile.getInputStream(pdfEntry)) {
            PDF pdf = new PDF(stream);
            assertThat(pdf.text).contains("A Simple PDF File");
            assertThat(pdf.numberOfPages).isEqualTo(2);
        }
    }
}
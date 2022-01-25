package domain.com.homework_5.lesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {

    // To point the system where we can find a classPath for uploading files:
    private ClassLoader cl = SelenideFilesTest.class.getClassLoader();
    // or
    private ClassLoader cl2 = getClass().getClassLoader();

    @Test
    void downloadTest() throws IOException {
        Selenide.open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");

        // Path to the downloaded file in folder /build/downloads in case there is a href attribute for downloading:
        File downloadedFile = $("#raw-url").download();

        // try with resources. For auto closing inputStream after its work:
        try (InputStream is = new FileInputStream(downloadedFile)) {
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8))
                    .contains("Eclipse Public License - v 2.0");
        }
    }

    @Test
    void downloadWithoutHrefTest() {
        // Configuration in case there is no href attribute for downloading using proxy server (rarely used):
        Configuration.proxyEnabled = true;
        Configuration.fileDownload = FileDownloadMode.PROXY;
    }

    @Test
    void uploadFile() {
        Selenide.open("https://the-internet.herokuapp.com/upload");

        // Find tag=input with type=file for uploading:
        $("input[type='file']").uploadFromClasspath("files_lesson/upload.txt");

        $("#file-submit").click();
        $("#uploaded-files").shouldHave(Condition.text("upload.txt"));
    }
}

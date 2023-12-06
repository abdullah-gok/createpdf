package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CreatePDFDocument {
    static int numberOfFiles = 100;
    public static void main(String[] args) {

        for (int i = 1; i <= numberOfFiles; i++) {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String filePath = "C:/Users/goeka/Desktop/testdokumente/unique_document_/" + timestamp + ".pdf";
            createDirectoryIfNotExists(filePath);
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                Document document = new Document();
                PdfWriter.getInstance(document, fileOutputStream);
                document.open();
                String uniqueText = UUID.randomUUID().toString();
                document.add(new Paragraph("Unique Text: " + uniqueText));
                document.close();
                System.out.println("PDF document created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (com.itextpdf.text.DocumentException e) {
                e.printStackTrace();
            }
        }
    }
    private static void createDirectoryIfNotExists(String filePath) {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory);
            } else {
                System.err.println("Failed to create directory: " + directory);
            }
        }
    }
}
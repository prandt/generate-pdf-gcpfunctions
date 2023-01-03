package com.example;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePdf {
    public static final void getPdf (People people) {
         Document document = new Document();
         try {
            final PdfWriter instance = PdfWriter.getInstance(document, new FileOutputStream(people.getName()+".pdf"));
            document.open();
            instance.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));

            document.add(new Paragraph("Nome: " + people.getName()));
            document.add(new Paragraph("Idade: " + people.getAge()));
            
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }
}

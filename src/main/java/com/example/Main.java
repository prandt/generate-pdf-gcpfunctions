package com.example;

import java.io.IOException;
import java.io.OutputStream;

import com.example.models.Exam;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.PdfWriter;

public class Main implements HttpFunction{
    private static final Gson gson = new Gson();

    private void generatePdf(Exam exam, OutputStream out) throws IOException {
        Document document = new Document();
        try {
        final PdfWriter instance = PdfWriter.getInstance(document, out);
           document.open();
           instance.getInfo().put(PdfName.CREATOR, new PdfString(Document.getVersion()));

           // Header
           HeaderFooter header = new HeaderFooter(new Phrase("Alo"), true);
           document.setHeader(header);

           // Titulo da avaliação
           Paragraph p1 = new Paragraph(new Chunk(exam.getName(), FontFactory.getFont(FontFactory.HELVETICA, 14)));
           document.add(p1);

           // Nome da turma
           document.add(new Paragraph("Turma: " + exam.getCourseName()));

           // Descrição da avaliação
           document.add(new Paragraph("Descrição da avaliação: " + exam.getDescription()));

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        if(request.getMethod() == "POST") {
            JsonObject body = gson.fromJson(request.getReader(), JsonObject.class);
            
            Exam exam = new Exam();
            exam.convertJsonToExam(body);
            
            response.setContentType("application/pdf");

            generatePdf(exam, response.getOutputStream());

          } else {
            response.setStatusCode(400);
          }
    }    
}

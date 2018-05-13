package pia.servlet;

import com.itextpdf.io.font.otf.Glyph;
import com.itextpdf.io.font.otf.GlyphLine;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import pia.data.AccountJ;
import pia.data.TransactionJ;
import pia.data.UserJ;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * Created by jakub on 27.01.2017.
 */
public class PdfServlet extends BaseServlet {
    /**
     * Font used in the pdf
     */
    public final static String FONT = "\\WEB-INF\\FreeSans.ttf";

    /**
     *  Creates pdf for download
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserJ user = (UserJ) req.getSession().getAttribute("user");
        AccountJ acc = accountsDao.findByUser(user.getId());
        List<TransactionJ> trans = transDao.findTransactionByAcc(acc);
        String relativePath = req.getServletContext().getRealPath("");
        System.out.println(" abc =  relativePath = " + relativePath);
        System.out.println(relativePath + "\\trans.pdf");
        PdfWriter writer = new PdfWriter(relativePath + "\\trans.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        //String relativePath = req.getServletContext().getRealPath("");

        Paragraph par = new Paragraph("Account history");
        PdfFont f = PdfFontFactory.createFont(relativePath+FONT, "cp1250",true);
        document.setFont(f);
        document.add(par);
        document.add(new Paragraph("Name: " + user.getName()).setFont(f));
        document.add(new Paragraph("Email: " + user.getEmail()));
        document.add(new Paragraph("Balance: " + String.valueOf(acc.getBalance())));
        document.add(new Paragraph("Account number: " + acc.getAccNumber()));
        document.add(new Paragraph("Date of birth: " + user.getBirthDate()));
        Table table = new Table(6, true);

        setTableHeader(table);

        document.add(table);
        for (int i = 0; i < trans.size(); i++) {
            for (int j = 0; j < 6; j++) {

                String text = getText(j, trans.get(i));
                table.addCell(new Cell().setKeepTogether(true).add(new Paragraph(text).setMargins(0, 0, 0, 0)));
            }
            table.flush();
        }

        table.complete();

        document.close();
        int k = 5;
        download(req, resp);


    }

    /**
     * Sets up download of created pdf, after download deletes the file
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws FileNotFoundException
     */
    private void download(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        String filePath = "trans.pdf";


        // if you want to use a relative path to context root:
        String relativePath = request.getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
        filePath= relativePath + "\\" +filePath;

        System.out.println(filePath);
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
        // obtains ServletContext
        ServletContext context = request.getServletContext();

        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/pdf";
        }
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        try {
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        downloadFile.delete();
    }

    /**
     * Pdf table row id
     */
    int id = 1;

    /**
     * Sets header of table
     * @param table table
     */
    public void setTableHeader(Table table) {
        Cell cell = new Cell();
        cell.setKeepTogether(true).add(new Paragraph("id"));
        Cell cellacc = new Cell();

        cellacc.setKeepTogether(true).add(new Paragraph("Target acc"));
        Cell cellSSym = new Cell();

        cellSSym.setKeepTogether(true).add(new Paragraph("Spec sym"));
        Cell cellBankC = new Cell();

        cellBankC.setKeepTogether(true).add(new Paragraph("Bank code"));
        Cell cellVarSym = new Cell();

        cellVarSym.setKeepTogether(true).add(new Paragraph("Var sym"));
        Cell cellAmount = new Cell();
        cellAmount.setKeepTogether(true).add(new Paragraph("Amount"));

        table.addHeaderCell(cell);
        table.addHeaderCell(cellacc);
        table.addHeaderCell(cellSSym);
        table.addHeaderCell(cellBankC);
        table.addHeaderCell(cellVarSym);
        table.addHeaderCell(cellAmount);


    }

    /**
     * Text of column at index j from transaction tj
     * 0 - id(class variable)
     * 1 - account number
     * 2 - spec symbol
     * 3 - bank code
     * 4 - var symbol
     * 5 - amount
     * @param j column index
     * @param tj transaction
     * @return text
     */
    public String getText(int j, TransactionJ tj) {
        String text = "";
        switch (j) {
            case 0:
                text = String.valueOf(id);
                id = id + 1;
                break;
            case 1:
                text = tj.getAccNumber();
                break;
            case 2:
                text = tj.getSpecSymbol();
                break;
            case 3:
                text = tj.getBankCode();
                break;
            case 4:
                text = tj.getVarSymbol();
                break;
            case 5:
                text = String.valueOf(tj.getAmount());
                break;
            default:
                text = "";
                break;

        }
        if (text == null)
            text = "";
        return text;
    }
}

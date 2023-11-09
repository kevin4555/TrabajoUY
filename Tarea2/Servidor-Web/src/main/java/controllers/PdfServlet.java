package controllers;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.webservices.DtDatosPdf;
import logica.webservices.OfertaLaboralNoExisteException_Exception;
import logica.webservices.PublicadorService;
import logica.webservices.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class PdfServlet
 */
@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public PdfServlet() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    PublicadorService publicadorService =
          new PublicadorService();
    logica.webservices.Publicador port =
          publicadorService.getPublicadorPort();
    String nicknamePostulante =
          request.getParameter("nicknamePostulante");
    String nombreOferta =
          request.getParameter("nombreOferta");

    DtDatosPdf datosPdf;
    try {
      response.setHeader("Content-Disposition",
            "inline; filename=" + nicknamePostulante + "_"
                  + nombreOferta);
      datosPdf = port.obtenerDatosPdf(nicknamePostulante,
            nombreOferta);
      Document document = new Document();
      PdfWriter.getInstance(document,
            response.getOutputStream());

      document.open();
      document.add(new Paragraph("Nombre del postulante: "
            + datosPdf.getNombrePostulante()));
      document.add(new Paragraph("Nombre de la empresa: "
            + datosPdf.getNombreEmpresa()));
      document.add(new Paragraph("Nombre de la oferta: "
            + datosPdf.getNombreOferta()));
      document.add(new Paragraph("Posición en el llamado: "
            + datosPdf.getPosicion()));
      document.add(new Paragraph("Fecha de la postulación: "
            + datosPdf.getFechaPostulacion()));
      document.add(new Paragraph("Fecha de resolución: "
            + datosPdf.getFechaResolucion()));

      document.close();
    } catch (OfertaLaboralNoExisteException_Exception
          | UsuarioNoExisteException_Exception
          | DocumentException e) {

      e.printStackTrace();
    }

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request,
   *      HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}

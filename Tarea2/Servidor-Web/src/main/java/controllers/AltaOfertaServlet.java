package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.imageio.ImageIO;

import excepciones.KeywordNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.controllers.Fabrica;
import logica.datatypes.DtCantidadTipoPublicacionRestante;
import logica.datatypes.DtCompraPaquete;
import logica.datatypes.DtpaquetePublicacion;
import logica.datatypes.DttipoPublicacion;
import logica.datatypes.Dtusuario;
import logica.interfaces.IcontroladorOferta;
import logica.interfaces.IcontroladorUsuario;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;

import model.EstadoSesion;
import model.TipoUsuario;

@MultipartConfig()

@WebServlet("/altaOferta")
public class AltaOfertaServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public AltaOfertaServlet() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  private void procesarGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    Dtusuario empresa = (Dtusuario) sesion.getAttribute("usuarioLogueado");
    if (sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO
        || sesion.getAttribute("tipoUsuario") != TipoUsuario.EMPRESA) {
      request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
    }
    IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    ArrayList<String> listaTipos = (ArrayList<String>) controladorOferta
        .listarTipoDePublicaciones();
    ArrayList<DttipoPublicacion> listaDtTipos = new ArrayList<DttipoPublicacion>();
    IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
    for (String nombreTipo : listaTipos) {
      try {
        listaDtTipos.add(controladorOferta.obtenerDttipoPublicacion(nombreTipo));
      } catch (TipoPublicacionNoExisteException e) {
        request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
        e.printStackTrace();
      }
    }
    
    try {
      ArrayList<DtCompraPaquete> listaCompraPaquetes = (ArrayList<DtCompraPaquete>) controladorUsuario
          .obtenerDtCompraPaqueteDeEmpresa(empresa.getNickname());
      request.setAttribute("listaCompraPaquetes", listaCompraPaquetes);
    } catch (UsuarioNoExisteException e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
      e.printStackTrace();
    }
    
    ArrayList<String> listKeywordsAtributo = (ArrayList<String>) controladorOferta
        .listarKeywords();
    request.setAttribute("listaKeywords", listKeywordsAtributo);
    request.setAttribute("listaTipoPublicacion", listaDtTipos);
    request.getRequestDispatcher("/WEB-INF/registros/AltaOferta.jsp").forward(request,
        response);
  }
  
  private void procesarPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession sesion = request.getSession();
    
    if (sesion.getAttribute("estadoSesion") != EstadoSesion.LOGIN_CORRECTO
        || sesion.getAttribute("tipoUsuario") != TipoUsuario.EMPRESA) {
      request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
    }
    
    IcontroladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
    Dtusuario empresa = (Dtusuario) sesion.getAttribute("usuarioLogueado");
    String tipoPublicacion = request.getParameter("tipoPublicacion");
    String nombreOferta = request.getParameter("nombreOferta");
    String descripcion = request.getParameter("descripcion");
    String[] keywords = request.getParameterValues("keywords");
    String horaInicio = request.getParameter("horaInicio");
    String horaFin = request.getParameter("horaFin");
    float remuneracion = 0;
    String departamento = request.getParameter("departamento");
    String ciudad = request.getParameter("ciudad");
    BufferedImage imagen = null;
    ArrayList<String> listKeywords = new ArrayList<>();
    LocalDate fechaAlta = LocalDate.now();
    String nombrePaquete = request.getParameter("nombrePaquete");
    if(nombrePaquete.equals("")) {
      nombrePaquete = null;
    }
    if (request.getParameter("remuneracion") != null) {
      remuneracion = Float.valueOf(request.getParameter("remuneracion"));
    }
    
    try {
      Part filePart = request.getPart("imagenOferta");
      if (filePart != null && filePart.getSize() > 0) {
        InputStream fileContent = filePart.getInputStream();
        imagen = ImageIO.read(fileContent);
      }
    } catch (IOException | ServletException e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
    }
    
    if (keywords != null) {
      for (String keyword : keywords) {
        listKeywords.add(keyword);
      }
    }
    if(nombrePaquete != null) {
      IcontroladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
      try {
        ArrayList<DtCompraPaquete>  listaCompras = (ArrayList<DtCompraPaquete>) controladorUsuario.obtenerDtCompraPaqueteDeEmpresa(empresa.getNickname());
        Boolean hayPublicaciones = false;
        for(DtCompraPaquete compra : listaCompras) {
          if(nombrePaquete.equals(compra.getPaquete().getNombre())){
            System.out.println("encuentra el dtcompra");
            for(DtCantidadTipoPublicacionRestante cantidadRestante : compra.getPublicacionesRestantes()) {
              System.out.println(cantidadRestante.getTipoPublicacion().getNombre());
              System.out.println(cantidadRestante.getCantidad());
              if(tipoPublicacion.equals(cantidadRestante.getTipoPublicacion().getNombre())) {
                System.out.println("encuentra el tipo");
                if(cantidadRestante.getCantidad()>0) {
                  System.out.println("setea hay publicacion en true");
                  hayPublicaciones = true;
                }
              }
            }
          }
        }
        if(!hayPublicaciones) {
          request.setAttribute("mensajeError", "el paquete no tiene publicaciones del tipo seleccionado");
          procesarGet(request, response);
          return;
        }
      } catch (UsuarioNoExisteException e) {
        request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
        e.printStackTrace();
      } catch (IOException e) {
        request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
        e.printStackTrace();
      }
    }
    try {
      controladorOferta.altaOfertaLaboral(nombreOferta, descripcion, horaInicio, horaFin,
          remuneracion, ciudad, departamento, fechaAlta, tipoPublicacion,
          empresa.getNickname(), listKeywords, imagen, nombrePaquete);
      String url = request.getContextPath() + "/perfil?nicknameUsuario="
          + URLEncoder.encode(empresa.getNickname(),"UTF-8" );
      response.sendRedirect(url);
      
    } catch (TipoPublicacionNoExisteException | KeywordNoExisteException
        | UsuarioNoExisteException e) {
      request.getRequestDispatcher("/WEB-INF/error/500.jsp").forward(request, response);
      
    } catch (OfertaLaboralYaExisteException e) {
      request.setAttribute("mensajeError", "nombre de oferta repetido");
      procesarGet(request, response);
      
    }
    
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   procesarGet(request, response);
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    procesarPost(request, response);
  }
  
}
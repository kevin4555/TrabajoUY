module tpgr04 {
  requires transitive java.desktop;
  requires jcalendar;
  requires junit;
  
  exports presentacion;
  exports logica.interfaces;
  exports logica.handlers;
  exports logica.datatypes;
  exports logica.controllers;
  exports logica.classes;
  exports excepciones;
  exports testing;
  
}

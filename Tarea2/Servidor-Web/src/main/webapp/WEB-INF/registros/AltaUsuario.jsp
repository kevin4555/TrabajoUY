<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="logica.datatypes.Dtusuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrar usuario</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resource/css/general.css">
<%@ include file="../include/Head.jsp"%>
</head>
<body>
<body class="login">
    <form class="formLogin" id="formAltaUsuario" action="">
      <a href="../../index.html"
        ><img src="../img/Logo.svg" alt="Logo" class="navbar-brand img-fluid"
      /></a>
      <div class="row mt-3">
        <div class="col">
          <label class="form-label"><strong>*Nombre</strong></label>
          <input
            type="text"
            class="form-control"
            placeholder="Ingrese su nombre"
          />
        </div>
        <div class="col">
          <label for="inputCity" class="form-label"
            ><strong>*Apellido</strong></label
          >
          <input
            type="text"
            class="form-control nom"
            placeholder="Ingrese su apellido"
          />
        </div>
      </div>
      <div class="col-md-6 mt-3">
        <label for="inputNickname" class="form-label"
          ><strong>*Nickname</strong></label
        >
        <input
          type="text"
          class="form-control"
          id="inputNickname"
          placeholder="Ingrese su nickname"
        />
      </div>
      <div class="col-md-6 mt-3">
        <label for="inputEmail4" class="form-label"
          ><strong>*Email</strong></label
        >
        <input
          type="email"
          class="form-control"
          id="inputEmail"
          placeholder="Ingrese su email"
        />
      </div>
      <div class="col-md-6 mt-3">
        <label for="inputPassword4" class="form-label"
          ><strong>*Contraseña</strong></label
        >
        <input
          type="password"
          class="form-control"
          id="inputPassword"
          placeholder="Ingrese su contraseña"
        />
      </div>
      <div class="col-md-6 mt-3">
        <label for="inputPassword4" class="form-label"
          ><strong>*Confirmar contraseña</strong></label
        >
        <input
          type="password"
          class="form-control"
          id="inputPasswordConfirm"
          placeholder="Ingrese su contraseña"
        />
      </div>
      <div class="col-md-6 mt-3">
        <label for="inputImgage" class="form-label"
          ><strong>Foto de perfil</strong></label
        >
        <input type="file" name="imagen1" />
      </div>
      <div class="row mt-3">
        <div class="col">
          <label for="inputTipoUsuario" class="form-label"
            ><strong>*Tipo de usuario</strong></label
          >
        </div>
        <div class="col">
          <div class="form-check">
            <input
              type="radio"
              class="form-check-input"
              id="radioPostulante"
              name="optradio"
              value="option1"
            /><strong>Postulante</strong>
            <label class="form-check-label" for="radio1"></label>
          </div>
          <div class="form-check">
            <input
              type="radio"
              class="form-check-input"
              id="radioEmpresa"
              name="optradio"
              value="option2"
            /><strong>Empresa</strong>
            <label class="form-check-label" for="radio2"></label>
          </div>
        </div>
      </div>
      <div class="col-md-6 mt-3" id="divNacionalidad">
        <label class="form-label"><strong>*Nacionalidad</strong></label>
        <input
          type="text"
          class="form-control"
          placeholder="Ingrese su nacionalidad"
        />
      </div>
      <div class="col-md-6 mt-3" id="divFechaNacimiento">
        <label class="form-label"><strong>*Fecha de Nacimiento</strong></label>
        <input type="date" class="form-control" />
      </div>
      <div class="col-md-6 mt-3" id="divSitioWeb">
        <label class="form-label"><strong>Sitio Web</strong></label>
        <input
          type="text"
          class="form-control"
          placeholder="Ingrese su sitio web"
        />
      </div>
      <div class="col-md-6 mt-3" id="divDescripcionEmpresa">
        <label class="form-label"><strong>*Descripción</strong></label>
        <input
          type="text"
          class="form-control"
          placeholder="Ingrese una descripción"
        />
      </div>
      <div class="col-6 mt-3" id="radioTipoUsuario">
        <button id="btnRegistrarUsuario" type="submit" class="btn btn-primary">
          Registrarse
        </button>
      </div>
    </form>

    <script src="../js/app.js"></script>
    <!--link archivo js-->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
      crossorigin="anonymous"
    ></script>
    <!--Instalación bootstrap-->
  </body>

</body>
</html>
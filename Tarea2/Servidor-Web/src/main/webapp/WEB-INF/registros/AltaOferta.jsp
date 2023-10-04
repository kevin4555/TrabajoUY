<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../../webapp/recourse/css/general.css">
    <jsp:include page="../include/Head.jsp"/>
</head>
<body>
    <jsp:include page="../include/NavBar.jsp"/>
    <main class="d-flex flex-column justify-content-center align-items-center flex-grow-1">
        <form action="/altaOferta" method="post" id="formAltaOferta" class="bg-white rounded container py-3 mx-3 shadow-sm">
            <div class="row g-5 mx-2">
                <div class="col-6">
                    <div class="camposForm">
                        <label for="tipoPublicacion" class="form-label">
                            *Seleccione un Tipo de Publicación:
                        </label>
                        <select class="form-control" id="tipoPublicacion" name="tipoPublicacion" required>
                            <option value="">
                                -- Selecciona un tipo de publicación --
                            </option>
                            <c:forEach var="opcion" items="${listaTipoPublicacion}">
                                <option value="${opcion}">${opcion}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <button id="botonRedireccionar" class="btn btn-primary" style="display: none">
                        Ver TipoPublicacion
                    </button>
                </div>

                <div class="camposForm">
                    <label for="inputNombreOferta" class="form-label">*Nombre</label>
                    <input type="text" class="form-control" id="inputNombreOferta" name="nombreOferta" placeholder="Ingrese el nombre de la Oferta" required/>
                </div>

                <div class="camposForm">
                    <div class="form-group">
                        <label for="textAreaDescripcion">*Descripción</label>
                        <textarea class="form-control" id="textAreaDescripcion" name="descripcion" rows="3" required></textarea>
                    </div>
                </div>

                <div class="camposForm">
                    <label for="selecKeyword" class="form-label">Seleccione las keywords:</label>
                    <select multiple class="form-select" id="selecKeyword" name="keywords">
                        <option value="">-- Selecciona una keyword --</option>
                        <c:forEach var="keyword" items="${listaKeywords}">
                            <option value="${keyword}">${keyword}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="col-6">
                <div class="camposForm">
                    <div class="cs-form">
                        <label for="horaInicio">*Hora de inicio</label>
                        <input type="time" class="form-control" id="horaInicio" name="horaInicio" required />
                    </div>
                </div>

                <div class="camposForm">
                    <div class="cs-form">
                        <label for="horaFin">*Hora de fin</label>
                        <input type="time" class="form-control" id="horaFin" name="horaFin" required/>
                    </div>
                </div>

                <div class="camposForm">
                    <label for="inputRemuneracion" class="form-label">*Remuneración</label>
                    <input type="number" class="form-control" id="inputRemuneracion" placeholder="Ingrese la Remuneración" min="1" name="remuneracion" required/>
                </div>

                <div class="camposForm">
                    <label for="departamentos">*Selecciona un departamento:</label>
                    <select class="form-control" id="departamentos" name="departamento" required>
                        <option value="">-- Selecciona un departamento --</option>
                        <option value="Artigas">Artigas</option>
                        <option value="Canelones">Canelones</option>
                        <option value="Cerro Largo">Cerro Largo</option>
                        <option value="Colonia">Colonia</option>
                        <option value="Durazno">Durazno</option>
                        <option value="Flores">Flores</option>
                        <option value="Florida">Florida</option>
                        <option value="Lavalleja">Lavalleja</option>
                        <option value="Maldonado">Maldonado</option>
                        <option value="Montevideo">Montevideo</option>
                        <option value="Paysandú">Paysandú</option>
                        <option value="Río Negro">Río Negro</option>
                        <option value="Rivera">Rivera</option>
                        <option value="Rocha">Rocha</option>
                        <option value="Salto">Salto</option>
                        <option value="San José">San José</option>
                        <option value="Soriano">Soriano</option>
                        <option value="Tacuarembó">Tacuarembó</option>
                        <option value="Treinta y Tres">Treinta y Tres</option>
                    </select>
                </div>

                <div class="camposForm">
                    <label for="inputCiudad" class="form-label">*Ciudad</label>
                    <input type="text" class="form-control" id="inputCiudad" placeholder="Ingrese la Ciudad" name="ciudad" required/>
                </div>

                <div class="camposForm pt-2">
                    <label for="imagenOferta" class="form-label">Imagen</label>
                    <input type="file" name="imagenOferta" />
                </div>

                <div class="row camposForm">
                    <div class="col">
                        <label for="inputTipoDePago" class="form-label">*Tipo de pago</label>
                    </div>
                    <div class="col">
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="radioGeneral" name="optradio" value="option1" required />General
                            <label class="form-check-label" for="radio1"></label>
                        </div>
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="radioPorPaquete" name="optradio" value="option2" />Por Paquete
                            <label class="form-check-label" for="radio2"></label>
                        </div>
                    </div>
                </div>

                <div class="camposForm" id="divSeleccionPaquete">
                    <label for="listPaquetes">*Selecciona un paquete:</label>
                    <select class="form-control" id="listPaquetes" name="nombrePaquete">
                        <option value="">-- Selecciona un paquete --</option>
                        <c:forEach var="paquete" items="${listaPaquetes}">
                            <option value="${paquete}">${paquete}</option>
                        </c:forEach>
                    </select>
                    <div id="descuentoContainer" class="mt-3"></div>
                </div>

                <div class="col" id="botonesConfirmarCancelar">
                    <button id="btnConfirmar" type="submit" class="btn btn-primary" onclick="enviarFormulario()">Confirmar</button>
                    
                    <a id="btnCancelar" href="./indexEmpresa.html" class="btn btn-primary">
                        Cancelar
                    </a>
                </div>
            </div>
        </div>
    </form>
</main>
</body>
</html>
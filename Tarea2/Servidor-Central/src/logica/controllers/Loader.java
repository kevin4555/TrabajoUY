package logica.controllers;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.KeywordNoExisteException;
import excepciones.KeywordYaExisteException;
import excepciones.OfertaLaboralNoExisteException;
import excepciones.OfertaLaboralYaExisteException;
import excepciones.PaquetePublicacionYaExisteException;
import excepciones.TipoPublicacionNoExisteException;
import excepciones.TipoPublicacionYaExisteException;
import excepciones.UsuarioEmailRepetidoException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaExisteException;
import logica.DataTypes.DTCantidadTipoPublicacion;
import logica.classes.Keyword;
import logica.interfaces.IControladorOferta;
import logica.interfaces.IControladorUsuario;

public class Loader {

	public Loader() {
		
	}
	
	public void cargarDatos() throws UsuarioNoExisteException, OfertaLaboralNoExisteException, ParseException, UsuarioYaExisteException, UsuarioEmailRepetidoException, TipoPublicacionYaExisteException, KeywordYaExisteException, KeywordNoExisteException, TipoPublicacionNoExisteException, OfertaLaboralYaExisteException, PaquetePublicacionYaExisteException {
		IControladorUsuario controladorUsuario = Fabrica.getInstance().obtenerControladorUsuario();
		IControladorOferta controladorOferta = Fabrica.getInstance().obtenerControladorOferta();
		
		

		controladorUsuario.altaPostulante("lgarcia", "Lucía", "García", "lgarcia85@gmail.com", LocalDate.parse("1985-03-15"),
				"Uruguaya", null, "1234");
		controladorUsuario.altaPostulante("matilo", "Matías", "López", "matias.lopez90@hotmail.com",
				LocalDate.parse("1990-08-21"), "Argentina", null, "1234");
		controladorUsuario.altaPostulante("maro", "María", "Rodríguez", "marrod@gmail.com",LocalDate.parse("1988-11-10"),
				"Uruguaya", null, "1234");
		controladorUsuario.altaPostulante("javierf", "Javier", "Fernández", "javierf93@yahoo.com", LocalDate.parse("1993-06-05"),
				"Mexicana", null, "1234");
		controladorUsuario.altaPostulante("valen25", "Valentina", "Martínez", "vale87@gmail.com", LocalDate.parse("1987-02-25"),
				"Uruguaya", null, "1234");
		controladorUsuario.altaPostulante("andpe12", "Andrés", "Pérez", "anpe92@hotmail.com", LocalDate.parse("1992-04-12"),
				"Chilena", null, "1234");
		controladorUsuario.altaPostulante("sicam", "Camila", "Silva", "camilasilva89@gmail.com", LocalDate.parse("1989-09-30"),
				"Uruguaya", null, "1234");
		controladorUsuario.altaPostulante("isabel", "Isabella", "López", "loisa@gmail.com", LocalDate.parse("1991-07-07"),
				"Uruguaya", null, "1234");
		controladorUsuario.altaPostulante("marram02", "Martín", "Ramírez", "marram@hotmail.com", LocalDate.parse("1986-12-02"),
				"Argentina", null, "1234");

		controladorUsuario.altaEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
				"EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.",
				"http://www.EcoTechInnovations.com", null, "1234");
		controladorUsuario.altaEmpresa("FusionTech", "William", "Smith", "contacto@FusionTech.net",
				"FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial y la automatización avanzada. Nuestro equipo multidisciplinario de ingenieros, científicos de datos y desarrolladores crea soluciones innovadoras que aprovechan la potencia de la IA para transformar industrias. Desde la optimización de procesos industriales hasta la creación de asistentes virtuales altamente personalizados, nuestro objetivo es revolucionar la forma en que las empresas operan y se conectan con sus clientes. Creemos en la sinergia entre la mente humana y las capacidades de la IA, y trabajamos para construir un mundo donde la tecnología mejore y amplíe nuestras capacidades innatas.",
				"http://www.fusiontechdynamics.net", null, "1234");
		controladorUsuario.altaEmpresa("GlobalHealth", "Isabella", "Brown", "jobs@GlobalHealth.uy",
				"GlobalHealth Dynamics es una empresa comprometida con el avance de la atención médica a nivel mundial. Como líderes en el campo de la salud digital, desarrollamos plataformas y herramientas que permiten a los profesionales de la salud ofrecer diagnósticos más precisos, tratamientos personalizados y seguimiento continuo de los pacientes. Nuestra visión es crear un ecosistema de salud conectado en el que los datos médicos se utilicen de manera ética y segura para mejorar la calidad de vida de las personas. A través de la innovación constante y la colaboración con expertos m´edicos, estamos dando forma al futuro de la atención médica, donde la tecnología y la compasión se unen para salvar vidas y mejorar el bienestar en todo el mundo",
				"http://www.globalhealthdynamics.uy/info", null, "1234");
		controladorUsuario.altaEmpresa("ANTEL", "Washington", "Rocha", "jarrington@ANTEL.com.uy",
				"En Antel te brindamos servicios de vanguardia en tecnología de comunicación en Telefonía Móvil, Fija, Banda Ancha y Datos",
				"ANTEL.com.uy", null, "1234");
		controladorUsuario.altaEmpresa("MIEM", "Pablo", "Bengoechea", "eldiez@MIEM.org.uy",
				"Balance Energ´etico Nacional (BEN). La Dirección Nacional de Energía (DNE) del Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.",
				"MIEM.com.uy", null, "1234");
		controladorUsuario.altaEmpresa("TechSolutions", "Mercedes", "Venn", "Mercedes@TechSolutions.com.uy",
				" \"TechSolutions Inc.\" es una empresa líder en el campo de la tecnología de la información y el software. Su experiencia radica en la creación de soluciones de software a medida para compañías de distintos tamaños e industrias. Su principal enfoque se orienta hacia el desarrollo de aplicaciones empresariales vanguardistas, diseñadas para mejorar procesos, optimizar la eficiencia y proporcionar una ventaja competitiva a sus clientes. Se especializan en proporcionar innovación tecnológica para impulsar el éxito empresarial.",
				"TechSolutions.com", null, "1234");

		controladorOferta.altaTipoPublicacion("Premium", "Obtén máxima visibilidad", "1", 30, 4000f,
				LocalDate.parse("2023-08-10"));
		controladorOferta.altaTipoPublicacion("Destacada", "Destaca tu anuncio", "2", 15, 500f, LocalDate.parse("2023-08-05"));
		controladorOferta.altaTipoPublicacion("Estándar", "Mejora la posición de tu anuncio", "3", 20, 150f,
				LocalDate.parse("2023-08-15"));
		controladorOferta.altaTipoPublicacion("Básica", "Publica de forma sencilla en la lista de ofertas", "4", 7, 50f,
				LocalDate.parse("2023-08-07"));

		controladorOferta.altaKeyword("Tiempo completo");
		controladorOferta.altaKeyword("Medio tiempo");
		controladorOferta.altaKeyword("Remoto");
		controladorOferta.altaKeyword("Freelance");
		controladorOferta.altaKeyword("Temporal");
		controladorOferta.altaKeyword("Permanente");
		controladorOferta.altaKeyword("Computación");
		controladorOferta.altaKeyword("Administración");
		controladorOferta.altaKeyword("Logística");
		controladorOferta.altaKeyword("Contabilidad");

		Keyword keyword1 = controladorOferta.obtenerKeywords("Tiempo completo");
		Keyword keyword2 = controladorOferta.obtenerKeywords("Medio tiempo");
		Keyword keyword3 = controladorOferta.obtenerKeywords("Remoto");
		Keyword keyword4 = controladorOferta.obtenerKeywords("Freelance");
		Keyword keyword5 = controladorOferta.obtenerKeywords("Temporal");
		Keyword keyword6 = controladorOferta.obtenerKeywords("Permanente");
		
		
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add(keyword1.getNombre());
		keywords.add(keyword2.getNombre());
		keywords.add(keyword3.getNombre());
		keywords.add(keyword4.getNombre());
		keywords.add(keyword5.getNombre());
		keywords.add(keyword6.getNombre());
		

		controladorOferta.altaOfertaLaboral("Desarrollador Frontend",
				"Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.",
				"09:00", "18:00", 90000f, "Montevideo", "Montevideo", LocalDate.parse("2023-08-14"),
				"Premium", "EcoTech", keywords, null, null);
				
		ArrayList<String> keywords2 = new ArrayList<String>();
		keywords2.add(keyword5.getNombre());
		controladorOferta.altaOfertaLaboral("Estratega de Negocios",
				"Forma parte de nuestro equipo de estrategia y contribuye al crecimiento de las empresas clientes.",
				"08:00", "17:00", 80000f, "Punta del Este", "Maldonado", LocalDate.parse("2023-08-14"),
				"Estándar","GlobalHealth", keywords2, null, null);
      
		ArrayList<String> keywords3 = new ArrayList<String>();
		keywords3.add(keyword2.getNombre());
		keywords3.add(keyword3.getNombre());
		keywords3.add(keyword6.getNombre());
		controladorOferta.altaOfertaLaboral("Diseñador UX/UI",
				"Trabaja en colaboración con nuestro talentoso equipo de diseño para crear soluciones impactantes.",
				"14:00", "18:00", 65000f, "Rosario", "Colonia", LocalDate.parse("2023-08-13"), "Estándar", "FusionTech", keywords3, null, null);

		ArrayList<String> keywords4 = new ArrayList<String>();
		keywords4.add(keyword2.getNombre());
		controladorOferta.altaOfertaLaboral("Analista de Datos",
				"Ayuda a nuestros clientes a tomar decisiones informadas basadas en análisis y visualizaciones de datos.",
				"09:00", "13:00", 40000f, "Maldonado", "Maldonado",LocalDate.parse("2023-08-11"),
				"Premium", "ANTEL", keywords4, null, null);

		ArrayList<String> keywords5 = new ArrayList<String>();
		keywords5.add(keyword4.getNombre());
		controladorOferta.altaOfertaLaboral("Content Manager",
				"Gestiona y crea contenido persuasivo y relevante para impulsar la presencia en línea de nuestros clientes.",
				"18:00", "22:00", 10000f, "Montevideo", "Montevideo", LocalDate.parse("2023-08-20"),
				"Destacada", "MIEM", keywords5, null, null);

		ArrayList<String> keywords6 = new ArrayList<String>();
		keywords6.add(keyword1.getNombre());
		controladorOferta.altaOfertaLaboral("Soporte Técnico",
				"Ofrece un excelente servicio de soporte técnico a nuestros clientes, resolviendo problemas y brindando soluciones",
				"09:00", "18:00", 30000f, "Minas", "Lavalleja", LocalDate.parse("2023-08-15"),
				"Básica", "TechSolutions", keywords6, null, null);

		controladorOferta.altaOfertaLaboral("A. de Marketing Digital",
				"Unete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.", "10:00",
				"19:00", 80000f, "Flores", "Flores", LocalDate.parse("2023-08-15"), "Premium", "EcoTech", new ArrayList<String>(), null, null);

		controladorOferta.altaOfertaLaboral("Contador Senior",
				"Unete a nuestro equipo contable y ayuda en la gestión financiera de la empresa.", "08:30", "17:30",
				100000f, "Colonia Suiza", "Colonia",LocalDate.parse("2023-08-16"),
				"Destacada", "GlobalHealth", new ArrayList<String>(), null, null);

		controladorUsuario.registrarPostulacion(
				"Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos Microsoft Office.",
				"Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.",
				LocalDate.parse("2023-08-16"), "lgarcia", "Desarrollador Frontend");

		controladorUsuario.registrarPostulacion(
				"Estudiante de Comunicación, habilidades en redacción y manejo de redes sociales. Experiencia en prácticas en medios locales.",
				"Me encantaría formar parte de un equipo que me permita desarrollar mis habilidades en comunicación y marketing.",
				LocalDate.parse("2023-08-15"), "matilo", "Estratega de Negocios");

		controladorUsuario.registrarPostulacion(
				"Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles. Conocimientos en JavaScript y React.",
				"Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
				LocalDate.parse("2023-08-14"), "maro", "Desarrollador Frontend");

		controladorUsuario.registrarPostulacion(
				"Técnico en Electricidad, experiencia en mantenimiento industrial. Conocimientos en lectura de planos eléctricos.",
				"Estoy interesado en formar parte de un equipo que me permita aplicar mis habilidades técnicas y contribuir al mantenimiento eficiente.",
				LocalDate.parse("2023-08-13"), "javierf", "Diseñador UX/UI");

		controladorUsuario.registrarPostulacion(
				"Músico profesional, experiencia en espectáculos en vivo. Habilidades en canto y guitarra.",
				"Me gustaría combinar mi pasión por la música con una oportunidad laboral que me permita seguir creciendo como artista.",
				LocalDate.parse("2023-08-12"), "valen25", "Estratega de Negocios");

		controladorUsuario.registrarPostulacion(
				"Licenciada en Administración, me considero genia, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office.",
				"Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.",
				LocalDate.parse("2023-08-16"), "lgarcia", "Estratega de Negocios");

		ArrayList<DTCantidadTipoPublicacion> paquete1 = new ArrayList<DTCantidadTipoPublicacion>();
		ArrayList<DTCantidadTipoPublicacion> paquete2 = new ArrayList<DTCantidadTipoPublicacion>();
		ArrayList<DTCantidadTipoPublicacion> paquete3 = new ArrayList<DTCantidadTipoPublicacion>();
		ArrayList<DTCantidadTipoPublicacion> paquete4 = new ArrayList<DTCantidadTipoPublicacion>();

		DTCantidadTipoPublicacion tipo1 = new DTCantidadTipoPublicacion("Premium", 1);
		DTCantidadTipoPublicacion tipo2 = new DTCantidadTipoPublicacion("Destacada", 1);
		DTCantidadTipoPublicacion tipo3 = new DTCantidadTipoPublicacion("Estándar", 1);
		paquete1.add(tipo1);
		paquete1.add(tipo2);
		paquete1.add(tipo3);

		DTCantidadTipoPublicacion tipo4 = new DTCantidadTipoPublicacion("Estándar", 2);
		DTCantidadTipoPublicacion tipo5 = new DTCantidadTipoPublicacion("Básica", 1);
		paquete2.add(tipo4);
		paquete2.add(tipo5);

		DTCantidadTipoPublicacion tipo6 = new DTCantidadTipoPublicacion("Premium", 2);
		DTCantidadTipoPublicacion tipo7 = new DTCantidadTipoPublicacion("Estándar", 2);
		paquete3.add(tipo6);
		paquete3.add(tipo7);

		DTCantidadTipoPublicacion tipo8 = new DTCantidadTipoPublicacion("Destacada", 2);
		paquete4.add(tipo8);

		controladorOferta.registrarPaquete("Básico", "Publica ofertas laborales en nuestra plataforma por un período de 30 días",
				30, 20f,null, LocalDate.parse("2023-08-16"), paquete1);

		controladorOferta.registrarPaquete("Destacado",
				"Publica ofertas laborales destacadas que se mostrará en la parte superior de los resultados de búsqueda por 45 días",
				45, 10f, null, LocalDate.parse("2023-08-15"), paquete2);

		controladorOferta.registrarPaquete("Premium",
				"Publica ofertas laborales premium que incluye promoción en nuestras redes sociales y listado en la sección destacada por 60 días",
				60, 15f, null, LocalDate.parse("2023-08-14"), paquete3);

		controladorOferta.registrarPaquete("Express",
				"Publica ofertas laborales urgentes resaltada en color y se mostrará en la sección de urgente por 15 días.",
				15, 5f, null, LocalDate.parse("2023-08-13"), paquete4);
	}
}

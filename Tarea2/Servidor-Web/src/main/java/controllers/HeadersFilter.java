package controllers;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class HeadersFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // Configuración de inicialización si es necesaria
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Convertir el objeto ServletResponse a HttpServletResponse
        jakarta.servlet.http.HttpServletResponse httpServletResponse = (jakarta.servlet.http.HttpServletResponse) response;

        // Permitir todas las solicitudes a dominios específicos
        httpServletResponse.setHeader("Content-Security-Policy", "default-src 'self' https://googleads.g.doubleclick.net https://static.doubleclick.net; style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net; script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://ajax.googleapis.com https://cdnjs.cloudflare.com https://www.youtube.com https://maxcdn.bootstrapcdn.com; img-src 'self' data: https://cdn.jsdelivr.net; font-src 'self' https://cdn.jsdelivr.net; frame-src 'self' https://www.youtube.com;");
        chain.doFilter(request, httpServletResponse); // Usar el objeto HttpServletResponse
    }

    public void destroy() {
        // Realizar tareas de limpieza si es necesario
    }
}

package br.com.gustavonascimento.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gustavonascimento.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  public IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

      var servletPath = request.getServletPath();

      if(servletPath.startsWith("/tasks/")) {

        // Pegando usuário e senha
        var authorization = request.getHeader("Authorization");
        var authBase64 = authorization.substring("Basic".length()).trim();

        byte[] authBase64Decoded = Base64.getDecoder().decode(authBase64);

        var authBase64String = new String(authBase64Decoded);
        String[] credentials = authBase64String.split(":");

        String username = credentials[0];
        String password = credentials[1];

        // Validando usuário
        var user = this.userRepository.findByUsername(username);
        
        if(user == null) {
          response.sendError(401);
        } else {
          //Validando senha
          var isPasswordValid = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
          if(isPasswordValid.verified) {
            request.setAttribute("idUser", user.getId());
            filterChain.doFilter(request, response);
          } else {
            response.sendError(401);
          }
          // Seguindo com o fluxo da aplicação
        }

      } else {
        filterChain.doFilter(request, response);
      }
        
  }
  
}

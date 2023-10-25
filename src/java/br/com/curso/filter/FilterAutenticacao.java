package br.com.curso.filter;

import br.com.curso.model.Usuario;
import br.com.curso.utils.SingleConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"})
public class FilterAutenticacao implements Filter {

    private static Connection conexao;
    private final Set<String> urlsSemAutenticacao = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        conexao = SingleConnection.getConnection();
        urlsSemAutenticacao.add("/index.jsp");
        urlsSemAutenticacao.add("/homeLogado.jsp");
        urlsSemAutenticacao.add("/footer.jsp");
        urlsSemAutenticacao.add("/header.jsp");
        urlsSemAutenticacao.add("/login.jsp");
        urlsSemAutenticacao.add("/menu.jsp");
        urlsSemAutenticacao.add("/menuAdministrador.jsp");
        urlsSemAutenticacao.add("/menucliente.jsp");
        urlsSemAutenticacao.add("/fornecedor.jsp");
        urlsSemAutenticacao.add("/pedido.jsp");
        // Adicione outras URLs sem autenticação aqui
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String url = req.getRequestURI();
            
            // Verifica se a URL começa com "/css/" para permitir o acesso aos recursos CSS
            if (url.startsWith(req.getContextPath() + "/css/")) {
                chain.doFilter(request, response);
                return;
            }
            // Verifica se a URL começa com "/js/" para permitir o acesso aos recursos CSS
            if (url.startsWith(req.getContextPath() + "/js/")) {
                chain.doFilter(request, response);
                return;
            }
            // Verifica se a URL começa com "/scss/" para permitir o acesso aos recursos CSS
            if (url.startsWith(req.getContextPath() + "/scss/")) {
                chain.doFilter(request, response);
                return;
            }
            // Verifica se a URL começa com "/vendor/" para permitir o acesso aos recursos CSS
            if (url.startsWith(req.getContextPath() + "/vendor/")) {
                chain.doFilter(request, response);
                return;
            }
            
            HttpSession sessao = req.getSession(false);
            String urlParaAutenticar = req.getServletPath();
            if (urlsSemAutenticacao.contains(urlParaAutenticar) || sessao != null) {
                if (!Usuario.verificaUsuario(urlParaAutenticar, sessao)) {
                    request.getRequestDispatcher("/homeLogado.jsp").forward(request, response);
                    return;
                }
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Tratamento de exceções mais robusto
            throw new ServletException("Erro durante o filtro de autenticação.", e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

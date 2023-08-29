package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menuAdministrador_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!-- Page Wrapper -->\n");
      out.write("    <div id=\"wrapper\">\n");
      out.write("\n");
      out.write("        <!-- Sidebar -->\n");
      out.write("        <ul class=\"navbar-nav bg-gradient-danger sidebar sidebar-dark accordion\" id=\"accordionSidebar\">\n");
      out.write("\n");
      out.write("            <!-- Sidebar - Brand -->\n");
      out.write("            <a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"homeLogado.jsp\">\n");
      out.write("                <div class=\"sidebar-brand-icon rotate-n-15\">\n");
      out.write("                    <i class=\"fas fa-car\"></i>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"sidebar-brand-text mx-3\">Sistema SJV</div>\n");
      out.write("            </a>\n");
      out.write("\n");
      out.write("            <!-- Divider -->\n");
      out.write("            <hr class=\"sidebar-divider my-0\">\n");
      out.write("\n");
      out.write("            <!-- Nav Item - Dashboard -->\n");
      out.write("            <li class=\"nav-item active\">\n");
      out.write("                <a class=\"nav-link\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/homeLogado.jsp\">\n");
      out.write("                    <i class=\"fas fa-fw fa-tachometer-alt\"></i>\n");
      out.write("                    <span>Home</span></a>\n");
      out.write("            </li>\n");
      out.write("\n");
      out.write("            <!-- Divider -->\n");
      out.write("            <hr class=\"sidebar-divider\">\n");
      out.write("\n");
      out.write("            <!-- Heading -->\n");
      out.write("            <div class=\"sidebar-heading\">\n");
      out.write("                Cadastros\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- Nav Item - Pages Collapse Menu -->\n");
      out.write("            <li class=\"nav-item\">\n");
      out.write("                <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapseTwo\"\n");
      out.write("                    aria-expanded=\"true\" aria-controls=\"collapseTwo\">\n");
      out.write("                    <i class=\"fas fa-fw fa-cog\"></i>\n");
      out.write("                    <span>Cadastros</span>\n");
      out.write("                </a>\n");
      out.write("                <div id=\"collapseTwo\" class=\"collapse\" aria-labelledby=\"headingTwo\" data-parent=\"#accordionSidebar\">\n");
      out.write("                    <div class=\"bg-white py-2 collapse-inner rounded\">\n");
      out.write("                        <h6 class=\"collapse-header\">Cadastros Primarios:</h6>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/VeiculoListar\">VEICULOS</a>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/MarcaListar\">MARCAS</a>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/ModeloListar\">MODELOS</a>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/TipoListar\">TIPOS</a>\n");
      out.write("                        <div class=\"collapse-divider\"></div>\n");
      out.write("                        <h6 class=\"collapse-header\">Endereços:</h6>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/CidadeListar\">CIDADE</a>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/EstadoListar\">ESTADO</a>\n");
      out.write("                        <div class=\"collapse-divider\"></div>\n");
      out.write("                        <h6 class=\"collapse-header\">Usuarios:</h6>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/AdministradorListar\">ADMINISTRADOR</a>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/ClienteListar\">CLIENTE</a>\n");
      out.write("                        <a class=\"collapse-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/FornecedorListar\">FORNECEDOR</a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </li>\n");
      out.write("\n");
      out.write("            <!-- Nav Item - Utilities Collapse Menu -->\n");
      out.write("            \n");
      out.write("\n");
      out.write("            <!-- Divider -->\n");
      out.write("            <hr class=\"sidebar-divider\">\n");
      out.write("\n");
      out.write("            <!-- Sidebar Toggler (Sidebar) -->\n");
      out.write("            <div class=\"text-center d-none d-md-inline\">\n");
      out.write("                <button class=\"rounded-circle border-0\" id=\"sidebarToggle\"></button>\n");
      out.write("            </div>\n");
      out.write("        </ul>\n");
      out.write("        <!-- End of Sidebar -->\n");
      out.write("\n");
      out.write("        <!-- Content Wrapper -->\n");
      out.write("        <div id=\"content-wrapper\" class=\"d-flex flex-column\">\n");
      out.write("\n");
      out.write("            <!-- Main Content -->\n");
      out.write("            <div id=\"content\">\n");
      out.write("\n");
      out.write("                <!-- Topbar -->\n");
      out.write("                <nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">\n");
      out.write("\n");
      out.write("                    <!-- Sidebar Toggle (Topbar) -->\n");
      out.write("                    <button id=\"sidebarToggleTop\" class=\"btn btn-link d-md-none rounded-circle mr-3\">\n");
      out.write("                        <i class=\"fa fa-bars\"></i>\n");
      out.write("                    </button>\n");
      out.write("\n");
      out.write("                    <!-- Topbar Navbar -->\n");
      out.write("                    <ul class=\"navbar-nav ml-auto\">\n");
      out.write("\n");
      out.write("                        <!-- Nav Item - Alerts -->\n");
      out.write("                        <li class=\"nav-item dropdown no-arrow mx-1\">\n");
      out.write("                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"alertsDropdown\" role=\"button\"\n");
      out.write("                                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n");
      out.write("                                <i class=\"fas fa-bell fa-fw\"></i>\n");
      out.write("                                <!-- Counter - Alerts \n");
      out.write("                                <span class=\"badge badge-danger badge-counter\">3+</span>\n");
      out.write("                                -->\n");
      out.write("                            </a>\n");
      out.write("                            <!-- Dropdown - Alerts -->\n");
      out.write("                            <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"\n");
      out.write("                                aria-labelledby=\"alertsDropdown\">\n");
      out.write("                                <h6 class=\"dropdown-header\">\n");
      out.write("                                    Alertas\n");
      out.write("                                </h6>\n");
      out.write("                                <!--\n");
      out.write("                                <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n");
      out.write("                                    <div class=\"mr-3\">\n");
      out.write("                                        <div class=\"icon-circle bg-primary\">\n");
      out.write("                                            <i class=\"fas fa-file-alt text-white\"></i>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div>\n");
      out.write("                                        <div class=\"small text-gray-500\">December 12, 2019</div>\n");
      out.write("                                        <span class=\"font-weight-bold\">A new monthly report is ready to download!</span>\n");
      out.write("                                    </div>\n");
      out.write("                                </a>-->\n");
      out.write("                                <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Mostrar Alertas</a>\n");
      out.write("                            </div>\n");
      out.write("                        </li>\n");
      out.write("\n");
      out.write("                        <!-- Nav Item - Messages -->\n");
      out.write("                        <li class=\"nav-item dropdown no-arrow mx-1\">\n");
      out.write("                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"messagesDropdown\" role=\"button\"\n");
      out.write("                                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n");
      out.write("                                <i class=\"fas fa-envelope fa-fw\"></i>\n");
      out.write("                                <!-- Counter - Messages \n");
      out.write("                                <span class=\"badge badge-danger badge-counter\">7</span>\n");
      out.write("                                -->\n");
      out.write("                            </a>\n");
      out.write("                            <!-- Dropdown - Messages -->\n");
      out.write("                            <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"\n");
      out.write("                                aria-labelledby=\"messagesDropdown\">\n");
      out.write("                                <h6 class=\"dropdown-header\">\n");
      out.write("                                    Mensagens\n");
      out.write("                                </h6>\n");
      out.write("                                <!--\n");
      out.write("                                <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">\n");
      out.write("                                    <div class=\"dropdown-list-image mr-3\">\n");
      out.write("                                        <img class=\"rounded-circle\" src=\"img/undraw_profile_1.svg\"\n");
      out.write("                                            alt=\"...\">\n");
      out.write("                                        <div class=\"status-indicator bg-success\"></div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"font-weight-bold\">\n");
      out.write("                                        <div class=\"text-truncate\">Hi there! I am wondering if you can help me with a\n");
      out.write("                                            problem I've been having.</div>\n");
      out.write("                                        <div class=\"small text-gray-500\">Emily Fowler · 58m</div>\n");
      out.write("                                    </div>\n");
      out.write("                                </a>\n");
      out.write("                                -->\n");
      out.write("                                <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Read More Messages</a>\n");
      out.write("                            </div>\n");
      out.write("                        </li>\n");
      out.write("\n");
      out.write("                        <div class=\"topbar-divider d-none d-sm-block\"></div>\n");
      out.write("\n");
      out.write("                        <!-- Nav Item - User Information -->\n");
      out.write("                        <li class=\"nav-item dropdown no-arrow\">\n");
      out.write("                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\"\n");
      out.write("                                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n");
      out.write("                                <span class=\"mr-2 d-none d-lg-inline text-gray-600 small\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.nomeusuario}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" - ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.tipousuario}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\n");
      out.write("                               <img class=\"img-profile rounded-circle\"\n");
      out.write("                                    src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.fotousuario}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                            </a>\n");
      out.write("                            <!-- Dropdown - User Information -->\n");
      out.write("                            <div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\"\n");
      out.write("                                aria-labelledby=\"userDropdown\">\n");
      out.write("                                <a class=\"dropdown-item\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/UsuarioDeslogar\">\n");
      out.write("                                    <i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>\n");
      out.write("                                    Sair\n");
      out.write("                                </a>\n");
      out.write("                            </div>\n");
      out.write("                        </li>\n");
      out.write("\n");
      out.write("                    </ul>\n");
      out.write("\n");
      out.write("                </nav>\n");
      out.write("                <!-- End of Topbar -->\n");
      out.write("\n");
      out.write("                <!-- Begin Page Content -->\n");
      out.write("                <div class=\"container-fluid\">\n");
      out.write("\n");
      out.write("                   ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

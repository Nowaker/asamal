package pl.softwaremill.asamal.plugin.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import pl.softwaremill.asamal.extension.AsamalExtension;
import pl.softwaremill.asamal.extension.view.ContextConstants;
import pl.softwaremill.asamal.extension.view.PresentationContext;
import pl.softwaremill.asamal.extension.view.PresentationExtension;
import pl.softwaremill.asamal.extension.view.ResourceResolver;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Presentation extension for Asamal using velocity
 */
@AsamalExtension
public class ThymeleafPresentationExtension implements PresentationExtension {

    private static final String EXTENSION = "thy";
    private static final TemplateEngine engine = new TemplateEngine();

    static {
        engine.setTemplateResolver(new ServletContextTemplateResolver());
    }

    private final HttpServletRequest request;

    @Inject
    public ThymeleafPresentationExtension(final HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getExtension() {
        return EXTENSION;
    }

    @Override
    public PresentationContext createNewPresentationContext() {
        return new ThymeleafPresentationContext(request);
    }

    @Override
    public String evaluateTemplate(final PresentationContext context, final ResourceResolver resourceResolver,
                                   final String templateContent) {

        final String controllerName = (String) context.get(ContextConstants.CONTROLLER_NAME);
        final String viewName = (String) context.get(ContextConstants.VIEW);
        final String path = "/view/" + controllerName + "/" + viewName + "." + EXTENSION;

        final ThymeleafPresentationContext thymeleafContext = (ThymeleafPresentationContext) context;
        System.out.println(thymeleafContext.getThymeleafContext().getVariables());
        return engine.process(path, thymeleafContext.getThymeleafContext());
    }

}

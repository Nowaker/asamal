package pl.softwaremill.asamal.plugin.thymeleaf;

import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import pl.softwaremill.asamal.extension.view.PresentationContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class ThymeleafPresentationContext implements PresentationContext {

    private final IContext context;

    @Inject
    public ThymeleafPresentationContext(final HttpServletRequest req) {
        context = new WebContext(req, req.getServletContext());
    }

    @Override
    public void put(final String key, final Object value) {
        context.getVariables().put(key, value);
    }

    @Override
    public Object get(final String key) {
        return context.getVariables().get(key);
    }

    public IContext getThymeleafContext() {
        return context;
    }

}

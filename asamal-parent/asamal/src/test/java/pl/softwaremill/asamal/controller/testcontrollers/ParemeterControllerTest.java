package pl.softwaremill.asamal.controller.testcontrollers;

import org.jboss.resteasy.specimpl.MultivaluedMapImpl;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.softwaremill.asamal.controller.AsamalContext;
import pl.softwaremill.asamal.controller.ControllerBean;
import pl.softwaremill.asamal.controller.exception.AutobindingException;
import pl.softwaremill.asamal.controller.exception.NoSuchParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: szimano
 */
public class ParemeterControllerTest extends ControllerBean {

    @Test
    public void shouldReturnNullForNonExistingParamsFromRequest() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameterMap()).thenReturn(new HashMap<String, String[]>());
        context = new AsamalContext(request, mock(HttpServletResponse.class), null, null);

        // when
        String parameter = getParameter("non-existing");
        List<String> parameterValues = getParameterValues("non-existing");

        // then
        assertThat(parameter).isNull();
        assertThat(parameterValues).isNull();
    }

    @Test
    public void shouldReturnNullForNonExistingParamsFromParamMap() {
        // given
        context = new AsamalContext(mock(HttpServletRequest.class), mock(HttpServletResponse.class), null,
                new MultivaluedMapImpl<String, Object>());

        // when
        String parameter = getParameter("non-existing");
        List<String> parameterValues = getParameterValues("non-existing");

        // then
        assertThat(parameter).isNull();
        assertThat(parameterValues).isNull();
    }

}
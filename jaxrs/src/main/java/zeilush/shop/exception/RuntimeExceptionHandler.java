package zeilush.shop.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by AAA on 23.03.2016.
 */
@Provider
public class RuntimeExceptionHandler implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new Error(exception.getMessage())).build();
    }
}

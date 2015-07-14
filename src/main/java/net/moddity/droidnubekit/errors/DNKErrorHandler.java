package net.moddity.droidnubekit.errors;

import net.moddity.droidnubekit.DroidNubeKit;
import net.moddity.droidnubekit.responsemodels.DNKUnauthorizedResponse;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * Created by jaume on 12/6/15.
 */
public class DNKErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {

        switch (cause.getResponse().getStatus()) {
            case DNKErrorCodes.AUTHENTICATION_REQUIRED:
                DNKUnauthorizedResponse errorResponse = (DNKUnauthorizedResponse)cause.getBodyAs(DNKUnauthorizedResponse.class);
                DroidNubeKit.showAuthDialog(errorResponse.getRedirectUrl());
                return new DNKAuthenticationRequiredException(errorResponse);
            default:
               return cause.getCause();
        }
    }
}

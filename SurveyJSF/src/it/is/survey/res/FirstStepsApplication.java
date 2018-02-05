package it.is.survey.res;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class FirstStepsApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
    	System.out.println("In test restlet");
        
        Router router = new Router(getContext());
        //router.attach("/icon/",HelloWorldResource.class);
        router.attach("/sondaggi/{fruitoreId}",SondaggiResource.class);
        router.attach("/upload/",UploadServerResource.class);
        router.attach("/ack/{status}", AckResource.class);
        router.attach("/image/{imageId}", ImageServerResource.class);
        
        return router;
    }
}
package lecturepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class LecProducerActivator implements BundleActivator {

    private static BundleContext bundleContext;
    private ServiceRegistration serviceRegistration;
    private ILectureServices lectureService;

    static BundleContext getContext() {
        return bundleContext;
    }

    //start service
    public void start(BundleContext context) throws Exception {
        System.out.println("Lecture management service is starting...!!!");
        LecProducerActivator.bundleContext = context;

        lectureService = new LectureServiceImplementation();
        serviceRegistration = context.registerService(ILectureServices.class.getName(), lectureService, null);
    }

    //stop service
    public void stop(BundleContext context) throws Exception {
        LecProducerActivator.bundleContext = null;

        System.out.println("Lecture management service has stopped!!!.");
        serviceRegistration.unregister();
    }
}

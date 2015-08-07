import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

/**
 * A little class which runs the JVM Out of Memory.
 * Used to demonstrate how to use the -XX:OnOutOfMemoryError JVM Option to recover/react to OutOfMemoryErrors.
 */
public class TestOutOfMemory {

    public static void main(String[] args) throws Exception {

        TestOutOfMemory t = new TestOutOfMemory();
        t.writePIDToDisk();
        System.out.println("This is going to go round and round so you have 10secs to stop the bus");
        System.out.println("Hit CTR+C to exit");
        Thread.sleep(1000 * 10);
        System.out.println("Ok I am awake, let's run out of memory");

        t.generateError();
    }

    public void generateError() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();

        while (true) {
            list.add(0);
        }
    }

    /**
     * Got this from http://stackoverflow.com/a/7690178
     */
    private static String getPID(final String fallback) {
        // Note: may fail in some JVM implementations
        // therefore fallback has to be provided

        // something like '<pid>@<hostname>', at least in SUN / Oracle JVMs
        final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        final int index = jvmName.indexOf('@');

        if (index < 1) {
            // part before '@' empty (index = 0) / '@' not found (index = -1)
            return fallback;
        }

        try {
            return Long.toString(Long.parseLong(jvmName.substring(0, index)));
        } catch (NumberFormatException e) {
            // ignore
        }
        return fallback;
    }

    private void writePIDToDisk() {
        try {
            String pid = getPID("-42");
            if (pid.equals("-42")) {
                throw new IllegalStateException("Cannot get the process id, sorry for you...");
            }
            String filename = "app.pid";
            File file = new File("./" + filename);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(pid);
            bw.close();

            System.out.println("Written pid to " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}